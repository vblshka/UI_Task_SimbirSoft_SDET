package com.ui_autotests.tests;

import com.ui_autotests.core.BaseTest;
import com.ui_autotests.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void addProductsToCart() {
        mainPage.initUniqueProductsOnMainPage();
        int counter = 0;

        while (counter < 4) {
            mainPage.chooseRandomProductsFromMainPage()
                    .addProductInCart()
                    .goToHomePage();
            counter++;
        }

        List<Double> results = mainPage.chooseRandomProductsFromMainPage()
                .addProductInCart()
                .deleteEvenProducts();

        Assertions.assertEquals(results.get(0), results.get(1), "results are different");
    }
}
