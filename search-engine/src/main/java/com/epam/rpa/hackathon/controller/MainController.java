package com.epam.rpa.hackathon.controller;

import com.epam.rpa.hackathon.model.Event;
import com.epam.rpa.hackathon.model.EventConverter;
import com.epam.rpa.hackathon.model.ResultObject;
import com.epam.rpa.hackathon.model.SiteData;
import com.epam.rpa.hackathon.web.IEvent;
import com.epam.rpa.hackathon.web.IGetEventsAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController()
public class MainController {

    @Autowired
    private Map<String, IGetEventsAction> eventsActionMap;

    Logger logger = LoggerFactory.getLogger(MainController.class);


    @PostMapping("/processEvents")
    public ResultObject processEvents(@RequestBody SiteData siteData) {
        System.out.println("siteData: " + siteData);

        String siteToProcess = siteData.getSiteId();
        IGetEventsAction eventsAction = eventsActionMap.get(siteToProcess);

//        List<IEvent> eventList = eventsAction.getEventsForPeriod(siteData.getDateFrom(), siteData.getDateTo());
        String eventsJson = eventsAction.getEventsJson(siteData.getDateFrom(), siteData.getDateTo());
        List<Event> eventList = new EventConverter().convertToEventList(eventsJson);


        System.out.println("eventList: " + eventList);

        ResultObject resultObject = new ResultObject();
        resultObject.setSiteData(siteData);
        resultObject.setEventList(eventList);

        System.out.println("resultObject: " + resultObject);

        return resultObject;
    }
}


