package com.jcgon.quarkus.cucumber.ws.service.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import br.com.jgon.canary.ws.rest.CanaryRestResources;

@OpenAPIDefinition(
    info = @Info(
        version = "1.0.0",
        title = "Quarkus Cucumber Example Service API",
        description = "API to expose example services",
        license = @License(name = "Licensed under the Apache License, Version 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"),
        contact = @Contact(
            name = "Quarkus Cucumber Example",
            url = "https://github.com/jurandirjcg/quarkus-cucumber-example")),
    servers = {
        @Server(url = "http://localhost:8080")
    })
@ApplicationPath("/v1")
public class RestApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> resources = new HashSet<Class<?>>();

    /**
     *
     */
    public RestApplication() {
        resources.addAll(CanaryRestResources.getClasses());

        //resources.add(V1PacienteEndpoint.class);
    }

    /**
     *
     */
    @Override
    public Set<Class<?>> getClasses() {
        return resources;
    }

    /**
     *
     */
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
