package com.stackroute.resourcerequestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ResourceRequestServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceRequestServiceApplication.class, args);
    }
}
