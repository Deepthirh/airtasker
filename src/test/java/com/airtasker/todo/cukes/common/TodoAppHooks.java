package com.airtasker.todo.cukes.common;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TodoAppHooks {

    private TestContext testContext;

    public TodoAppHooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void beforeScenario() {
        testContext.getWebBrowserDriver().launchDriver();
        testContext.getTodoRestApi().deleteAllTasks();
    }

    @After
    public void afterScenario() throws Exception {
        testContext.getWebBrowserDriver().closeDriver();
    }
}
