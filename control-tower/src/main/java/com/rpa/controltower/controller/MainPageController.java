package com.rpa.controltower.controller;

import com.rpa.controltower.converter.DataConverter;
import com.rpa.controltower.datastore.TempDatastore;
import com.rpa.controltower.model.CTRequestData;
import com.rpa.controltower.model.ResultObject;
import com.rpa.controltower.model.SearchData;
import com.rpa.controltower.model.ui.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("main")
public class MainPageController {

    @Autowired
    TempDatastore tempDatastore;

    @Autowired
    WebClient webClient;

    @GetMapping("/search")
    public String getInfo(Model model) {

        //TODO Sobo make flexy
        List<Site> allSites = Arrays.asList(
                new Site(1, "lvivInfo", "lviv.com", "nice Site"),
                new Site(2, "touristLviv", "tourists.com", "norm"),
                new Site(3, "livandovkaLife", "gopnik.com", "bad site")
        );


        model.addAttribute("allSites", allSites);
        model.addAttribute("resultData", new SearchData());
        return "main";
    }

    @PostMapping("/search")
    @ResponseBody
    public ModelAndView processInfo(@ModelAttribute SearchData searchData) {

        tempDatastore.clear();

        System.out.println("here");
        System.out.println(searchData);

        CTRequestData requestData = new DataConverter().toRequestData(searchData);

        requestData.getData().forEach(body -> {
            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.GET).uri("http://localhost:8082/processEvents").body(BodyInserters.fromObject(body));
            Mono<ResultObject> resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class);
            resultObjectMono.subscribe(e -> tempDatastore.append(e));
        });

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");

        System.out.println("In the end");

        return modelAndView;
    }
}