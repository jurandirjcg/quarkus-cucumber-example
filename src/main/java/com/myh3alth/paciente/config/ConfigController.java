package com.myh3alth.paciente.config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 22/10/2019
 *
 */
@ApplicationScoped
public class ConfigController {

    @Inject
    @ConfigProperty(name = "ambiente")
    private String ambiente;

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @return {@link String}
     */
    public String getAmbiente() {
        return ambiente;
    }
}
