package com.myh3alth.paciente.ws.service.rest.util;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;

import br.com.jgon.canary.ws.rest.exception.RestExceptionMapper;

/**
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 23/10/2019
 *
 */
@Provider
public class PacienteRestExceptionMapper extends RestExceptionMapper {

    @Inject
    @Metric(name = "failure_endpoint_counter")
    Counter counterFailure;

    /**
     *
     */
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(final Exception exception) {
        counterFailure.inc();
        return super.toResponse(exception);
    }
}
