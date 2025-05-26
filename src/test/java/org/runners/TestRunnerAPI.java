package org.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/search_api.feature",
        glue = {"org.stepDefs"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)

public class TestRunnerAPI extends AbstractTestNGCucumberTests{
}
