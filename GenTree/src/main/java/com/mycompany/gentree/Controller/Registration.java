/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gentree.Controller;

import com.mycompany.gentree.Model.IApp;
import com.mycompany.gentree.Model.DataBase.IDataBase;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;


import jakarta.ws.rs.POST;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.core.Response;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;


@Path("/")
public class Registration {
    
    @Inject
    private IApp app;
    @Inject
    private IDataBase db;
    
    
    @POST
    @Path("/register")
    @Produces("text/plain") //Что возвращает
    @Consumes("text/plain") //что принимает
    //        .header("text/plain", "value2")
    public Response register(String data){
        System.out.println(data);
        Jsonb jb = JsonbBuilder.create();

        User user = jb.fromJson("{\"name\":\"Arseniy\",\"secondName\":\"Kalinko\",\"fathersName\":\"Evgenievizh\",\"email\":\"ars050402@gmail.com\",\"password\":\"123\"}",User.class);
        System.out.println(user.getEmail());
        db.registration(user);
        Response.ResponseBuilder rb = Response.ok("Registration Complete");
        Response response = rb.build(); 
        return response;
    }
}
