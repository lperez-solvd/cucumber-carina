package com.solvd.saucedemo;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberBaseTest;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features/SaucedemoPurchase.feature",
        glue = "com.solvd.saucedemo.cucumber.steps",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class SauceDemoPurchaseTest extends CucumberBaseTest {

}
