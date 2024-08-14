package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class JokeControllerTest {

    @Test
    public void testGetJoke() {
        given()
                .when().get("/joke")
                .then()
                .statusCode(200)
                .contentType("text/plain")
                .body(notNullValue(String.class));
    }
}
