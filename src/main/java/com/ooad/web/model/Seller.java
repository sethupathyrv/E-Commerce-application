/*
 * Created by Sandeep Tadepalli on 13/02/18 13:12
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.dao.SellerDao;
import com.ooad.web.utils.Constants;
import org.json.JSONObject;
import javax.ws.rs.core.Response.Status;
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
    private String mobileNumber;
    private String streetAddress;
    private String landmark;
    private String city;
    private String state;
    private String pincode;
    private String country;

    public Seller(int id, String userName, String emailId, String password, boolean isEnabled, String storeName, String mobileNumber, String streetAddress, String landmark, String city, String state, String pincode, String country) {
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

    public String getMobileNumber() {
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

    public String getPincode() {
        return pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setCountry(String country) {
        this.country = country;
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
        final int SubCategoryId = item.getInt("subCategoryId");
        final int offerType = item.getInt("offerType");
        final int itemBarcode = item.getInt("itemBarcode");
        final String itemColour = item.getString("itemColour");
        int discountPercentage = 0;
        int priceOffer = 0;
        final ItemDao itemDao=new ItemDao();
        int offerId =0;
        if(offerType == 202){
            priceOffer = item.getInt("priceOffer");
            offerId = itemDao.createOffer(202,0 ,priceOffer );
        } else if(offerType == 201){
            discountPercentage = item.getInt("discountPercentage");
            offerId = itemDao.createOffer(201, discountPercentage,0 );
        }else if(offerType == -1){
            offerId = -1;
        } else if(offerType== 203) {
            ;
            //TODO add BundleOffer

        }
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

        final boolean valid=itemDao.createItem(itemName,itemPrice,imageUrl,this.id,itemDescription,
                itemBrand,itemHeight,itemWidth,itemQuantity,SubCategoryId,offerId,itemBarcode, itemColour);
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

    public static Seller find(int id) {
        SellerDao sellerDao = new SellerDao();
        return sellerDao.getSeller(id);
    }

    /*public boolean save() {
        System.out.println("reached model");
        return new SellerDao().saveSeller(this);
    }*/

    public JSONObject updateSeller(JSONObject req, int id) {
        Seller s = Seller.find(id);
        String str = req.getString("storeName");
        System.out.println(str);
        s.setStoreName(req.getString("storeName"));
        s.setMobileNumber(req.getString("mobileNumber"));
        s.setStreetAddress(req.getString("streetAddress"));
        s.setLandmark(req.getString("landmark"));
        s.setCity(req.getString("city"));
        s.setState(req.getString("state"));
        s.setPincode(req.getString("pincode"));
        s.setCountry(req.getString("country"));
        System.out.println(s.getCountry());
        SellerDao sellerDao = new SellerDao();
        sellerDao.updateSeller(s);
        return new JSONObject().put("status", Status.OK.getStatusCode());
    }
}
