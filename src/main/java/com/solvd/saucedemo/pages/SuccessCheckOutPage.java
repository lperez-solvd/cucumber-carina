package com.solvd.saucedemo.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class SuccessCheckOutPage extends AbstractPage {

    @FindBy(xpath = "//div[@data-test = 'subtotal-label']")
    ExtendedWebElement totalPurchase;

    @FindBy(id = "finish")
    ExtendedWebElement finishButton;

    public SuccessCheckOutPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
        setPageURL("/checkout-step-two.html");
    }

    public BigDecimal getTotal() {
        String text = totalPurchase.getText(); // Example: "Item total: $45.98"
        String numericValue = text.replaceAll("[^0-9.]", ""); // Remove everything except numbers and decimal point
        return new BigDecimal(numericValue);
    }

    public FinishPage clickFinishButton() {
        finishButton.click();
        return new FinishPage(driver);
    }
}
