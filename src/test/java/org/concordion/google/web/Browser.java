package org.concordion.google.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Manages the browser session.
 */
public class Browser {
    private WebDriver driver;

    public Browser() {
        driver = new FirefoxDriver();
    }

    public void close() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
