package com.epam.rpa.hackathon;

import com.epam.rpa.hackathon.web.gastroli.GetGastroliUaEventsAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.rpa.hackathon.web.ticketclub.GetTicketClubEventsAction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
//@ComponentScan("com.epam.rpa")
//@ComponentScan(basePackageClasses = MainController.class)
public class SearchEngine {
    public static void main(String[] args) {
        SpringApplication.run(SearchEngine.class, args);
    }
}
