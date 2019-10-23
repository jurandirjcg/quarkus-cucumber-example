package com.myh3alth.paciente.ws.service.rest.v1;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.myh3alth.paciente.entity.enumeration.Situacao;

import br.com.jgon.canary.ws.rest.util.HalJsonRootName;
import br.com.jgon.canary.ws.rest.util.ResponseConverter;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 22/10/2019
 *
 */
@HalJsonRootName("situacao")
@Schema(name = "Situacao", description = "situacao do registro")
class ResponseSituacao extends ResponseConverter<Situacao>{

	private String id;
	private String titulo;
	
	public ResponseSituacao() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
