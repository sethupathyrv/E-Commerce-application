package com.ooad.web.model;

import org.json.JSONObject;

public class OrderItem {
    private final int id;
    private final Item item;
    private final Order order;
    private float itemPrice;
    private int quantity;

    public OrderItem(int id, Item item, Order order, float itemPrice, int quantity) {
        this.id = id;
        this.item = item;
        this.order = order;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public JSONObject toJSON(){
        JSONObject j = new JSONObject();
        j.put("item",item.toJSON() );
        j.put("itemPrice",itemPrice);
        j.put("orderId",order.getId());
        j.put("quantity",quantity);
        return j;
    }

}