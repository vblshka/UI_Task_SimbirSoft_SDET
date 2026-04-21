package com.ui_autotests.tests;

import com.ui_autotests.core.BaseTest;
import com.ui_autotests.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SearchAndCartTest extends BaseTest {

    @Test
    public void searchByKeywordTest() {
        String keyword = "shirt";

        MainPage mainPage = MainPage.getInstance();
        Assertions.assertEquals(keyword, mainPage.searchByKeyword(keyword)
                .getValueOfSearching());
    }

    @Test
    public void addProductToCart () {
        String keyword = "shirt";
        int productNumber = 1;
        MainPage mainPage = MainPage.getInstance();

        List<Double> results = mainPage.searchByKeyword(keyword)
                .chooseProduct(productNumber)
                .addProductInCart()
                .searchByKeyword(keyword)
                .chooseProduct(++productNumber)
                .addProductInCart()
                .changeQuantityOfProduct();

        Assertions.assertEquals(results.get(0), results.get(1), 0.001);
    }
}
