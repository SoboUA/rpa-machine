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
    public String processInfo(@ModelAttribute SearchData searchData) {

        tempDatastore.clear();

        System.out.println("here");
        System.out.println("search data: " + searchData);

        CTRequestData requestData = new DataConverter().toRequestData(searchData);

        System.out.println("requestData.getData(): " + requestData.getData());

        SiteData siteData = requestData.getData().get(0);

        ServiceInstance serviceInstance = loadBalancerClient.choose("engine-service");
        ServiceInstance serviceInstance2 = loadBalancerClient.choose("email-service");
        System.out.println("serviceInstance: " + serviceInstance);
        String searchEngineUrl = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/processEvents";
        String emailServiceUrl = "http://" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort() + "/receiveResultObjects";
        System.out.println("URL: " + searchEngineUrl);
        System.out.println("URL: " + emailServiceUrl);

//        ResponseEntity entity = restTemplate.exchange(url, HttpMethod.POST, siteData, SiteData.class);
//        SiteData restTempl = restTemplate.postForObject("http://engine-service/processEvents", siteData, SiteData.class);

//        requestData.getData().forEach(body -> {
////            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.POST).uri("http://engine-service/processEvents").body(BodyInserters.fromObject(body));
//            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.POST).uri(searchEngineUrl).body(BodyInserters.fromObject(body));
//            Mono<ResultObject> resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class);
//            resultObjectMono.subscribe(e -> tempDatastore.append(e));
////            ResultObject resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class).block();
////            tempDatastore.append(resultObjectMono);
//        });

//        Flux<ResultObject> resultObjectMono = null;
//        List<ResultObject> resultObjects = new ArrayList<>();
        List<SiteData> data = requestData.getData();
        for (int i = 0; i < data.size(); i++) {
            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.POST).uri("http://localhost:8082/processEvents").body(BodyInserters.fromObject(data.get(i)));
//            resultObjectMono = requestBodySpec.retrieve().bodyToFlux(ResultObject.class);
//            resultObjectMono.subscribe(resultObjects::add);++bbb
            Mono<ResultObject> resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class);
            resultObjectMono.subscribe(e -> tempDatastore.append(e));

            if (data.size()-1 == i) {
                int size = tempDatastore.getResultObjects().size();
                System.out.println(size);
            }
        }

//        Flux<ResultObject> resultObjectFlux = Flux.fromIterable(data)
//                .flatMap(site -> webClient.post()
//                        .uri(searchEngineUrl)
//                        .body(BodyInserters.fromObject(site))
////                        .accept(MediaType.APPLICATION_JSON)
//                        .retrieve()
//                        .bodyToMono(ResultObject.class))
//                .subscribeOn(Schedulers.parallel());
//
////                .map(site -> webClient
////                                .post()
////                                .uri(emailServiceUrl)
////                                .body(BodyInserters.fromObject(site))
////                                .retrieve()
////                                .bodyToMono(Void.class)
//////                        .subscribe(v -> System.out.println("thiiiss" + v))
////                )
////                 .subscribe();
//
//
////        System.out.println("!!!!!!!!!!!1" + resultObjectFlux);
////
//        Flux.from(resultObjectFlux)
//                .subscribe(System.)
//
//                .map(site -> webClient
//                        .post()
//                        .uri(emailServiceUrl)
//                        .body(BodyInserters.fromObject(site))
//                        .retrieve()
//                        .bodyToMono(Void.class)
////                        .subscribe(v -> System.out.println("thiiiss" + v))
//                )
//                .subscribe();
//

//
//        WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.POST)
//                .uri("http://localhost:8082/processEvents")
//                .body(BodyInserters.fromObject(data.get(i)));
//
//        Mono.when(resultObjectMono).subscribe(e -> tempDatastore.appendList(resultObjects));
//        Mono.when(resultObjectMono).doOnSuccess(e -> System.out.println("++++++++++++++ " + e));
//
//        System.out.println("TD: " + tempDatastore);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("result");
//
        System.out.println("In the end");
//        List<ResultObject> resultObjects = tempDatastore.getResultObjects();


//        Mono<Void> request = webClientBuilder.build().post().uri("http://localhost:8083/email/receiveResultObjects")
//                .body(BodyInserters.fromObject(resultObjects))
//                .retrieve()
//                .bodyToMono(Void.class);

//        webClientBuilder.build().post().uri("http://localhost:8083/email/receiveResultObjects")
//                .body(BodyInserters.fromObject(tempDatastore.getResultObjects()));

//        WebClient.RequestHeadersSpec spec = webClient.method(HttpMethod.POST).uri("http://localhost:8083/email/receiveResultObjects")
//                .body(BodyInserters.fromObject(resultObjects.get(0)));

//        ResultObject result = restTemplate.postForObject("http://localhost:8083/email/receiveResultObjects", resultObjects, ResultObject.class);

//        resultObjects.forEach(resultObject -> {
//            WebClient.RequestHeadersSpec spec = webClient.method(HttpMethod.POST).uri("http://localhost:8083/email/receiveResultObjects")
//                .body(BodyInserters.fromObject(resultObject));
//        });

        System.out.println("OK");

        return "result";
    }


}