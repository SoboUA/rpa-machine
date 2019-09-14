package com.epam.rpa.hackathon.web.gastroli;

import com.epam.rpa.hackathon.property.SiteNames;
import com.epam.rpa.hackathon.util.JsonUtil;
import com.epam.rpa.hackathon.web.IEvent;
import com.epam.rpa.hackathon.web.IGetEventsAction;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(SiteNames.GASTROLI)
public class GetGastroliUaEventsAction implements IGetEventsAction {
    @Override
    public List<? extends IEvent> getEvents() {
        return null;
    }

    @Override
    public List<? extends IEvent> getEvents(String from, String to) {
        WebDriver webDriver = new GastroliWebDriverProvider().getDriver();
        try {
            GastroliUaHomePage gastroliPage = new GastroliUaHomePage(webDriver);
            gastroliPage.setDataRange(from, to);

            return gastroliPage.setup().getEvents();
        } finally {
            webDriver.close();
        }
    }

    @Override
    public String getEventsJson() {
        return JsonUtil.toStringRepresentation(this.getEvents());
    }
}
