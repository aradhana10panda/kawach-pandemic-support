package com.stackroute.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic smoke test for Config Server application context.
 * Uses native (file-system) backend to avoid requiring a real git repo.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = {
    "spring.cloud.config.server.native.search-locations=classpath:/config",
    "spring.profiles.active=native",
    "eureka.client.enabled=false",
    "spring.cloud.config.enabled=false"
})
class ConfigServerApplicationTests {

    @Test
    void contextLoads() {
        // Verifies the Spring application context starts without errors
        assertTrue(true, "Application context loaded successfully");
    }
}
