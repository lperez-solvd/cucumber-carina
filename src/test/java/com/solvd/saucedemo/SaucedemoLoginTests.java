package com.solvd.saucedemo;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberBaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/SaucedemoLogin.feature",
        glue = "com.solvd.saucedemo.cucumber.steps")
public class SaucedemoLoginTests extends CucumberBaseTest {
}

