package com.epam.rpa.hackathon.controller;

import com.epam.rpa.hackathon.model.SiteData;
import com.epam.rpa.hackathon.web.IGetEventsAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
public class MainController {

    @Autowired
    private Map<String, IGetEventsAction> eventsActionMap;


    @PostMapping("/processEvents")
    public String processEvents(@RequestBody SiteData siteData) {
        String siteToProcess = siteData.getSite();
        IGetEventsAction eventsAction = eventsActionMap.get(siteToProcess);

        String events = eventsAction.getEventsJson();

        System.out.println(events);

        return "events";
    }
}


