package com.airtasker.todo.cukes.steps;

import com.airtasker.todo.cukes.common.Task;
import com.airtasker.todo.cukes.common.TestContext;
import com.airtasker.todo.cukes.pages.Homepage;
import cucumber.api.java.en.Given;

public class AppCommonStep {

    private final TestContext testContext;
    private final Homepage homepage;

    public AppCommonStep(TestContext testContext, Homepage homepage) {
        this.testContext = testContext;
        this.homepage = homepage;
    }

    @Given("^I am on todo app home page$")
    public void i_am_on_todo_app_home_page() throws Throwable {
        homepage.navigateTo();
        homepage.waitUntilPageLoads();
    }

    @Given("^I have \"([^\"]*)\" task in the list$")
    public void i_have_task_in_the_list(String task) throws Throwable {
        testContext.getTodoRestApi().addTask(new Task(task));
    }
}
