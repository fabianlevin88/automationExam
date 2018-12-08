package com.automation.exam.pages;

import com.automation.exam.utils.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
}
