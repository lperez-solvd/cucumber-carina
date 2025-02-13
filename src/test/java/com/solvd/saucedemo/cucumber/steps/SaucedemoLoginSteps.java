package com.solvd.saucedemo.cucumber.steps;

import com.solvd.saucedemo.SaucedemoBaseTest;
import com.solvd.saucedemo.models.User;
import com.solvd.saucedemo.pages.HomePage;
import com.solvd.saucedemo.pages.LoginPage;
import com.solvd.saucedemo.utils.UtilsSQL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import static java.lang.Boolean.parseBoolean;

public class SaucedemoLoginSteps extends SaucedemoBaseTest {

    @Given("I am on login page")
    public void iAmOnLoginPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
    }

    @When("I login with valid credentials for {string}")
    public void iLoginWithValidCredentials(String userId) {
        LoginPage loginPage = new LoginPage(getDriver());
        User testUser = UtilsSQL.getUserByIdSQL(Integer.parseInt(userId));
        loginPage.enterUserName(testUser.getName());
        loginPage.enterPasswordToInput(testUser.getPassword());
        System.out.println("TEST USER: " + loginPage.getUserInput().getText());
    }

    @Then("The username and password fields should be correctly filled with {string}")
    public void verifyCredentialsFilledCorrectly(String userId) {
        LoginPage loginPage = new LoginPage(getDriver());
        User testUser = UtilsSQL.getUserByIdSQL(Integer.parseInt(userId));
        Assert.assertEquals(loginPage.getUserInput().getAttribute("value"), testUser.getName(), "The field is not completed");
        Assert.assertEquals(loginPage.getPasswordInput().getAttribute("value"), testUser.getPassword(), "The field is not completed");
    }

    @And("I submit my information")
    public void iSubmitMyInformation() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickLoginButton();
    }

    @Then("the {string} result should be found")
    public void pageHomeShouldBeOpen(String expected) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        if (parseBoolean(expected)) {
            Assert.assertTrue(homePage.isPageOpened(), "The home page is not opened");
        } else {
            Assert.assertTrue(loginPage.loginHasFailed(), "The fail message is not present");
        }
    }

}
