package com.airtasker.todo.cukes.pages;

import com.airtasker.todo.cukes.common.Task;
import com.airtasker.todo.cukes.common.TestContext;
import com.airtasker.todo.cukes.common.TodoRestApi;
import com.airtasker.todo.cukes.common.WebBrowserDriver;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertTrue;

public class Homepage {

    @FindBy(css = "div#mainContent>div>h2")
    private WebElement pageTitle;

    @FindBy(css = "div#mainContent>div div.form-group>input#addTaskInput")
    private WebElement whatDoUWant2DoTxtBox;

    @FindBy(css = "div#mainContent>div div.form-group>span#helpBlock")
    private WebElement whatDoUWant2DoHelpBlock;

    @FindBy(css = "div#mainContent>div:nth-child(2)>div:nth-child(1)")
    private WebElement todoItems;

    @FindBy(css = "div#mainContent>div:nth-child(2)>div:nth-child(1)>h3")
    private WebElement todoItemsLbl;

    @FindBy(css = "div#mainContent > div:nth-child(2)>div:nth-child(1)>div")
    private List<WebElement> todoItemsList;

    @FindBy(css = "div#mainContent div#alertContainer>p#alertText")
    private WebElement alertContainer;

    private final TodoRestApi todoRestApi;
    private final WebBrowserDriver webBrowserDriver;
    private final WebDriver driver;
    private String lastAddedTask;

    public Homepage(TestContext testContext) {
        todoRestApi = testContext.getTodoRestApi();
        webBrowserDriver = testContext.getWebBrowserDriver();
        driver = webBrowserDriver.getDriver();
    }

    public void navigateTo() {
        driver.get(webBrowserDriver.getAppUrl());
    }

    public void waitUntilPageLoads() {
        webBrowserDriver.getWait().until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        refresh();
    }

    private void refresh() {
        PageFactory.initElements(driver, this);
    }

    public void verifyPageTitle(String pageTitle) {
        assertThat(pageTitle, is(this.pageTitle.getText()));
    }

    public void verifyTaskTextBoxIsPresent() {
        assertTrue(whatDoUWant2DoTxtBox.isDisplayed() && whatDoUWant2DoTxtBox.isEnabled());
    }

    public void verifyEmptyTodoList() {
        webBrowserDriver.getWait().until(webDriver -> alertContainer.isDisplayed());
        assertTrue(alertContainer.isDisplayed());
        assertThat(alertContainer.getText(), is("All Caught Up\n" +
                "You do not have any todo items"));
    }

    public void addTask(String task) {
        lastAddedTask = task;
        waitUntilPageLoads();
        whatDoUWant2DoTxtBox.sendKeys(task + "\n");
    }

    public void verifyListedTask() {
        refresh();
        webBrowserDriver.getWait().until(webDriver -> todoItems.isDisplayed());
        assertTrue(todoItems.isDisplayed());
        assertTrue(todoItemsLbl.isDisplayed());
        assertThat(todoItemsLbl.getText(), is("Todo Items"));
        assertThat(todoItemsList.size(), is(1));

        verifyListedTask(todoItemsList.get(0));
    }

    private void verifyListedTask(WebElement taskElement) {
        WebElement taskDetails = taskElement.findElement(By.id("taskDetails"));
        WebElement deleteTask = taskElement.findElement(By.id("deleteTask"));

        assertThat(taskDetails.getAttribute("value"), is(lastAddedTask));
        assertThat(deleteTask.getText(), is("X"));
    }

    public void verifyCompletedTask(boolean completed) {
        WebElement taskDetails = todoItemsList.get(0).findElement(By.id("taskDetails"));
        WebElement taskSelect = todoItemsList.get(0).findElement(By.id("taskSelect"));

        assertThat(Boolean.valueOf(taskSelect.getAttribute("value")), is(completed));
        if (completed) {
            assertThat(taskDetails.getAttribute("class"), containsString("todo__done"));
        } else {
            assertThat(taskDetails.getAttribute("class"), not(containsString("todo__done")));
        }
    }

    public void editTask(String task) {
        waitUntilPageLoads();
        webBrowserDriver.getWait().until(webDriver -> todoItems.isDisplayed());

        WebElement taskDetails = todoItemsList.get(0).findElement(By.id("taskDetails"));
        taskDetails.clear();
        taskDetails.sendKeys(task + "\n");
    }

    public void verifyEditedTask(String expectedTask) {
        WebElement taskDetails = todoItemsList.get(0).findElement(By.id("taskDetails"));
        assertThat(taskDetails.getAttribute("value"), is(expectedTask));
    }

    public void completeTask() {
        waitUntilPageLoads();
        webBrowserDriver.getWait().until(webDriver -> todoItems.isDisplayed());

        WebElement taskSelect = todoItemsList.get(0).findElement(By.id("taskSelect"));
        taskSelect.click();
    }

    public void deleteTask() {
        waitUntilPageLoads();
        webBrowserDriver.getWait().until(webDriver -> todoItems.isDisplayed());

        List<Task> allTasks = todoRestApi.getAllTasks();
        assertThat(allTasks, not(empty()));
        assertThat(allTasks.size(), is(1));
        assertThat(allTasks.get(0).getName(), is(todoRestApi.getLastTask().getName()));

        WebElement deleteTask = todoItemsList.get(0).findElement(By.id("deleteTask"));
        deleteTask.click();
    }

    public void verifyTaskDeleted() {
        assertThat(todoRestApi.getAllTasks(), empty());
    }
}
