package com.myh3alth.paciente.test.step;

import static io.restassured.RestAssured.given;

import java.util.Date;

import io.cucumber.java8.Pt;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 26/10/2019
 *
 */
public class PesquisarPacienteSteps implements Pt {

    private RequestSpecification requestSpecification;
    private Response response;

    public PesquisarPacienteSteps() {

        Dado("que eu esteja na tela para pesquisa de paciente", () -> {
            requestSpecification = given();
        });

        Dado("que os filtros estejam em branco", () -> {
            // Filtros
        });

        Quando("eu preencher os campos {string}, {date}, {string}, {string}, {string}", (String nome, Date dataNascimento, String cpf, String rg, String ufRG) -> {

        });

        Quando("eu acionar o botão de Pesquisar", () -> {
            response = requestSpecification.get("/pacientes");
        });

        Entao("o sistema deverá listar os pacientes correspondentes", () -> {
            response
                .then()
                .statusCode(200);
            // .body("totalElements", greaterThan(1));
        });

        Quando("eu preencher o campo Nome com {string}", (String string) -> {
            requestSpecification = given();
        });

        Entao("o sistema deverá retornar a mensagem de {string}", (String string) -> {
            response.then()
                .statusCode(200);
        });

        Quando("eu acionar o botão Incluir", () -> {
            // Implementado na interface
        });

        Entao("o sistema deverá apresentar a tela de Cadastro de Paciente", () -> {
            // Implementado na interface
        });
    }
}
