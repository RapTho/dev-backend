package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import jakarta.transaction.Transactional;

import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {
    static Integer userId;

    @Test
    @Transactional
    @Order(1)
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
    @Order(2)
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
    @Order(3)
    public void testCreateUser() {
        String userJson = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"age\":\"40\"}";

        userId = given()
                .contentType(ContentType.JSON)
                .body(userJson)
                .when().post("/users")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", equalTo("John Doe"))
                .body("email", equalTo("john.doe@example.com"))
                .extract().response().body().path("id");
    }

    @Test
    @Transactional
    @Order(4)
    public void testUpdateUser() {
        String userJson = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"age\":\"45\"}";

        given()
                .contentType(ContentType.JSON)
                .body(userJson)
                .when().put("/users/" + Integer.toString(userId))
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("John Doe"))
                .body("email", equalTo("john.doe@example.com"))
                .body("age", equalTo(45));
        ;
    }

    @Test
    @Transactional
    @Order(5)
    public void testDeleteUser() {
        given()
                .when().delete("/users/" + Integer.toString(userId))
                .then()
                .statusCode(200);
    }
}