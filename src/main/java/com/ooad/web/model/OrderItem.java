package com.ooad.web.model;

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

}
