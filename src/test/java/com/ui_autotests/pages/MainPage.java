package com.ui_autotests.pages;

import com.ui_autotests.core.BasePage;
import com.ui_autotests.utils.WaitUtils;
import com.ui_autotests.utils.RandomUtils;
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
    @FindBy(xpath = "//ul[@class='nav-pills categorymenu']/li/a[contains(@href, 'path=68')]")
    private WebElement apparelAccessoriesButton;

    @FindBy(id = "filter_keyword")
    private WebElement inputLine;

    @FindBy(xpath = "//a[@class='prdocutname']")
    private List<WebElement> products;

    private static List<String> availableProductTitles = new ArrayList<>();

    public MainPage() {
        driver.get("https://automationteststore.com/");
        PageFactory.initElements(driver, this);
    }

    public void initUniqueProductsOnMainPage() {
        for (WebElement product : products) {
            String title = product.getAttribute("title");

            if (!availableProductTitles.contains(title)) {
                availableProductTitles.add(title);
            }
        }
        CartState.getInstance().setAvailableProductTitles(availableProductTitles);
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

        String selectedTitle = RandomUtils.getRandomProduct(CartState.getInstance().getAvailableProductTitles());

        WebElement productToAddInCart = driver.findElement(
                By.xpath("//a[@class='prdocutname'][@title='" + selectedTitle + "']"));

        WaitUtils.getDefaultWait().until(ExpectedConditions.elementToBeClickable(productToAddInCart));

        productToAddInCart.click();

        CartState.getInstance().getAvailableProductTitles().remove(selectedTitle);

        return new ProductPage();
    }
}
