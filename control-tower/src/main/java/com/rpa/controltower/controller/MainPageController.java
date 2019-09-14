package com.rpa.controltower.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpa.controltower.converter.DataConverter;
import com.rpa.controltower.converter.ExcelConverter;
import com.rpa.controltower.datastore.TempDatastore;
import com.rpa.controltower.model.*;
import com.rpa.controltower.model.input.CTRequestData;
import com.rpa.controltower.model.input.SiteData;
import com.rpa.controltower.model.ui.ScrappingFormData;
import com.rpa.controltower.model.ui.Site;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.rpa.controltower.model.Category.*;


@Controller
@RequestMapping("main")
public class MainPageController {


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


    @GetMapping("/search")
    public String getInfo(Model model) {
        ScrappingFormData data = new ScrappingFormData();
        HashMap<Category, String> categoryMap = new HashMap<>();
        categoryMap.put(CONCERTS, "Концерти");
        categoryMap.put(CONFERENCEC, "Конференції");
        categoryMap.put(EXHIBITIONS, "Виставки");
        categoryMap.put(FESTIVAL, "Фестивалі");
        categoryMap.put(OTHER, "Інше");
        List<Site> allSites = Arrays.asList(
                new Site("lvivInfo", "LvivOnline", "https://lviv-online.com/ua/", categoryMap),
                new Site("gastroli", "Gastroli", "https://gastroli.ua/en/Lviv", categoryMap),
                new Site("philarmonia", "Philarmonia", "https://philharmonia.lviv.ua/events/", categoryMap),
                new Site("ticketClub", "TicketClub", "https://ticketclub.com.ua/?ct=1", categoryMap));

        data.setOutputSites(allSites);
        model.addAttribute("allSites", data);
        model.addAttribute("resultData", new SearchData());
        return "index";
    }

    @PostMapping("/search")
    public String processInfo(ServletRequest request) throws IOException {
        //https://stackoverflow.com/questions/3831680/httpservletrequest-get-json-post-data
        String jsonString = request.getReader().lines().collect(Collectors.joining());
        ScrappingFormData scrappingFormData = new ObjectMapper().readValue(jsonString, ScrappingFormData.class);
        System.out.println(scrappingFormData);
        System.out.println(jsonString);

        TempDatastore tempDatastore = new TempDatastore();

        CTRequestData requestData = new DataConverter().toRequestDataScraping(scrappingFormData);
        System.out.println("requestData.getData(): " + requestData.getData());

        //Load Balancing
        ServiceInstance serviceEngineInstance = loadBalancerClient.choose("engine-service");
        String searchEngineUrl = "http://" + serviceEngineInstance.getHost() + ":" + serviceEngineInstance.getPort() + "/processEvents";
        System.out.println("URL: " + searchEngineUrl);


        List<SiteData> data = requestData.getData();
        for (int i = 0; i < data.size(); i++) {
            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.POST)
                    .uri(searchEngineUrl)
                    .body(BodyInserters.fromObject(data.get(i)));

            Mono<ResultObject> resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class);
            resultObjectMono.subscribe(e -> {
                tempDatastore.append(e);
                if (data.size() == tempDatastore.getSize()) {
                    System.out.println("INTO for");
                    System.out.println("data size: "+ data.size());
                    int size = tempDatastore.getResultObjects().size();
                    System.out.println("size: " + size);

                    Workbook workbook = saveDataToExcel(tempDatastore);

                    sendMail(workbook, scrappingFormData.getEmail());
                }
            });
        }

        return "redirect:/main/search";
    }

    public void sendMail(Workbook workbook, String email) {
    }

    @PostMapping("/searchOld")
    public String processInfo(@ModelAttribute SearchData searchData) {

        TempDatastore tempDatastore = new TempDatastore();

        System.out.println("here");
        System.out.println("search data: " + searchData);

        CTRequestData requestData = new DataConverter().toRequestData(searchData);

        System.out.println("requestData.getData(): " + requestData.getData());

        ServiceInstance serviceEngineInstance = loadBalancerClient.choose("engine-service");
        String searchEngineUrl = "http://" + serviceEngineInstance.getHost() + ":" + serviceEngineInstance.getPort() + "/processEvents";
        System.out.println("URL: " + searchEngineUrl);


//        ResultObject resultObjectFlux = Flux.fromIterable(requestData.getData())
//                .flatMap(site -> webClient.post()
//                                .uri(searchEngineUrl)
//                                .body(BodyInserters.fromObject(site))
////                        .accept(MediaType.APPLICATION_JSON)
//                                .retrieve()
//                                .bodyToFlux(ResultObject.class)
//
//                )
//                .subscribeOn(Schedulers.parallel())
//                .collect()
//                .blockLast();
//
////                .map(site -> webClient
////                                .post()
////                                .uri(emailServiceUrl)
////                                .body(BodyInserters.fromObject(site))
////                                .retrieve()
////                                .bodyToMono(Void.class)
//////                        .subscribe(v -> System.out.println("thiiiss" + v))
////                )

//        requestData.getData().forEach(body -> {
//            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.GET).uri("http://localhost:8090/easy/send").body(BodyInserters.fromObject(body));
//            Mono<ResultObject> resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class).block();
//            resultObjectMono.subscribe(e -> tempDatastore.append(e));
//        });

        List<SiteData> data = requestData.getData();
        for (int i = 0; i < data.size(); i++) {
            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.POST)
                    .uri(searchEngineUrl)
                    .body(BodyInserters.fromObject(data.get(i)));

            Mono<ResultObject> resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class);
            resultObjectMono.subscribe(e -> {
                tempDatastore.append(e);
                if (data.size() == tempDatastore.getSize()) {
                    System.out.println("INTO for");
                    int size = tempDatastore.getResultObjects().size();
                    System.out.println("size: " + size);

                }
            });
        }

        return "";

    }

    public Workbook saveDataToExcel(TempDatastore datastores) {
        List<ResultObject> resultObjects = datastores.getResultObjects();

        Workbook xssfWorkbook = new XSSFWorkbook();
        return  new ExcelConverter().fillWorkbook(xssfWorkbook, resultObjects);

        //method();
    }

    @PostMapping("/search111")
    public String processInfoSecond(ServletRequest request) throws IOException {
        //https://stackoverflow.com/questions/3831680/httpservletrequest-get-json-post-data
        String jsonString = request.getReader().lines().collect(Collectors.joining());
        ScrappingFormData scrappingFormData = new ObjectMapper().readValue(jsonString, ScrappingFormData.class);
        System.out.println(scrappingFormData);
        System.out.println(jsonString);


        return "redirect:/main/search";
    }
}
