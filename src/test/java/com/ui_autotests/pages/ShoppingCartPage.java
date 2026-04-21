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

public class ShoppingCartPage extends BasePage {

    @FindBy(id = "filter_keyword")
    private WebElement inputLine;

    @FindBy(xpath = "(//table[@class='table table-striped table-bordered'])[1]/tbody/tr")
    private List<WebElement> rowsOfTable;

    @FindBy(xpath = "(//div[@class='input-group input-group-sm'])[1]/input")
    private WebElement firstProductQuantity;

    @FindBy(xpath = "(//div[@class='input-group input-group-sm'])[2]/input")
    private WebElement secondProductQuantity;

    @FindBy(xpath = "//button[@title='Update']")
    private WebElement updateCartButton;

    @FindBy(xpath = "(//span[@class='bold '])[1]")
    private WebElement subTotalPrice;

    @FindBy(xpath = "//a[@class='active menu_home']")
    private WebElement homePageButton;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-default']")
    private List<WebElement> deleteButtons;

    @FindBy(xpath = "(//span[@class='label label-orange font14'])[1]")
    private WebElement currency;

    public ShoppingCartPage() {
        PageFactory.initElements(driver, this);
    }

    public SearchByKeywordsPage searchByKeyword(String keyword) {
        inputLine.sendKeys(keyword, Keys.ENTER);
        return new SearchByKeywordsPage();
    }

    public MainPage goToHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(homePageButton));

        homePageButton.click();
        return MainPage.getInstance();
    }

    public List<Double> changeQuantityOfProduct() {

        List<Double> totalCosts = new ArrayList<>();

        WebElement firstRow = rowsOfTable.get(1);
        List<WebElement> firstRowCells = firstRow.findElements(By.tagName("td"));

        if (firstRowCells.size() >= 6) {
            String firstTotalText = firstRowCells.get(5).getText();
            double firstTotal = Double.parseDouble(firstTotalText.replace(currency.getText(), "").trim());
            totalCosts.add(firstTotal);
        }

        WebElement secondRow = rowsOfTable.get(2);
        List<WebElement> secondRowCells = secondRow.findElements(By.tagName("td"));

        if (secondRowCells.size() >= 6) {
            String secondTotalText = secondRowCells.get(5).getText();
            double secondTotal = Double.parseDouble(secondTotalText.replace(currency.getText(), "").trim());
            totalCosts.add(secondTotal);
        }

        String firstUnitCostText = firstRowCells.get(3).getText();
        double firstUnitCost = Double.parseDouble(firstUnitCostText.replace(currency.getText(), "").trim());

        String secondUnitCostText = secondRowCells.get(3).getText();
        double secondUnitCost = Double.parseDouble(secondUnitCostText.replace(currency.getText(), "").trim());

        if (totalCosts.get(0) < totalCosts.get(1)) {
            int currentFirstProductQuantity
                    = Integer.parseInt(firstProductQuantity.getAttribute("value"));

            firstProductQuantity.clear();
            firstProductQuantity.sendKeys(
                    Integer.toString(currentFirstProductQuantity * 2));

            totalCosts.remove(0);
            totalCosts.add(0, (double) (currentFirstProductQuantity * 2)
                    * firstUnitCost);
        }
        else {
            int currentSecondProductQuantity
                    = Integer.parseInt(secondProductQuantity.getAttribute("value"));

            secondProductQuantity.clear();
            secondProductQuantity.sendKeys(
                    Integer.toString(currentSecondProductQuantity * 2));

            totalCosts.remove(1);
            totalCosts.add(1, (double) (currentSecondProductQuantity * 2)
                    * secondUnitCost);
        }

        String oldTotalPrice = subTotalPrice.getText();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(updateCartButton));

        updateCartButton.click();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(subTotalPrice, oldTotalPrice)));

        List<Double> results = new ArrayList<>();

        results.add(totalCosts.get(0) + totalCosts.get(1));         //expected result

        results.add(Double.parseDouble(subTotalPrice.getText()
                .replace(currency.getText(), "").trim()));         //current result

        return results;
    }

    public List<Double> deleteEvenProducts() {
        WebElement firstRow = rowsOfTable.get(1);
        List<WebElement> firstRowCells = firstRow.findElements(By.tagName("td"));

        double firstProductQuantity = Double.parseDouble(firstRowCells.get(4)
                .findElement(By.tagName("input"))
                .getAttribute("value"));

        double firstProductUnitPrice = Double.parseDouble(firstRowCells.get(3).getText()
                .replace(currency.getText(),"").trim());

        WebElement thirdRow = rowsOfTable.get(3);
        List<WebElement> thirdRowCells = thirdRow.findElements(By.tagName("td"));

        double thirdProductQuantity = Double.parseDouble(thirdRowCells.get(4)
                .findElement(By.tagName("input"))
                .getAttribute("value"));

        double thirdProductUnitPrice = Double.parseDouble(thirdRowCells.get(3).getText()
                .replace(currency.getText(),"").trim());

        WebElement fifthRow = rowsOfTable.get(5);
        List<WebElement> fifthRowCells = fifthRow.findElements(By.tagName("td"));

        double fifthProductQuantity = Double.parseDouble(fifthRowCells.get(4)
                .findElement(By.tagName("input"))
                .getAttribute("value"));

        double fifthProductUnitPrice = Double.parseDouble(fifthRowCells.get(3).getText()
                .replace(currency.getText(),"").trim());

        List<String> evenDeleteButtonsHref = new ArrayList<>();

        for (int i = 0; i < deleteButtons.size(); i++) {
            if (i % 2 == 1) {
                evenDeleteButtonsHref.add(deleteButtons.get(i).getAttribute("href"));
            }
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < deleteButtons.size(); i++) {
            if (deleteButtons.get(i).getAttribute("href").equals(evenDeleteButtonsHref.get(0))) {
                wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(i)));
                deleteButtons.get(i).click();
                evenDeleteButtonsHref.remove(0);
            }
        }

        List<Double> results = new ArrayList<>();

        results.add(firstProductUnitPrice * firstProductQuantity
                + thirdProductUnitPrice * thirdProductQuantity
                + fifthProductUnitPrice * fifthProductQuantity);    //expected result

        results.add(Double.parseDouble(subTotalPrice.getText()
                .replace(currency.getText(), "")
                .replace(",", "").trim()));        //current result

        System.out.println(results);
        return results;
    }
}
