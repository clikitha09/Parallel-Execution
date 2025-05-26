package org.hooks;

public class BrowserManager {
    private static ThreadLocal<String> browser = new ThreadLocal<>();

    public static void setBrowser(String browserName) {
        browser.set(browserName);
    }

    public static String getBrowser() {
        return browser.get();
    }

    public static void clear() {
        browser.remove();
    }
}
