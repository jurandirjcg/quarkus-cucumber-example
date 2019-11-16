package com.myh3alth.paciente.test.step;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    public CadastrarPacienteSteps() {
        Before(() -> {
            TesteUtils.executeSqlFile(DBTestFile.DELETE_PACIENTE);

            // RestAssured.baseURI = "http://paciente.desenv.myh3alth.com";
            RestAssured.baseURI = "http://localhost:8081";
            RestAssured.basePath = "/api/v1";
        });

        After(() -> {
            TesteUtils.executeSqlFile(DBTestFile.DELETE_PACIENTE);
        });

        Dado("que eu esteja na tela Cadastro de Paciente", () -> {
            requestSpecification = given();
            paciente = new V1RequestPaciente();
        });

        Quando(
            "eu preencher os campos foto {string}, dataNascimento {localDate}, cidadedeNacimento {string}, cidadeResidencia {string}, pai {string}, mae {string}, estadoCivil {string}",
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

        E("possuiFilhos {string}, quantosFilhos {int}, raca {string}, etnia {string}, orientacaoSexual {string}, religiao {string}, CPF {string}",
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

        E("escolaridade {string}, profissao {string}, peso {double}, altura {double}, telefone {string}, telefoneComercial {string}",
            (String escolaridade, String profissao, Double peso, Double altura, String telefone, String telefoneComercial) -> {
                paciente.setEscolaridade(escolaridade);
                paciente.setProfissao(profissao);
                paciente.setPeso(new BigDecimal(peso));
                paciente.setAltura(new BigDecimal(altura));
                paciente.setTelefone(telefone);
                paciente.setTelefoneComercial(telefoneComercial);
            });

        E("clicar no botão Salvar da tela de Cadastro de Paciente", () -> {
            requestSpecification.body(TesteUtils.toJson(paciente));
            response = requestSpecification.post("/pacientes");
        });

        Então("o sistema deverá apresentar a tela Cadastro Adicional.", () -> {
            response
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        });

        Então("o sistema deverá apresentar a mensagem {string}.", (String string) -> {

        });
    }
}
