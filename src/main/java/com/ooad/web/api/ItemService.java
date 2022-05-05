package com.ooad.web.api;

import com.ooad.web.dao.*;
import com.ooad.web.model.*;
import com.ooad.web.utils.TokenAuth;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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

    @Path("/{category}/{subCategory}/{sortby}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsfromCategory(@PathParam("category") String category,@PathParam("subCategory") String subCategory,@PathParam("sortby") String sortby) {
        //TODO SortBy not Implemented
        final JSONArray j = new JSONArray();
        ArrayList<Item> items = Item.getItemsfromCategory(category, subCategory);
        for (Item item : items) {
                j.put(item.toJSON());
        }
        return Response.status(Status.OK).entity(new JSONObject().put("items", j).toString()).build();
    }

    @Path("/{category}/{subCategory}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsfromCategory(@PathParam("category") String category,@PathParam("subCategory") String subCategory) {
        final JSONArray j = new JSONArray();
        ArrayList<Item> items = Item.getItemsfromCategory(category, subCategory);
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

    @Path("/pricefilter")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterPrice(String req){
        JSONObject re = new JSONObject(req);
        int max = re.getInt("max");
        int min = re.getInt("min");
        JSONObject resp = re.getJSONObject("data");
        String clr=re.getString("color");
        JSONArray items = resp.getJSONArray("items");
        JSONArray items_new = new JSONArray();
        for (int i = 0; i < items.length(); i++) {
            JSONObject json = (JSONObject) items.get(i);
            int price = json.getInt("price");
            String itemcolour=json.getString("itemColour");
            if ((min <= price && price <= max) && clr.equals(itemcolour)){
                items_new.put(json);
            }
        }
        JSONObject resp2 = new JSONObject();
        resp2.put("items",items_new);
        return Response.status(Status.OK).entity(resp2.toString()).build();
    }

    @Path("/filter")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response filter(String req){
        JSONObject re = new JSONObject(req);
        int max = re.getInt("max");
        int min = re.getInt("min");
        String Colour = re.getString("colour");
        JSONObject resp = re.getJSONObject("data");
        JSONArray items = resp.getJSONArray("items");
        JSONArray returnItems = new JSONArray();
        if(min >=0 && max > min){
            for(Object Item: items){
                JSONObject jItem = (JSONObject) Item;
                int price = jItem.getInt("price");
                if ((min <= price && price <= max)){
                    returnItems.put(jItem);
                }
            }
        }else{
            returnItems = items;
        }
        JSONArray returnItems1 = new JSONArray();
        if(!Colour.equals("000")){
            for(Object item: returnItems){
                JSONObject jItem  = (JSONObject) item;
                String colour = jItem.getString("itemColour");
                if(colour.equals(Colour)) {
                    returnItems1.put(item);
                }
            }
        }else{
            returnItems1 = returnItems;
        }
        JSONObject resp2 = new JSONObject();
        resp2.put("items",returnItems1);
        return Response.status(Status.OK).entity(resp2.toString()).build();
    }

    @Path("addcategory")
    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCategory(String req,@HeaderParam("authToken") String token) throws Exception {
        JSONObject re = new JSONObject(req);
        User user = TokenAuth.getUserFromToken(token);
        if (user == null) {
            return Response.status(Status.OK).entity(new JSONObject().put("status", Status.UNAUTHORIZED.getStatusCode())
                    .toString()).build();
        }
        ItemCategoryDao itemCategoryDao = new ItemCategoryDao();
        itemCategoryDao.createCategory(re);
        return null;
    }

    @Path("/updatecart")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCartItem(String req,@HeaderParam("authToken") String token) {
        JSONObject reqJson = new JSONObject(req);
        User user = TokenAuth.getUserFromToken(token);
        if (user == null) {
            return Response.status(Status.OK).entity(new JSONObject().put("status", Status.UNAUTHORIZED.getStatusCode())
                    .toString()).build();
        }
        Cart cart = user.getCart();
        int quantity = reqJson.getInt("quantity");
        int id = reqJson.getInt("cartItemId");
        JSONObject jsonObject = cart.updateCart(quantity,id);
        return Response.status(Status.OK).entity(jsonObject.toString()).build();
    }

    @Path("dispatchitem/{orderItemId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response dispatchItem(@HeaderParam("sellerAuthToken") String token,@PathParam("orderItemId") int orderItemId) {
        Seller seller = TokenAuth.getSellerFromToken(token);
        if (seller == null) {
            return Response.status(Status.OK).entity(new JSONObject().put("status", Status.UNAUTHORIZED.getStatusCode())
                    .toString()).build();
        }
        OrderItem orderItem = OrderItem.find(orderItemId);
        JSONObject resp = seller.dispatchOrderItem(orderItem);
        return Response.status(Status.OK).entity(resp.toString()).build();
    }

    @Path("delivered/{orderItemId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response itemDelivered(@HeaderParam("authToken") String token, @PathParam("orderItemId") int orderItemId){
        User u = TokenAuth.getUserFromToken(token);
        if(u == null){
            return Response.status(Status.OK).entity(new JSONObject().put("status", Status.UNAUTHORIZED.getStatusCode())
                    .toString()).build();
        }
        OrderItem orderItem = OrderItem.find(orderItemId);
        JSONObject resp = u.itemDelivered(orderItem);
        return Response.status(Status.OK).entity(resp.toString()).build();
    }

    @Path("/directbuy")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response directBuy(@HeaderParam("authToken")String token,String req){
        JSONObject jreq = new JSONObject(req);
        int itemId = jreq.getInt("itemId");
        int quantity = jreq.getInt("quantity");
        User u = TokenAuth.getUserFromToken(token);
        u.getCart().emptyCart();
        CartDao cartDao = new CartDao();
        Item i = Item.find(itemId);
        if(i.getQuantity() > quantity) {
            cartDao.insertItem(u.getId(), itemId, quantity);
            return Response.status(Status.OK).entity(new JSONObject().put("status", Status.OK.getStatusCode()).toString()).build();
        }else{
            return Response.status(Status.OK).entity(new JSONObject().put("status",Status.BAD_REQUEST.getStatusCode())
                    .put("error","Quantity Exeeded").toString()).build();
        }
    }

    @POST
    @Path("/list")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addToList(@HeaderParam("authToken")String token,String req){
        User u = TokenAuth.getUserFromToken(token);
        if(u==null){
            return Response.status(Status.OK).entity(new JSONObject().put("status", Status.UNAUTHORIZED.getStatusCode())).build();
        }
        JSONObject j = new JSONObject(req);
        int itemId = j.getInt("itemId");
        UserDao userDao = new UserDao();
        WishListItem w = userDao.addItemToWishList(u, itemId);
        return Response.status(Status.OK).entity(new JSONObject().put("status", Status.OK.getStatusCode()).toString()).build();
    }

    @DELETE
    @Path("/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeItemFromList(@HeaderParam("authToken")String token,@PathParam("id") int wishListItemId){
        User u = TokenAuth.getUserFromToken(token);
        UserDao userDao = new UserDao();
        userDao.removeWishListItem(wishListItemId);
        return Response.status(Status.OK).entity(new JSONObject().put("status", Status.OK.getStatusCode()).toString()).build();
    }

    @POST
    @Path("/movetocart")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response moveToCart(@HeaderParam("authToken")String token,String req){
        User u = TokenAuth.getUserFromToken(token);
        UserDao userDao = new UserDao();
        JSONObject j = new JSONObject(req);
        int wishListItemId = j.getInt("wishListItemId");
        WishListItem w = WishListItem.find(wishListItemId);
        userDao.moveToCart(u,w);
        return Response.status(Status.OK).entity(new JSONObject().put("status", Status.OK.getStatusCode()).toString()).build();
    }

    @POST
    @Path("/rating/{orderItemId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRating(@HeaderParam("authToken") String token,@PathParam("orderItemId") int orderItemId, String req){
        User u = TokenAuth.getUserFromToken(token);
        if(u == null){
            return Response.status(Status.OK).entity(new JSONObject()
                    .put("status",Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        }
        JSONObject j = new JSONObject(req);
        int rating = j.getInt("rating");
        u.rateItemSeller(orderItemId,rating);
        JSONObject resp = new JSONObject();
        resp.put("status",Status.OK.getStatusCode() );
        return Response.status(Status.OK).entity(resp.toString()).build();
    }
/*
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(@HeaderParam("sellerAuthToken")String token,@PathParam("id") int itemId,String req) {
        Seller s = TokenAuth.getSellerFromToken(token);
        JSONObject reqJson = new JSONObject(req);
        if (s == null) {
            return Response.status(Status.OK).entity(new JSONObject()
                    .put("status", Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        }
        Item item = Item.find(itemId);
        JSONObject jsonObject = item.updateItem(reqJson);
        return Response.status(Status.OK).entity(jsonObject.toString()).build();
    }*/


    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(@HeaderParam("sellerAuthToken")String token,@PathParam("id") int itemId,String req) {
        Seller s = TokenAuth.getSellerFromToken(token);
        JSONObject reqJson = new JSONObject(req);
        if (s == null) {
            return Response.status(Status.OK).entity(new JSONObject()
                    .put("status", Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        }
        Item item = Item.find(itemId);
        JSONObject jsonObject = item.updateItem(reqJson);
        return Response.status(Status.OK).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("/compare/{id_1}/{id_2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response compareItems(@PathParam("id_1")int itemId1, @PathParam("id_2") int itemId2 ){
        Item i1 = Item.find(itemId1);
        Item i2 = Item.find(itemId2);
        JSONObject r = new JSONObject();
        r.put("item1",i1.toJSON() );
        r.put("item2",i2.toJSON() );
        return Response.status(Status.OK).entity(r.toString()).build();

    }

    @DELETE
    @Path("/cart/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCartItem(@PathParam("id") int  cartItemId){
        CartDao cartDao = new CartDao();
        cartDao.removeItem(cartItemId);
        JSONObject r = new JSONObject();
        return Response.status(Status.OK).entity(r.toString()).build();
    }

    @GET
    @Path("/seller/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSellerItems(@PathParam("id")String email){
        Seller s = new SellerDao().getSeller(email);
        ItemDao itemDao = new ItemDao();
        ArrayList<Item> items = (ArrayList<Item>) itemDao.getSellerItem(s.getId());
        JSONObject j = new JSONObject();
        JSONArray jitems = new JSONArray();
        int count = 1;
        for (Item i : items) {
            jitems.put(new JSONObject().put("item", i.toJSON()).put("srlNo",count ));
            count++;
        }
        j.put("items",jitems );
        return Response.status(Status.OK).entity(j.toString()).build();

    }

    @GET
    @Path("/filter/quantity/{sellerId}/{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSellerItems(@PathParam("sellerId")String email,
                                   @PathParam("from") int from,
                                   @PathParam("to") int to){
        Seller s = new SellerDao().getSeller(email);
        ItemDao itemDao = new ItemDao();
        ArrayList<Item> items = (ArrayList<Item>) itemDao.getSellerItem(s.getId());
        JSONObject j = new JSONObject();
        JSONArray jitems = new JSONArray();
        for (Item i : items) {
            int qSold = itemDao.getQuantitySold(i);
            if(qSold >= from && qSold <=to){
                jitems.put(i.toJSON());
            }
        }
        j.put("items",jitems );
        return Response.status(Status.OK).entity(j.toString()).build();

    }


}