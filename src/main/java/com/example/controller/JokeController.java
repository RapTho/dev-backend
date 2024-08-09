// src/main/java/com/example/controller/UserController.java
package com.example.controller;

import com.example.service.JokeService;

import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

import io.smallrye.mutiny.Uni;;

@Path("/joke")
@Produces(MediaType.TEXT_PLAIN)
public class JokeController {

    @Inject
    JokeService jokeService;

    @GET
    public Uni<String> getJoke() {
        return jokeService.getRandomJoke();
    }
}
