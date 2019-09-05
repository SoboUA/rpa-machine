package com.epam.rpa.hackathon.controller;

import com.epam.rpa.hackathon.model.SiteData;
import com.epam.rpa.hackathon.model.SiteToProcessFactory;
import com.epam.rpa.hackathon.web.lvivonline.GetLvivOnlineEventsAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@RequestMapping("engine")
@RestController()
public class MainController {

    @Autowired
    private GetLvivOnlineEventsAction getLvivOnlineEventsAction;

    @Autowired
    private SiteToProcessFactory siteToProcessFactory;

    @GetMapping("/runLvivOnline")
    public String getEngine(Model model){
        System.out.println("/RUN");

//        getLvivOnlineEventsAction.getEventsJson();
        String lvivOnlineEvents = getLvivOnlineEventsAction.getEventsJson();
        model.addAttribute("lvivOnlineEvents", lvivOnlineEvents);

        return "run";
    }

    @PostMapping("/processEvents")
    public String processEvents(@RequestBody SiteData siteData){
        String siteToProcess = siteData.getSite();
        String eventsJson = siteToProcessFactory.getEventsJson(siteToProcess);

        return "aa";
    }
}


