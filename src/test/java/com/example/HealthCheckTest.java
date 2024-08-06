package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class HealthCheckTest {

    @Test
    public void testHealthEndpoint() {
        
    given()
        .when().get("/q/health")
        .then()
        .statusCode(200)
        .body("status", Matchers.equalTo("UP"));
    }
}
