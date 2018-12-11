package com.automation.exam.pages;

import com.automation.exam.pages.interfaces.IWidget;
import com.automation.exam.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PackageWidget extends BasePage implements IWidget {

    @FindBy(id = "package-origin-hp-package")
    private WebElement flyingFromInput;

    @FindBy(id = "package-destination-hp-package")
    private WebElement flyingToInput;

    @FindBy(id = "package-departing-hp-package")
    private WebElement departingCalendar;

    @FindBy(id = "package-returning-hp-package")
    private WebElement returningCalendar;

    @FindBy(xpath = "//*[@id=\"package-departing-wrapper-hp-package\"]/div/div/button[2]")
    private WebElement deptRightArrow;

    @FindBy(xpath = "//*[@id=\"package-returning-wrapper-hp-package\"]/div/div/button[2]")
    private WebElement retRightArrow;

    @FindBy(xpath = "//*[@id=\"package-departing-wrapper-hp-package\"]/div/div/div[3]/table/tbody/tr[2]/td[4]/button")
    private WebElement deptDate;

    @FindBy(xpath = "//*[@id=\"package-returning-wrapper-hp-package\"]/div/div/div[2]/table/tbody/tr[4]/td[3]/button")
    private WebElement retDate;

    @FindBy(id = "aria-option-0")
    private WebElement result;

    @FindBy(id = "search-button-hp-package")
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

    @Override
    public void flyingFrom(String cityFrom) {

        super.enterFlyingFrom(flyingFromInput, cityFrom, result);
    }

    @Override
    public void flyingTo(String cityTo) {

        super.enterFlyingFrom(flyingToInput, cityTo, result);
    }

    @Override
    public void departureDate() {

        super.selectTripDate("departing", departingCalendar, deptDate, deptRightArrow);
    }

    @Override
    public void returningDate() {

        super.selectTripDate("returning", returningCalendar, retDate, retRightArrow);
    }

    @Override
    public void selectAdultsNumber(String number) {

    }

    @Override
    public void selectChildrenNumber(String childrenNumber) {

    }
}
