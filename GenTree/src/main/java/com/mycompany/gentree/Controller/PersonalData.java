/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gentree.Controller;

/**
 *
 * @author 4eis
 */

import com.mycompany.gentree.Model.IApp;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;



import jakarta.ws.rs.POST;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.core.Response;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import jakarta.ws.rs.Path;


@Path("/")
public class PersonalData {
    @Inject
    private IApp app;
    
    @POST
    @Path("/personalData")
    @Produces("text/plain") //Что возвращает
    @Consumes("text/plain") //что принимает
    public Response personalData(String data){
        
        String answer = app.personalData(data);
        Response.ResponseBuilder rb = Response.ok(answer);
        Response response = rb.build(); 
        return response;
    }
}
