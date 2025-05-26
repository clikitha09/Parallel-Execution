package org.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Properties;
import java.io.FileInputStream;

public class BrowserStackHooks {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String USERNAME;
    private static String ACCESS_KEY;

    static {
        try {
            Properties prop = new Properties();
            FileInputStream file = new FileInputStream("src/test/resources/browserstack.properties");
            prop.load(file);
            USERNAME = prop.getProperty("username");
            ACCESS_KEY = prop.getProperty("access_key");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp(io.cucumber.java.Scenario scenario) throws Exception {
        String browser = System.getProperty("browser");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", browser);
        caps.setCapability("name", scenario.getName());
        caps.setCapability("project", "Search Functionality");
        caps.setCapability("build", "CrossBrowser-Parallel");

        String url = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
        driver.set(new RemoteWebDriver(new URL(url), caps));
        getDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
