package com.epam.rpa.hackathon.web.lvivonline;

import com.epam.rpa.hackathon.util.JsonUtil;
import com.epam.rpa.hackathon.web.IEvent;
import com.epam.rpa.hackathon.web.IGetEventsAction;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class GetLvivOnlineEventsAction implements IGetEventsAction {

    @Override
    public List<? extends IEvent> getEvents() {

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
    public String getEventsJson() {
        return JsonUtil.toStringRepresentation(this.getEvents());
    }
}