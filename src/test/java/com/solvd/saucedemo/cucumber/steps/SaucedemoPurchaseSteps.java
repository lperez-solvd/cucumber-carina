package com.solvd.saucedemo.cucumber.steps;

import com.solvd.saucedemo.SaucedemoBaseTest;
import com.solvd.saucedemo.components.InventoryItem;
import com.solvd.saucedemo.models.Product;
import com.solvd.saucedemo.models.User;
import com.solvd.saucedemo.models.UserOrder;
import com.solvd.saucedemo.pages.*;
import com.solvd.saucedemo.utils.UtilsSQL;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


import java.math.BigDecimal;
import java.util.List;

public class SaucedemoPurchaseSteps extends SaucedemoBaseTest {

    LoginPage loginPage = null;
    HomePage homePage = null;
    CartPage cartPage = null;
    CheckOutPage checkOutPage = null;
    SuccessCheckOutPage successCheckOutPage = null;

    UserOrder userOrder = null;


    @Given("I am logged in with {string}")
    public void iAmLoggedIn(String userID) {
        User testUser = UtilsSQL.getUserByIdSQL(Integer.parseInt(userID));
        loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.enterUserName(testUser.getName());
        loginPage.enterPasswordToInput(testUser.getPassword());
        homePage = loginPage.clickLoginButton();
    }

    @Then("I should be on the homepage")
    public void iShouldBeOnTheHomepage() {
        Assert.assertTrue(homePage.isPageOpened(), "Login failed, home page not opened");
    }

    @When("I add products to the cart from {string}")
    public void iAddProductsToTheCart(String orderID) {

        userOrder = UtilsSQL.getOrderByIdSQL(Integer.parseInt(orderID));

        List<Product> products = userOrder.getProducts();
        for (Product product : products) {
            homePage.findAndAddItemByName(product.getName());
        }

        cartPage = homePage.clickONCartButton();

        Assert.assertEquals(cartPage.getAllCartItems().size(), products.size(), "The added elements are not the expected");
    }

    @And("^I go to checkout")
    public void iGoToCheckout() {
        checkOutPage = cartPage.clickCheckOutButton();
        Assert.assertTrue(checkOutPage.isPageOpened(), "Checkout page is not correctly opened");
    }

    @And("^I submit my zipping information")
    public void iSubmitMyZippingInformation() {
        checkOutPage.completeAllFields();
        successCheckOutPage = checkOutPage.clickContinueButton();
        Assert.assertTrue(successCheckOutPage.isPageOpened(), "Checkout page is not correctly opened");
    }

    @And("^I confirm my purchase")
    public void iConfirmMyPurchase() {
        List<Product> products = userOrder.getProducts();
        BigDecimal total = BigDecimal.valueOf(0);

        for (Product product : products) {
            total = total.add(product.getPrice());
        }

        Assert.assertEquals(successCheckOutPage.getTotal(), total, "The total is not the expected");

    }

    @Then("^page successful should open")
    public void pageSuccessfulShouldOpen() {
        FinishPage finishPage = successCheckOutPage.clickFinishButton();
        Assert.assertTrue(finishPage.isPageOpened(), "The successful page is not opened");
    }

}
