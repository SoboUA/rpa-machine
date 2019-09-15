package com.rpa.controltower.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpa.controltower.converter.DataConverter;
import com.rpa.controltower.converter.excel.ExcelConverter;
import com.rpa.controltower.converter.excel.ExcelStyle;
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
import java.io.FileOutputStream;
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
    EmailController emailController;

    @Autowired
    @LoadBalanced
    WebClient.Builder webClientBuilder;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;


//    @GetMapping("/search")
//    public String getInfo(Model model) {
//        ScrappingFormData data = new ScrappingFormData();
//        HashMap<Category, String> categoryMap = new HashMap<>();
//        categoryMap.put(CONCERTS, "Концерти");
//        categoryMap.put(CONFERENCEC, "Конференції");
//        categoryMap.put(EXHIBITIONS, "Виставки");
//        categoryMap.put(FESTIVAL, "Фестивалі");
//        categoryMap.put(OTHER, "Інше");
//        List<Site> allSites = Arrays.asList(
////                new Site("lvivInfo", "LvivOnline", "https://lviv-online.com/ua/", categoryMap),
////                new Site("gastroli", "Gastroli", "https://gastroli.ua/en/Lviv", categoryMap),
////                new Site("philarmonia", "Philarmonia", "https://philharmonia.lviv.ua/events/", categoryMap),
////                new Site("ticketClub", "TicketClub", "https://ticketclub.com.ua/?ct=1", categoryMap));
//                new Site(1, "LvivOnline", "https://lviv-online.com/ua/", categoryMap),
//                new Site(2, "Gastroli", "https://gastroli.ua/en/Lviv", categoryMap),
//                new Site(3, "Philarmonia", "https://philharmonia.lviv.ua/events/", categoryMap),
//                new Site(4, "TicketClub", "https://ticketclub.com.ua/?ct=1", categoryMap));
//
//        data.setOutputSites(allSites);
//        model.addAttribute("allSites", data);
//        model.addAttribute("resultData", new SearchData());
//        return "index";
//    }


    @GetMapping("/search")
    public String vikFront(Model model) {
        ScrappingFormData data = new ScrappingFormData();
        HashMap<Category, String> categoryMap = new HashMap<>();
        categoryMap.put(CONCERTS, "Концерти");
        categoryMap.put(CONFERENCEC, "Конференції");
        categoryMap.put(EXHIBITIONS, "Виставки");
        categoryMap.put(FESTIVAL, "Фестивалі");
        categoryMap.put(OTHER, "Інше");
        List<Site> allSites = Arrays.asList(
                new Site(1, "LvivOnline", "https://lviv-online.com/ua/", categoryMap),
                new Site(2, "Gastroli", "https://gastroli.ua/en/Lviv", categoryMap),
                new Site(3, "Philarmonia", "https://philharmonia.lviv.ua/events/", categoryMap),
                new Site(4, "TicketClub", "https://ticketclub.com.ua/?ct=1", categoryMap));
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

        ServiceInstance serviceEngineInstance = loadBalancerClient.choose("engine-service0");
        String searchEngineUr0 = "http://" + serviceEngineInstance.getHost() + ":" + serviceEngineInstance.getPort() + "/processEvents";
        ServiceInstance serviceEngineInstance1 = loadBalancerClient.choose("engine-service1");
        String searchEngineUr1 = serviceEngineInstance1 != null ? "http://" + serviceEngineInstance1.getHost() + ":" + serviceEngineInstance1.getPort() + "/processEvents" : searchEngineUr0;
        ServiceInstance serviceEngineInstance2 = loadBalancerClient.choose("engine-service2");
        String searchEngineUr2 = serviceEngineInstance2 != null ? "http://" + serviceEngineInstance2.getHost() + ":" + serviceEngineInstance2.getPort() + "/processEvents" : searchEngineUr1;
        List<String> allUrls = Arrays.asList(searchEngineUr0, searchEngineUr1, searchEngineUr2);
        System.out.println("URL: " + searchEngineUr0);
        System.out.println("URL: " + searchEngineUr1);
        System.out.println("URL: " + searchEngineUr2);


        List<SiteData> data = requestData.getData();
        data.forEach(d -> System.out.println("data: " + d));
        for (int i = 0; i < data.size(); i++) {
            WebClient.RequestHeadersSpec requestBodySpec = webClient.method(HttpMethod.POST)
                    .uri(allUrls.get(i))
                    .body(BodyInserters.fromObject(data.get(i)));

            Mono<ResultObject> resultObjectMono = requestBodySpec.retrieve().bodyToMono(ResultObject.class);
            resultObjectMono.subscribe(e -> {
                System.out.println("Inside subscribe" + e);
                tempDatastore.append(e);
                if (data.size() == tempDatastore.getSize()) {
                    System.out.println("INTO for");
                    System.out.println("data size: " + data.size());
                    int size = tempDatastore.getResultObjects().size();
                    System.out.println("size: " + size);

                    saveDataToExcel(tempDatastore);

                    emailController.sendMail(scrappingFormData.getEmail());
                }
            });
        }

        return "redirect:/main/search";
    }

    public void sendMail(Workbook workbook, String email) {
        System.out.println("Sending Email");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("C:\\Users\\Roman_Sobolevskyi\\Desktop\\newFile23234.xlsx");
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//

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

    public void saveDataToExcel(TempDatastore datastores) {
        List<ResultObject> resultObjects = datastores.getResultObjects();
        System.out.println("inside excel Working");
        Workbook xssfWorkbook = new XSSFWorkbook();
        new ExcelConverter().fillWorkbook(xssfWorkbook, resultObjects);
        new ExcelStyle().setStyle(xssfWorkbook);
        System.out.println("excel finished");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("src\\main\\resources\\output\\file.xlsx");
            xssfWorkbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
