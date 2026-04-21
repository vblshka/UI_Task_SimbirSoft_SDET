package com.ui_autotests.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class BasePage {
    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
    public static WebDriver getDriver() {
        return driver;
    }
    public static boolean isDriverAlive() {
        if (driver == null) return false;
        try {
            driver.getCurrentUrl();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }
}
