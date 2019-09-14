package com.rpa.controltower.controller;

import com.rpa.controltower.converter.DataConverter;
import com.rpa.controltower.datastore.TempDatastore;
import com.rpa.controltower.model.*;
import com.rpa.controltower.model.ui.ScrappingFormData;
import com.rpa.controltower.model.ui.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.rpa.controltower.model.Category.*;


@Controller
@RequestMapping("main")
public class MainPageController {

    @Autowired
    TempDatastore tempDatastore;

    @Autowired
    @LoadBalanced
    WebClient webClient;

    @Autowired
    @LoadBalanced
    WebClient.Builder webClientBuilder;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/searchOld")
    public String getOldInfo(Model model) {

        //TODO Sobo make flexy
        List<Site> allSites = Arrays.asList(
                new Site(1, "lvivInfo", "lviv.com"),
                new Site(2, "touristLviv", "tourists.com"),
                new Site(3, "livandovkaLife", "gopnik.com")
        );


        model.addAttribute("allSites", allSites);
        model.addAttribute("resultData", new SearchData());
        return "main";
    }

    @GetMapping("/search")
    public String getInfo(Model model) {

        ScrappingFormData data = new ScrappingFormData();
        List<Category> categories = Arrays.asList(CONCERTS, CONFERENCEC, EXHIBITIONS, FESTIVAL, OTHER);

        List<Site> allSites = Arrays.asList(
                new Site(1, "LvivOnline", "https://lviv-online.com/ua/", categories),
                new Site(2, "Gastroli", "https://gastroli.ua/en/Lviv", categories),
                new Site(3, "Philarmonia", "https://philharmonia.lviv.ua/events/", categories),
                new Site(4, "TicketClub", "https://ticketclub.com.ua/?ct=1", categories));


        data.setOutputSites(allSites);


        model.addAttribute("allSites", data);
        model.addAttribute("resultData", new SearchData());
        return "main";
    }

    @PostMapping("/searchOld")
    public ModelAndView processInfo(@ModelAttribute SearchData searchData) {

        tempDatastore.clear();

        System.out.println("here");
        System.out.println("search data: " + searchData);

        CTRequestData requestData = new DataConverter().toRequestData(searchData);

        System.out.println("requestData.getData(): " + requestData.getData());

        SiteData siteData = requestData.getData().get(0);

        ServiceInstance engineSrvice = loadBalancerClient.choose("engine-service");
        System.out.println("engineSrvice: " + engineSrvice);
        String searchEngineUrl = "http://" + engineSrvice.getHost() + ":" + engineSrvice.getPort() + "/processEvents";
        System.out.println("URL: " + searchEngineUrl);

        List<SiteData> data = requestData.getData();
        for (int i = 0; i < data.size(); i++) {
            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.POST).uri(searchEngineUrl).body(BodyInserters.fromObject(data.get(i)));
            Mono<ResultObject> resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class);
            resultObjectMono.subscribe(e -> {
                        tempDatastore.append(e);
                        if (data.size() == tempDatastore.getSize()) {
                            System.out.println("INTO for");
                            int size = tempDatastore.getResultObjects().size();
                            System.out.println("size: " + size);

                            //method();
                        }
                    }
            );
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");

        System.out.println("In the end");

        return modelAndView;
    }
}