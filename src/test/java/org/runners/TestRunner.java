package org.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.hooks.BrowserManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.stepDefs", "org.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setBrowser(String browser) {
        BrowserManager.setBrowser(browser);
        System.out.println("Executing tests on browser: " + browser); // Debug log
    }
}
