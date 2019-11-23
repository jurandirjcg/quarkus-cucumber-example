package com.myh3alth.paciente.test.util;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesRegex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.ConfigProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.jgon.canary.ws.rest.util.DominiosRest;
import io.restassured.RestAssured;
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

    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 20/11/2019
     *
     * @param nome
     * @return
     */
    public static Integer obterEscolaridade(String nome) {
        return given().queryParam("nome", nome).accept(DominiosRest.APPLICATION_HAL_JSON).get("/dominios/escolaridades")
            .then().extract().path("_embedded.escolaridades[0].id");
    }
    
    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 21/11/2019
     *
     * @param nome
     * @return
     */
    public static Integer obterEstadoCivil(String nome) {
        return given().queryParam("nome", nome).accept(DominiosRest.APPLICATION_HAL_JSON).get("/dominios/estados-civis")
            .then().extract().path("_embedded.estados-civis[0].id");
    }

    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 21/11/2019
     *
     * @param nome
     * @return
     */
    public static Integer obterReligiao(String nome) {
        return given().queryParam("nome", nome).accept(DominiosRest.APPLICATION_HAL_JSON).get("/dominios/religioes")
            .then().extract().path("_embedded.religioes[0].id");
    }
    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 21/11/2019
     *
     * @param nome
     * @return
     */
    public static Integer obterOrientacaoSexual(String nome) {
        return given().queryParam("nome", nome).accept(DominiosRest.APPLICATION_HAL_JSON).get("/dominios/orientacoes-sexuais")
            .then().extract().path("_embedded.orientacoes-sexuais[0].id");
    }
    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 21/11/2019
     *
     * @param nome
     * @return
     */
    public static Integer obterEtnia(String nome) {
        return given().queryParam("nome", nome).accept(DominiosRest.APPLICATION_HAL_JSON).get("/dominios/etnias")
            .then().extract().path("_embedded.etnias[0].id");
    }
    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 21/11/2019
     *
     * @param nome
     * @return
     */
    public static Integer obterProfissao(String nome) {
        return given().queryParam("nome", nome).accept(DominiosRest.APPLICATION_HAL_JSON).get("/dominios/profissoes")
            .then().extract().path("_embedded.profissoes[0].id");
    }
    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 21/11/2019
     *
     * @param nome
     * @return
     */
    public static Integer obterCidade(String nome) {
        return given().queryParam("nome", nome).accept(DominiosRest.APPLICATION_HAL_JSON).get("/dominios/municipios")
            .then().extract().path("_embedded.municipios[0].id");
    }

    public static void main(String[] args) {
        RestAssured.basePath = "/v1";
        RestAssured.port = 8080;
        System.out.println(obterEscolaridade("Analfabeto"));
        System.out.println(obterCidade("Curitiba"));
        System.out.println(obterEstadoCivil("CASADO"));
        System.out.println(obterEtnia("Branco"));
        System.out.println(obterOrientacaoSexual("HETERO"));
        System.out.println(obterProfissao("outro"));
        System.out.println(obterReligiao("Outro"));
    }
}
