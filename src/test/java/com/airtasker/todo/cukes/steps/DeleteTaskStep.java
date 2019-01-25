package com.airtasker.todo.cukes.steps;

import com.airtasker.todo.cukes.pages.Homepage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeleteTaskStep {

    private final Homepage homepage;

    public DeleteTaskStep(Homepage homepage) {
        this.homepage = homepage;
    }

    @When("^I click on X beside the task$")
    public void iClickOnXBesideTheTask() throws Throwable {
        homepage.navigateTo();
        homepage.deleteTask();
    }

    @Then("^The task is deleted$")
    public void theTaskIsDeleted() throws Throwable {
        homepage.verifyTaskDeleted();
        homepage.verifyEmptyTodoList();
    }
}
