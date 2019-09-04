package com.rpa.controltower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class ControlTowerApplication {

	@Bean
	public WebClient getWebClient(){
		return WebClient.create();
	}

	public static void main(String[] args) {
		SpringApplication.run(ControlTowerApplication.class, args);
	}

}
