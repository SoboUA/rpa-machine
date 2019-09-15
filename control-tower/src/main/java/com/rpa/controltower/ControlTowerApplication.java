package com.rpa.controltower;

import com.rpa.controltower.converter.excel.ExcelConverter;
import com.rpa.controltower.converter.excel.ExcelStyle;
import com.rpa.controltower.datastore.DatastoreFactory;
import com.rpa.controltower.datastore.TempDatastore;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

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


//    public static void main(String[] args) throws IOException, IOException {
//
//
//        Workbook xssfWorkbook = new XSSFWorkbook();
//        Workbook workbook = new ExcelConverter()
//                .fillWorkbook(xssfWorkbook, Arrays.asList());
//
//        new ExcelStyle().setStyle(workbook);
//
////        System.out.println(Class.getResource().getPath().toString());
//        FileOutputStream out = new FileOutputStream("src\\main\\resources\\output\\file.xlsx");
//
//        workbook.write(out);
//        out.close();
//
//
//    }
}
