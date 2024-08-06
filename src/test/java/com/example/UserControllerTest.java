package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import jakarta.transaction.Transactional;

import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
// @TestHTTPEndpoint(UserController.class)
public class UserControllerTest {
    @Test
    @Transactional
    public void testGetAllUsers() {
        given()
            .when().get("/users")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", hasSize(greaterThan(0)));
    }

    @Test
    @Transactional
    public void testGetUserById() {
        given()
            .when().get("/users/1")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(1));
    }

    @Test
    @Transactional
    public void testCreateUser() {
        String userJson = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}";

        given()
            .contentType(ContentType.JSON)
            .body(userJson)
            .when().post("/users")
            .then()
            .statusCode(201)
            .contentType(ContentType.JSON)
            .body("name", equalTo("John Doe"))
            .body("email", equalTo("john.doe@example.com"));
    }

    @Test
    @Transactional
    public void testUpdateUser() {
        String userJson = "{\"name\":\"Jane Doe\",\"email\":\"jane.doe@example.com\"}";

        given()
            .contentType(ContentType.JSON)
            .body(userJson)
            .when().put("/users/1")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("name", equalTo("Jane Doe"))
            .body("email", equalTo("jane.doe@example.com"));
    }

    @Test
    @Transactional
    public void testDeleteUser() {
        given()
            .when().delete("/users/1")
            .then()
            .statusCode(200);
    }
}