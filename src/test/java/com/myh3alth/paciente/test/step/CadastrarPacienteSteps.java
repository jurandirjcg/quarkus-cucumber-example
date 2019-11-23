package com.myh3alth.paciente.test.step;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpStatus;

import com.myh3alth.paciente.entity.enumeration.SimNao;
import com.myh3alth.paciente.test.util.TesteUtils;
import com.myh3alth.paciente.ws.service.rest.v1.request.V1RequestPaciente;

import io.cucumber.java8.Pt;
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

        Dado("que eu esteja na tela Cadastro de Paciente", () -> {
            requestSpecification = given();
            requestSpecification.contentType(MediaType.APPLICATION_JSON);
            paciente = new V1RequestPaciente();
        });
        
        E("o sistema tenha preenchido o campo nome:{string}, obtido do usuário logado", (String nome) -> {
            paciente.setNome(nome);
        });

        Quando(
            "eu preencher os campos foto:{string}, dataNascimento:{localDate}, CPF:{string}, cidadedeNacimento:{string}, cidadeResidencia:{string}, pai:{string}, mae:{string}",
            (
                String foto,
                LocalDate dataNascimento,
                String cpf,
                String cidadeNascimento,
                String cidadeResidencia,
                String nomePai,
                String nomeMae) -> {

                paciente.setFoto(foto);
                paciente.setCpf(cpf);
                paciente.setCidadeNascimento(TesteUtils.obterCidade(cidadeNascimento));
                paciente.setCidadeResidencia(TesteUtils.obterCidade(cidadeResidencia));
                paciente.setDataNascimento(dataNascimento);
                paciente.setNomePai(nomePai);
                paciente.setNomeMae(nomeMae);
            });

        E("estadoCivil:{string}, possuiFilhos:{string}, quantosFilhos:{int}, etnia:{string}, orientacaoSexual:{string}, religiao:{string}",
            (
                String estadoCivil,
                String possuiFilho,
                Integer quantosFilhos,
                String etnia,
                String orientacaoSexual,
                String religiao) -> {

                paciente.setEstadoCivil(TesteUtils.obterEstadoCivil(estadoCivil));
                paciente.setPossuiFilhos(SimNao.valueOf(possuiFilho));
                paciente.setQuantidadeFilhos(quantosFilhos);
                paciente.setEtnia(TesteUtils.obterEtnia(etnia));
                paciente.setOrientacaoSexual(TesteUtils.obterOrientacaoSexual(orientacaoSexual));
                paciente.setReligiao(TesteUtils.obterReligiao(religiao));
            });

        E("escolaridade:{string}, profissao:{string}, peso:{double}, altura:{double}, telefone:{string}, telefoneComercial:{string}",
            (String escolaridade, String profissao, Double peso, Double altura, String telefone, String telefoneComercial) -> {
                paciente.setEscolaridade(TesteUtils.obterEscolaridade(escolaridade));
                paciente.setProfissao(TesteUtils.obterProfissao(profissao));
                paciente.setPeso(new BigDecimal(peso));
                paciente.setAltura(new BigDecimal(altura));
                paciente.setTelefone(telefone);
                paciente.setTelefoneComercial(telefoneComercial);
            });

        E("clicar no botão Salvar da tela de Cadastro de Paciente", () -> {
            System.out.println(TesteUtils.toJson(paciente));
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
