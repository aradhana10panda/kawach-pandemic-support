package com.stackroute.patientotpservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PatientOtpServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatientOtpServiceApplication.class, args);
    }
}
