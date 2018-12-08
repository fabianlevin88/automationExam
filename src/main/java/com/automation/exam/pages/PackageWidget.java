package com.automation.exam.pages;

import com.automation.exam.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PackageWidget extends BasePage {

    @FindBy(id = "package-origin-hp-package")
    private WebElement flyingFromInput;

    @FindBy(id = "package-destination-hp-package")
    private WebElement flyingToInput;

    @FindBy(id = "flight-departing-hp-flight")
    private WebElement departingCalendar;

    @FindBy(id = "flight-returning-hp-flight")
    private WebElement returningCalendar;

    @FindBy(id = "aria-option-0")
    private WebElement result;

    @FindBy(css = ".btn-primary.btn-action.gcw-submit")
    private WebElement searchBtn;

    public PackageWidget(WebDriver pDriver) {
        super(pDriver);
    }

    @Override
    public Object search() {

        Logger.printInfo("Search method invoked. Waiting on search button to be clickable");

        getWait().until(ExpectedConditions.elementToBeClickable(searchBtn));

        searchBtn.click();

        Logger.printWarning("Closing pop ups and other windows");

        closePopUpWindows();

        Logger.printInfo("returning an instance of FlightSearch page");

        return null;
    }

    public void enterFlyingFrom(String cityFrom) {

        Logger.printInfo("Entering the city: " + cityFrom + " as origin");

        enterCity(flyingFromInput, cityFrom, result);

        Logger.printInfo("Calling the method to validate the origin city ");

        validateCityInput(flyingFromInput, cityFrom, result);
    }

    public void enterFlyingTo(String cityTo) {

        Logger.printInfo("Entering the city: " + cityTo + " as destination");

        enterCity(flyingToInput, cityTo, result);

        Logger.printInfo("Calling the method to validate the destination city ");

        validateCityInput(flyingToInput, cityTo, result);
    }
}
