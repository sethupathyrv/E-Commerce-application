/*
 * Created by Sandeep Tadepalli on 10/02/18 19:52
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.api;

import com.ooad.web.dao.UserDao;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/login")
public class LoginService {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayPlainTextHello(@QueryParam("email") final String email,
                                      @QueryParam("psword") final String password) {
        final JSONObject jsonObject = new JSONObject();
        final UserDao userDao = new UserDao();
        userDao.validateLogin(email, password);
        return Response.status(Status.OK).entity(jsonObject.toString()).build();
    }
}
