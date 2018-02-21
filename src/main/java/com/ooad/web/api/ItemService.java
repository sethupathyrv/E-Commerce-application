/*
 * Created by Sandeep Tadepalli on 20/02/18 18:32
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.api;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;

@Path("/item")
public class ItemService {
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") int id){
        ItemDao itemDao = new ItemDao();
        Item item = itemDao.getItembyId(id);
        JSONObject returnObject = new JSONObject();
        if(item!=null){
            returnObject.put("item",item.toJSON());
            returnObject.put("status",Status.OK.getStatusCode() );
            returnObject.put("errors","");
            return Response.status(Status.OK).entity(returnObject.toString()).build();
        }
        else{
            System.out.println();
            returnObject.put("item","" );
            returnObject.put("status",Status.BAD_REQUEST.getStatusCode() );
            returnObject.put("errors","No Item Exists" );
            return Response.status(Status.OK).entity(returnObject.toString()).build();
        }
    }

    @Path("lastfive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastFive(){
        ItemDao itemDao = new ItemDao();
        ArrayList<Item> items = (ArrayList<Item>) itemDao.getLastFiveItems();
        final JSONArray j = new JSONArray();
        for (Item item : items){
            j.put(item.toJSON());
        }
        return Response.status(Status.OK).entity(new JSONObject().put("items",j).toString()).build();
    }

}
