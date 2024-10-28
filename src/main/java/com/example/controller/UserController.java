// src/main/java/com/example/controller/UserController.java
package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;

import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.List;

import io.smallrye.mutiny.Uni;;

@Path("/users")
public class UserController {

    @Inject
    UserService userService;

    @GET
    public Uni<List<User>> getAllUsers(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("50") int size,
            @QueryParam("sort") @DefaultValue("id") String sortKey) {
        return userService.getAllUsers(page, size, sortKey);
    }

    @GET
    @Path("/{id}")
    public Uni<User> getUserById(@PathParam("id") Long id) {
        return userService.findUserById(id);
    }

    @POST
    public Uni<Response> createUser(User user) {
        return userService.createUser(user)
                .onItem()
                .transform(createdUser -> Response.status(Response.Status.CREATED).entity(createdUser).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> updateUser(@PathParam("id") Long id, User user) {
        return userService.updateUser(id, user)
                .onItem().ifNotNull().transform(updatedUser -> Response.ok(updatedUser).build())
                .onItem().ifNull().continueWith(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteUser(@PathParam("id") Long id) {
        return userService.deleteUser(id)
                .onItem().transform(deleted -> {
                    if (deleted) {
                        return Response.status(Response.Status.OK).build();
                    } else {
                        return Response.status(Response.Status.NOT_FOUND).build();
                    }
                });
    }
}
