package com.solvd.carina.demo.cucumber.steps;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberRunner;
import com.solvd.carina.demo.gui.components.NewsItem;
import com.solvd.carina.demo.gui.pages.HomePage;
import com.solvd.carina.demo.gui.pages.NewsPage;
import com.solvd.carina.demo.gui.pages.SearchResultsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import java.util.List;

public class GSMArenaSearchSteps extends CucumberRunner {

    HomePage homePage = null;
    SearchResultsPage searchResultsPage = null;
    String searchText = "Lucas";

    @Given("^I am on the main page")
    public boolean iAmOnMainPage() {
        homePage = new HomePage(getDriver());
        homePage.open();
        return homePage.isPageOpened();
    }

    @When("^I click 'Search' input and search$")
    public void iOpenSearchPage() {
        searchResultsPage = homePage.searchSomething(searchText);
    }

    @Then("^page 'Search' should be open$")
    public void pageSearchShouldBeOpen() {
        Assert.assertTrue(searchResultsPage.isPageOpened(), "Page is not opened!");
    }

    @And("^page 'Search' should contains search text$")
    public void pageSearchShouldContainSearchedText() {

        String searchTitle = searchResultsPage.getSearchPageTitle();
        Assert.assertTrue(searchTitle.contains(searchText), "The search text is not displayed in the Search page title");

    }

}
