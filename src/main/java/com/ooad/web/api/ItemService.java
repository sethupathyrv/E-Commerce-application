/*
 * Created by Sandeep Tadepalli on 20/02/18 18:32
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.api;

import com.ooad.web.dao.ItemCategoryDao;
import com.ooad.web.dao.ItemDao;
import com.ooad.web.model.*;
import com.ooad.web.utils.TokenAuth;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.*;
import java.util.ArrayList;

@Path("/item")
public class ItemService {
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") int id) {
        Item item = Item.find(id);
        JSONObject returnObject = new JSONObject();
        if (item != null) {
            returnObject.put("item", item.toJSON());
            returnObject.put("status", Status.OK.getStatusCode());
            returnObject.put("errors", "");
            return Response.status(Status.OK).entity(returnObject.toString()).build();
        } else {
            returnObject.put("item", "");
            returnObject.put("status", Status.BAD_REQUEST.getStatusCode());
            returnObject.put("errors", "No Item Exists");
            return Response.status(Status.OK).entity(returnObject.toString()).build();
        }
    }

    @Path("lastfive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastFive() {
        ArrayList<Item> items = Item.getLastFive();
        final JSONArray j = new JSONArray();
        for (Item item : items) {
            j.put(item.toJSON());
        }
        return Response.status(Status.OK).entity(new JSONObject().put("items", j).toString()).build();
    }

    @Path("add")
    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(@FormDataParam("file") InputStream fileInputStream,
                            @FormDataParam("file") FormDataContentDisposition fileMetaData,
                            @FormDataParam("json") String item,
                            @HeaderParam("authToken") String token) throws Exception {
        Seller seller = TokenAuth.getSellerFromToken(token);
        if (seller == null) {
            return Response.status(Status.OK).entity(new JSONObject().put("status", Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        }
        JSONObject itemObject = new JSONObject(item);
        JSONObject jsonObject;
        jsonObject = seller.addItem(itemObject, fileInputStream);
        return Response.status(Status.OK).entity(jsonObject.toString()).build();
    }

    @Path("addtocart")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addToCart(String req, @HeaderParam("authToken") String token) {
        JSONObject re = new JSONObject(req);
        User user = TokenAuth.getUserFromToken(token);
        if (user == null) {
            return Response.status(Status.OK).entity(new JSONObject().put("status", Status.UNAUTHORIZED.getStatusCode())
                    .toString()).build();
        }
        JSONObject j = user.addItemToCart(re);
        return Response.status(Status.OK).entity(j.toString()).build();
    }

    @Path("/{category}/{subcategory}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsfromCategory(@PathParam("category") String category,@PathParam("subcategory") String subcategory) {

        String CategoryName = category;
        String SubCategoryName = subcategory;
        ArrayList<Item> items = Item.getItemsfromCategory(CategoryName,SubCategoryName);
        final JSONArray j = new JSONArray();
        for (Item item : items) {
            j.put(item.toJSON());
        }
        return Response.status(Status.OK).entity(new JSONObject().put("items", j).toString()).build();
    }

    @Path("/getcategories")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() {
        ItemCategoryDao itemCategoryDao = new ItemCategoryDao();
        final JSONArray j = new JSONArray();
        ArrayList<ItemCategory> itemCategories = (ArrayList<ItemCategory>) itemCategoryDao.getAllCategories();
        for(ItemCategory itemCategory: itemCategories){
            ArrayList<ItemSubCategory> itemsubCategories = (ArrayList<ItemSubCategory>) itemCategoryDao.getAllsubCategories(itemCategory.getId());
            JSONArray subCategories = new JSONArray();
            for(ItemSubCategory itemSubCategory:itemsubCategories){
                JSONObject temp = new JSONObject();
                temp.put("name",itemSubCategory.getDisplayName());
                temp.put("id",itemSubCategory.getId());
                subCategories.put(temp);
            }
            String name = itemCategory.getDisplayName();
            JSONObject jo = new JSONObject();
            jo.put("name",name);
            jo.put("id",itemCategory.getId());
            jo.put("subcategories",subCategories);
            j.put(jo);
        }

        return Response.status(Status.OK).entity(new JSONObject().put("categories", j).toString()).build();
    }

}