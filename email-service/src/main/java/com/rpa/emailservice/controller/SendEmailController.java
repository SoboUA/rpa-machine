package com.rpa.emailservice.controller;

import com.rpa.emailservice.model.Event;
import com.rpa.emailservice.model.ResultObject;
import com.rpa.emailservice.model.SiteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

//@RestController
@Controller
@RequestMapping("email")
public class SendEmailController {

    @Autowired
    WebClient webClient;

    @Autowired
    WebClient.Builder webClientBuilder;

    @PostMapping("/receiveResultObjects")
    public void getEvents(@RequestBody List<ResultObject> resultObjects) {
        System.out.println("------------START");
        for (ResultObject resultObject : resultObjects) {
            SiteData siteData = resultObject.getSiteData();
            List<Event> events = resultObject.getEventList();

            System.out.println("SD: " + siteData);
        }
    }

}
