package com.rpa.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmailServiceApplication {

	@Bean
	public WebClient getWebClient(){
	    return WebClient.create();
    }

    @Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
