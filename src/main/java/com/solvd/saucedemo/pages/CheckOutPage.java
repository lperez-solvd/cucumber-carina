package com.solvd.saucedemo.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
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
    }
}
