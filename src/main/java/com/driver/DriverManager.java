package com.driver;

import org.openqa.selenium.WebDriver;


public class DriverManager {

    //Driver objenmizi local bir thread'te oluşturduk.
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {


        driver.manage().window().fullscreen();
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        DriverManager.driver.get().quit();
        driver.remove();
    }

}

