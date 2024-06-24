// src/main/java/com/example/controller/UserController.java
package com.example.controller;

import com.example.model.AppUser;
import com.example.service.AppUserService;

import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppUserController {

    @Inject
    AppUserService userService;

    @GET
    public List<AppUser> getAllUsers() {
        return userService.listAllUsers();
    }

    @GET
    @Path("/{id}")
    public AppUser getUserById(@PathParam("id") Long id) {
        return userService.findUserById(id);
    }

    @POST
    public Response createUser(AppUser user) {
        AppUser createdUser = userService.createUser(user);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, AppUser user) {
        AppUser updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return Response.ok(updatedUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
