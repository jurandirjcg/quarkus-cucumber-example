package com.myh3alth.paciente.ws.service.rest.v1;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.myh3alth.paciente.entity.enumeration.Situacao;

import br.com.jgon.canary.ws.rest.util.HalJsonRootName;
import br.com.jgon.canary.ws.rest.util.ResponseConverter;

/**
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 22/10/2019
 *
 */
@HalJsonRootName("situacao")
@Schema(name = "Situacao", description = "situacao do registro")
class ResponseSituacao extends ResponseConverter<Situacao> {

    private String id;
    private String titulo;

    /**
     *
     */
    ResponseSituacao() {

    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @return {@link String}
     */
    public String getId() {
        return id;
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @param id {@link String}
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @return {@link String}
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @param titulo {@link String}
     */
    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }
}
