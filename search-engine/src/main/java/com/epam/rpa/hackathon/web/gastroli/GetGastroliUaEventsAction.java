package com.epam.rpa.hackathon.web.gastroli;

import com.epam.rpa.hackathon.property.SiteNames;
import com.epam.rpa.hackathon.util.JsonUtil;
import com.epam.rpa.hackathon.web.IEvent;
import com.epam.rpa.hackathon.web.IGetEventsAction;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component(SiteNames.GASTROLI)
public class GetGastroliUaEventsAction implements IGetEventsAction {
    @Override
    public List<IEvent> getEvents() {
        return null;
    }

    @Override
    public List<IEvent> getEventsForPeriod(LocalDate from, LocalDate to) {
        WebDriver webDriver = new GastroliWebDriverProvider().getDriver();
        try {
            List<IEvent> list;
            for (int i = 0; true; i++) {

                try {
                    GastroliUaHomePage gastroliPage = new GastroliUaHomePage(webDriver);
                    gastroliPage.setDataRange(from, to);

                    list = gastroliPage.setup().getEvents();
                    System.out.println("After scrapping : " + list.toString());
                    break;
                } catch (Exception e) {
                    System.out.println("On reattempt : " + e.getMessage());
                    if (i == 2) {
                        throw e;
                    }
                }
            }

            return list;
        } finally {
            webDriver.close();
        }
    }

    @Override
    public String getEventsJson() {
        return JsonUtil.toStringRepresentation(this.getEvents());
    }

    @Override
    public String getEventsJson(LocalDate dateFrom, LocalDate dateTo) {
        String json = JsonUtil.toStringRepresentation(this.getEventsForPeriod(dateFrom, dateTo));
        System.out.println("In get Json : " + json);
        return json;
    }
}
