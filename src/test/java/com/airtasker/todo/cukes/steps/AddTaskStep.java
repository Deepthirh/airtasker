package com.airtasker.todo.cukes.steps;

import com.airtasker.todo.cukes.pages.Homepage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddTaskStep {

    private final Homepage homepage;

    public AddTaskStep(Homepage homepage) {
        this.homepage = homepage;
    }

    @When("^I enter \"([^\"]*)\" task$")
    public void i_enter_task(String task) throws Throwable {
        homepage.addTask(task);
    }

    @Then("^The task is listed under Todo Items$")
    public void theTaskIsListedUnderTodoItems() throws Throwable {
        homepage.verifyListedTask();
    }

    @Then("^Its status is non-completed$")
    public void its_status_is_non_completed() throws Throwable {
        homepage.verifyCompletedTask(false);
    }

    @Then("^The task is not listed under Todo Items$")
    public void theTaskIsNotListedUnderTodoItems() throws Throwable {
        homepage.verifyEmptyTodoList();
    }
}
