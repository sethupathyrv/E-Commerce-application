package com.ooad.web.model;

import com.ooad.web.dao.CartDao;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class Cart {
    private Collection<CartItem> cartItems;
    private final User user;
    private int promotionApplied;

    public Cart(User user) {
        this.user = user;
        CartDao cartDao = new CartDao();
        this.cartItems = cartDao.createCartItems(this.user.getId());
        this.promotionApplied = applyOffer();
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
        this.promotionApplied = applyOffer();
    }

    public int size(){
        return cartItems.size();
    }

    public JSONObject toJSON(){
        JSONObject cartObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (CartItem c:cartItems) {
            jsonArray.put(c.toJSON());
        }
        cartObject.put("cartItems",jsonArray );
        cartObject.put("promotion",this.promotionApplied);
        return cartObject;
    }

    public Collection<CartItem> getCartItems() {
        return cartItems;
    }

    public User getUser() {
        return user;
    }

    private int applyOffer(){
       int promotion = 0;
        for (CartItem c: cartItems) {
            promotion += c.applyOffer(this);
        }
        return promotion;
    }

    public int getPromotionApplied() {
        return promotionApplied;
    }

    public void emptyCart() {
        new CartDao().emptyCart(this.user.getId());
    }

    public float getSubTotal() {
        float subTotal = 0;
        for (CartItem c: cartItems) {
            subTotal += c.getQuantity()*c.getItem().getPrice();
        }
        return subTotal;
    }

    public float getGrandTotal(){
        return this.getSubTotal() - this.promotionApplied;
    }

    public void updateCart(int quantity,int id){
        ArrayList<CartItem> items = (ArrayList<CartItem>) getCartItems();
        CartItem cartItem = items.get(id);
        cartItem.setQuantity(quantity);
        cartItem.saveCartItem();
    }
}

