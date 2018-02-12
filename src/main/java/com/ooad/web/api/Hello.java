/*
 * Created by Sandeep Tadepalli on 11/02/18 21:20
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello(@QueryParam("name") String name) {
        return name;
    }
}
