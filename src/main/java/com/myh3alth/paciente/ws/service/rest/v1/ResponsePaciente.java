package com.myh3alth.paciente.ws.service.rest.v1;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.myh3alth.paciente.entity.Paciente;

import br.com.jgon.canary.ws.rest.util.HalJsonRootName;
import br.com.jgon.canary.ws.rest.util.ResponseConverter;

/**
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 22/10/2019
 *
 */
@HalJsonRootName("paciente")
@Schema(name = "Paciente", description = "informações do paciente")
public class ResponsePaciente extends ResponseConverter<Paciente> {

    private Long id;
    private String nome;
    private ResponseSituacao situacao;

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @return {@link Long}
     */
    public Long getId() {
        return id;
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @param id {@link Long}
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @return {@link String}
     */
    public String getNome() {
        return nome;
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @param nome {@link String}
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @return {@link ResponseSituacao}
     */
    public ResponseSituacao getSituacao() {
        return situacao;
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @param situacao {@link ResponseSituacao}
     */
    public void setSituacao(final ResponseSituacao situacao) {
        this.situacao = situacao;
    }
}
