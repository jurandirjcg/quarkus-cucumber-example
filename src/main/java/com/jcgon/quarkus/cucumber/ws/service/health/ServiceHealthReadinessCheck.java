package com.jcgon.quarkus.cucumber.ws.service.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ServiceHealthReadinessCheck implements HealthCheck {

    /**
     * 
     */
    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Database connection test");
        try {
            boolean masterDB = true; // Put master DB test
            boolean slaveDB = true; // Put slave DB test

            if (!masterDB && !slaveDB) {
                responseBuilder.down().withData("error", "Error to connect to Master and Slave Database");
            } else if (!masterDB) {
                responseBuilder.down().withData("error", "Error to connect to Master Database");
            } else if (!slaveDB) {
                responseBuilder.down().withData("error", "Error to connect to Slave Database");
            }

            responseBuilder.up();
        } catch (IllegalStateException e) {
            responseBuilder.down().withData("error", e.getMessage());
        }
        return responseBuilder.build();

    }

}