package com.myh3alth.paciente.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.quarkus.test.junit.QuarkusTestCucumber;

@RunWith(QuarkusTestCucumber.class)
@CucumberOptions(
		plugin = {"pretty", "summary"}, 
		features = "target/features",
		tags = "@desenv",
		glue = "com.myh3alth.paciente.test.step",
		dryRun = false,
		monochrome = false,
		strict = true)
public class RunCucumberDevelopFeatures {

}