package com.solvd.carina.demo.gui.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends AbstractPage {

    @FindBy (css = "h1.article-info-name")
    ExtendedWebElement searchTitle;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(searchTitle);

    }

    public String getSearchPageTitle () {
        return searchTitle.getText();
    }
}
