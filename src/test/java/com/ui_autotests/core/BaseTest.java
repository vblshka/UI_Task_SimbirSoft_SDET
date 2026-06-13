package com.ui_autotests.core;

import com.ui_autotests.pages.MainPage;
import com.ui_autotests.utils.WaitUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;

    @BeforeEach
    public void setUp() {
        String selenoidUrl = System.getenv("SELENOID_URL");
        boolean useSelenoid = selenoidUrl != null && !selenoidUrl.isEmpty();

        System.out.println("Use Selenoid: " + useSelenoid);
        if (useSelenoid) {
            System.out.println("Selenoid URL: " + selenoidUrl);
        }

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--remote-allow-origins=*");

        if (useSelenoid) {
            // Настройки для Selenoid
            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableVideo", false);
            selenoidOptions.put("screenResolution", "1920x1080x24");
            options.setCapability("selenoid:options", selenoidOptions);
            options.setCapability("browserName", "chrome");
            options.setCapability("browserVersion", "118.0");

            try {
                driver = new RemoteWebDriver(new URL(selenoidUrl), options);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Selenoid URL: " + selenoidUrl, e);
            }
        } else {
            // Локальный запуск
            WebDriverManager.chromedriver().setup();
            String headless = System.getenv("HEADLESS");
            if ("true".equalsIgnoreCase(headless)) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        BasePage.setDriver(driver);

        WaitUtils.init(driver);

        mainPage = new MainPage();
    }

    @AfterEach
    public void tearDown() {
        WebDriver driver = BasePage.getDriver();
        if (driver != null) {
            driver.quit();
            this.driver = null;
            BasePage.setDriver(null);
        }
    }
}
