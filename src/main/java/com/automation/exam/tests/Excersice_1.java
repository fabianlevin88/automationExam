package com.automation.exam.tests;

import com.automation.exam.pages.*;
import com.automation.exam.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Excersice_1 extends BaseTest {

    boolean validateSort, validateSelect, validateDuration, validateBaggage, validateOrder,
            priceIsPresent, verifyDepInfoIsPresent, verifyRetInfoIsPresent, priceGuaranteeIsPresent;

    @Parameters({"cityFrom", "cityTo", "adultsNumber", "childrenNumber", "typeOfTrip", "sort"})
    @Test
    public void validateFlightSelection(String cityFrom, String cityTo, String adultsNumber, String childrenNumber, String typeOfTrip, String sort) {

        LandingPage landingPage = getLandingPage();

        FlightWidget flightWidget = landingPage.openFlightsTab();

        flightWidget.selectTypeOfTrip(typeOfTrip);

        flightWidget.flyingFrom(cityFrom);

        flightWidget.flyingTo(cityTo);

        flightWidget.departureDate();

        flightWidget.returningDate();

        flightWidget.selectAdultsNumber(adultsNumber);

        flightWidget.selectChildrenNumber(childrenNumber);

        FlightSearchPage flightSearchPage = flightWidget.search();

        validateSort = flightSearchPage.validateSortOptions();

        validateSelect = flightSearchPage.validateSelectButtonPresent();

        validateDuration = flightSearchPage.validateflightDurationPresent();

        validateBaggage = flightSearchPage.validateBaggageFeesIsPresent();

        validateOrder = flightSearchPage.validateSort(sort);

        DetailPage detailPage = flightSearchPage.selectFlight();

        priceIsPresent = detailPage.verifyPriceIsPresent();

        verifyDepInfoIsPresent = detailPage.verifyDepartureInfoIsPresent();

        verifyRetInfoIsPresent = detailPage.verifyReturningInfoIsPresent();

        //priceGuaranteeIsPresent = detailPage.verifyPriceGuarantee();

        BookingPage bookingPage = detailPage.continueBooking();

        Assert.assertTrue(validateSort, "VERIFICATION FAILED: Some of the options are not displayed.");

        Assert.assertTrue(validateSelect, "VERIFICATION FAILED: Some of the flights results in the list do not contain the select button");

        Assert.assertTrue(validateDuration, "VERIFICATION FAILED: Some of the flight results do not display the duration information");

        Assert.assertTrue(validateBaggage, "VERIFICATION FAILED: Some of the flight results do not display the baggage fees information");

        Assert.assertTrue(validateOrder, "VERIFICATION FAILED: The flights result list is NOT correctly ordered by: " + sort);

        Assert.assertTrue(priceIsPresent, "VERIFICATION FAILED: Price is not peresent");

        Assert.assertTrue(verifyDepInfoIsPresent, "VERIFICATION FAILED: Departure information is not displayed");

        Assert.assertTrue(verifyRetInfoIsPresent, "VERIFICATION FAILED: Returning information is not displayed");

        //Assert.assertTrue(priceGuaranteeIsPresent, "VERIFICATION FAILED: Price guarantee message is not peresent");

        Assert.assertTrue(bookingPage.validatePreferences());

        Assert.assertTrue(bookingPage.validateFlightSection());

        Assert.assertTrue(bookingPage.validateConfirmationSection());

        Assert.assertTrue(bookingPage.validatePayment());

        Assert.assertTrue(bookingPage.validateCompleteButton());
    }

}
