package com.stackroute.eurekaserver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

/** Integration tests for Eureka Server. Tests complete server functionality and API endpoints. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
    properties = {"eureka.client.register-with-eureka=false", "eureka.client.fetch-registry=false"})
class EurekaServerIntegrationTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void eurekaServerDashboardIsAccessible() {
    ResponseEntity<String> response =
        restTemplate.getForEntity("http://localhost:" + port + "/", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("Eureka");
  }

  @Test
  void eurekaAppsEndpointReturnsValidXml() {
    ResponseEntity<String> response =
        restTemplate.getForEntity("http://localhost:" + port + "/eureka/apps", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("<applications>");
  }

  @Test
  void actuatorHealthShowsUpStatus() {
    ResponseEntity<String> response =
        restTemplate.getForEntity("http://localhost:" + port + "/actuator/health", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("\"status\":\"UP\"");
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
}
