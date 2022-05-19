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
        //System.out.println(data);
        Jsonb jb = JsonbBuilder.create();

        Person person = jb.fromJson("{\"email\":\"test@mail.com\", \"password\":\"123\",\"date_of_bith\":\"2020:05:10\",\"name\":\"Test\",\"secondName\":\"Test\",\"fathersName\":\"Test\"}",Person.class);
        if(db.checkPersonInDataBase(person)){
            Integer id = db.registrationPerson(person);
            if(id != 0){
                person.setUserId(id);
            }
            db.createPerson(person);
            Response.ResponseBuilder rb = Response.ok("Registration Complete");
            Response response = rb.build(); 
            return response;
        }
        else{
            Response.ResponseBuilder rb = Response.ok("Such User already exist");
            Response response = rb.build(); 
            return response;
        }
        
    }
}
