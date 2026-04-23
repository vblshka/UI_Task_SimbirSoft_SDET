package com.ui_autotests.pages;

import com.ui_autotests.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public List<String> chooseSortByNameAsc() {
        dropDown.click();
        sortByNameAsc.click();

        List<String> productNames= new ArrayList<>();
        for(WebElement elements: products) {
            String title = elements.getAttribute("title");
            productNames.add(title);
        }

        return productNames;
    }

    public List<String> chooseSortByNameDesc() {
        dropDown.click();
        sortByNameDesc.click();

        List<String> productNames= new ArrayList<>();
        for(WebElement elements: products) {
            String title = elements.getAttribute("title");
            productNames.add(title);
        }

        return productNames;
    }

    public List<Double> chooseSortByPriceAsc() {
        dropDown.click();
        sortByPriceAsc.click();

        List<Double> productPrices = new ArrayList<>();
        for(WebElement elements: prices) {
            String prices = elements.getText();
            String valOfPrices = prices.replace("$", "").trim();
            productPrices.add(Double.parseDouble(valOfPrices));
        }

        return productPrices;
    }

    public List<Double> chooseSortByPriceDesc() {
        dropDown.click();
        sortByPriceDesc.click();

        List<Double> productPrices = new ArrayList<>();
        for(WebElement elements: prices) {
            String prices = elements.getText();
            String valOfPrices = prices.replace("$", "").trim();
            productPrices.add(Double.parseDouble(valOfPrices));
        }

        return productPrices;
    }
}
