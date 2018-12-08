package com.automation.exam.pages;

import com.automation.exam.utils.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

public class FlightSearchPage extends BasePage {

    @FindBy(css = ".btn-secondary.btn-sub-action")
    private WebElement searchBtn;

    @FindBy(id = "sortDropdown")
    private WebElement sortDropdown;

    @FindBys({@FindBy( css = ".grid-container.standard-padding .btn-secondary.btn-action.t-select-btn")})
    private List<WebElement> selectBtnList;

    @FindBys({@FindBy(css = ".grid-container.standard-padding")})
    private List<WebElement> containerGridList;

    @FindBys({@FindBy(css = ".duration-emphasis")})
    private List<WebElement> flightDuration;

    @FindBys({@FindBy(className = "show-flight-details")})
    private List<WebElement> flightDetailsLink;

    @FindBy(css = ".flight-details-link.toggle-trigger.open")
    private WebElement flightDetailsLinkOpened;

    @FindBy(css = ".details-utility-item.details-baggage-fee-info")
    private WebElement bagFees;

    @FindBys({@FindBy(css = ".details-utility-item.segment-info-details.secondary")})
    private WebElement flightDetails;

    @FindBy(css = ".btn-secondary.btn-action.t-select-btn")
    private WebElement selectBtn;

    @FindBy(className = "modal-inner")
    private WebElement hotelModal;

    @FindBy(id = "forcedChoiceNoThanks")
    private WebElement noThanksBtn;

    @FindBy(xpath = "//*[@id=\"basic-economy-tray-content-1\"]/div/div/div[2]/button")
    private WebElement selectThisFareLabel;

    @FindBy(xpath = "//*[@id=\"basic-economy-tray-content-1\"]/div/div/div[2]/button")
    private WebElement selectThisFareBtn;

    private String sort[] = {"Price (Lowest)", "Price (Highest)", "Duration (Shortest)", "Duration (Longest)", "Departure (Earliest)", "Departure (Latest)", "Arrival (Earliest)", "Arrival (Latest)"};

    /**
     * description: This is the class constructor that takes an instance of the web
     *              driver as parameter calling the super class basePage
     * @param pDriver
     */
    public FlightSearchPage(WebDriver pDriver) {
        super(pDriver);
    }

    /**
     * description: This method is not implemented in this page due to functionality
     *              not present in this page. If called, an exception is thrown.
     * @return object
     */
    @Override
    public Object search() {

        Logger.printInfo("Search method invoked. Waiting on search button to be clickable");

        getWait().until(ExpectedConditions.elementToBeClickable(searchBtn));

        searchBtn.click();

        Logger.printInfo("returning an instance of FlightSearch page");

        return new FlightSearchPage(getDriver());
    }

    /**
     * description: This method validates that all of the options in the sort
     *              dropdown are present and are available to select.
     *              It returns a boolean that is evaluated in the test assertion.
     * @return boolean
     */
    public Boolean validateSortOptions() {

        Logger.printInfo("Validating the sort options. Making sure every option is present");

        Select sortOptions = new Select(sortDropdown);
        List<WebElement> sortValues = sortOptions.getOptions();

        int count = 0;

        for (int i = 0; i < sort.length; i++) {
            Logger.printInfo("Sort option evaluating is: " + sortValues.get(i).getText());
            if(sort[i].equals(sortValues.get(i).getText())) {
                count++;
            }
        }

        return (sortDropdown.isDisplayed() && count == sort.length);
    }

    /**
     * description: This method validates that every flight result listed
     *              in the container grid has a select button.
     *              It returns a boolean that is evaluated in the test assertion.
     * @return boolean
     */
    public Boolean validateSelectButtonPresent() {

        Logger.printInfo("Evaluating if every result contains the select button");

        return containerGridList.size() == selectBtnList.size();
    }

    /**
     * description: This method validates that every flight result listed
     *              in the container grid has a the flight duration section displayed.
     *              It returns a boolean that is evaluated in the test assertion.
     * @return boolean
     */
    public Boolean validateflightDurationPresent() {

        Logger.printInfo("Making sure every flight option has the proper details section");

        int index = 0;

        for(WebElement flightlink: flightDetailsLink) {
            flightlink.click();
            if (flightDetails.isDisplayed()) {
                index++;
                flightDetailsLinkOpened.click();
            }
        }

        return (index == flightDetailsLink.size());
    }

