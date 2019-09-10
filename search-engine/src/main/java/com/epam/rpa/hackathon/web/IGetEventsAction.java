package com.epam.rpa.hackathon.web;

import com.epam.rpa.hackathon.web.IEvent;

import java.util.List;

public interface IGetEventsAction {

    List<? extends IEvent> getEvents();
//    List getEvents();

    String getEventsJson();

}
