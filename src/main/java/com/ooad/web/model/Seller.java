/*
 * Created by Sandeep Tadepalli on 13/02/18 13:12
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.dao.SellerDao;
import com.ooad.web.utils.Constants;
import org.json.JSONObject;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;

public class Seller {
    private int id;
    private String userName;
    private String emailId;
    private String password;
    private boolean isEnabled;
    private String storeName;
    private int mobileNumber;
    private String streetAddress;
    private String landmark;
    private String city;
    private String state;
    private int pincode;
    private String country;

    public Seller(int id, String userName, String emailId, String password, boolean isEnabled, String storeName, int mobileNumber, String streetAddress, String landmark, String city, String state, int pincode, String country) {
        this.id = id;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.isEnabled = isEnabled;
        this.storeName = storeName;
        this.mobileNumber = mobileNumber;
        this.streetAddress = streetAddress;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", storeName='" + storeName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", streetAddress='" + streetAddress + '\'' +
                ", landmark='" + landmark + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                ", country='" + country + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }


    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getPincode() {
        return pincode;
    }

    public String getCountry() {
        return country;
    }

    public JSONObject toJSON() {
        JSONObject sellerJsonObject = new JSONObject();
        sellerJsonObject.put("id", id);
        sellerJsonObject.put("username", userName);
        sellerJsonObject.put("emailId", emailId);
        sellerJsonObject.put("storeName", storeName);
        sellerJsonObject.put("mobileNumber", mobileNumber);
        sellerJsonObject.put("streetAddress", streetAddress);
        sellerJsonObject.put("landmark", landmark);
        sellerJsonObject.put("city", city);
        sellerJsonObject.put("state", state);
        sellerJsonObject.put("pincode", pincode);
        sellerJsonObject.put("country", country);
        return sellerJsonObject;
    }

    public JSONObject addItem(JSONObject item, InputStream fileInputStream) {
        final String itemName=item.getString("name");
        final float itemPrice=item.getFloat("price");
        final String itemDescription = item.getString("description");
        final String itemBrand = item.getString("brand");
        final float itemHeight =item.getFloat("height");
        final float itemWidth = item.getFloat("width");
        final int itemQuantity = item.getInt("quantity");
        final JSONObject errors=new JSONObject();
        String imageUrl="";
        if(itemName==null){
            errors.put("name","name should not be null");
        }
        else if(itemPrice==0.0f) {
            errors.put("price", "price should not be null");
        }
        try{
            int read = 0;
            byte[] bytes = new byte[1024];
            String filePath = Constants.FILE_UPLOAD_PATH;
            final String path = String.valueOf(System.currentTimeMillis());

            OutputStream out = new FileOutputStream(new File(filePath+path));
            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            imageUrl = "/files/"+path;
        } catch (IOException e){
            errors.put("file","file could not be saved");
        }
        final ItemDao itemDao=new ItemDao();
        final boolean valid=itemDao.createItem(itemName,itemPrice,imageUrl,this.id,itemDescription,
                itemBrand,itemHeight,itemWidth,itemQuantity);
        JSONObject jsonObject=new JSONObject();
        if(valid){
            jsonObject.put("status", Response.Status.CREATED.getStatusCode());
            jsonObject.put("errors","");
            return jsonObject;
        }
        errors.put("item","item addition failed");
        jsonObject.put("errors",errors);
        jsonObject.put("status", Response.Status.BAD_REQUEST.getStatusCode());
        return jsonObject;
    }

    public static Seller find(int id) { return new SellerDao().getSeller(id);}

    public boolean save() {
        System.out.println("reached model");
        return new SellerDao().saveSeller(this);
    }
}
