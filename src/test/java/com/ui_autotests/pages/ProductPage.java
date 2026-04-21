package com.ui_autotests.pages;

import com.ui_autotests.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@class='input-group col-sm-10']//input")
            ));
        } catch (TimeoutException e) {
            return new ArrayList<>();
        } finally {
            driver.manage().timeouts().implicitlyWait(oldTimeout);
        }
    }

    public ShoppingCartPage addProductInCart() {

        Random random = new Random();

        List<WebElement> sizes = checkoutSizes();
        if (sizes.size()!=0) {
            sizes.get(random.nextInt(sizes.size())).click();
        }

        String quantity = String.valueOf(random.nextInt(9) + 2);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productQuantity));

        productQuantity.clear();

        wait.until(ExpectedConditions.elementToBeClickable(productQuantity));

        productQuantity.sendKeys(quantity);

        System.out.println("quantity: "+quantity+"\n");

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

        addToCartButton.click();

        return new ShoppingCartPage();
    }
}
