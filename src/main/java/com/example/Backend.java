package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
// import org.json.JSONObject;

@Path("/")
public class Backend {

    // @GET
    // @Produces(MediaType.APPLICATION_JSON)
    // public JSONObject hello(@QueryParam("format") String format) {
    // JSONObject res = new JSONObject();
    // if (format == "json") {
    // return res.put("message", "hello world!");
    // }
    // return res.put("error", "wrong format!");
    // }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello world!\n";
    }

    @POST
    @Path("echo")
    @Produces(MediaType.APPLICATION_JSON)
    public String echo(String message) {
        return message;
    }
}
