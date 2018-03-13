/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.model.Offer.Offer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private JSONArray itemDetails;

    public Item(int id, String name, float price, String url, int quantity, Seller seller,
                String description, String brand, float height, float width, JSONArray itemDetails,
                Offer offer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.url = url;
        this.quantity = quantity;
        this.seller = seller;
        this.description = description;
        this.brand = brand;
        this.height = height;
        this.width = width;
        this.itemDetails = itemDetails;
        this.offer = offer;
    }

    public static ArrayList<Item> getLastFive() {
        return (ArrayList<Item>) new ItemDao().getLastFiveItems();
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

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", quantity=" + quantity +
                ", seller=" + seller +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Item)){
            return false;
        } else {
            return this.id == ((Item) o).getId();
        }
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
        return itemJsonObject;
    }

    public boolean save() {
        return new ItemDao().saveItem(this);
    }

}
