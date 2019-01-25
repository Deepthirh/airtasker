package com.airtasker.todo.cukes.common;

import java.io.IOException;
import java.util.Properties;

public class TestContext {

    private WebBrowserDriver webBrowserDriver;
    private TodoRestApi todoRestApi;
    private Properties properties;

    public TestContext(WebBrowserDriver webBrowserDriver, TodoRestApi todoRestApi) throws IOException {
        this.webBrowserDriver = webBrowserDriver;
        this.todoRestApi = todoRestApi;
        this.properties = new Properties();

        properties.load(getClass().getClassLoader().getResourceAsStream("com/airtasker/todo/config.properties"));

        String appUrl = properties.getProperty("app.url");
        webBrowserDriver.setAppUrl(appUrl);
        todoRestApi.setApiUrl(appUrl);
        todoRestApi.setApiPath(properties.getProperty("rest.api.path"));
    }

    public WebBrowserDriver getWebBrowserDriver() {
        return webBrowserDriver;
    }

    public TodoRestApi getTodoRestApi() {
        return todoRestApi;
    }
}
