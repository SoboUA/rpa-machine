package com.epam.rpa.hackathon.web.ticketclub;

import com.epam.rpa.hackathon.property.SiteNames;
import com.epam.rpa.hackathon.web.IEvent;
import com.epam.rpa.hackathon.web.IGetEventsAction;
import com.epam.rpa.hackathon.web.lvivonline.LvivOnlineHomePage;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component(SiteNames.TICKET_CLUB)
public class GetTicketClubEventsAction implements IGetEventsAction {
    @Override
    public List<IEvent> getEvents() {
        WebDriver webDriver = new TicketClubWebDriverProvider().getDriver();
        try {
            TicketClubHomePage ticketClubPage = new TicketClubHomePage(webDriver);
            return ticketClubPage.selectLvivCity()
                    .selectAllLocations()
                    .getAllEventsFromSite();

        } finally {
            webDriver.close();
        }
    }

    @Override
    public List<IEvent> getEventsForPeriod(LocalDate dateFrom, LocalDate dateTo) {
        WebDriver webDriver = new TicketClubWebDriverProvider().getDriver();
        try {
            TicketClubHomePage ticketClubPage = new TicketClubHomePage(webDriver);
            return ticketClubPage.selectLvivCity()
                    .selectAllLocations()
                    .setDateFrom(dateFrom)
                    .setDateTo(dateTo)
                    .getAllEventsFromSite();

        } finally {
            webDriver.close();
        }
    }

}
