package com.ooad.web.model;

import com.ooad.web.dao.OrderDao;
import org.json.JSONObject;

public class OrderItem {
    private final int id;
    private final Item item;
    private final Order order;
    private float itemPrice;
    private int quantity;
    private OrderItemStatus orderItemStatus;

    public OrderItem(int id, Item item, Order order, float itemPrice, int quantity,OrderItemStatus orderItemStatus) {
        this.id = id;
        this.item = item;
        this.order = order;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.orderItemStatus = orderItemStatus;
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

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", item=" + item +
                ", order=" + order +
                ", itemPrice=" + itemPrice +
                ", quantity=" + quantity +
                '}';
    }

    public Order getOrder() {
        return order;
    }

    public OrderItemStatus getOrderItemStatus() {
        return orderItemStatus;
    }

    public static OrderItem find(int id){
        return new OrderDao().getOrderItem(id);
    }

    public void setOrderItemStatus(OrderItemStatus orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
    }

    public boolean save() {
        return new OrderDao().saveOrderItem(this);
    }
}
