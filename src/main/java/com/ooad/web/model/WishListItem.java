package com.ooad.web.model;

import com.ooad.web.dao.UserDao;
import org.json.JSONObject;

public class WishListItem {
    private int id;
    private Item item;

    public WishListItem(int id, Item item) {
        this.id = id;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
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

    public static WishListItem find(int wishListItemId) {
        UserDao u = new UserDao();
        return u.getWishListItem(wishListItemId);
    }
}
