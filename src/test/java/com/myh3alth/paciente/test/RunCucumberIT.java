package com.myh3alth.paciente.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/site/cucumber"}, 
		features = "target/features",
		tags = "not @analise",
		glue = "com.myh3alth.paciente.test.step",
		dryRun = false,
		monochrome = true,
		strict = true)
public class RunCucumberIT {

}
