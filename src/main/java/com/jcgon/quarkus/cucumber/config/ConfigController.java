package com.jcgon.quarkus.cucumber.config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ConfigController {

	public static final String REST_DEFAULT_RESULT_PAGE = "1";
	public static final String REST_DEFAULT_RESULT_LIMIT = "20";
	public static final Integer REST_MAX_RESULT_LIST = 100;
	
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
