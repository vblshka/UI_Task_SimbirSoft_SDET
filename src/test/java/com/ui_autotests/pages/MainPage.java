package com.ui_autotests.pages;

import com.ui_autotests.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainPage extends BasePage {
    private static MainPage instance;

    @FindBy(xpath = "//ul[@class='nav-pills categorymenu']/li/a[contains(@href, 'path=68')]")
    private WebElement apparelAccessoriesButton;

    @FindBy(id = "filter_keyword")
    private WebElement inputLine;

    @FindBy(xpath = "//a[@class='prdocutname']")
    private List<WebElement> products;

    private static List<String> availableProductTitles = new ArrayList<>();

    private MainPage() {
        driver.get("https://automationteststore.com/");
        PageFactory.initElements(driver, this);
    }

    public static MainPage getInstance() {
        if (instance == null || !isDriverAlive()) {
            instance = new MainPage();
        }
        return instance;
    }

    public MainPage initUniqueProductsOnMainPage() {
        for (WebElement product : products) {
            String title = product.getAttribute("title");

            if (!availableProductTitles.contains(title)) {
                availableProductTitles.add(title);
            }
        }
        return this;
    }

    public ApparelAccessoriesPage openApparelAccessoriesPage() {
        apparelAccessoriesButton.click();
        return new ApparelAccessoriesPage();
    }

    public SearchByKeywordsPage searchByKeyword(String keyword) {
        inputLine.sendKeys(keyword, Keys.ENTER);
        return new SearchByKeywordsPage();
    }

    public ProductPage chooseRandomProductsFromMainPage() {

        Random random = new Random();
        int productNumber = random.nextInt(availableProductTitles.size());
        String selectedTitle = availableProductTitles.get(productNumber);

        WebElement productToAddInCart = driver.findElement(
                By.xpath("//a[@class='prdocutname'][@title='" + selectedTitle + "']"));

        System.out.println("Expect added to cart: " + availableProductTitles.get(productNumber));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(productToAddInCart));

        productToAddInCart.click();
        System.out.println("Added to cart: " + availableProductTitles.get(productNumber));
        availableProductTitles.remove(productNumber);

        return new ProductPage();
    }
}
