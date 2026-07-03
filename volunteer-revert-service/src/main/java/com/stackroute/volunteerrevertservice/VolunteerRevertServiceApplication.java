package com.stackroute.volunteerrevertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VolunteerRevertServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VolunteerRevertServiceApplication.class, args);
    }
}
