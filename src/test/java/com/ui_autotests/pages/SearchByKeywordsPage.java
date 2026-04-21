package com.ui_autotests.pages;

import com.ui_autotests.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchByKeywordsPage extends BasePage {
    @FindBy(id = "keyword")
    private WebElement keyword;

    @FindBy(id = "sort")
    private WebElement dropDown;

    @FindBy(xpath = "//option[@value='pd.name-ASC']")
    private WebElement sortByNameAsc;

    @FindBy(css = ".thumbnails.grid.row.list-inline > div")
    private List<WebElement> allProducts;

    public SearchByKeywordsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getValueOfSearching() {
        return keyword.getAttribute("value");
    }

    public ProductPage chooseProduct(int x) {
        dropDown.click();
        sortByNameAsc.click();

        WebElement neededProduct = allProducts.get(x);
        WebElement titleOfProduct = neededProduct.findElement(By.className("prdocutname"));
        titleOfProduct.click();

        return new ProductPage();
    }
}
