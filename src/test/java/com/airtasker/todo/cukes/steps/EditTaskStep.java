package com.airtasker.todo.cukes.steps;

import com.airtasker.todo.cukes.pages.Homepage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EditTaskStep {

    private final Homepage homepage;

    public EditTaskStep(Homepage homepage) {
        this.homepage = homepage;
    }

    @When("^I edit the task to \"([^\"]*)\"$")
    public void i_edit_the_task_to(String task) throws Throwable {
        homepage.navigateTo();
        homepage.editTask(task);
    }

    @Then("^The task values is changed to \"([^\"]*)\"$")
    public void the_task_values_is_changed_to(String expectedTask) throws Throwable {
        homepage.verifyEditedTask(expectedTask);
    }
}
