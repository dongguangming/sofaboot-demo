package com.example.demo.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/rest")
//@Consumes("application/json;charset=UTF-8")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces("application/json;charset=UTF-8")
public interface HelloService {

    @GET
    @Path("/sayName/{string}")
    String saySync(String string);
}