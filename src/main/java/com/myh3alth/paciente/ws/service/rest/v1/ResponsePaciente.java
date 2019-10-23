package com.myh3alth.paciente.ws.service.rest.v1;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.myh3alth.paciente.entity.Paciente;

import br.com.jgon.canary.ws.rest.util.HalJsonRootName;
import br.com.jgon.canary.ws.rest.util.ResponseConverter;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 22/10/2019
 *
 */
@HalJsonRootName("paciente")
@Schema(name = "Paciente", description = "informações do paciente")
class ResponsePaciente extends ResponseConverter<Paciente>{

	private Long id;
	private String nome;
	private ResponseSituacao situacao;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ResponseSituacao getSituacao() {
		return situacao;
	}
	
	public void setSituacao(ResponseSituacao situacao) {
		this.situacao = situacao;
	}
}
