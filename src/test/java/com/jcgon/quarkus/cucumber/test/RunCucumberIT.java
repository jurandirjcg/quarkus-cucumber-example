package com.jcgon.quarkus.cucumber.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.quarkus.test.junit.QuarkusTestCucumber;

@RunWith(QuarkusTestCucumber.class)
@CucumberOptions(
    plugin = { "pretty", "json:target/cucumber.json" }, // , "de.monochromata.cucumber.report.PrettyReports:target/site"},
    features = "target/features",
    tags = "not @analise",
    glue = "com.myh3alth.paciente.test.step",
    dryRun = false,
    monochrome = true,
    strict = true)
public class RunCucumberIT {

}
