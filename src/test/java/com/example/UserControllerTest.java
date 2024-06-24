// package com.example;

// import io.quarkus.test.junit.QuarkusTest;
// import io.quarkus.test.common.http.TestHTTPEndpoint;
// import org.junit.jupiter.api.Test;

// import com.example.controller.UserController;

// import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.is;

// @QuarkusTest
// @TestHTTPEndpoint(UserController.class)
// public class UserControllerTest {

//     @Test
//     public void helloEndpoint() {
//         given()
//             .when()
//                 .get("/")
//             .then()
//                 .body(is("hello world!\n"));
//     }

//     @Test
//     public void echoEndpoint() {
//         given()
//             .when()
//                 .body("repeat after me...")
//                 .post("/echo")
//             .then()
//                 .body(is("repeat after me..."));
//     }
// }