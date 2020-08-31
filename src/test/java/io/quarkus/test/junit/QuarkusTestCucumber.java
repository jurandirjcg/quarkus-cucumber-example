package io.quarkus.test.junit;

import static io.quarkus.test.common.PathTestHelper.getAppClassLocationForTestLocation;
import static io.quarkus.test.common.PathTestHelper.getTestClassesLocation;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.commons.lang3.BooleanUtils;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.jboss.jandex.Index;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import io.cucumber.core.options.CucumberProperties;
import io.cucumber.junit.Cucumber;
import io.quarkus.bootstrap.app.AugmentAction;
import io.quarkus.bootstrap.app.CuratedApplication;
import io.quarkus.bootstrap.app.QuarkusBootstrap;
import io.quarkus.bootstrap.app.RunningQuarkusApplication;
import io.quarkus.bootstrap.app.StartupAction;
import io.quarkus.bootstrap.model.PathsCollection;
import io.quarkus.bootstrap.runner.Timing;
import io.quarkus.runtime.configuration.ProfileManager;
import io.quarkus.test.common.PathTestHelper;
import io.quarkus.test.common.RestAssuredURLManager;
import io.quarkus.test.common.TestClassIndexer;
import io.quarkus.test.common.TestResourceManager;
import io.quarkus.test.common.http.TestHTTPResourceManager;
import io.quarkus.test.junit.QuarkusTestExtension.TestBuildChainFunction;

public class QuarkusTestCucumber extends Runner {

    private static ClassLoader originalCl;
    private static RunningQuarkusApplication runningQuarkusApplication;
    private Class<?> clazz;
	private Cucumber cucumber;
    
    public QuarkusTestCucumber(Class<?> clazz) throws Exception {
		this.clazz = clazz;
		cucumber = new Cucumber(clazz);
    }
    
