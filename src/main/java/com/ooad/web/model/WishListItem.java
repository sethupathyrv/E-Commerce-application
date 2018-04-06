package com.ooad.web.model;

import org.json.JSONObject;

public class WishListItem {
    private int id;
    private Item item;
    private User user;

    public WishListItem(int id, Item item, User user) {
        this.id = id;
        this.item = item;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "WishListItem{" +
                "id=" + id +
                ", item=" + item +
                '}';
    }

    public JSONObject toJSON(){
        return new JSONObject().put("id",id ).put("item",item );
    }
}
