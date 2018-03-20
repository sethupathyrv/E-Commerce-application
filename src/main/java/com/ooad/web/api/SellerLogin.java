/*
 * Created by Shravan on  13/2/18 11:00 PM.
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.api;

import com.ooad.web.dao.SellerDao;
import com.ooad.web.dao.UserDao;
import com.ooad.web.model.User;
import com.ooad.web.utils.TokenAuth;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/seller")
public class SellerLogin {
    @POST
    @Path("/login")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateSellerLogin(String req) {
        JSONObject reqJson = new JSONObject(req);
        final String email = reqJson.getString("email");
        final String password = reqJson.getString("psword");
        final JSONObject jsonObject;
        final SellerDao sellerDao = new SellerDao();
        jsonObject = sellerDao.validateSellerLogin(email, password);
        return Response.status(Status.OK).entity(jsonObject.toString()).build();
    }
    @POST
    @Path("/register")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sellerRegister(String req) {
        final JSONObject reqJson = new JSONObject(req);
        final String userName = reqJson.getString("username");
        final String email = reqJson.getString("email");
        final String password = reqJson.getString("psword");
        final SellerDao sellerDao = new SellerDao();
        final JSONObject jsonObject = sellerDao.validateSellerRegister(userName, email, password);
        return Response.status(Status.OK).entity(jsonObject.toString()).build();
    }

}
