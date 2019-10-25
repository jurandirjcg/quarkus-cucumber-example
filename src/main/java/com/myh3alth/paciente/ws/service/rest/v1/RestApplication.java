package com.myh3alth.paciente.ws.service.rest.v1;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import br.com.jgon.canary.ws.rest.CanaryRestResources;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 22/10/2019
 *
 */
@OpenAPIDefinition(
		info = @Info(
				version = "1.0.0", 
				title = "Paciente Service API", 
				description = "API de serviços de pacientes",
				license = @License(
						name = "Zion Mountain Software - 2019",
						url = "https://zionsofware.com/licenca"
				),
				contact = @Contact(
						email = "suporte@zionmountain.com",
						name = "Suporte API",
						url = "https://zionsofware.com/suporte"
				)
		),
		servers = @Server(
				url = "http://paciente-desenv.myh3alth.com"
		)
)
@ApplicationPath("/api/v1/")
public class RestApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> resources = new HashSet<Class<?>>();
	
	public RestApplication() {
		resources.addAll(CanaryRestResources.getClasses());
		resources.add(PacienteEndpoint.class);

		CorsFilter corsFilter = new CorsFilter();
		corsFilter.getAllowedOrigins().add("*");
		corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
		corsFilter.setCorsMaxAge(-1);
		corsFilter.setAllowedHeaders("Origin, Content-Type, Accept");
		corsFilter.setExposedHeaders("X-Pagintion-Current-Page, X-Pagination-Page-Count, X-Pagination-Per-Page, X-Pagination-Total-Count, Link");
		singletons.add(corsFilter);

	}
	@Override
	public Set<Class<?>> getClasses() {
		return resources;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
