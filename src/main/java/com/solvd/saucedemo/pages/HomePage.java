package com.solvd.saucedemo.pages;

import com.solvd.saucedemo.components.InventoryItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
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
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
        setPageURL("/inventory.html");
    }

    public List<InventoryItem> getAllItems() {
        return allItems;
    }

    public CartPage clickONCartButton() {
        cartButton.click();
        return new CartPage(driver);
    }

    public void findAndAddItemByName(String name) {
        InventoryItem itemToClick = allItems.stream()
                .filter(item -> item.getItemName().equals(name))
                .findFirst()
                .orElse(null); // Returns null if no match is found

        assert itemToClick != null;
        itemToClick.clickAddItemButton();
    }


}
