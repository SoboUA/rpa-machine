package com.epam.rpa.hackathon;

import com.epam.rpa.hackathon.web.lvivonline.GetLvivOnlineEventsAction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

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
