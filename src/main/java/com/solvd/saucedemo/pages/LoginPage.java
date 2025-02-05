package com.solvd.saucedemo.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(id = "user-name")
    ExtendedWebElement userInput;

    @FindBy(id = "password")
    ExtendedWebElement passwordInput;

    @FindBy(id = "login-button")
    ExtendedWebElement loginButton;

    @FindBy(xpath = "//h3[@data-test = 'error']")
    ExtendedWebElement failMessage;

    public ExtendedWebElement getUserInput() {
        return userInput;
    }

    public void setUserInput(ExtendedWebElement userInput) {
        this.userInput = userInput;
    }

    public ExtendedWebElement getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(ExtendedWebElement passwordInput) {
        this.passwordInput = passwordInput;
    }

    public ExtendedWebElement getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(ExtendedWebElement loginButton) {
        this.loginButton = loginButton;
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUserName(String username) {
        enterTextToInput(userInput, username);
    }

    public void enterPasswordToInput(String password) {
        enterTextToInput(passwordInput, password);
    }

    public HomePage clickLoginButton() {
        loginButton.click();
        return new HomePage(driver);
    }

    public boolean loginHasFailed() {
        return failMessage.isElementPresent();
    }

    private void enterTextToInput(ExtendedWebElement input, String text) {
        input.type(text);
    }
}
