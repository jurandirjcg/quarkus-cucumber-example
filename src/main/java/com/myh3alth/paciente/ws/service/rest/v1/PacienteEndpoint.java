package com.myh3alth.paciente.ws.service.rest.v1;

import java.util.Date;

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
import com.myh3alth.paciente.ws.service.rest.v1.request.V1RequestPaciente;
import com.myh3alth.paciente.ws.service.rest.v1.response.V1ResponsePaciente;

import br.com.jgon.canary.exception.ApplicationException;
import br.com.jgon.canary.persistence.exception.SaveEntityException;
import br.com.jgon.canary.util.Page;
import br.com.jgon.canary.ws.rest.link.LinkPaginate;
import br.com.jgon.canary.ws.rest.link.LinkResource;
import br.com.jgon.canary.ws.rest.link.LinkResources;
import br.com.jgon.canary.ws.rest.param.WSFieldParam;
import br.com.jgon.canary.ws.rest.param.WSParamFormat;
import br.com.jgon.canary.ws.rest.param.WSSortParam;
import br.com.jgon.canary.ws.rest.util.DominiosRest;

/**
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 22/10/2019
 *
 */
@ApplicationScoped
@Path("/pacientes")
@GZIP
@Produces({ DominiosRest.APPLICATION_HAL_JSON, MediaType.APPLICATION_JSON })
//Fault Tolerance
@Retry(maxRetries = Utils.DEFAULT_RETRY_MAX_RETRIES)
@Timeout(Utils.MEDIUM_TIMETOUT)
@CircuitBreaker(
        delay = Utils.DEFAULT_CIRCUIT_BRAKER_DELAY,
        requestVolumeThreshold = Utils.DEFAULT_CIRCUIT_BRAKER_REQUEST_VOLUME_THRESHOLD,
        failureRatio = Utils.DEFAULT_CIRCUIT_BRAKER_FAILURE_RATIO,
        successThreshold = Utils.DEFAULT_CIRCUIT_BRAKER_SUCCESS_THRESHOLD)
//Metrics
@Counted(
        name = "endpointPaciente",
        displayName = "Endpoint Pacientes",
        description = "Metrica que apresenta quantas vezes o endpoint de pacientes foi chamado")
@Timed(
        name = "timeEndpointPaciente",
        description = "Metrica que apresenta o tempo de duração do evento do endpoint de paciente")
//Open API
@Tag(name = "Pacientes")
public class PacienteEndpoint {

    @Inject
    PacienteBusiness pacienteBusiness;

    /**
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 08/11/2019
     *
     * @param paciente - {@link V1RequestPaciente}
     * @return {@link V1ResponsePaciente}
     * @throws SaveEntityException {@link SaveEntityException}
     */
    public V1ResponsePaciente salvar(final V1RequestPaciente paciente) throws SaveEntityException {
        return new V1ResponsePaciente().converter(pacienteBusiness.salvar(paciente.converter()));
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 22/10/2019
     *
     * @param id     {@link Long}
     * @param fields {@link List}
     * @return {@link V1ResponsePaciente}
     * @throws ApplicationException {@link ApplicationException}
     */
    @GET
    @Path("/{id}")
    @Timed
    @LinkResources(
            value = { @LinkResource(rel = "self", title = "Self", pathParameters = { "${id}" }),
                    @LinkResource(rel = "update", title = "Update", pathParameters = { "${id}" }),
                    @LinkResource(rel = "delete", title = "Delete", pathParameters = { "${id}" }),
                    @LinkResource(rel = "create", title = "Create", pathParameters = { "" }) })
    @Operation(summary = "obtém as informações do paciente")
    @APIResponse(content = @Content(schema = @Schema(implementation = V1ResponsePaciente.class)))
    public V1ResponsePaciente obterPaciente(@Parameter() @PathParam("id") final Long id, @Parameter(
            schema = @Schema(
                    implementation = String.class)) @QueryParam("fields") @DefaultValue("id,nome") @WSParamFormat(V1ResponsePaciente.class)
            final WSFieldParam fields)
            throws ApplicationException {

        return new V1ResponsePaciente().converter(pacienteBusiness.obter(id, fields.getListField()));
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 22/10/2019
     *
     * @param id       {@link Long}
     * @param nome     {@link String}
     * @param situacao {@link Situacao}
     * @param cpf      {@link String}
     * @param dataNascimento {@link Date}
     * @param page     {@link Integer}
     * @param limit    {@link Integer}
     * @param fields   {@link List}
     * @param sort     {@link List}
     * @return {@link Page}
     * @throws ApplicationException {@link ApplicationException}
     */
    @GET
    @Path("/")
    @Timeout(Utils.BIG_TIMETOUT)
    @Timed
    @LinkPaginate(pageParamName = "page", limitParamName = "limit", embeddedCollectionName = "pacientes")
    @Operation(summary = "paginação de pacientes conforme filtros aplicados")
    @APIResponse(content = @Content(schema = @Schema(implementation = V1ResponsePaciente.class)))
    public Page<V1ResponsePaciente> paginarPaciente(
            // @HeaderParam("Authorization") String token,
            @Parameter() @QueryParam("id") final Long id, @Parameter() @QueryParam("nome")
            final String nome,
            @Parameter(
                    schema = @Schema(
                            implementation = String.class,
                            enumeration = { "ATIVO", "INATIVO" })) @QueryParam("situacao") final Situacao situacao,
            @Parameter() @QueryParam("cpf") final String cpf,
            @Parameter(example = "2001-11-01") final Date dataNascimento,
            @Parameter(
                    schema = @Schema(
                            implementation = String.class,
                            defaultValue = "id,nome")) @QueryParam("fields") @DefaultValue("id,nome") @WSParamFormat(V1ResponsePaciente.class)
            final WSFieldParam fields,
            @Parameter(
                    schema = @Schema(
                            implementation = String.class,
                            defaultValue = "nome:asc")) @QueryParam("sort") @DefaultValue("nome:asc") @WSParamFormat(V1ResponsePaciente.class)
            final WSSortParam sort,
            @Parameter(
                    schema = @Schema(
                            defaultValue = Utils.DEFAULT_REST_PAGE)) @QueryParam("page") @DefaultValue(Utils.DEFAULT_REST_PAGE)
            final Integer page,
            @Parameter(
                    schema = @Schema(
                            defaultValue = Utils.DEFAULT_REST_LIMIT)) @QueryParam("limit") @DefaultValue(Utils.DEFAULT_REST_LIMIT)
            final Integer limit)
            throws ApplicationException {

        return new V1ResponsePaciente().converter(pacienteBusiness.paginarPaciente(id, nome, situacao, cpf,
                dataNascimento, fields.getListField(), sort.getListSort(), page, limit));
    }
}
