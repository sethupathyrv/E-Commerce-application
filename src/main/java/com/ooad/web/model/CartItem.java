package com.ooad.web.model;

import com.ooad.web.dao.CartDao;
import org.json.JSONObject;

public class CartItem {
    private final int id;
    private final Item item;
    private int quantity;
    private boolean offerApplied;

    public CartItem(int id, Item item, int quantity, boolean offerApplied) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.offerApplied = offerApplied;
    }

    public CartItem(int id, Item item, int quantity) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.offerApplied = false;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartItem)) return false;
        CartItem c = (CartItem) o;
        return this.getItem().getId() == c.getItem().getId();
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }

    public Item getItem() {
        return item;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public JSONObject toJSON() {
        JSONObject cartItem = new JSONObject();
        cartItem.put("id", this.id);
        cartItem.put("item", this.item.toJSON());
        cartItem.put("quantity", this.quantity);
        cartItem.put("offerApplied", this.offerApplied);
        return cartItem;
    }

    public boolean saveCartItem() {
        return new CartDao().save(this);
    }

    public boolean isOfferApplied() {
        return offerApplied;
    }

    public void setOfferApplied(boolean offerApplied) {
        this.offerApplied = offerApplied;
    }

    public int applyOffer(Cart cart) {
        if (this.offerApplied) {
            return 0;
        } else {
            if (this.getItem().getOffer() != null)
                return this.getItem().getOffer().applyOffer(this, cart);
            else
                System.out.print("offer is null");
        }
        //TODO applyOffer
        return 0;
    }


}
