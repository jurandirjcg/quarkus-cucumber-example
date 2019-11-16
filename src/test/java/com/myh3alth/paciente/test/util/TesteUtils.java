package com.myh3alth.paciente.test.util;

import static org.hamcrest.Matchers.matchesRegex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.ConfigProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.config.ObjectMapperConfig;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;
import io.restassured.response.ValidatableResponse;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 14/11/2019
 *
 */
public abstract class TesteUtils {

    public static final long TIME_OUT_DEFAULT = 5;
    private static Connection con = null;

    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 14/11/2019
     *
     */
    public enum DBTestFile {
        DELETE_PACIENTE("delete_paciente.sql");

        private String file;

        DBTestFile(String file) {
            this.file = file;
        }

        public String getFile() {
            return file;
        }
    }

    /**
     * Suspende a execução por 'TIME_OUT_DEFAULT' segundos.
     * 
     * @author lgustavolima
     * @since 11/06/2019
     */
    public static void sleep() {
        try {
            Thread.sleep(TIME_OUT_DEFAULT * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Suspende a execução poela quantidade de segundos informada.
     * 
     * @author lgustavolima
     * @since 11/06/2019
     * @param segundos : int
     */
    public static void sleep(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 14/11/2019
     *
     * @param dbTestFile
     * @throws Exception
     */
    public static void executeSqlFile(DBTestFile dbTestFile) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(TesteUtils.class.getClassLoader().getResourceAsStream("scripts/".concat(dbTestFile.getFile()))));
        String line;
        Statement stmt = createConnection();
        while ((line = br.readLine()) != null) {
            if (StringUtils.isNotBlank(line)) {
                stmt.executeUpdate(line);
            }
        }
        br.close();
    }

    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 14/11/2019
     *
     * @return
     * @throws Exception
     */
    private static Statement createConnection() throws Exception {
        if (con == null) {
            String driver = ConfigProvider.getConfig().getValue("quarkus.datasource.driver", String.class);
            String urlConnection = ConfigProvider.getConfig().getValue("quarkus.datasource.url", String.class);
            String dbUser = ConfigProvider.getConfig().getValue("quarkus.datasource.username", String.class);
            String dbPass = ConfigProvider.getConfig().getValue("quarkus.datasource.password", String.class);

            Class.forName(driver);
            con = DriverManager.getConnection(urlConnection,
                dbUser,
                dbPass);
        }

        return con.createStatement();
    }
    
    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 15/11/2019
     *
     * @param key
     * @param validatableResponse
     */
    public static void checkErrorMessage(String key, ValidatableResponse validatableResponse) {
        validatableResponse.body("message", matchesRegex("*"));
    }

    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 14/11/2019
     *
     * @param dateFormat
     * @return
     */
    public static ObjectMapperConfig getGsonObjectMapperConfig(String dateFormat) {
        return ObjectMapperConfig.objectMapperConfig().gsonObjectMapperFactory(new GsonObjectMapperFactory() {
            public Gson create(Type cls, String charset) {
                return new GsonBuilder().setDateFormat(dateFormat).create();
            }
        });
    }
    
    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 15/11/2019
     *
     * @param <T> generic para receber qualquer tipo de objeto
     * @param obj objeto para conversão
     * @return {@link String}
     */
    public static <T> String toJson(T obj) {
        Jsonb jsonb = JsonbBuilder.newBuilder().build();
        return jsonb.toJson(obj);
    }
    
    public static void main(String[] args) {
        Pattern p = Pattern.compile("(?<=(UCS[\\ \\w\\-]{10,100})[\\w\\W]{0,700}\\@\\w{3,10})\n#{1,5}\\s?Funcionalidade\\s?");
        Matcher m = p.matcher("# UCS - Cadastrar Paciente\n" + 
            "\n" + 
            "## 1. Introdução\n" + 
            "\n" + 
            "Este caso de uso descreve as funções de cadastro do paciente.\n" + 
            "\n" + 
            "## 2. Fluxo\n" + 
            "<!BDD.INICIO>\n" + 
            "\n" + 
            "### Tag\n" + 
            "@desenv\n" + 
            "### Funcionalidade: \n" + 
            "Como Usuário, após t");
        
        if(m.find()){
            System.out.println(m.group(1));
        }
    }
}
