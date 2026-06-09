package com.ui_autotests.pages;

import com.ui_autotests.core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TshirtsPage extends BasePage {

    @FindBy(id = "sort")
    private WebElement dropDown;

    @FindBy(xpath = "//option[@value='pd.name-ASC']")
    private WebElement sortByNameAsc;

    @FindBy(xpath = "//option[@value='pd.name-DESC']")
    private WebElement sortByNameDesc;

    @FindBy(xpath = "//option[@value='p.price-ASC']")
    private WebElement sortByPriceAsc;

    @FindBy(xpath = "//option[@value='p.price-DESC']")
    private WebElement sortByPriceDesc;

    @FindBy(xpath = "//div[@class='thumbnails grid row list-inline']//a[@class='prdocutname']")
    private List<WebElement> products;

    @FindBy(xpath = "//div[@class='thumbnails grid row list-inline']//div[@class='oneprice']")
    private List<WebElement> prices;

    public TshirtsPage() {
        PageFactory.initElements(driver, this);
    }

    private void selectSortOption(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dropDown));

        Select select = new Select(dropDown);
        select.selectByValue(value);
    }

    public List<String> chooseSortByNameAsc() {
        selectSortOption("pd.name-ASC");

        List<String> productNames= new ArrayList<>();
        for(WebElement elements: products) {
            String title = elements.getAttribute("title");
            productNames.add(title);
        }

        return productNames;
    }

    public List<String> chooseSortByNameDesc() {
        selectSortOption("pd.name-DESC");

        List<String> productNames= new ArrayList<>();
        for(WebElement elements: products) {
            String title = elements.getAttribute("title");
            productNames.add(title);
        }

        return productNames;
    }

    public List<Double> chooseSortByPriceAsc() {
        selectSortOption("p.price-ASC");

        List<Double> productPrices = new ArrayList<>();
        for(WebElement elements: prices) {
            String prices = elements.getText();
            String valOfPrices = prices.replace("$", "").trim();
            productPrices.add(Double.parseDouble(valOfPrices));
        }

        return productPrices;
    }

    public List<Double> chooseSortByPriceDesc() {
        selectSortOption("p.price-DESC");

        List<Double> productPrices = new ArrayList<>();
        for(WebElement elements: prices) {
            String prices = elements.getText();
            String valOfPrices = prices.replace("$", "").trim();
            productPrices.add(Double.parseDouble(valOfPrices));
        }

        return productPrices;
    }
}
