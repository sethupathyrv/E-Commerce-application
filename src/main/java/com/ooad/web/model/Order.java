package com.ooad.web.model;

import java.sql.Date;
import java.util.Collection;

public class Order {
    private final int id;
    private final User user;
    private final UserAddress deliveryAddress;
    private Collection<OrderItem> orderItems;
    private Date orderPlacedDate;
    private OrderStatus os;
    private int itemsSubToatal;
    private int shippingCharges;

    public Order(User user, int id, UserAddress deliveryAddress, int itemsSubToatal) {
        this.user = user;
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.itemsSubToatal = itemsSubToatal;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public UserAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getItemsSubToatal() {
        return itemsSubToatal;
    }

    public Collection<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getOs() {
        return os;
    }

    public void generateInvoice(){
        //TODO generate invoice.
    }

    public int totalItems(){
        return this.orderItems.size();
    }

    public int grandTotal(){
        return this.shippingCharges + this.itemsSubToatal;
    }

}
