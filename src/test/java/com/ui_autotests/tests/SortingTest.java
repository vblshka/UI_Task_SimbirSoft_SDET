package com.ui_autotests.tests;
import com.ui_autotests.core.BaseTest;
import com.ui_autotests.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingTest extends BaseTest {

    @Test
    public void sortByNameAscTest() {
        List<String> actualProductNames = new ArrayList<>();
        actualProductNames.addAll(mainPage.openApparelAccessoriesPage()
                .openTshirtsPage()
                .chooseSortByNameAsc());

        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames);

        Assertions.assertEquals(expectedProductNames, actualProductNames, "Product names differ");
    }

    @Test
    public void sortByNameDescTest() {
        List<String> actualProductNames = new ArrayList<>();
        actualProductNames.addAll(mainPage.openApparelAccessoriesPage()
                .openTshirtsPage()
                .chooseSortByNameDesc());

        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames, Collections.reverseOrder());

        Assertions.assertEquals(expectedProductNames, actualProductNames, "Product names differ");
    }

    @Test
    public void sortByPriceAscTest() {
        List<Double> actualProductPrices = new ArrayList<>();
        actualProductPrices.addAll(mainPage.openApparelAccessoriesPage()
                .openTshirtsPage()
                .chooseSortByPriceAsc());

        List<Double> expectedProductPrices = new ArrayList<>(actualProductPrices);
        Collections.sort(expectedProductPrices);

        Assertions.assertEquals(expectedProductPrices, actualProductPrices, "Product prices differ");
    }

    @Test
    public void sortByPriceDescTest() {
        List<Double> actualProductPrices = new ArrayList<>();
        actualProductPrices.addAll(mainPage.openApparelAccessoriesPage()
                .openTshirtsPage()
                .chooseSortByPriceDesc());

        List<Double> expectedProductPrices = new ArrayList<>(actualProductPrices);
        Collections.sort(expectedProductPrices, Collections.reverseOrder());

        Assertions.assertEquals(expectedProductPrices, actualProductPrices, "Product prices differ");
    }

}
