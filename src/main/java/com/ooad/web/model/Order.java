package com.ooad.web.model;

import com.ooad.web.dao.OrderDao;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.Collection;

public class Order {
    private final int id;
    private final User user;
    private UserAddress deliveryAddress;
    private Collection<OrderItem> orderItems;
    private Date orderPlacedDate;
    private OrderStatus os;
    private int itemsSubToatal;
    private int shippingCharges;

    public Order(User user, int id, UserAddress deliveryAddress) {
        this.user = user;
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.itemsSubToatal = 0;
        this.shippingCharges = 0;

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

    public OrderStatus getOrderStatus() {
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

    public Date getOrderPlacedDate() {
        return orderPlacedDate;
    }

    public int getShippingCharges() {
        return shippingCharges;
    }

    public void setDeliveryAddress(UserAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setOrderItems(Collection<OrderItem> orderItems) {
        this.orderItems = orderItems;
        if(this.itemsSubToatal == 0) {
            calculateItemsSubToatal();
        }
    }
    public void setItemsSubTotal(int promotionApplied){
        if(this.itemsSubToatal>0)
            itemsSubToatal=itemsSubToatal-promotionApplied;
        else {
            calculateItemsSubToatal();
            itemsSubToatal = itemsSubToatal - promotionApplied;
        }
    }

    private void calculateItemsSubToatal() {
        for (OrderItem oi: this.orderItems) {
            itemsSubToatal += oi.getItem().getPrice()*oi.getQuantity();
        }
    }

    public void setOrderPlacedDate(Date orderPlacedDate) {
        this.orderPlacedDate = orderPlacedDate;
    }

    public void setOrderStatus(OrderStatus os) {
        this.os = os;
    }

    public void setItemsSubToatal(int itemsSubToatal) {
        this.itemsSubToatal = itemsSubToatal;
    }

    public void setShippingCharges(int shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public boolean save() {
        return new OrderDao().saveOrder(this);
    }

    public JSONObject toJSON(){
        JSONObject j = new JSONObject();
        j.put("id",this.id);
        j.put("user",this.user);
        JSONArray ja = new JSONArray();
        for (OrderItem oi: this.orderItems) {
            ja.put(oi.toJSON());
        }
        j.put("orderItems",ja);
        j.put("deliveryAddress",this.deliveryAddress.toJSON() );
        return j;
    }

    public static Order find(int orderId) {
        return new OrderDao().getOrderById(orderId);
    }
}
