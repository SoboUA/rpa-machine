package com.epam.rpa.hackathon.web.ticketclub;

import com.epam.rpa.hackathon.web.ChromeDriverProvider;
import com.epam.rpa.hackathon.web.IWebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;

public class TicketClubWebDriverProvider extends ChromeDriverProvider {
    @Override
    public WebDriver getDriver() {
        return super.getDriver();
    }
}
