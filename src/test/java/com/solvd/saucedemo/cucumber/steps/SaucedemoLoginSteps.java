package com.solvd.saucedemo.cucumber.steps;

import com.solvd.saucedemo.SaucedemoBaseTest;
import com.solvd.saucedemo.models.User;
import com.solvd.saucedemo.pages.HomePage;
import com.solvd.saucedemo.pages.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class SaucedemoLoginSteps extends SaucedemoBaseTest {
    LoginPage loginPage = null;
    HomePage homePage = null;

    User testUser = null;

    @Before
    public void setup() {
        testUser = getUserByIdSQL(1);
        if (testUser == null) {
            throw new RuntimeException("Test user not found in database!");
        }
    }

    @Given("^I am on login page")
    public boolean iAmOnMainPage() {
        loginPage = new LoginPage(getDriver());
        loginPage.open();
        return loginPage.isPageOpened();
    }

    @When("^I enter my username")
    public void iEnterMyUsername() {
        loginPage.enterUserName(testUser.getName());
        System.out.println("TEST USER: " + loginPage.getUserInput().getText());
        Assert.assertEquals(loginPage.getUserInput().getAttribute("value"), testUser.getName(), "The field is not completed");
    }

    @And("^I enter my password")
    public void iEnterMyPassword() {
        loginPage.enterPasswordToInput(testUser.getPassword());
        Assert.assertEquals(loginPage.getPasswordInput().getAttribute("value"), testUser.getPassword(), "The field is not completed");
    }

    @And("^I submit my information")
    public void iSubmitMyInformation() {
        homePage = loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isPageOpened(), "The submitting wasn't ok");
    }

    @Then("^page 'Home' should open")
    public void pageHomeShouldBeOpen() {
        Assert.assertTrue(homePage.isPageOpened(), "The home page is not opened");
    }


}
