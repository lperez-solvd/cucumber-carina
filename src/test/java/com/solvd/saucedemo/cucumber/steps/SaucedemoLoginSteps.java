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

    LoginPage loginPage = null;
    HomePage homePage = null;

    User testUser = null;


    @Given("I am on login page")
    public boolean iAmOnMainPage() {
        loginPage = new LoginPage(getDriver());
        loginPage.open();
        return loginPage.isPageOpened();
    }

    @When("I enter my username for {string}")
    public void iEnterMyUsername(String userId) {
        testUser = UtilsSQL.getUserByIdSQL(Integer.parseInt(userId));
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
        Assert.assertTrue(homePage.isPageOpened() || loginPage.loginHasFailed(), "The submitting wasn't ok");
    }

    @Then("the {string} result should be found")
    public void pageHomeShouldBeOpen(String expected) {
        if (parseBoolean(expected)) {
            Assert.assertTrue(homePage.isPageOpened(), "The home page is not opened");
        } else {
            Assert.assertTrue(loginPage.loginHasFailed(), "The fail message is not present");
        }

    }


}
