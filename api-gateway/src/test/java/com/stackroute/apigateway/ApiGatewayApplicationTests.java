package com.stackroute.apigateway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Tests for API Gateway application context and actuator endpoints.
 * Uses WebTestClient since Spring Cloud Gateway is reactive (WebFlux).
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "eureka.client.enabled=false",
    "management.endpoints.web.exposure.include=health,info,metrics,gateway"
})
class ApiGatewayApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void actuatorHealthEndpointIsAccessible() {
        webTestClient.get()
            .uri("/actuator/health")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .value(body -> assertThat(body).contains("UP"));
    }

    @Test
    void mainMethodExists() {
        assertThat(ApiGatewayApplication.class.getDeclaredMethods())
            .anyMatch(method -> method.getName().equals("main"));
    }
}
