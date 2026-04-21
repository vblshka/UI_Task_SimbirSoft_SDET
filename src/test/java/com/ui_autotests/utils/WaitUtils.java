package com.ui_autotests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {
    private static WebDriver driver;
    private static final int DEFAULT_TIMEOUT_SECONDS = 30;

    public static void init(WebDriver webDriver) {
        driver = webDriver;
    }

    public static WebDriverWait getDefaultWait() {
        if (driver == null) {
            throw new IllegalStateException("WaitUtils не инициализирован");
        }
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
    }

    public static WebDriverWait getWaitWithTimeout(int seconds) {
        if (driver == null) {
            throw new IllegalStateException("WaitUtils не инициализирован");
        }
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
}