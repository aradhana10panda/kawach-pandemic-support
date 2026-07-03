package com.stackroute.doctorconsultationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DoctorConsultationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoctorConsultationServiceApplication.class, args);
    }
}
