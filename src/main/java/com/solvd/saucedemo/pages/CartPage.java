package com.solvd.saucedemo.pages;

import com.solvd.saucedemo.components.InventoryItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(xpath = "//div[@data-test = 'inventory-item']")
    List<InventoryItem> addedItems;

    @FindBy(id = "checkout")
    ExtendedWebElement checkOutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage clickCheckOutButton() {
        checkOutButton.click();
        return new CheckOutPage(driver);
    }

    public List<InventoryItem> getAllCartItems () {
        return addedItems;
    }
}
