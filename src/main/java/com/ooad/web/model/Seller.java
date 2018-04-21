/*
 * Created by Sandeep Tadepalli on 13/02/18 13:12
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.dao.OrderDao;
import com.ooad.web.dao.SellerDao;
import com.ooad.web.utils.Constants;
import org.json.JSONObject;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
    private int totalRatings;
    private int ratingsCount;

    public Seller(int id, String userName, String emailId, String password, boolean isEnabled, String storeName, String mobileNumber, String streetAddress, String landmark, String city, String state, String pincode, String country, int totalRatings,int ratingsCount) {
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
        this.totalRatings = totalRatings;
        this.ratingsCount = ratingsCount;
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
                ", mobileNumber='" + mobileNumber + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", landmark='" + landmark + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", country='" + country + '\'' +
                ", totalRatings=" + totalRatings +
                ", ratingsCount=" + ratingsCount +
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

    public int getTotalRatings() {
        return totalRatings;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public float getSellerRating(){
        if(totalRatings == 0)
            return (float) 0.0;
        if(ratingsCount == 0 ) return (float) 0.0;
        return (float) (((float)totalRatings)/(ratingsCount*1.0));
    }

    public void addRating(int rating){
        this.totalRatings = this.totalRatings + rating;
        this.ratingsCount += 1;
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
        sellerJsonObject.put("rating", getSellerRating());
        sellerJsonObject.put("ratingCount",ratingsCount);

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String start = item.getString("startDate");
        final String end = item.getString("endDate");
        final String dealId = item.getString("dealId");
        Date startDate= null;
        Date endDate=null;
        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int discountPercentage = 0;
        int priceOffer = 0;
        final ItemDao itemDao=new ItemDao();
        int offerId =0;
        if(offerType == 202){
            priceOffer = item.getInt("priceOffer");
            offerId = itemDao.createOffer(202,dealId,0 ,priceOffer,startDate,endDate );
        } else if(offerType == 201){
            discountPercentage = item.getInt("discountPercentage");
            offerId = itemDao.createOffer(201,dealId, discountPercentage,0,startDate,endDate );
        }else if(offerType == -1){
            offerId = -1;
        } else if(offerType== 203) {
            int buyX = item.getInt("bundleOfferX");
            int getY = item.getInt("bundleOfferY");
            offerId = itemDao.createOffer(203,dealId,0,0,buyX,getY,startDate,endDate);

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

    public JSONObject updateSeller(JSONObject req, int id) {
//        Seller s = Seller.find(id);
        String str = req.getString("storeName");
        System.out.println(str);
        this.setStoreName(req.getString("storeName"));
        this.setMobileNumber(req.getString("mobileNumber"));
        this.setStreetAddress(req.getString("streetAddress"));
        this.setLandmark(req.getString("landmark"));
        this.setCity(req.getString("city"));
        this.setState(req.getString("state"));
        this.setPincode(req.getString("pincode"));
        this.setCountry(req.getString("country"));
        System.out.println(this.getCountry());
        this.save();
        return new JSONObject().put("status", Status.OK.getStatusCode());
    }

    public Collection<OrderItem> getOrderItems(){
        return new OrderDao().getSellerOrderItems(this);
    }

    public JSONObject dispatchOrderItem(OrderItem oi){
        if(oi.getOrderItemStatus() != OrderItemStatus.WAITING_FOR_SELLER){
            return new JSONObject().put("status", Status.BAD_REQUEST.getStatusCode())
                    .put("error","Order Item already dispatched or does not exist" );
        }
        oi.setOrderItemStatus(OrderItemStatus.SHIPPED);
        oi.save();
        oi.getOrder().refreshStatus();
        return new JSONObject().put("status",Status.OK.getStatusCode() );
    }

    public boolean save(){
        SellerDao sellerDao = new SellerDao();
        sellerDao.updateSeller(this);
        return true;
    }

    public static Collection<Seller> getAllSellers(){
        return new SellerDao().getAllSeller();
    }
}
