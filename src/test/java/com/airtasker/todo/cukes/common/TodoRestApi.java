package com.airtasker.todo.cukes.common;

import com.jayway.restassured.RestAssured;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TodoRestApi {

    private static final String APPLICATION_JSON = "application/json";
    private Task lastTask;

    void setApiUrl(String apiUrl) {
        RestAssured.baseURI = apiUrl;
    }

    void setApiPath(String apiPath) {
        RestAssured.basePath = apiPath;
    }

    public void addTask(Task task) {
        lastTask = given().contentType(APPLICATION_JSON).body(task)
                .when().post("/add")
                .then().statusCode(200)
                .extract().as(Task.class);
    }

    public Task updateTask(Task task) {
        return given().contentType(APPLICATION_JSON).pathParam("taskId", task.getId())
                .when().post("/update/{taskId}", task)
                .then().statusCode(200)
                .extract().as(Task.class);
    }

    public void deleteTask(Task task) {
        String message = given().contentType(APPLICATION_JSON).pathParam("taskId", task.getId())
                .when().get("/delete/{taskId}")
                .then().statusCode(200)
                .extract().as(String.class);

        assertThat(message, is("Successfully removed"));
    }

    public List<Task> getAllTasks() {
        return Arrays.asList(given().contentType(APPLICATION_JSON)
                .when().get("/all")
                .as(Task[].class));
    }

    public void deleteAllTasks() {
        getAllTasks().forEach(this::deleteTask);
    }

    public Task getLastTask() {
        return lastTask;
    }
}
