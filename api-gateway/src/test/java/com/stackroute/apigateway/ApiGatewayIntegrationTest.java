package com.stackroute.apigateway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for API Gateway actuator endpoints.
 * Uses WebTestClient (reactive) since Gateway runs on WebFlux.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "eureka.client.enabled=false",
    "management.endpoints.web.exposure.include=health,info,metrics,gateway"
})
class ApiGatewayIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void actuatorHealthShowsUpStatus() {
        webTestClient.get()
            .uri("/actuator/health")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .value(body -> assertThat(body).contains("UP"));
    }

    @Test
    void actuatorGatewayRoutesEndpointReturnsRoutes() {
        webTestClient.get()
            .uri("/actuator/gateway/routes")
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void actuatorMetricsEndpointIsAvailable() {
        webTestClient.get()
            .uri("/actuator/metrics")
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void actuatorInfoEndpointIsAvailable() {
        webTestClient.get()
            .uri("/actuator/info")
            .exchange()
            .expectStatus().isOk();
    }
}
