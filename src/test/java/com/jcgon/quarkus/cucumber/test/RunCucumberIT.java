package com.jcgon.quarkus.cucumber.test;

import com.jcgon.quarkus.cucumber.test.util.QuarkusPostgreSQLResource;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTestCucumber;

@RunWith(QuarkusTestCucumber.class)
@CucumberOptions(
    plugin = { "pretty", "json:target/cucumber.json" },
    features = "target/features",
    tags = "not @analise",
    glue = "com.jcgon.quarkus.cucumber.test.step",
    dryRun = false,
    monochrome = true,
    strict = true)
@QuarkusTestResource(QuarkusPostgreSQLResource.class)
public class RunCucumberIT {

}
