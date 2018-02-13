/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

import java.util.Map;

public class Item {
    private final int id;
    private final String name;
    private final float price;
    private final Offer offer;
    private final Seller seller;
    private final SubSubCategory subSubCategory;
    private final String itemDescription;
    private final Map<String,String> itemDetails;

    public Item(int id, String name, float price, Offer offer,
                Seller seller, SubSubCategory subSubCategory,
                String itemDescription, Map<String, String> itemDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.offer = offer;
        this.seller = seller;
        this.subSubCategory = subSubCategory;
        this.itemDescription = itemDescription;
        this.itemDetails = itemDetails;
    }
}
