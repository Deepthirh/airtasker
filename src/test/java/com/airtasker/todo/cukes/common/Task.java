package com.airtasker.todo.cukes.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

    @JsonProperty("_id")
    private String id;

    private String name;

    private boolean done;

    public Task() {
    }

    public Task(String name) {
        this(name, false);
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }
}
