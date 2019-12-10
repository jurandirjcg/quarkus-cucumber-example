package com.myh3alth.paciente.test.step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.apache.http.HttpStatus;

import io.cucumber.java8.Pt;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 26/10/2019
 *
 */
public class PesquisarTabelasAuxliaresStep implements Pt {

    protected RequestSpecification requestSpecification;
    protected Response response;

    public PesquisarTabelasAuxliaresStep() {

        Dado("que eu esteja listando as informações da escolaridade", () -> {

        });

        Quando("eu preencher os campos id:{int}, nome:{string}", (Integer id, String nome) -> {
            requestSpecification = given()
                .queryParam("id", id)
                .queryParam("nome", nome);
        });

        E("eu acionar o botão de pesquisar escolaridade, Pesquisar", () -> {
            response = requestSpecification.get("/escolaridades");
        });

        Entao("o sistema deverá listar as escolaridades correspondentes", () -> {
            response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("_embedded.pacientes.size", greaterThan(0));
        });

        Dado("que eu esteja obtendo uma escolaridade", () -> {
        });

        Quando("eu buscar pelo id:{int}", (Integer id) -> {
            requestSpecification = given()
                .pathParam("id", id);
        });

        Quando("eu acionar o botão de obter escolaridade, Obter", () -> {
            response = requestSpecification.post("/escolaridades/{id}");
        });

        Entao("o sistema deverá retornar a escolaridade nome:{string}", (String nome) -> {
            response.then().statusCode(HttpStatus.SC_OK).body("nome", equalTo(nome));
        });
    }
}
