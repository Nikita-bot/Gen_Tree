/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gentree.resources;

import jakarta.ws.rs.Path;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.core.Response;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;

import java.util.ArrayList;
import java.util.List;


@Path("/")
public class Ping {
    @GET
    @Path("/ping")
    //@Produces("text/plain") //Что возвращает
    //        .header("text/plain", "value2")
    public Response ping(){
        Response.ResponseBuilder rb = Response.ok("ping");
        Response response = rb.build(); 
        return response;
    }

}