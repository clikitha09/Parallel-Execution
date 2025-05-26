package org.runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features/search_browserstack.feature",
        glue = {"org.stepDefs", "org.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)

public class TestRunnerBrowserStack {
    @Parameters("browser")
    @BeforeTest
    public void setBrowser(String browser) {
        System.setProperty("browser", browser);
    }
}
