package com.mycompany.gentree.resources;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("/")
public class JakartaEE9Resource {
    
    @POST
    @Consumes("text/plain")
    @Produces("application/JSON")
    public Response Register(){

        String res = "";

        Response.ResponseBuilder rb = Response.ok(res);
        Response result = rb.header("Acccess-Control-Allow-Origin", "*").build();
        return result;
    }
}
