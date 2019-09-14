package com.rpa.controltower;

import com.rpa.controltower.datastore.DatastoreFactory;
import com.rpa.controltower.datastore.TempDatastore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class ControlTowerApplication {

    @Bean
    public TempDatastore getTempDatastore() {
        return DatastoreFactory.create();
    }

    @Bean
    @LoadBalanced
    public WebClient getWebClient() {
        return WebClient.create();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ControlTowerApplication.class, args);
    }

//    public static void main(String[] args) throws IOException {
//        ResultObject resultObject = new ResultObject();
//        ResultObject resultObject1 = new ResultObject();
//        SiteData siteData = new SiteData();
//        SiteData siteData2 = new SiteData();
//        siteData.setSiteId("sheetName");
//        resultObject.setSiteData(siteData);
//        siteData2.setSiteId("sheetName22222");
//        resultObject1.setSiteData(siteData2);
//        List<IEvent> events = new ArrayList<>();
//        IEvent event = new Event();
//        event.setTitle("asdadas");
//        event.setDescription("asdadas");
//
//
//        IEvent event1 = new Event();
//        event1.setTitle("asdaaaaaadas");
//        event1.setDescription("asdaaaaadas");
//        events.add(event);
//        events.add(event1);
//
//        resultObject.setEventList(events);
//        resultObject1.setEventList(events);
//
//        Workbook xssfWorkbook = new XSSFWorkbook();
//        Workbook workbook = new ExcelConverter()
//                .fillWorkbook(xssfWorkbook, Arrays.asList(resultObject, resultObject1));
//
//        new ExcelStyle().setStyle(workbook);
//
//
//        FileOutputStream out = new FileOutputStream("C:\\Users\\Roman_Sobolevskyi\\Desktop\\newFile23234.xlsx");
//
//        workbook.write(out);
//        out.close();
//
//
//    }

    ;
}
