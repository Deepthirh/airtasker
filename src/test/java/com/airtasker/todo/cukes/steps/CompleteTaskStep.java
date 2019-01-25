package com.airtasker.todo.cukes.steps;

import com.airtasker.todo.cukes.pages.Homepage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CompleteTaskStep {

    private final Homepage homepage;

    public CompleteTaskStep(Homepage homepage) {
        this.homepage = homepage;
    }


    @When("^I tick the task checkbox$")
    public void iTickTheTaskCheckbox() throws Throwable {
        homepage.completeTask();
    }

    @Then("^The task is marked as completed$")
    public void theTaskIsMarkedAsCompleted() throws Throwable {
        homepage.verifyCompletedTask(true);
    }
}