    /**
     * description: This method validates that every flight result listed
     *              in the container grid has a the baggage fees section displayed.
     *              It returns a boolean that is evaluated in the test assertion.
     * @return boolean
     */
    public Boolean validateBaggageFeesIsPresent() {

        Logger.printInfo("Making sure every flight option has the proper baggage details section");

        int index = 0;

        for(WebElement flightlink: flightDetailsLink) {
            flightlink.click();
            if (bagFees.isDisplayed()) {
                index++;
                flightDetailsLinkOpened.click();
            }
        }
        return (index == flightDetailsLink.size());
    }

    /**
     * description: This method validates that the flight's result list is sorted
     *              correctly using the sort parameter.
     *              It returns a boolean that is evaluated in the test assertion.
     * @return a boolean value according to evaluation if the result lilst is ordered or not
     */
    public Boolean validateSort(String sort) {

        Logger.printInfo("Validating that the flights result list is sorted by: " + sort);

        Select sortList = new Select(sortDropdown);
        sortList.selectByVisibleText(sort);

        ArrayList durations = new ArrayList(flightDuration.size());

        Logger.printInfo("Converting the duration into minutes for comparison");

        getWait().until(ExpectedConditions.elementToBeClickable(sortDropdown));

        for (WebElement duration: flightDuration) {
            formatTime(duration.getText(), durations);
        }

        Logger.printInfo("Calling the verifyOrder method to evaluate if the list is ordered properly");

        return verifyOrder(durations);
    }

    /**
     * description: This method converts the duration string taken from the description
     *              so it can be easily compared.
     * @param time
     * @param durations
     * @return This method returns an ArrayList with all the flight's durations expressed in minutes
     */
    public ArrayList formatTime(String time, ArrayList durations) {

        Logger.printInfo("Splitting the string into hours and minutes");
        String[] splitTime = time.split(" ");

        Logger.printInfo("Taking the integer part from hours and minutes");
        Integer hours = Integer.valueOf(splitTime[0].replaceAll("\\D+",""));
        Integer minutes = Integer.valueOf(splitTime[1].replaceAll("\\D+",""));

        int flightDuration = (hours*60) + minutes;
        Logger.printInfo("converting " + hours + " hours and " + minutes + " minutes to: " + flightDuration + " minutes total");

        durations.add(flightDuration);

        return durations;
    }

    /**
     * description: This method iterates through the flight's duration ArrayList verifying
     *              that it is ordered by the criteria selected.
     * @param duration
     * @return
     */
    public Boolean verifyOrder(ArrayList duration) {

        int firstDuration = duration.indexOf(0);

        boolean ordered = true;

        Logger.printInfo("Verifying the flight duration of every flight from the list");

        for (int i = 1; i < duration.size() || ordered == false; i++) {
            if (firstDuration > duration.indexOf(i)) {
                ordered = false;
            }
        }
        return ordered;
    }

    /**
     *
     */
    public DetailPage selectFlight() {

        Logger.printInfo("Making sure the sort option is the correct one");

        if (!sortDropdown.getAttribute("value").equals("duration:asc")) {

            Logger.printDebug("Sort option is not correct and has to be selected");

            Select sortOptions = new Select(sortDropdown);

            sortOptions.selectByVisibleText(sort[2]);
        }

        Logger.printInfo("Selecting flight");

        selectOption();

        getNewActiveWindow();

        return new DetailPage(getDriver());
    }

    /**
     *
     */
    public void selectOption() {

        selectBtn.click();

        selectThisFare();

        Logger.printInfo("Selecting the flight option");

        getWait().until(ExpectedConditions.visibilityOf(selectBtn));

        if (doesElementExist(selectBtnList.get(2))) {

            Select sortOptions = new Select(sortDropdown);

            sortOptions.selectByVisibleText(sort[2]);

            Logger.printInfo("Selecting the third option flight");

            try {

                sleep(3000);

                selectBtnList.get(2).click();

                selectThisFare();

            } catch (Exception e) {

            }

        }

        if (doesElementExist(noThanksBtn)) {

            Logger.printInfo("Waiting for the modal window to display");

            Logger.printInfo("Modal window displayed and clicking on the \"no thanks\" button");

            noThanksBtn.click();
        }
    }

    public void selectThisFare() {

        getWait().until(ExpectedConditions.elementToBeClickable(selectThisFareLabel));

        if (doesElementExist(selectThisFareLabel)) {
            selectThisFareLabel.click();
        }
    }

    public void getNewActiveWindow() {

        String[] windows = getDriver().getWindowHandles().toArray(new String[0]);

        getDriver().switchTo().window(windows[windows.length - 1]);
    }
}
