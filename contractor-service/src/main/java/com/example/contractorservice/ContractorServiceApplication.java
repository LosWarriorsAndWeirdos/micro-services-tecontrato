package com.example.contractorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ContractorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractorServiceApplication.class, args);
	}

}
