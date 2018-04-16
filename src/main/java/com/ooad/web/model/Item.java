/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.model.Offer.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Item {
    private final int id;
    private final Seller seller;
    private Offer offer;
    private String name;
    private float price;
    private String url;
    private int quantity;
    private String description;
    private String brand;
    private float height;
    private float width;
    private boolean isEnabled;
    private ItemSubCategory subCategory;
    private JSONArray itemDetails;
    private int itemBarcode;
    private String itemColour;


    public Item(int id, String name, float price, String url, int quantity, Seller seller,
                String description, String brand, float height, float width, JSONArray itemDetails,
                Offer offer, ItemSubCategory subCategory,int itemBarcode, String itemColour) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.url = url;
        this.quantity = quantity;
        this.itemBarcode = itemBarcode;
        this.seller = seller;
        this.description = description;
        this.brand = brand;
        this.height = height;
        this.width = width;
        this.itemDetails = itemDetails;
        this.offer = offer;
        this.subCategory=subCategory;
        this.itemColour=itemColour;
    }

    public static ArrayList<Item> getLastFive() {
        return (ArrayList<Item>) new ItemDao().getLastFiveItems();
    }
    public static ArrayList<Item> getItemsfromCategory(String CategoryName,String SubCategoryName) {
        return (ArrayList<Item>) new ItemDao().getItemsFromSubCategory(CategoryName,SubCategoryName);
    }

    public static Item find(int id) {
        return new ItemDao().getItembyId(id);
    }

    public Seller getSeller() {
        return seller;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setItemDetails(JSONArray itemDetails) {
        this.itemDetails = itemDetails;
    }

    public void setItemBarcode(int itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public void setItemColour(String itemColour) {
        this.itemColour = itemColour;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Offer getOffer() {
        return offer;
    }

    public int getItemBarcode() {
        return itemBarcode;
    }

    public String getItemColour() {
        return itemColour;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public ItemSubCategory getSubCategory() {
        return subCategory;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", seller=" + seller +
                ", offer=" + offer +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", isEnabled=" + isEnabled +
                ", subCategory=" + subCategory +
                ", itemDetails=" + itemDetails +
                ", itemBarcode=" + itemBarcode +
                ", itemColour=" + itemColour +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Item && this.id == ((Item) o).getId();
    }

    public JSONArray getItemDetails() {
        return itemDetails;
    }

    public JSONObject toJSON() {
        JSONObject itemJsonObject = new JSONObject();
        itemJsonObject.put("id", id);
        itemJsonObject.put("name", name);
        itemJsonObject.put("price", price);
        itemJsonObject.put("url", url);
        itemJsonObject.put("quantity", quantity);
        itemJsonObject.put("seller", seller.toJSON());
        itemJsonObject.put("description", this.description);
        itemJsonObject.put("brand", this.brand);
        itemJsonObject.put("height", this.height);
        itemJsonObject.put("width", this.width);
        itemJsonObject.put("isEnabled",this.isEnabled );
        itemJsonObject.put("itemDetails",this.itemDetails);
        itemJsonObject.put("SubCategory",subCategory.toJSON());
        itemJsonObject.put("itemBarcode",this.itemBarcode);
        itemJsonObject.put("itemColour",this.itemColour);
        itemJsonObject.put("offer",this.offer.toJSON());
        return itemJsonObject;
    }

    public boolean save() {
        return new ItemDao().saveItem(this);
    }

    public boolean itemSave() {
        return new ItemDao().saveSellerItem(this);
    }

    public int getEffectivePrice() {
        switch (this.getOffer().getOfferCode()) {
            case 202:
                PriceOffer p = (PriceOffer) offer;
                return (int) (this.price - p.getPriceCut());
            case 201:
                DiscountOffer d = (DiscountOffer) offer;
                float effectivePercent = (100 - d.getPercentage());
                return (int) (this.price * effectivePercent / 100);
            case -1:
                return (int) this.price;
            default:
                return (int) this.price;
        }
    }

    public static Collection<Item> getAllItems() {
        return new ItemDao().getAllItem();
    }

    public static Collection<Item> getAllSellerItems(int sellerId) {
        return new ItemDao().getSellerItem(sellerId);
    }

    public JSONObject updateItem(JSONObject req) {
//        Seller s = Seller.find(id);

        this.setName(req.getString("name"));
        this.setPrice(req.getFloat("price"));
        this.setDescription(req.getString("description"));
        this.setQuantity(req.getInt("quantity"));
        this.setBrand(req.getString("brand"));
        this.setHeight(req.getFloat("height"));
        this.setWidth(req.getFloat("width"));
        int offerType = req.getInt("offerType");
        String start = req.getString("startDate");
        String end = req.getString("endDate");
        int subCategoryId = req.getInt("subCategoryId");
        ItemDao itemDao = new ItemDao();
        ItemSubCategory itemSubCategory = itemDao.getItemSubCategory(subCategoryId);
        this.subCategory = itemSubCategory;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate= null;
        Date endDate=null;
        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(offerType==-1){
            offer = new EmptyOffer();
        }else if(offerType==201){
            if(this.offer.getId()!=-1)
                offer = new DiscountOffer(this.offer.getId(),startDate,endDate,req.getFloat("discountPercentage"));
            else {
                int id = itemDao.createOffer(offerType,req.getInt("discountPercentage"),-1,startDate,endDate);
                offer = new DiscountOffer(id,startDate,endDate,req.getFloat("discountPercentage"));
            }
            offer.save(offer);
        }else if(offerType==202){
            if(this.offer.getId()!=-1)
                offer = new PriceOffer(this.offer.getId(),startDate,endDate,req.getInt("priceOffer"));
            else{
                int id = itemDao.createOffer(offerType,-1,req.getInt("priceOffer"),startDate,endDate);
                offer = new PriceOffer(id,startDate,endDate,req.getInt("priceOffer"));
            }
            offer.save(offer);
        }else if(offerType==203){
            if(this.offer.getId()!=-1)
                offer = new BuyXGetYOffer(this.offer.getId(),startDate,endDate,req.getInt("bundleOfferX")
                ,req.getInt("bundleOfferY"));
            else{
                int id = itemDao.createOffer(offerType,-1,-1,req.getInt("bundleOfferX")
                        ,req.getInt("bundleOfferY"),startDate,endDate);
                offer = new BuyXGetYOffer(id,startDate,endDate,req.getInt("bundleOfferX")
                        ,req.getInt("bundleOfferY"));

            }
            offer.save(offer);
        }
        this.setOffer(offer);
        this.setItemBarcode(req.getInt("itemBarcode"));
        this.setItemColour(req.getString("itemColour"));
        this.itemSave();
        return new JSONObject().put("status", Response.Status.OK.getStatusCode());
    }

}
