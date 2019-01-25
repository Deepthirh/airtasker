package com.airtasker.todo.cukes.steps;

import com.airtasker.todo.cukes.pages.Homepage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AppHomepageStep {

    private final Homepage homepage;

    public AppHomepageStep(Homepage homepage) {
        this.homepage = homepage;
    }

    @Given("^I launch Todo app in chrome web browser$")
    public void iLaunchTodoAppInChromeWebBrowser() throws Throwable {
        homepage.navigateTo();
    }

    @Given("^The todo list is empty$")
    public void theTodoListIsEmpty() throws Throwable {
    }

    @When("^The app homepage loads$")
    public void the_app_homepage_loads() throws Throwable {
        homepage.waitUntilPageLoads();
    }

    @Then("^I verify the page title as \"([^\"]*)\"$")
    public void i_verify_the_page_title_as(String pageTitle) throws Throwable {
        homepage.verifyPageTitle(pageTitle);
    }

    @Then("^With a text box that allows to enter task$")
    public void with_a_text_box_that_allows_to_enter_task() throws Throwable {
        homepage.verifyTaskTextBoxIsPresent();
    }

    @Then("^Shows the status of an empty todo list$")
    public void shows_the_status_of_an_empty_todo_list() throws Throwable {
        homepage.verifyEmptyTodoList();
    }
}
