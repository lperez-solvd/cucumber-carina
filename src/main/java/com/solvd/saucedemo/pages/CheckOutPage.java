package com.solvd.saucedemo.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage extends AbstractPage {

    @FindBy(id = "first-name")
    ExtendedWebElement nameInput;

    @FindBy(id = "last-name")
    ExtendedWebElement lastNameInput;

    @FindBy(id = "postal-code")
    ExtendedWebElement zipCodeInput;

    @FindBy(id = "continue")
    ExtendedWebElement continueButton;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
        setPageURL("/checkout-step-one.html");
    }

    public void addFirstName(String name) {
        nameInput.type(name);
    }

    public void addLastName(String lastName) {
        lastNameInput.type(lastName);
    }

    public void addZipCode(String zipcode) {
        zipCodeInput.type(zipcode);
    }

    public void completeAllFields() {
        addFirstName("NAME");
        addLastName("LASTNAME");
        addZipCode("1990");
    }

    public SuccessCheckOutPage clickContinueButton() {
        continueButton.click();
        return new SuccessCheckOutPage(driver);
    }
}
