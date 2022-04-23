package com.utility;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.Arrays;

public class Log {

    private Log() {
    }

    // Initialize Log4j logs
    private static final Logger LOGGER = LogManager.getLogger(Log.class);


    //Info Level Logs
    public static <T> void pass(T message) {
        Allure.step(message.toString(), Status.PASSED);
        LOGGER.info(message);
    }

    //Warn Level Logs
    public static <T> void warning(T message) {
        Allure.step(message.toString(), Status.BROKEN);
        LOGGER.warn(message);
    }


    //Error Level Logs
    public static <T> void fail(T message) {
        fail(message, null);
    }

    public static <T> void fail(T message, Exception e) {
        String stackTrace = "";
        if (e != null)
            stackTrace = Arrays.toString(e.getStackTrace());
        Allure.step(message + stackTrace, Status.FAILED);
        LOGGER.error(message);
        Assert.fail(message.toString(), e);
    }
}
