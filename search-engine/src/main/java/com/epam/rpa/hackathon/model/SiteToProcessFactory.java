package com.epam.rpa.hackathon.model;

import com.epam.rpa.hackathon.property.Property;
import com.epam.rpa.hackathon.web.GetEvents;
import com.epam.rpa.hackathon.web.lvivonline.GetLvivOnlineEventsAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SiteToProcessFactory implements GetEvents {

    @Autowired
    private GetLvivOnlineEventsAction getLvivOnlineEventsAction;

    @Override
    public String getEventsJson(String site) {
        if (site.equals(Property.LVIV_ONLINE_HOMEPAGE.toString())){
            return getLvivOnlineEventsAction.getEventsJson();
        }
        return "wrong site";
    }
}
