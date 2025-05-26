package org.hooks;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;


public class Hooks {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String HUB_URL = "http://localhost:4444/wd/hub";

    @Before
    public void setUp() throws Exception {
        String browser = BrowserManager.getBrowser();
        System.out.println("🔧 Hooks initializing browser: " + browser + " | Thread: " + Thread.currentThread().getId());

        if (browser == null) {
            throw new IllegalArgumentException("Browser is not specified! Make sure to pass the 'browser' parameter dynamically.");
        }

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            driver.set(new RemoteWebDriver(new URL(HUB_URL), options));
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver.set(new RemoteWebDriver(new URL(HUB_URL), options));
        }
        else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            driver.set(new RemoteWebDriver(new URL(HUB_URL), options));
        }
        driver.get().manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.get().quit();
        driver.remove();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
