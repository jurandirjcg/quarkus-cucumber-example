package com.myh3alth.paciente.ws.service.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@SuppressWarnings("deprecation")
@Health
@ApplicationScoped
public class ServiceHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named(ServiceHealthCheck.class.getSimpleName()).up().build();
    }
}
