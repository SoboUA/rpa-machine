package com.rpa.controltower.controller;

import com.rpa.controltower.converter.DataConverter;
import com.rpa.controltower.datastore.TempDatastore;
import com.rpa.controltower.model.*;
import com.rpa.controltower.model.ui.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Controller
@RequestMapping("main")
public class MainPageController {

    @Autowired
    TempDatastore tempDatastore;

    @Autowired
    WebClient webClient;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

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
        System.out.println("search data: " + searchData);

        CTRequestData requestData = new DataConverter().toRequestData(searchData);

        System.out.println("requestData.getData(): " + requestData.getData());

        requestData.getData().forEach(body -> {
            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.POST).uri("http://localhost:8082/processEvents").body(BodyInserters.fromObject(body));
//            Mono<ResultObject> resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class);
//            resultObjectMono.subscribe(e -> tempDatastore.append(e));
            ResultObject resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class).block();
            tempDatastore.append(resultObjectMono);
        });

//        Flux<ResultObject> resultObjectMono = null;
//        List<ResultObject> resultObjects = new ArrayList<>();
//        List<SiteData> data = requestData.getData();
//        for (int i = 0; i < data.size(); i++) {
//            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.POST).uri("http://localhost:8082/processEvents").body(BodyInserters.fromObject(data.get(i)));
//            resultObjectMono = requestBodySpec.retrieve().bodyToFlux(ResultObject.class);
//            resultObjectMono.subscribe(resultObjects::add);
////            ResultObject resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class).block();
////            tempDatastore.append(resultObjectMono);
//
//            if (data.size() == i) {
//
//            }
//        }

//        Mono.when(resultObjectMono).subscribe(e -> tempDatastore.appendList(resultObjects))
//        Mono.when(resultObjectMono).doOnSuccess(e -> System.out.println("++++++++++++++ " + e));

        System.out.println("TD: " + tempDatastore);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");

        System.out.println("In the end");

        List<ResultObject> resultObjects = tempDatastore.getResultObjects();

//        Mono<Void> request = webClientBuilder.build().post().uri("http://localhost:8083/email/receiveResultObjects")
//                .body(BodyInserters.fromObject(resultObjects))
//                .retrieve()
//                .bodyToMono(Void.class);

//        webClientBuilder.build().post().uri("http://localhost:8083/email/receiveResultObjects")
//                .body(BodyInserters.fromObject(tempDatastore.getResultObjects()));

//        WebClient.RequestHeadersSpec spec = webClient.method(HttpMethod.POST).uri("http://localhost:8083/email/receiveResultObjects")
//                .body(BodyInserters.fromObject(resultObjects.get(0)));

        ResultObject result = restTemplate.postForObject("http://localhost:8083/email/receiveResultObjects", resultObjects, ResultObject.class);

//        resultObjects.forEach(resultObject -> {
//            WebClient.RequestHeadersSpec spec = webClient.method(HttpMethod.POST).uri("http://localhost:8083/email/receiveResultObjects")
//                .body(BodyInserters.fromObject(resultObject));
//        });

        System.out.println("OK");

        return modelAndView;
    }

}