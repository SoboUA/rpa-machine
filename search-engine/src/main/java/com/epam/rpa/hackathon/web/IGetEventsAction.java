package com.epam.rpa.hackathon.web;

import com.epam.rpa.hackathon.util.JsonUtil;
import com.epam.rpa.hackathon.web.IEvent;

import java.util.List;

public interface IGetEventsAction {

    List<? extends IEvent> getEvents();

//    String getEventsJson();

    default String getEventsJson() {
        return JsonUtil.toStringRepresentation(this.getEvents());
    }

}
