package com.myh3alth.paciente.test.step;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpStatus;

import com.myh3alth.paciente.entity.enumeration.SimNao;
import com.myh3alth.paciente.test.util.TesteUtils;
import com.myh3alth.paciente.test.util.TesteUtils.DBTestFile;
import com.myh3alth.paciente.ws.service.rest.v1.request.V1RequestPaciente;

import io.cucumber.java8.Pt;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 26/10/2019
 *
 */
public class CadastrarPacienteSteps implements Pt {

    private RequestSpecification requestSpecification;
    private Response response;
    private V1RequestPaciente paciente;
    Jsonb jsonb;

    public CadastrarPacienteSteps() {
        Before(() -> {
            TesteUtils.executeSqlFile(DBTestFile.DELETE_PACIENTE);

            // RestAssured.baseURI = "http://paciente.desenv.myh3alth.com";
            RestAssured.baseURI = "http://localhost:8081";
            RestAssured.basePath = "/api/v1";
            jsonb = JsonbBuilder.newBuilder().build();
        });

        After(() -> {
            TesteUtils.executeSqlFile(DBTestFile.DELETE_PACIENTE);
        });

        Dado("que eu esteja na tela Cadastro de Paciente", () -> {
            requestSpecification = given();
            paciente = new V1RequestPaciente();
        });

        Quando("eu preencher os campos {string}, {localDate}, {string}, {string}, {string}, {string}, {string}",
            (
                String foto,
                LocalDate dataNascimento,
                String cidadeNascimento,
                String cidadeResidencia,
                String nomePai,
                String nomeMae,
                String estadoCivil) -> {
                requestSpecification.contentType(MediaType.APPLICATION_JSON);

                paciente.setFoto(foto);
                paciente.setDataNascimento(dataNascimento);
                paciente.setNome("Nome do usuário logado");
                paciente.setNomePai(nomePai);
                paciente.setNomeMae(nomeMae);
                paciente.setEstadoCivil(estadoCivil);
            });

        E("{string}, {int}, {string}, {string}, {string}, {string}, {string}",
            (
                String possuiFilho,
                Integer quantosFilhos,
                String raca,
                String etnia,
                String orientacaoSexual,
                String religiao,
                String cpf) -> {

                paciente.setPossuiFilhos(SimNao.valueOf(possuiFilho));
                paciente.setQuantidadeFilhos(quantosFilhos);
                paciente.setEtnia(etnia);
                paciente.setOrientacaoSexual(orientacaoSexual);
                paciente.setReligiao(religiao);
                paciente.setCpf(cpf);
            });

        E("{string}, {string}, {double}, {double}, {string}, {string}",
            (String escolaridade, String profissao, Double peso, Double altura, String telefone, String telefoneComercial) -> {
                paciente.setEscolaridade(escolaridade);
                paciente.setProfissao(profissao);
                paciente.setPeso(new BigDecimal(peso));
                paciente.setAltura(new BigDecimal(altura));
                paciente.setTelefone(telefone);
                paciente.setTelefoneComercial(telefoneComercial);
            });

        E("clicar no botão Salvar", () -> {
            requestSpecification.body(jsonb.toJson(paciente));
            response = requestSpecification.post("/pacientes");
        });

        Então("o sistema deverá apresentar a tela Cadastro Adicional.", () -> {
            response
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        });

        Então("o sistema deverá apresentar a mensagem {string}.", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
            // throw new cucumber.api.PendingException();
        });
    }
}
