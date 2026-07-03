package com.stackroute.apigateway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

/** Integration tests for API Gateway. Tests gateway routing and actuator endpoints. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
    properties = {"eureka.client.enabled=false", "spring.cloud.gateway.enabled=true"})
class ApiGatewayIntegrationTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void actuatorHealthShowsUpStatus() {
    ResponseEntity<String> response =
        restTemplate.getForEntity("http://localhost:" + port + "/actuator/health", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("\"status\":\"UP\"");
  }

  @Test
  void actuatorGatewayRoutesEndpointReturnsRoutes() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(
            "http://localhost:" + port + "/actuator/gateway/routes", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void actuatorMetricsEndpointIsAvailable() {
    ResponseEntity<String> response =
        restTemplate.getForEntity("http://localhost:" + port + "/actuator/metrics", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("names");
  }

  @Test
  void actuatorInfoEndpointIsAvailable() {
    ResponseEntity<String> response =
        restTemplate.getForEntity("http://localhost:" + port + "/actuator/info", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void actuatorGatewayGlobalFiltersEndpointIsAvailable() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(
            "http://localhost:" + port + "/actuator/gateway/globalfilters", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void actuatorPrometheusEndpointIsAvailable() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(
            "http://localhost:" + port + "/actuator/prometheus", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
