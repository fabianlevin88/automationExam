package com.automation.exam.pages;

import com.automation.exam.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DetailPage extends BasePage {

    @FindBy(className = "packagePriceTotal")
    private WebElement totalTripPrice;

    @FindBy(className = "priceGuarantee")
    private WebElement priceGuarantee;

    @FindBys({@FindBy( css = ".departure")})
    private List<WebElement> travelingInfo;

    @FindBys({@FindBy( css = ".arrival")})
    private List<WebElement> travelingArrivingInfo;

    @FindBy(xpath = "//*[@id=\"bookButton\"]")
    private WebElement continueBookingBtn;

    private boolean displayed;

    public DetailPage(WebDriver pDriver) {
        super(pDriver);
    }

    @Override
    public Object search() {
        throw new UnsupportedOperationException();
    }

    /**
     * description: Method to verify that the total trip price element is present in the detail page
     * @return boolean
     */
    public Boolean verifyPriceIsPresent() {

        getWait().until(ExpectedConditions.visibilityOf(totalTripPrice));

        Logger.printInfo("Verifying that the flight price is present");

        return doesElementExist(totalTripPrice);
    }

    /**
     * description: Method to verify that the departure information is present in the detail page
     * @return boolean
     */
    public Boolean verifyDepartureInfoIsPresent() {

        Logger.printInfo("Verifying that the flight departure information is displayed");

        return flightDetailsVerify(travelingInfo);
    }

    /**
     * description: Method to verify that the returning information is present in the detail page
     * @return boolean
     */
    public Boolean verifyReturningInfoIsPresent() {

        Logger.printInfo("Verifying that the flight arriving information is displayed");

        return flightDetailsVerify(travelingArrivingInfo);
    }

    public Boolean flightDetailsVerify(List<WebElement> travel) {

        Logger.printInfo("Enetering to flight details verify method to ensure that the flight details are displayed");

        displayed = true;
        int count = 0;

        for(int i = 0; i < travelingArrivingInfo.size(); i++) {

            if (travelingArrivingInfo.get(i).isDisplayed() && !travelingArrivingInfo.get(i).getText().equals("")) {
                Logger.printInfo("Evaluating: " + travelingArrivingInfo.get(i).getText());
                count++;
            }
        }

        if (count != 2) {
            displayed = false;
        }

        return displayed;
    }

    /**
     * description: Method to verify that the is present in the detail page
     * @return boolean
     */
    public Boolean verifyPriceGuarantee() {

        Logger.printInfo("Verifying that the flight price guaranteed message is present");

        Logger.printInfo("Price guarantee message found: " + priceGuarantee.getText());

        return doesElementExist(priceGuarantee);
    }

    public BookingPage continueBooking() {

        continueBookingBtn.click();

        Logger.printInfo("Clicking on the continue booking button");

        return new BookingPage(getDriver());
    }
}
