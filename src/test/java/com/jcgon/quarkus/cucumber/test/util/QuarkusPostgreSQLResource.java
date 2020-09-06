package com.jcgon.quarkus.cucumber.test.util;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class QuarkusPostgreSQLResource implements QuarkusTestResourceLifecycleManager {

    public static final PostgreSQLContainer<?> PG_DATABASE = new PostgreSQLContainer<>("postgres:11-alpine")// ("mdillon/postgis:11-alpine")
            .withDatabaseName(System.getenv("test-container-pg-db"))
            .withUsername(System.getenv("test-container-pg-user"))
            .withPassword(System.getenv("test-container-pg-passwd"));

    @Override
    public Map<String, String> start() {
        PG_DATABASE.start();
        Map<String, String> datasourceProperties = new HashMap<String, String>();
        datasourceProperties.put("quarkus.datasource.url", PG_DATABASE.getJdbcUrl());
        datasourceProperties.put("quarkus.datasource.replica.url", PG_DATABASE.getJdbcUrl());

        return datasourceProperties;
    }

    @Override
    public void stop() {
    }
}