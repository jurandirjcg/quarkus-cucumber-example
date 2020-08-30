package com.jcgon.quarkus.cucumber.test.step;

import com.myh3alth.paciente.test.util.TesteUtils;
import com.myh3alth.paciente.test.util.TesteUtils.DBTestFile;

import io.cucumber.core.api.Scenario;
import io.cucumber.java8.Pt;
import io.restassured.RestAssured;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 16/11/2019
 *
 */
public class CucumberGenericSteps implements Pt {

    public CucumberGenericSteps() {
        Before((Scenario scenario) -> {
            RestAssured.basePath = "/v1";
            if (isScenarioUri(scenario, "UCS-CadastrarPaciente")) {
                TesteUtils.executeSqlFile(DBTestFile.DELETE_PACIENTE);
            }
        });

        After((Scenario scenario) -> {
            if (isScenarioUri(scenario, "UCS-CadastrarPaciente")) {
                TesteUtils.executeSqlFile(DBTestFile.DELETE_PACIENTE);
            }
        });
    }

    /**
     * Verifica se o nome da feature confere com o arquivo de feature gerado
     * 
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 16/11/2019
     *
     * @param scenario    {@link Scenario}
     * @param featureName {@link String}
     * @return {@link Boolean}
     */
    private static boolean isScenarioUri(Scenario scenario, String featureName) {
        return scenario.getUri().matches("(?i:.*".concat(featureName).concat(".feature)"));
    }
}
