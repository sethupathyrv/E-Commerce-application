/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

import org.json.JSONObject;

import java.util.Map;

public class Item {
    private final int id;
    private final String name;
    private final float price;
    private final String url;
//    private final Offer offer;
//    private final Seller seller;
//    private final SubSubCategory subSubCategory;
//    private final String itemDescription;
//    private final Map<String,String> itemDetails;

    public Item(int id, String name, float price,String url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.url = url;
        /*this.offer = offer;
        this.seller = seller;
        this.subSubCategory = subSubCategory;
        this.itemDescription = itemDescription;
        this.itemDetails = itemDetails;*/
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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +

                ", url='" + url + '\'' +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject itemJsonObject = new JSONObject();
        itemJsonObject.put("id", id);
        itemJsonObject.put("name", name);
        itemJsonObject.put("price", price);
        itemJsonObject.put("url", url);
        return itemJsonObject;
    }
}
