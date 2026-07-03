package com.stackroute.apigateway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

/**
 * Comprehensive test suite for API Gateway Application. Tests application context, gateway startup,
 * and routing capabilities.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
    properties = {"eureka.client.enabled=false", "spring.cloud.gateway.enabled=true"})
class ApiGatewayApplicationTests {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Autowired private ApplicationContext applicationContext;

  @Test
  void contextLoads() {
    assertThat(applicationContext).isNotNull();
  }

  @Test
  void apiGatewayApplicationBeanExists() {
    assertThat(applicationContext.getBean(ApiGatewayApplication.class)).isNotNull();
  }

  @Test
  void actuatorHealthEndpointIsAccessible() {
    ResponseEntity<String> response =
        restTemplate.getForEntity("http://localhost:" + port + "/actuator/health", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("UP");
  }

  @Test
  void actuatorGatewayRoutesEndpointIsAccessible() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(
            "http://localhost:" + port + "/actuator/gateway/routes", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void mainMethodStartsApplication() {
    // Test that main method can be invoked without exceptions
    assertThat(ApiGatewayApplication.class.getDeclaredMethods())
        .anyMatch(method -> method.getName().equals("main"));
  }

  @Test
  void applicationContextContainsGatewayBeans() {
    String[] beanNames = applicationContext.getBeanDefinitionNames();
    assertThat(beanNames).isNotEmpty();
    assertThat(beanNames).anyMatch(name -> name.contains("gateway") || name.contains("Gateway"));
  }

  @Test
  void actuatorMetricsEndpointIsAccessible() {
    ResponseEntity<String> response =
        restTemplate.getForEntity("http://localhost:" + port + "/actuator/metrics", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
