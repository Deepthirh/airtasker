package com.airtasker.todo;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/com/airtasker/todo/cukes/features/", plugin = { "pretty", "html:target/htmlreports" })
public class TodoAppTest {

    @BeforeClass
    public static void setupDrivers() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver");
    }
}
