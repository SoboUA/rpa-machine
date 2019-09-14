package com.epam.rpa.hackathon.property;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public enum Property {

    WEBDRIVER_CHROME_DRIVER("webdriver.chrome.driver"),
    LVIV_ONLINE_HOMEPAGE("lviv.online.homepage"),
    TICKET_CLUB_HOMEPAGE("ticket.club.homepage");

    private String value;

    Property(String s) {
        this.value = s;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
