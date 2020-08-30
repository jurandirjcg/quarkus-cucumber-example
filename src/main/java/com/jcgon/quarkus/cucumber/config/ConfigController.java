package com.jcgon.quarkus.cucumber.config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ConfigController {

    @Inject
    @ConfigProperty(name = "ambiente")
    private String ambiente;

    /**
     * 
     * @return
     */
    public String getAmbiente() {
        return ambiente;
    }
}
