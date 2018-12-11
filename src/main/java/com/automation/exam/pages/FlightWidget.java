package com.automation.exam.pages;

import com.automation.exam.pages.interfaces.IWidget;
import com.automation.exam.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightWidget extends BasePage implements IWidget {

    @FindBy(id = "flight-type-roundtrip-hp-flight")
    private WebElement typeOfTripSelect;

    @FindBy(css = ".col.slim-lob-type-drop-down-label")
    private WebElement newTypeOfTrip;

    @FindBy(id = "flight-type-roundtrip-label-hp-flight")
    private WebElement newRoundTripLabel;

    @FindBy(id = "flight-type-one-way-label-hp-flight")
    private WebElement newOneWayLabel;

    @FindBy(id = "flight-type-multi-dest-label-hp-flight")
    private WebElement newMultiCityLabel;

    @FindBy(id = "flight-type-roundtrip-label-hp-flight")
    private WebElement roundTripBtn;

    @FindBy(id = "flight-type-one-way-label-hp-flight")
    private WebElement oneWayBtn;

    @FindBy(id = "flight-type-multi-dest-label-hp-flight")
    private WebElement multiCityBtn;

    @FindBy(id = "flight-origin-hp-flight")
    private WebElement flyingFromInput;

    @FindBy(id = "flight-destination-hp-flight")
    private WebElement flyingToInput;

    @FindBy(id = "flight-departing-hp-flight")
    private WebElement departingCalendar;

    @FindBy(id = "flight-returning-hp-flight")
    private WebElement returningCalendar;

    @FindBy(xpath = "//*[@id=\"gcw-flights-form-hp-flight\"]/div[3]/div[6]/div/ul/li/button | //*[@id=\"flight-adults-hp-flight\"]")
    private WebElement adultsDropdown;

    @FindBy(xpath = "//*[@id=\"gcw-flights-form-hp-flight\"]/div[3]/div[6]/div/ul/li/button")
    private WebElement newTravelersDropdown;

    @FindBy(css = ".uitk-step-input-button.uitk-step-input-plus")
    private WebElement adultsPlusBtn;

    @FindBy(css = ".uitk-step-input-button.uitk-step-input-minus")
    private WebElement adultsMinusBtn;

    @FindBy(css = ".uitk-step-input-button.uitk-step-input-plus")
    private WebElement childrenPlusBtn;

    @FindBy(css = ".uitk-step-input-button.uitk-step-input-minus")
    private WebElement childrenMinusBtn;

    @FindBy(css = ".uitk-step-input-button.uitk-step-input-plus")
    private WebElement infantsPlusBtn;

    @FindBy(css = ".uitk-step-input-button.uitk-step-input-minus")
    private WebElement infantsMinusBtn;

    @FindBy(css = ".close.btn-text")
    private WebElement travelersCloseBtn;

    @FindBy(id = "flight-children-hp-flight")
    private WebElement childrenDropdown;

    @FindBy(id = "flight-add-hotel-checkbox-hp-flight")
    private WebElement addHotelCheck;

    @FindBy(id = "flight-add-car-checkbox-hp-flight")
    private WebElement addCarCheck;

    @FindBy(css = ".btn-primary.btn-action.gcw-submit")
    private WebElement searchBtn;

    @FindBy(id = "aria-option-0")
    private WebElement result;

    @FindBy(xpath = "//*[@id='flight-departing-wrapper-hp-flight']/div/div/button[2]")
    private WebElement depRightCalendarArrow;

    @FindBy(xpath = "//*[@id='flight-departing-wrapper-hp-flight']/div/div/button[1]")
    private WebElement depLeftCalendarArrow;

    @FindBy(xpath = "//*[@id=\"flight-returning-wrapper-hp-flight\"]/div/div/button[2]")
    private WebElement retRightCalendarArrow;

    @FindBy(xpath = "//*[@id=\"flight-returning-wrapper-hp-flight\"]/div/div/button[1]")
    private WebElement retLeftCalendarArrow;

    @FindBy(xpath = "//*[@id=\"flight-departing-wrapper-hp-flight\"]/div/div/div[3]/table/tbody/tr[3]/td[4]/button")
    private WebElement departDate;

    @FindBy(xpath = "//*[@id=\"flight-returning-wrapper-hp-flight\"]/div/div/div[3]/table/tbody/tr[3]/td[3]/button")
    private WebElement retDate;

    public FlightWidget(WebDriver pDriver) {
        super(pDriver);
    }

    @Override
    public FlightSearchPage search() {

        Logger.printInfo("Search method invoked. Waiting on search button to be clickable");

        getWait().until(ExpectedConditions.elementToBeClickable(searchBtn));

        searchBtn.click();

        Logger.printWarning("Closing pop ups and other windows");

        closePopUpWindows();

        Logger.printInfo("returning an instance of FlightSearch page");

        return new FlightSearchPage(getDriver());
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

        super.selectTripDate("departing", departingCalendar, departDate, depRightCalendarArrow);
    }

    @Override
    public void returningDate() {

        super.selectTripDate("returning", returningCalendar, retDate, retRightCalendarArrow);
    }

    public void selectTypeOfTrip(String type) {

        if (!doesElementExist(newTypeOfTrip)) {
            Logger.printDebug("Old page format displayed. Type of trip separated in labels");
            switch (type) {
                case "roundTrip":
                    roundTripBtn.click();
                    break;
                case "oneWay":
                    oneWayBtn.click();
                    break;
                case "multiCity":
                    multiCityBtn.click();
                    break;
                default:
                    roundTripBtn.click();
                    break;
            }
        } else {
            Logger.printDebug("New page format displayed. Type of trip inside a dropdown");
            newTypeOfTrip.click();
            switch (type) {
                case "roundTrip":
                    newRoundTripLabel.click();
                    break;
                case "oneWay":
                    newOneWayLabel.click();
                    break;
                case "multiCity":
                    newMultiCityLabel.click();
                    break;
                default:
                    newRoundTripLabel.click();
                    break;
            }
        }
    }

    @Override
    public void selectAdultsNumber(String number) {

          if (doesElementExist(newTravelersDropdown)) {
              if (number.equals("-1")) {
                  super.selectTravelers("new", newTravelersDropdown, number, adultsMinusBtn, travelersCloseBtn);
              } else {
                  super.selectTravelers("new", newTravelersDropdown, number, adultsPlusBtn, travelersCloseBtn);
              }
          } else {
              if (number.equals("-1")) {
                  super.selectTravelers("old", adultsDropdown, number, adultsMinusBtn, travelersCloseBtn);
              } else {
                  super.selectTravelers("old", adultsDropdown, number, adultsPlusBtn, travelersCloseBtn);
              }
          }
    }

    @Override
    public void selectChildrenNumber(String childrenNumber) {

        if (doesElementExist(newTravelersDropdown)) {

            super.selectTravelers("new", newTravelersDropdown, childrenNumber, childrenPlusBtn, travelersCloseBtn);

        } else {

            super.selectTravelers("old", childrenDropdown, childrenNumber, childrenPlusBtn, travelersCloseBtn);
        }
    }
}
