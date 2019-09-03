package com.rpa.controltower.controller;

import com.rpa.controltower.model.CTResultData;
import com.rpa.controltower.model.ResultObject;
import com.rpa.controltower.model.SearchData;
import com.rpa.controltower.model.ui.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("main")
public class MainPageController {

    @Autowired
    WebClient.Builder webClientBuilder;

    @GetMapping("/search")
    public String getInfo(Model model){

        //TODO Sobo make flexy
        List<Site> allSites = Arrays.asList(
                new Site(1,"lvivInfo", "lviv.com", "nice Site"),
                new Site(2,"touristLviv", "tourists.com", "norm"),
                new Site(3,"livandovkaLife", "gopnik.com", "bad site")
        );


        model.addAttribute("allSites", allSites);

        return "main";
    }

    @PostMapping("/search")
    public String processInfo(){

        //TODO Sobo make flexy
        List<Site> allSites = Arrays.asList(
                new Site(1,"lvivInfo", "lviv.com", "nice Site"),
                new Site(2,"touristLviv", "tourists.com", "norm"),
                new Site(3,"livandovkaLife", "gopnik.com", "bad site")
        );

        List<ResultObject> results = allSites.stream()
                .map(t -> {
                    SearchData data = new SearchData(t, new ArrayList<>(), true);
                    WebClient.RequestHeadersSpec requestBodySpec = WebClient.create("http://localhost:8090/easy/send").method(HttpMethod.GET).body(BodyInserters.fromObject(data));
                    return requestBodySpec.retrieve().bodyToMono(ResultObject.class).block();
                })
                .collect(Collectors.toList());

        System.out.println(results);

        return "restesttab";
    }
}
