package com.epam.rpa.hackathon.web;

import com.epam.rpa.hackathon.util.JsonUtil;
import com.epam.rpa.hackathon.web.IEvent;

import java.time.LocalDate;
import java.util.List;

public interface IGetEventsAction {

    List<IEvent> getEvents();

    List<IEvent> getEventsForPeriod(LocalDate dateFrom, LocalDate dateTo);

    default String getEventsJson() {
        return JsonUtil.toStringRepresentation(this.getEvents());
    }

}
