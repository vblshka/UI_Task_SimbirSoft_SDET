package com.ui_autotests.pages;

import com.ui_autotests.core.BasePage;
import com.ui_autotests.utils.RandomUtils;
import com.ui_autotests.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class ProductPage extends BasePage {

    @FindBy(id = "product_quantity")
    WebElement productQuantity;

    @FindBy(xpath = "//ul[@class='productpagecart']")
    WebElement addToCartButton;

    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    private List<WebElement> checkoutSizes() {
        Duration oldTimeout = driver.manage().timeouts().getImplicitWaitTimeout();

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            return WaitUtils.getWaitWithTimeout(5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@class='input-group col-sm-10']//input")));
        } catch (TimeoutException e) {
            return new ArrayList<>();
        } finally {
            driver.manage().timeouts().implicitlyWait(oldTimeout);
        }
    }

    public ShoppingCartPage addProductInCart() {

        List<WebElement> sizes = checkoutSizes();
        if (sizes.size()!=0) {
            sizes.get(RandomUtils.getRandomInt(sizes.size()-1)).click();
        }

        String quantity = String.valueOf(RandomUtils.getRandomInt(9) + 2);

        WaitUtils.getWaitWithTimeout(10).until(ExpectedConditions.visibilityOf(productQuantity));

        productQuantity.clear();

        WaitUtils.getWaitWithTimeout(10).until(ExpectedConditions.elementToBeClickable(productQuantity));

        productQuantity.sendKeys(quantity);

        WaitUtils.getWaitWithTimeout(10).until(ExpectedConditions.elementToBeClickable(addToCartButton));

        addToCartButton.click();

        return new ShoppingCartPage();
    }
}
