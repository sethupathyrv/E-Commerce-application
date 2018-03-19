package com.ooad.web.api;

import com.ooad.web.model.User;
import com.ooad.web.utils.TokenAuth;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserService {
    @POST
    @Path("/address")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAddress(@HeaderParam("authToken") String token, String req) {
        User user = TokenAuth.getUserFromToken(token);
        JSONObject reqJson = new JSONObject(req);
        if(user == null){
            return Response.status(Response.Status.OK).entity(new JSONObject().put("status", Response.Status.UNAUTHORIZED.getStatusCode())
                    .toString()).build();
        }
        JSONObject j = user.addAddress(reqJson);
        return Response.status(Response.Status.OK).entity(j.toString()).build();
    }


    @Path("/address/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAddress(@HeaderParam("authToken") String token, @PathParam("id") int id) {
        User user = TokenAuth.getUserFromToken(token);
        if(user == null){
            return Response.status(Response.Status.OK).entity(new JSONObject().put("status", Response.Status.UNAUTHORIZED.getStatusCode())
                    .toString()).build();
        }
        JSONObject j = user.deleteAddress(id);
        return Response.status(Response.Status.OK).entity(j.toString()).build();

    }

    @Path("/address/{id}")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAddress(@HeaderParam("authToken") String token, String req,@PathParam("id") int id) {
        User user = TokenAuth.getUserFromToken(token);
        JSONObject reqJson = new JSONObject(req);
        if(user == null){
            return Response.status(Response.Status.OK).entity(new JSONObject().put("status", Response.Status.UNAUTHORIZED.getStatusCode())
                    .toString()).build();
        }
        JSONObject j = user.updateAddress(reqJson,id);
        return Response.status(Response.Status.OK).entity(j.toString()).build();

    }

}

