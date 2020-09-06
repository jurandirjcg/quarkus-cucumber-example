package com.jcgon.quarkus.cucumber.test.step;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import br.com.jgon.canary.util.DateUtil;
import io.cucumber.java8.Pt;
import io.cucumber.java8.Scenario;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.ObjectMapperType;

public class CucumberInitSteps implements Pt {
    public CucumberInitSteps() {
        init();

        Before("not @analysis", 0, (Scenario scenario) -> {
            
        });

        After("not @analysis", 0, (Scenario scenario) -> {

        });

        //Date and Time Converter (pt-BR pattern)
        ParameterType("date", "\\d{4}\\-\\d{2}\\-\\d{2}", (String s) -> DateUtil.parseDate(s));
        ParameterType("localDate", "\\d{4}\\-\\d{2}\\-\\d{2}", (String s) -> LocalDate.parse(s));
        ParameterType("localDateTime", "\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}(\\:\\d{2})?",
                (String s) -> LocalDateTime.parse(s));
        ParameterType("localTime", "\\d{2}\\:\\d{2}(\\:\\d{2})?", (String s) -> LocalTime.parse(s));
    }

    private static void init() {
        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.JSONB));
                
        RestAssured.basePath = "/v1";
    }
}