    /**
     * Adapatado do projeto da classe io.quarkus.test.junit.QuarkusTestExtension
     *
     * @param clazz
     */
    public void doJavaStart() {
        if (runningQuarkusApplication != null) {
            return;
        }

        Closeable testResourceManager = null;
        String TEST_LOCATION = "test-location";
        String TEST_CLASS = "test-class";

        try {
            final LinkedBlockingDeque<Runnable> shutdownTasks = new LinkedBlockingDeque<>();

            Class<?> requiredTestClass = clazz;
            Path testClassLocation = getTestClassesLocation(requiredTestClass);
            final Path appClassLocation = getAppClassLocationForTestLocation(testClassLocation.toString());

            PathsCollection.Builder rootBuilder = PathsCollection.builder();

            if (!appClassLocation.equals(testClassLocation)) {
                rootBuilder.add(testClassLocation);
                // if test classes is a dir, we should also check whether test resources dir exists as a separate dir (gradle)
                // TODO: this whole app/test path resolution logic is pretty dumb, it needs be re-worked using proper workspace discovery
                final Path testResourcesLocation = PathTestHelper.getResourcesForClassesDirOrNull(testClassLocation, "test");
                if (testResourcesLocation != null) {
                    rootBuilder.add(testResourcesLocation);
                }
            }
            if (Files.exists(testClassLocation.getParent().resolve("testFixtures"))) {
                rootBuilder.add(testClassLocation.getParent().resolve("testFixtures"));
            }

            originalCl = Thread.currentThread().getContextClassLoader();
            Map<String, String> sysPropRestore = new HashMap<>();
            sysPropRestore.put(ProfileManager.QUARKUS_TEST_PROFILE_PROP,
                    System.getProperty(ProfileManager.QUARKUS_TEST_PROFILE_PROP));

            final QuarkusBootstrap.Builder runnerBuilder = QuarkusBootstrap.builder()
                    .setIsolateDeployment(true)
                    .setMode(QuarkusBootstrap.Mode.TEST);
            QuarkusTestProfile profileInstance = null;
            
            final Path projectRoot = Paths.get("").normalize().toAbsolutePath();
            runnerBuilder.setProjectRoot(projectRoot);
            Path outputDir;
            try {
                // this should work for both maven and gradle
                outputDir = projectRoot.resolve(projectRoot.relativize(testClassLocation).getName(0));
            } catch (Exception e) {
                // this shouldn't happen since testClassLocation is usually found under the project dir
                outputDir = projectRoot;
            }
            runnerBuilder.setTargetDirectory(outputDir);

            rootBuilder.add(appClassLocation);
            final Path appResourcesLocation = PathTestHelper.getResourcesForClassesDirOrNull(appClassLocation, "main");
            if (appResourcesLocation != null) {
                rootBuilder.add(appResourcesLocation);
            }

            runnerBuilder.setApplicationRoot(rootBuilder.build());

            CuratedApplication curatedApplication = runnerBuilder
                    .setTest(true)
                    .build()
                    .bootstrap();

            Index testClassesIndex = TestClassIndexer.indexTestClasses(requiredTestClass);
            // we need to write the Index to make it reusable from other parts of the testing infrastructure that run in different ClassLoaders
            TestClassIndexer.writeIndex(testClassesIndex, requiredTestClass);

            Timing.staticInitStarted(curatedApplication.getBaseRuntimeClassLoader());
            final Map<String, Object> props = new HashMap<>();
            props.put(TEST_LOCATION, testClassLocation);
            props.put(TEST_CLASS, requiredTestClass);
            AugmentAction augmentAction = curatedApplication
                    .createAugmentor(TestBuildChainFunction.class.getName(), props);
            
            StartupAction startupAction = augmentAction.createInitialRuntimeApplication();
            Thread.currentThread().setContextClassLoader(startupAction.getClassLoader());
            
            //must be done after the TCCL has been set
            testResourceManager = (Closeable) startupAction.getClassLoader().loadClass(TestResourceManager.class.getName())
                    .getConstructor(Class.class, List.class)
                    .newInstance(requiredTestClass,
                            getAdditionalTestResources(profileInstance, startupAction.getClassLoader()));
            testResourceManager.getClass().getMethod("init").invoke(testResourceManager);
            testResourceManager.getClass().getMethod("start").invoke(testResourceManager);

            
            runningQuarkusApplication = startupAction.run();

            ConfigProviderResolver.setInstance(new RunningAppConfigResolver(runningQuarkusApplication));

            //configura o class path para seu valor original
            Thread.currentThread().setContextClassLoader(originalCl);

            System.setProperty("test.url", TestHTTPResourceManager.getUri(runningQuarkusApplication));

            RestAssuredURLManager.setURL(false);

            Closeable tm = testResourceManager;
            
            Closeable shutdownTask = new Closeable() {
                @Override
                public void close() throws IOException {
                    try {
                        runningQuarkusApplication.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            while (!shutdownTasks.isEmpty()) {
                                shutdownTasks.pop().run();
                            }
                        } finally {
                            for (Map.Entry<String, String> entry : sysPropRestore.entrySet()) {
                                String val = entry.getValue();
                                if (val == null) {
                                    System.clearProperty(entry.getKey());
                                } else {
                                    System.setProperty(entry.getKey(), val);
                                }
                            }
                            tm.close();
                        }
                    }
                }
            };

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        shutdownTask.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        curatedApplication.close();
                    }
                }
            }, "Quarkus Test Cleanup Shutdown task"));
        } catch (Throwable e) {
            try {
                if (testResourceManager != null) {
                    testResourceManager.close();
                }
            } catch (Exception ex) {
                e.addSuppressed(ex);
            }
        } finally {
            if (originalCl != null) {
                Thread.currentThread().setContextClassLoader(originalCl);
            }
        }
    }

    /**
     * Since {@link TestResourceManager} is loaded from the ClassLoader passed in as an argument,
     * we need to convert the user input {@link QuarkusTestProfile.TestResourceEntry} into instances of
     * {@link TestResourceManager.TestResourceClassEntry}
     * that are loaded from that ClassLoader
     */
    private List<Object> getAdditionalTestResources(
            QuarkusTestProfile profileInstance, ClassLoader classLoader) {
        if ((profileInstance == null) || profileInstance.testResources().isEmpty()) {
            return Collections.emptyList();
        }

        try {
            Constructor<?> testResourceClassEntryConstructor = Class
                    .forName(TestResourceManager.TestResourceClassEntry.class.getName(), true, classLoader)
                    .getConstructor(Class.class, Map.class);

            List<QuarkusTestProfile.TestResourceEntry> testResources = profileInstance.testResources();
            List<Object> result = new ArrayList<>(testResources.size());
            for (QuarkusTestProfile.TestResourceEntry testResource : testResources) {
                Object instance = testResourceClassEntryConstructor.newInstance(
                        Class.forName(testResource.getClazz().getName(), true, classLoader), testResource.getArgs());
                result.add(instance);
            }

            return result;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to handle profile " + profileInstance.getClass());
        }
    }

    @Override
    public Description getDescription() {
        return cucumber.getDescription();
    }

    @Override
    public void run(RunNotifier notifier) {
        Map<String, String> map = CucumberProperties.fromEnvironment();
		map.putAll(CucumberProperties.fromPropertiesFile());
        
		if (BooleanUtils.isFalse(BooleanUtils.toBoolean(map.get("stop-server")))) {
            doJavaStart();
        }
        cucumber.run(notifier);
    }

    public static boolean serverStaterd(){
        return runningQuarkusApplication != null;
    }
}