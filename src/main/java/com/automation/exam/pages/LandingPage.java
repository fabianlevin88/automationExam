package com.automation.exam.pages;

import com.automation.exam.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.automation.exam.utils.Config.getUrl;

public class LandingPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"tab-flight-tab-hp\"]")
    private WebElement flightstTab;

    @FindBy(className = "flex-card")
    private WebElement flex;

    @FindBy(id = "tab-flight-tab-hp")
    private WebElement flightsBtn;

    @FindBy(id = "tab-hotel-tab-hp")
    private WebElement hotelsBtn;

    @FindBy(xpath = "//*[@id=\"tab-package-tab-hp\"]")
    private WebElement packageBtn;

    public LandingPage(WebDriver pDriver) {
        super(pDriver);
        pDriver.get(getUrl());
    }

    @Override
    public Object search() {
        throw new UnsupportedOperationException();
    }

    public FlightWidget openFlightsTab() {

        getWait().until(ExpectedConditions.visibilityOf(flex));

        Logger.printInfo("Waiting for element to be clickable and switch to flights tab");

        if (flightstTab.isDisplayed()) {
            flightstTab.click();
        } else {
            flightsBtn.click();
        }

        return new FlightWidget(getDriver());
    }

    public HotelWidget openHotelsTab() {
        hotelsBtn.click();
        return new HotelWidget(getDriver());
    }

    public PackageWidget openPackageTab() {
        packageBtn.click();
        return new PackageWidget(getDriver());
    }
}
