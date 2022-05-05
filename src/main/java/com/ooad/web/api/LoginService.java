package com.ooad.web.api;

import com.ooad.web.dao.UserDao;
import com.ooad.web.model.User;
import com.ooad.web.utils.TokenAuth;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/login")
public class LoginService {
    @POST
    @Path("/login")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateLogin(String req) {
        JSONObject reqJson = new JSONObject(req);
        final String email = reqJson.getString("email");
        final String password = reqJson.getString("psword");
        final JSONObject jsonObject;
        final UserDao userDao = new UserDao();
        //TODO Change validate login to user Model
        jsonObject = userDao.validateLogin(email, password);
        return Response.status(Status.OK).entity(jsonObject.toString()).build();
    }

    /*@POST
    @Path("/register")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response userRegister(String req) {
        final JSONObject reqJson = new JSONObject(req);
        final String userName = reqJson.getString("username");
        final String email = reqJson.getString("email");
        final String password = reqJson.getString("psword");
        //TODO Change validateRegister to user model
        final UserDao userDao = new UserDao();
        final JSONObject jsonObject = userDao.validateRegister(userName, email, password);
        return Response.status(Status.OK).entity(jsonObject.toString()).build();
    }
*/
    @GET
    @Path("/test")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response testLoggedInUser(@HeaderParam("authToken") String token) {
        User user = TokenAuth.getUserFromToken(token);
        System.out.println(token);
        System.out.println(user);
        if (user == null) {
            return Response.status(Status.OK).entity(
                    new JSONObject().put("status", Status.UNAUTHORIZED).toString()
            ).build();
        } else {
            return Response.status(Status.OK).entity(user.toJSON().toString()).build();
        }
    }
}

