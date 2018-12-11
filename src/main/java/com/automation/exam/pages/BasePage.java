package com.automation.exam.pages;

import com.automation.exam.utils.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;

public abstract class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String parent;
    private Set<String> s1;
    private Iterator<String> I1;

    public BasePage(WebDriver pDriver) {
        PageFactory.initElements(pDriver, this);
        wait = new WebDriverWait(pDriver, 20);
        driver = pDriver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closePopUpWindows() {

        parent = getDriver().getWindowHandle();
        s1 = getDriver().getWindowHandles();
        I1 = s1.iterator();

        Logger.printInfo("Iterating over the windows and pop ups opened");

        Logger.printDebug("Number of child pages: " + s1.size());

        while(I1.hasNext()) {
            String child_window = I1.next();

            if(!parent.equals(child_window)){
                getDriver().switchTo().window(child_window);
                Logger.printDebug("Closing the recently opened pop ups");
                getDriver().close();
            }
        }

        Logger.printInfo("Returning to default page context");
        getDriver().switchTo().window(parent);
    }

    public Boolean verifyPopUps() {

        parent = getDriver().getWindowHandle();
        s1 = getDriver().getWindowHandles();
        I1 = s1.iterator();

        String child_window = I1.next();

        return parent.equals(child_window);
    }

    public void dispose() {
        if (driver != null) {
            driver.quit();
        }
    }

    public abstract Object search();

    public Boolean doesElementExist(WebElement element) {

        try {

            if (element.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            e.getCause();
        }
        return false;
    }

    public void enterFlyingFrom(WebElement input, String cityFrom, WebElement result) {

        Logger.printInfo("Entering the city: " + cityFrom + " as origin");

        enterCity(input, cityFrom, result);

        Logger.printInfo("Calling the method to validate the origin city ");

        validateCityInput(input, cityFrom, result);
    }

    /**
     *
     * @param input
     * @param city
     */
    public Boolean enterCity(WebElement input, String city, WebElement result) {

        input.clear();

        input.sendKeys(city);

        Logger.printInfo("Waiting on the autocomplete result to be clickable");

        getWait().until(ExpectedConditions.elementToBeClickable(result));

        result.click();

        return input.getAttribute("value").equals(city);
    }

    /**
     *
     * @param input
     * @param city
     */
    public void validateCityInput(WebElement input, String city, WebElement result) {

        Logger.printInfo("Validating the city: " + city);


        while (enterCity(input, city, result)) {

            Logger.printInfo("Entering the city as it is not correct");
        }
    }

    public void selectTripDate(String trip, WebElement calendar, WebElement date, WebElement arrow) {

        Logger.printInfo("Waiting for the departing calendar datepicker to be clickable");

        getWait().until(ExpectedConditions.elementToBeClickable(calendar));

        calendar.click();

        Logger.printInfo("Selecting departing date");

        chooseDate(trip, date, arrow);
    }

    public void chooseDate(String type, WebElement date, WebElement arrow) {

        Logger.printInfo("Navigating through the datepicker calendar");

        switch (type) {
            case "departing":
                Logger.printInfo("Selecting departing date two months from now");
                arrow.click();
                break;
            case "returning":
                Logger.printInfo("Selecting returning date two months from now");
                arrow.click();
                break;
            default:
                break;
        }

        date.click();
    }

    public void selectTravelers(String format, WebElement dropdown, String number, WebElement numberBtn, WebElement closeBtn) {
        Logger.printInfo("Waiting for the travelers dropdown to be visible");

        if(format.equals("new")) {
            Logger.printDebug("New page format displayed, " + number + " adults selected");
            dropdown.click();
            switch (number) {
                case "-1":
                    selectTravelersNumber(numberBtn, -1, closeBtn);
                    break;
                case "2":
                    selectTravelersNumber(numberBtn, 1, closeBtn);
                    break;
                case "3":
                    selectTravelersNumber(numberBtn, 2, closeBtn);
                    break;
                default:
                    closeBtn.click();
                    break;
            }
        } else {
            Logger.printDebug("Old page format displayed, " + number + " adults selected");

            Select adults = new Select(dropdown);

            adults.selectByValue(number);
        }
    }

    private void selectTravelersNumber(WebElement button, int number, WebElement closeBtn) {

        if(number < 0) {
            for (int i=0; i > number; i--) {
                button.click();
            }
        } else {
            for(int i=number; i <= number; i++) {
                button.click();
            }
        }
        closeBtn.click();
    }
}
