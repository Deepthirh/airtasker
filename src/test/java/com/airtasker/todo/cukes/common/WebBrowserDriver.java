package com.airtasker.todo.cukes.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebBrowserDriver {

    private WebDriver driver;
    private WebDriverWait wait;
    private String appUrl;

    public WebDriver getDriver() {
        return driver;
    }

    void launchDriver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    void closeDriver() {
        driver.close();
        driver.quit();
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }
}
