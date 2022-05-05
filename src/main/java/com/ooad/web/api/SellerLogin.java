package com.ooad.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ooad.web.dao.SellerDao;
import com.ooad.web.dao.UserDao;
import com.ooad.web.model.Seller;
import com.ooad.web.model.User;
import com.ooad.web.utils.TokenAuth;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.InputStream;

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
        final String storeName = reqJson.getString("storename");
        final String mobileNumber = reqJson.getString("mobilenumber");
        final String streetAddress = reqJson.getString("streetaddress");
        final String landmark = reqJson.getString("landmark");
        final String city = reqJson.getString("city");
        final String state = reqJson.getString("state");
        final String pincode = reqJson.getString("pincode");
        final String country = reqJson.getString("country");

        final SellerDao sellerDao = new SellerDao();
        final JSONObject jsonObject = sellerDao.validateSellerRegister(userName, email, password,
                storeName, mobileNumber, streetAddress, landmark, city, state, pincode, country);
        return Response.status(Status.OK).entity(jsonObject.toString()).build();
    }

    /*@PUT
    @Path("/updateinfo")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSeller(String req, @HeaderParam("sellerAuthToken") String token) throws Exception {
        Seller seller = TokenAuth.getSellerFromToken(token);
        JSONObject re = new JSONObject(req);
        System.out.println(re);
        String str = re.toString();

        if(seller==null){
            return Response.status(Status.OK).entity(new JSONObject().put("status",Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        } else {
            System.out.println("hello2");
            *//*ObjectMapper objectMapper = new ObjectMapper();
            Seller s = objectMapper.readValue(str, Seller.class);
            System.out.println(s);*//*
            boolean b = seller.save();
            System.out.println(b);
            JSONObject jsonObject = seller.toJSON();
            if (b) {
                System.out.println("hello3");
                return Response.status(Status.OK).entity(jsonObject.toString()).build();
            }
        }
        return Response.status(Status.OK).entity(new JSONObject().put("status",Status.UNAUTHORIZED.getStatusCode()).toString()).build();
    }*/

    @PUT
    @Path("updateinfo")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSeller(String req, @HeaderParam("sellerAuthToken") String token) throws Exception {
        Seller seller = TokenAuth.getSellerFromToken(token);
        JSONObject reqJson = new JSONObject(req);
        System.out.println(reqJson);
        String str = reqJson.toString();

        if(seller==null){
            return Response.status(Status.OK).entity(new JSONObject().put("status",Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        }

        System.out.println("hello2");
        int id = seller.getId();
        System.out.println(id);
        JSONObject jsonObject = seller.updateSeller(reqJson,id);
        System.out.println("hello3");
        return Response.status(Status.OK).entity(jsonObject.toString()).build();

    }
}
