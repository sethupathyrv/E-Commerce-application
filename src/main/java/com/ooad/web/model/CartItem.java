package com.ooad.web.model;

import com.ooad.web.dao.CartDao;
import org.json.JSONObject;

public class CartItem {
    private final int id;
    private final Item item;
    private int quantity;

    public CartItem(int id, Item item, int quantity) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof CartItem)) return false;
        CartItem c = (CartItem) o;
        return this.getItem().getId() == c.getItem().getId();
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public JSONObject toJSON() {
        JSONObject cartItem = new JSONObject();
        cartItem.put("id", this.id);
        cartItem.put("item", this.item.toJSON());
        cartItem.put("quantity", this.quantity);
        return cartItem;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean saveCartItem(){
        return new CartDao().save(this);
    }

    public int applyOffer(Cart cart) {
        //TODO applyOffer
        return 0;
//
    }
}
