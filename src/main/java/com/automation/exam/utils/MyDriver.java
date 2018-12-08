package com.automation.exam.utils;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class MyDriver {

    private WebDriver driver;

    public MyDriver(String browser) {

        switch (browser) {
            case "remoteFirefox":
                try {
                    FirefoxDriverManager.getInstance().setup();
                    driver = new RemoteWebDriver(new URL("http://10.0.75.1:4444/wd/hub"), DesiredCapabilities.firefox());
                    break;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            case "remoteChrome":
                try {
                    ChromeDriverManager.getInstance().setup();
                    driver = new RemoteWebDriver(new URL("http://10.0.75.1:4444/wd/hub"), DesiredCapabilities.chrome());
                    break;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            case "firefox":
                FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver();
                driver.manage().window().fullscreen();
                break;
            case "chrome":
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                driver.manage().window().fullscreen();
                break;
            default:
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
