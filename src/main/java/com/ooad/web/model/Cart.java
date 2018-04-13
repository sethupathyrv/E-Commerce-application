package com.ooad.web.model;

import com.ooad.web.dao.CartDao;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class Cart {
    private Collection<CartItem> cartItems;
    private ArrayList<CartItem> testItems;
    private final User user;
    private int promotionApplied;

    public ArrayList<CartItem> getTestItems() {
        return testItems;
    }

    public Cart(User user) {
        this.user = user;
        CartDao cartDao = new CartDao();
        this.cartItems = cartDao.createCartItems(this.user.getId());
        this.testItems = new ArrayList<CartItem>();
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
        Iterator<CartItem> it =cartItems.iterator();
        while(it.hasNext()){
            CartItem c = it.next();
            promotion += c.applyOffer(this);
        }
        for(CartItem c : testItems){
            cartItems.add(c);
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

    public JSONObject updateCart(int quantity,int id){
        ArrayList<CartItem> items = (ArrayList<CartItem>) getCartItems();
        for(CartItem cartItem:items){
            if(cartItem.getId()==id){
                if(quantity<=cartItem.getItem().getQuantity()) {
                    cartItem.setQuantity(quantity);
                    cartItem.saveCartItem();
                    return new JSONObject().put("status", Response.Status.OK.getStatusCode())
                            .put("errors","");
                }else{
                    return new JSONObject().put("status", Response.Status.BAD_REQUEST.getStatusCode())
                            .put("errors","quantity out of bounds");
                }
            }
        }
        return new JSONObject().put("status", Response.Status.BAD_REQUEST.getStatusCode())
                .put("errors","Item is not found in the cart");
    }
}

