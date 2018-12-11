package com.automation.exam.pages.interfaces;

public interface IWidget {

    void flyingFrom(String cityFrom);

    void flyingTo(String cityTo);

    void departureDate();

    void returningDate();

    void selectAdultsNumber(String number);

    void selectChildrenNumber(String childrenNumber);
}
