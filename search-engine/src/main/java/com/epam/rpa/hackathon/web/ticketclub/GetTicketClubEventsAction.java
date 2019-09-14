package com.epam.rpa.hackathon.web.ticketclub;

import com.epam.rpa.hackathon.web.IEvent;
import com.epam.rpa.hackathon.web.IGetEventsAction;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetTicketClubEventsAction implements IGetEventsAction {
    @Override
    public List<? extends IEvent> getEvents() {
        WebDriver webDriver = new TicketClubWebDriverProvider().getDriver();
        try {
            TicketClubHomePage ticketClubPage = new TicketClubHomePage(webDriver);
            ticketClubPage.selectLvivCity()
                    .selectAllLocations()
                    .setDateFrom(LocalDate.now());
            return new ArrayList<>();
        } finally {
            webDriver.close();
        }
    }

    @Override
    public List<? extends IEvent> getEvents(String from, String to) {
        return null;
    }

    @Override
    public String getEventsJson() {
        return null;
    }

}
