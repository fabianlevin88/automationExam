package com.automation.exam.tests;

import com.automation.exam.pages.LandingPage;
import com.automation.exam.pages.PackageWidget;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Excersice_2 extends BaseTest{

    @Parameters({"cityFrom", "cityTo"})
    @Test
    public void validateFlightAndHotel(String cityFrom, String cityTo) {

        LandingPage landingPage = getLandingPage();

        PackageWidget packageWidget = landingPage.openPackageTab();

        packageWidget.flyingFrom(cityFrom);

        packageWidget.flyingTo(cityTo);

        packageWidget.departureDate();

        packageWidget.returningDate();

        packageWidget.search();
    }


}
