package com.jcgon.quarkus.cucumber.ws.service.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class ServiceHealthLivenessCheck implements HealthCheck {

    /**
	 *
	 */
	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.named("Liveness - OK").up().build();
	}
}
