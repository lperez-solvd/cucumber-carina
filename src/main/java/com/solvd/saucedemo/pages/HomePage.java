package com.solvd.saucedemo.pages;

import com.solvd.saucedemo.components.InventoryItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//div[@data-test = 'inventory-item']")
    List<InventoryItem> allItems;

    @FindBy(xpath = "//*[@data-test = 'shopping-cart-link']")
    ExtendedWebElement cartButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickONCartButton() {
        cartButton.click();
    }


}
