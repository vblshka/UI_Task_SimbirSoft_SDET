package com.ui_autotests.pages;

import com.ui_autotests.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApparelAccessoriesPage extends BasePage {

    @FindBy(xpath = "//ul[@class='thumbnails row']/li/a[contains(@href, 'path=68_70')]")
    private WebElement tshirtsButton;

    public ApparelAccessoriesPage() {
        PageFactory.initElements(driver, this);
    }

    public TshirtsPage openTshirtsPage() {
        tshirtsButton.click();
        return new TshirtsPage();
    }
}
