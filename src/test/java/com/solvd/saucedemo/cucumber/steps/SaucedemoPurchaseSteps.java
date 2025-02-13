package com.solvd.saucedemo.cucumber.steps;

import com.solvd.saucedemo.SaucedemoBaseTest;
import com.solvd.saucedemo.models.Product;
import com.solvd.saucedemo.models.User;
import com.solvd.saucedemo.models.UserOrder;
import com.solvd.saucedemo.pages.*;
import com.solvd.saucedemo.utils.UtilsSQL;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.List;

public class SaucedemoPurchaseSteps extends SaucedemoBaseTest {


    @Given("I am logged in with {string}")
    public void iAmLoggedIn(String userID) {
        User testUser = UtilsSQL.getUserByIdSQL(Integer.parseInt(userID));
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.enterUserName(testUser.getName());
        loginPage.enterPasswordToInput(testUser.getPassword());
        loginPage.clickLoginButton();

    }

    @Then("I should be on the homepage")
    public void iShouldBeOnTheHomepage() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isPageOpened(), "Login failed, home page not opened");
    }

    @When("I add products to the cart from {string}")
    public void iAddProductsToTheCart(String orderID) {
        UserOrder userOrder = UtilsSQL.getOrderByIdSQL(Integer.parseInt(orderID));

        HomePage homePage = new HomePage(getDriver());
        List<Product> products = userOrder.getProducts();
        for (Product product : products) {
            homePage.findAndAddItemByName(product.getName());
        }

    }

    @Then("Products should be on cart for {string}")
    public void productsShouldBeOnCart(String orderID) {
        UserOrder userOrder = UtilsSQL.getOrderByIdSQL(Integer.parseInt(orderID));
        HomePage homePage = new HomePage(getDriver());
        List<Product> products = userOrder.getProducts();
        CartPage cartPage = homePage.clickONCartButton();
        Assert.assertEquals(cartPage.getAllCartItems().size(), products.size(), "The added elements are not the expected");
    }

    @And("I go to checkout")
    public void iGoToCheckout() {
        CartPage cartPage = new CartPage(getDriver());
        CheckOutPage checkOutPage = cartPage.clickCheckOutButton();

        Assert.assertTrue(checkOutPage.isPageOpened(), "Checkout page is not correctly opened");
    }

    @And("I submit my zipping information")
    public void iSubmitMyZippingInformation() {
        CheckOutPage checkOutPage = new CheckOutPage(getDriver());
        checkOutPage.completeAllFields();

        SuccessCheckOutPage successCheckOutPage = checkOutPage.clickContinueButton();
        Assert.assertTrue(successCheckOutPage.isPageOpened(), "Checkout page is not correctly opened");
    }

    @And("I confirm my purchase for {string}")
    public void iConfirmMyPurchase(String orderID) {
        SuccessCheckOutPage successCheckOutPage = new SuccessCheckOutPage(getDriver());

        UserOrder userOrder = UtilsSQL.getOrderByIdSQL(Integer.parseInt(orderID));
        List<Product> products = userOrder.getProducts();
        BigDecimal total = BigDecimal.ZERO;

        for (Product product : products) {
            total = total.add(product.getPrice());
        }

        Assert.assertEquals(successCheckOutPage.getTotal(), total, "The total is not the expected");
    }

    @Then("page successful should open")
    public void pageSuccessfulShouldOpen() {
        SuccessCheckOutPage successCheckOutPage = new SuccessCheckOutPage(getDriver());
        FinishPage finishPage = successCheckOutPage.clickFinishButton();

        Assert.assertTrue(finishPage.isPageOpened(), "The successful page is not opened");
    }
}
