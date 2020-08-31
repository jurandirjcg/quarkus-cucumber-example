package com.jcgon.quarkus.cucumber.ws.service.rest.v1.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jcgon.quarkus.cucumber.business.MovieBusiness;
import com.jcgon.quarkus.cucumber.config.ConfigController;
import com.jcgon.quarkus.cucumber.ws.service.rest.v1.request.V1MovieRequest;
import com.jcgon.quarkus.cucumber.ws.service.rest.v1.response.V1MovieResponse;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.GZIP;

import br.com.jgon.canary.util.Page;
import br.com.jgon.canary.ws.rest.link.LinkPaginate;
import br.com.jgon.canary.ws.rest.param.WSFieldParam;
import br.com.jgon.canary.ws.rest.param.WSSortParam;
import br.com.jgon.canary.ws.rest.util.DominiosRest;
import br.com.jgon.canary.ws.rest.util.ResponseConverter;
import io.smallrye.common.constraint.NotNull;

@GZIP
@Path("/movies")
@Produces({ DominiosRest.APPLICATION_HAL_JSON, MediaType.APPLICATION_JSON })
@RequestScoped
// Open API
@Tag(name = "Movies")
public class V1MovieResource {
    @Inject
    MovieBusiness movieBusiness;

    /**
     * 
     * @param movieRequest
     * @return
     */
    public V1MovieResponse save(@RequestBody @NotNull @Valid V1MovieRequest movieRequest) {
        return ResponseConverter.converter(V1MovieResponse.class, movieBusiness.save(movieRequest.converter()));
    }

    /**
     * 
     * @param id
     * @param name
     * @param releaseDate
     * @param fields
     * @param sort
     * @param page
     * @param limit
     * @return
     */
    @GET
    @Path("/")
    @LinkPaginate(pageParamName = "page", limitParamName = "limit", embeddedCollectionName = "movies")
    @Operation(summary = "movie's pagination")
    @APIResponse(content = @Content(schema = @Schema(implementation = V1MovieResponse.class)))
    public Page<V1MovieResponse> paginate(@Parameter final Integer id, @Parameter final String name,
            @Parameter(description = "Allow to use regex: <, <=, =, >, >=, btwn, !=. Ex: 2020-01-02 btwn 2020-07-31") final String releaseDate,
            @Parameter(schema = @Schema(implementation = String.class)) @QueryParam("fields") @DefaultValue("id,name") final WSFieldParam fields,
            @Parameter(schema = @Schema(implementation = String.class)) @QueryParam("sort") @DefaultValue("name:asc") final WSSortParam sort,
            @Parameter @QueryParam("page") @DefaultValue(ConfigController.REST_DEFAULT_RESULT_PAGE) final Integer page,
            @Parameter @QueryParam("limit") @DefaultValue(ConfigController.REST_DEFAULT_RESULT_LIMIT) final Integer limit) {

        return ResponseConverter.converter(V1MovieResponse.class,
                movieBusiness.paginate(id, name, releaseDate, fields.toList(), sort.toList(), page, limit));
    }

}