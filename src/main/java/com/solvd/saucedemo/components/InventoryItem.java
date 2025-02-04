package com.solvd.saucedemo.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class InventoryItem extends AbstractUIObject {

    @FindBy(xpath = ".//div[@data-test = 'inventory-item-name']")
    ExtendedWebElement itemName;

    @FindBy(xpath = ".//div[@data-test = 'inventory-item-price']")
    ExtendedWebElement itemPrice;

    @FindBy(xpath = ".//div[@class = 'pricebar']//button")
    ExtendedWebElement addItemButton;


    public InventoryItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getItemName() {
        return itemName.getText();
    }

    public double getItemPrice() {
        return parseTextToDouble(itemPrice.getText());
    }

    public void clickAddItemButton() {
        addItemButton.click();
    }

    // Helper

    private double parseTextToDouble(String text) {
        return Double.parseDouble(text.replace("$", ""));
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "itemName=" + getItemName() +
                ", itemPrice=" + getItemPrice() +
                '}';
    }
}
