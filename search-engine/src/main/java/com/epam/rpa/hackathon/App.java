package com.epam.rpa.hackathon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
//@ComponentScan("com.epam.rpa")
//@ComponentScan(basePackageClasses = MainController.class)
public class App {
    public static void main(String[] args) {
//        System.out.println(new GetLvivOnlineEventsAction().getEventsJson());

        SpringApplication.run(App.class, args);
    }
}
