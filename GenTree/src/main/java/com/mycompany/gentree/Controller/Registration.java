/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gentree.Controller;

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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("/")
public class Registration {
    @POST
    @Path("/register")
    @Produces("text/plain") //Что возвращает
    @Consumes("text/plain") //что принимает
    //        .header("text/plain", "value2")
    public Response register(String data){
        System.out.println(data);
        Response.ResponseBuilder rb = Response.ok("Registration Complete");
        Response response = rb.build(); 
        return response;
    }
}
