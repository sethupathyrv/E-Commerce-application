package com.ooad.web.model;

import com.ooad.web.dao.CartDao;
import com.ooad.web.dao.ItemDao;
import org.json.JSONObject;

import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.Collection;

public class User {

    private int id;
    private String userName;
    private String emailId;
    private String password;
    private boolean isEnabled;
    private Cart cart;

    public User(int id, String userName, String emailId, String password, boolean isEnbaled) {
        this.id = id;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.isEnabled = isEnbaled;
        this.cart = new Cart(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", isEnbaled=" + isEnabled +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public Cart getCart() {
        return cart;
    }

    public JSONObject toJSON() {
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("email", emailId);
        userJsonObject.put("username", userName);
        userJsonObject.put("cart",this.cart.toJSON());
        return userJsonObject;
    }

    public JSONObject addItemToCart(JSONObject req) {
        boolean isValid = true;
        final JSONObject errors = new JSONObject();
        int itemId = 0;
        int quantity =0;
        if(req.has("itemId")){
            itemId = req.getInt("itemId");
        } else{
            isValid = false;
            errors.put("itemId","Should contain an Item");
        }
        if(req.has("quantity")) {
            quantity = req.getInt("quantity");
        } else{
            isValid = false;
            errors.put("quantity","Should contain quantity");
        }
        if(isValid) {
            ItemDao itemDao = new ItemDao();
            Item item = itemDao.getItembyId(itemId);
            if(item == null) {
                errors.put("itemId","Should be a valid item" );
                return new JSONObject().put("status", Status.BAD_REQUEST.getStatusCode())
                        .put("errors",errors);
            }
            if(item.getQuantity() < quantity){
                isValid = false;
                errors.put("quantity","Out of Stock");
            } else {
                CartDao cartDao = new CartDao();
                cartDao.insertItem(this.id,itemId ,quantity );
                this.cart.refreshCart();
                return new JSONObject().put("status",Status.OK.getStatusCode())
                        .put("errors",errors)
                        .put("cart",cart.toJSON());
            }
        }
        return new JSONObject().put("status", Status.BAD_REQUEST.getStatusCode())
                .put("errors",errors);
    }

    public JSONObject cartCheckout(){
        if(cart.size() <=0) {
            return new JSONObject().put("status",Status.BAD_REQUEST.getStatusCode() )
                    .put("errors",new JSONObject().put("cart","Cart can't be empty"));
        }else {
            Collection<OrderItem> orderItems = new ArrayList<OrderItem>();
            for (CartItem c: cart.getCartItems()) {
                Item item = c.getItem();

            }
            //TODO Clear Cart
        }
        return null;

    }
}
