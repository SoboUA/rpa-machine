package com.epam.rpa.hackathon.web.lvivonline;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public enum EventCategory {
    THEATRE("Театр");

    private String value;

    EventCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
