package com.ooad.web.model;

import com.ooad.web.dao.CartDao;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;

public class Cart {
    private Collection<CartItem> cartItems;
    private final User user;

    public Cart(User user) {
        this.user = user;
        CartDao cartDao = new CartDao();
        this.cartItems = cartDao.createCartItems(this.user.getId());
    }

    public boolean addCartItem(CartItem cartItem){
        try{
            cartItems.add(cartItem);
            CartDao cartDao = new CartDao();
            cartDao.insertItem(this.user.getId(),cartItem.getItem().getId(),cartItem.getQuantity() );
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeCartItem(CartItem cartItem){
        try{
            this.cartItems.remove(cartItem);
            CartDao cartDao = new CartDao();
            cartDao.removeItem(cartItem.getId());
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void refreshCart(){
        CartDao cartDao = new CartDao();
        cartItems = cartDao.createCartItems(this.user.getId());
    }

    public int size(){
        return cartItems.size();
    }

    public JSONArray toJSON(){
        JSONArray jsonArray = new JSONArray();
        for (CartItem c:cartItems) {
            jsonArray.put(c.toJSON());
        }
        return jsonArray;
    }

    public Collection<CartItem> getCartItems() {
        return cartItems;
    }

    public User getUser() {
        return user;
    }

    public void applyOffer(){
        int promotion=0;
        for (CartItem c: cartItems) {
            promotion += c.applyOffer(this);
        }
    }
}

