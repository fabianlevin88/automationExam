package com.automation.exam.utils;

public class Config {

    private static final String url= "https://www.travelocity.com/";
    private static final String firefox = "firefox";
    private static final String chrome = "chrome";
    private static final String remoteChrome = "remoteChrome";
    private static final String remoteFirefox = "remoteFirefox";

    public static String getUrl() {
        return url;
    }

    public static String getFirefox() {
        return firefox;
    }

    public static String getChrome() {
        return chrome;
    }

    public static String getRemoteChrome() {
        return remoteChrome;
    }

    public static String getRemoteFirefox() {
        return remoteFirefox;
    }

}
