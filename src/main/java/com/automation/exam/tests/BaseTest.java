package com.automation.exam.tests;

import com.automation.exam.pages.LandingPage;
import com.automation.exam.utils.Logger;
import com.automation.exam.utils.MyDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.automation.exam.utils.Config.getChrome;


public abstract class BaseTest {

    MyDriver driver;
    private LandingPage landingPage;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {

        Logger.printInfo("Before test suite started");

        driver = new MyDriver(getChrome());
        landingPage = new LandingPage(driver.getDriver());
    }

    @AfterSuite
    public void tearDown() {

        Logger.printInfo("After suite started and disposing resources");

        landingPage.dispose();
    }

    public LandingPage getLandingPage() {

        Logger.printInfo("Landing page instance returned");

        return landingPage;
    }

}
