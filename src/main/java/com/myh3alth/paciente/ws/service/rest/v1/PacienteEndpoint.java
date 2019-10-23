package com.myh3alth.paciente.ws.service.rest.v1;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.GZIP;

import com.myh3alth.paciente.business.PacienteBusiness;
import com.myh3alth.paciente.entity.enumeration.Situacao;
import com.myh3alth.paciente.util.Utils;

import br.com.jgon.canary.exception.ApplicationException;
import br.com.jgon.canary.util.Page;
import br.com.jgon.canary.ws.rest.link.LinkPaginate;
import br.com.jgon.canary.ws.rest.param.WSFieldParam;
import br.com.jgon.canary.ws.rest.param.WSParamFormat;
import br.com.jgon.canary.ws.rest.param.WSSortParam;
import br.com.jgon.canary.ws.rest.util.DominiosRest;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 22/10/2019
 *
 */
@ApplicationScoped
@Path("/pacientes")
@GZIP
@Produces({DominiosRest.APPLICATION_HAL_JSON, MediaType.APPLICATION_JSON})
//Fault Tolerance
@Retry(maxRetries = 2)
@Timeout(Utils.DEFAULT_REST_TIMEOUT)
@CircuitBreaker(delay = 1000, requestVolumeThreshold = 5, failureRatio = 0.75, successThreshold = 10)
//Metrics
@Counted(
		name = "endpointPaciente",
		displayName = "Endpoint Pacientes",
		description = "Metrica que apresenta quantas vezes o endpoint de pacientes foi chamado"
		)
@Timed(
		name = "timeEndpointPaciente",
		description = "Metrica que apresenta o tempo de duração do evento do endpoint de paciente"
		)
//Open API
@Tag(name = "Pacientes")
public class PacienteEndpoint {
  
	@Inject
	private PacienteBusiness pacienteBusiness;
	
	/**
	 * 
	 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
	 * @since 22/10/2019
	 *
	 * @param id
	 * @return {@link ResponsePaciente}
	 * @throws ApplicationException 
	 */
	@GET
	@Path("/{id}")
	@Timed
	@Operation(summary = "obtém as informações do paciente")
	@APIResponse(content = @Content(schema = @Schema(implementation = ResponsePaciente.class)))
	public ResponsePaciente obterPaciente(
			@Parameter() 
			@PathParam("id") 
			Long id,
			@Parameter(schema = @Schema(implementation = String.class)) 
			@QueryParam("fields") 
			@DefaultValue("id,nome") 
			@WSParamFormat(ResponsePaciente.class) 
			WSFieldParam fields
			) throws ApplicationException {
		
		return new ResponsePaciente().converter(pacienteBusiness.obterPaciente(id, fields.getListField()));
	}
	
	/**
	 * 
	 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
	 * @since 22/10/2019
	 *
	 * @param id
	 * @param nome
	 * @param situacao
	 * @param page
	 * @param limit
	 * @param fields
	 * @param sort
	 * @return {@link Page}
	 * @throws ApplicationException
	 */
	@GET
	@Path("/")
	@Timeout(3000)
	@Timed
	@LinkPaginate(pageParamName = "page", limitParamName = "limit")
	@Operation(summary = "paginação de pacientes conforme filtros aplicados")
	@APIResponse(content = @Content(schema = @Schema(implementation = ResponsePaciente.class)))
	public Page<ResponsePaciente> paginarPaciente(
			//@HeaderParam("Authorization") String token,
			@Parameter() 
			@QueryParam("id") 
			Long id,
			@Parameter() 
			@QueryParam("nome") 
			String nome,
			@Parameter(schema = @Schema(implementation = String.class, enumeration = {"ATIVO", "INATIVO"})) 
			@QueryParam("situacao") 
			Situacao situacao,
			@Parameter(schema = @Schema(implementation = String.class, defaultValue = "id,nome")) 
			@QueryParam("fields") 
			@DefaultValue("id,nome") 
			@WSParamFormat(ResponsePaciente.class) 
			WSFieldParam fields,
			@Parameter(schema = @Schema(implementation = String.class, defaultValue = "nome:asc")) 
			@QueryParam("sort") 
			@DefaultValue("nome:asc") 
			@WSParamFormat(ResponsePaciente.class) 
			WSSortParam sort,
			@Parameter(schema = @Schema(defaultValue = Utils.DEFAULT_REST_PAGE)) 
			@QueryParam("page") 
			@DefaultValue(Utils.DEFAULT_REST_PAGE) 
			Integer page, 
			@Parameter(schema = @Schema(defaultValue = Utils.DEFAULT_REST_LIMIT)) 
			@QueryParam("limit") 
			@DefaultValue(Utils.DEFAULT_REST_LIMIT) 
			Integer limit
			) throws ApplicationException {
		
		return new ResponsePaciente().converter(pacienteBusiness.paginarPaciente(id, nome, situacao, fields.getListField(), sort.getListSort(), page, limit));
	}
}
