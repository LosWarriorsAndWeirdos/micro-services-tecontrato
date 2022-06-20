package com.example.propietarioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PropietarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropietarioServiceApplication.class, args);
    }

}
