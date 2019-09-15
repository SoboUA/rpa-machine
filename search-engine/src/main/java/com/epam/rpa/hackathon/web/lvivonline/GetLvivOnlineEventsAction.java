package com.epam.rpa.hackathon.web.lvivonline;

import com.epam.rpa.hackathon.property.SiteNames;
import com.epam.rpa.hackathon.util.JsonUtil;
import com.epam.rpa.hackathon.web.IEvent;
import com.epam.rpa.hackathon.web.IGetEventsAction;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component(SiteNames.LVIV_ONLINE)
public class GetLvivOnlineEventsAction implements IGetEventsAction {

    @Override
    public List<IEvent> getEvents() {

        WebDriver webDriver = new LvivOnlineWebDriverProvider().getDriver();
        try {
            LvivOnlineHomePage lvivPage = new LvivOnlineHomePage(webDriver);
            return lvivPage.goToAllEventsPage()
                    .getEventsFromAllPages();

        } finally {
            webDriver.close();
        }
    }

    @Override
    public List<IEvent> getEventsForPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return this.getEvents();
    }

    @Override
    public String getEventsJson() {
        return JsonUtil.toStringRepresentation(getEvents());
    }

    @Override
    public String getEventsJson(LocalDate dateFrom, LocalDate dateTo) {
        return JsonUtil.toStringRepresentation(getEventsForPeriod(dateFrom, dateTo));
    }
}
