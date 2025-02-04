package com.solvd.saucedemo.cucumber.steps;

import com.solvd.saucedemo.SaucedemoBaseTest;
import com.solvd.saucedemo.components.InventoryItem;
import com.solvd.saucedemo.models.Product;
import com.solvd.saucedemo.models.User;
import com.solvd.saucedemo.models.UserOrder;
import com.solvd.saucedemo.pages.*;
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

    User testUser = null;
    UserOrder userOrder = null;

    @Before
    public void setup() {
        testUser = getUserByIdSQL(1);
        userOrder = getOrderByIdSQL(1);
        if (testUser == null) {
            throw new RuntimeException("Test user not found in database!");
        }
        if (userOrder == null) {
            throw new RuntimeException("Order not found in database!");
        }

    }

    @Given("^I am logged in")
    public boolean iAmOnMainPage() {
        loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.enterUserName(testUser.getName());
        loginPage.enterPasswordToInput(testUser.getPassword());
        homePage = loginPage.clickLoginButton();
        return homePage.isPageOpened();
    }

    @When("^I add products to the cart")
    public void iAddProductsToTheCart() {
        List<InventoryItem> items = homePage.getAllItems();

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
