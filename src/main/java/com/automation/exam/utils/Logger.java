package com.automation.exam.utils;

import org.joda.time.DateTime;
import org.testng.Reporter;

public class Logger {
    private static final String DATEPATTERN = "MM-dd-yyyy HH:mm:ss ZZ";
    private static final String LOGTEMPLATE = "[%s %s] %s";

    public static void printInfo(String message) {
        String logMessage = String.format(LOGTEMPLATE, "INFO", DateTime.now().toString(DATEPATTERN), message);
        Reporter.log(logMessage, true);
    }

    public static void printDebug(String message) {
        String logMessage = String.format(LOGTEMPLATE, "DEBUG", DateTime.now().toString(DATEPATTERN), message);
        Reporter.log(logMessage, true);
    }

    public static void printWarning(String message) {
        String logMessage = String.format(LOGTEMPLATE, "WARN", DateTime.now().toString(DATEPATTERN), message);
        Reporter.log(logMessage, true);
    }

    public static void printError(String message) {
        String logMessage = String.format(LOGTEMPLATE, "ERROR", DateTime.now().toString(DATEPATTERN), message);
        Reporter.log(logMessage, true);
    }

}
