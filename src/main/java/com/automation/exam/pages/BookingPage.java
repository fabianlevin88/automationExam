package com.automation.exam.pages;

import com.automation.exam.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingPage extends BasePage {

    @FindBy(id = "preferences")
    private WebElement preferenceSection;

    @FindBy(id = "insurance")
    private WebElement protectFlightSection;

    @FindBy(id = "email")
    private WebElement confirmationSection;

    @FindBy(id = "payments")
    private WebElement paymentSection;

    @FindBy(id = "complete-booking")
    private WebElement completeBookingBtn;

    public BookingPage(WebDriver pDriver) {
        super(pDriver);
    }

    @Override
    public Object search() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return boolean value according to existence of the preference section.
     */
    public Boolean validatePreferences() {

        Logger.printInfo("Validating that the preference section is displayed");

        return doesElementExist(preferenceSection);
    }

    /**
     *
     * @return boolean value according to existence of the protect flight section.
     */
    public Boolean validateFlightSection() {

        Logger.printInfo("Validating that the protect flight section is displayed");

        return doesElementExist(protectFlightSection);
    }

    /**
     *
     * @return boolean value according to existence of the assurance section.
     */
    public Boolean validateConfirmationSection() {

        Logger.printInfo("Validating that the assurance section is displayed");

        return doesElementExist(confirmationSection);
    }

    /**
     *
     * @return boolean value according to existence of the payment section.
     */
    public Boolean validatePayment() {

        Logger.printInfo("Validating that the payment section is displayed");

        return doesElementExist(paymentSection);
    }

    /**
     *
     * @return boolean value according to existence of the complete booking button.
     */
    public Boolean validateCompleteButton() {

        Logger.printInfo("Validating that the completebooking button is displayed");

        return doesElementExist(completeBookingBtn);
    }
}
