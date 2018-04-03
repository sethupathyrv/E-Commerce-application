package com.ooad.web.model;

import com.ooad.web.dao.*;
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
    private int defaultAddressId;

    public User(int id, String userName, String emailId, String password, boolean isEnbaled, int defaultAddressId) {
        this.id = id;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.isEnabled = isEnbaled;
        this.cart = new Cart(this);
        this.defaultAddressId = defaultAddressId;
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
        userJsonObject.put("cart", this.cart.toJSON());
        return userJsonObject;
    }

    public JSONObject addItemToCart(JSONObject req) {
        boolean isValid = true;
        final JSONObject errors = new JSONObject();
        int itemId = 0;
        int quantity = 0;
        if (req.has("itemId")) {
            itemId = req.getInt("itemId");
        } else {
            isValid = false;
            errors.put("itemId", "Should contain an Item");
        }
        if (req.has("quantity")) {
            quantity = req.getInt("quantity");
        } else {
            isValid = false;
            errors.put("quantity", "Should contain quantity");
        }
        if (isValid) {
            ItemDao itemDao = new ItemDao();
            Item item = itemDao.getItembyId(itemId);
            if (item == null) {
                errors.put("itemId", "Should be a valid item");
                return new JSONObject().put("status", Status.BAD_REQUEST.getStatusCode())
                        .put("errors", errors);
            }
            if (item.getQuantity() < quantity) {
                isValid = false;
                errors.put("quantity", "Out of Stock");
            } else {
                CartItem c = alreadyInCart(item);
                if(c!=null){
                    if(item.getQuantity() < quantity + c.getQuantity()){
                        isValid = false;
                        errors.put("quantity","Out of stock");
                    } else{
                        c.setQuantity(c.getQuantity() + quantity);
                        c.saveCartItem();
                        return new JSONObject().put("status", Status.OK.getStatusCode())
                                .put("errors", errors).put("cart", cart.toJSON());
                    }
                } else {
                    CartDao cartDao = new CartDao();
                    cartDao.insertItem(this.id, itemId, quantity);
                    this.cart.refreshCart();
                    return new JSONObject().put("status", Status.OK.getStatusCode())
                            .put("errors", errors)
                            .put("cart", cart.toJSON());
                }
            }
        }
        return new JSONObject().put("status", Status.BAD_REQUEST.getStatusCode())
                .put("errors", errors);
    }

    private CartItem alreadyInCart(Item item) {
        for(CartItem c : this.cart.getCartItems()){
            if(c.getItem().getId()  == item.getId()){
                return c;
            }
        }
        return null;
    }

    public JSONObject createOrder(int addressId) {
        if (cart.size() <= 0) {
            return new JSONObject().put("status", Status.BAD_REQUEST.getStatusCode())
                    .put("errors", new JSONObject().put("cart", "Cart can't be empty"));
        } else {
            Collection<OrderItem> orderItems = new ArrayList<OrderItem>();
            OrderDao orderDao = new OrderDao();
            Order o = orderDao.createEmptyOrder(this, addressId);
            for (CartItem c : cart.getCartItems()) {
                Item item = c.getItem();
                float price = c.getItem().getPrice();
                int quantity = c.getQuantity();
                OrderItem oi = orderDao.createOrderItem(item, o, price, quantity);
                if (oi != null) {
                    orderItems.add(oi);
                } else {
                    return new JSONObject().put("status", Status.BAD_REQUEST.getStatusCode())
                            .put("error", "Cant create a cart");
                }
            }

            o.setOrderItems(orderItems);
            o.setItemsSubTotal(cart.getPromotionApplied());
            o.setOrderStatus(OrderStatus.PLACED);
            o.setShippingCharges(0);
            o.save();
            this.cart.emptyCart();
            return new JSONObject().put("status", Status.OK.getStatusCode())
                    .put("order", o.toJSON())
                    .put("errors", "");

        }
    }

    public int getDefaultAddressId() {
        return defaultAddressId;
    }

    public void setDefaultAddressId(int defaultAddressId) {
        this.defaultAddressId = defaultAddressId;
    }

    public JSONObject addAddress(JSONObject req) {
        final String fullName = req.getString("fullName");
        final String mobileNumber = req.getString("mobileNumber");
        final String pincode = req.getString("pincode");
        final String streetAddress = req.getString("streetAddress");
        final String landmark = req.getString("landmark");
        final String city = req.getString("city");
        final String state = req.getString("state");
        final JSONObject errors = new JSONObject();
        final int userId = this.id;
        boolean isValid = true;

        if (fullName == null || fullName == "") {
            isValid = false;
            errors.put("fullName", "full name should not be null");
        } else if (mobileNumber == null || mobileNumber == "" || mobileNumber.length() != 10) {
            isValid = false;
            errors.put("mobielNumber", "mobileNumber should not be null or ");
        } else if (pincode == null || pincode == "" || pincode.length() != 6) {
            isValid = false;
            errors.put("pincode", "pincode should not be null");
        } else if (streetAddress == null || streetAddress == "") {
            isValid = false;
            errors.put("streetAddress", "streetAddress should not be null");
        } else if (landmark == null || landmark == "") {
            isValid = false;
            errors.put("landmark", "landmark should not be null");
        } else if (city == null || city == "") {
            isValid = false;
            errors.put("city", "city should not be null");
        } else if (state == null || state == "") {
            isValid = false;
            errors.put("state", "state should not be null");
        }
        if (isValid) {
            UserAddressDao userAddressDao = new UserAddressDao();
            userAddressDao.addAddress(fullName, mobileNumber, pincode, streetAddress, landmark, city, state, userId);
            return new JSONObject().put("status", Status.CREATED.getStatusCode())
                    .put("errors", errors);
        }
        return new JSONObject().put("status", Status.BAD_REQUEST.getStatusCode())
                .put("errors", errors);
        //Get each of the address variables
        //Pincode, and all
        //Check their validity example pincode 6 chars
        //Create a UserAddressDao
        //call useraddressdao.addAddress and give all the parameters

    }

    public boolean save() {
        return new UserDao().save(this);
    }

    public JSONObject createOrder(String req) {
        JSONObject j = new JSONObject(req);
        if (!j.has("addressId")) {
            return new JSONObject().put("status", Status.BAD_REQUEST.getStatusCode()).put("errors", "addressId is missing");
        }
        return this.createOrder(j.getInt("addressId"));
    }

    public static User find(int userId) {
        return new UserDao().getUser(userId);
    }

    public JSONObject deleteAddress(int id) {
        UserAddressDao userAddressDao = new UserAddressDao();
        userAddressDao.deleteAddress(id);
        return new JSONObject().put("status", Status.OK.getStatusCode());
    }

    public JSONObject updateAddress(JSONObject req,int id) {
          UserAddress u = UserAddress.find(id);
          u.setFullname(req.getString("fullName"));
          u.setMobilenumber(req.getString("mobileNumber"));
          u.setPincode(req.getString("pincode"));
          u.setStreetAddress(req.getString("streetAddress"));
          u.setLandmark(req.getString("landmark"));
          u.setCity(req.getString("city"));
          u.setState(req.getString("state"));
          UserAddressDao userAddressDao = new UserAddressDao();
          userAddressDao.updateAddress(u);
          return new JSONObject().put("status", Status.OK.getStatusCode());
    }

    public JSONObject createTransaction(JSONObject req) {
        final int orderId = req.getInt("orderId");
        final JSONObject errors = new JSONObject();
        OrderDao orderDao = new OrderDao();
        Order order = orderDao.getOrderById(orderId);
        TransactionDao transactionDao = new TransactionDao();
        Transaction transaction = null;
        UserDao userDao = new UserDao();
        UserAccount userAccount = userDao.getUserAccountFromId(id);
        UserAccount amazonAccount = userDao.getUserAccountFromId(2);
        int currentAmount = amazonAccount.getAmount();
        if(order.grandTotal()<= userAccount.getAmount()){
            transaction = transactionDao.createTransaction(order,userAccount,1);
            userAccount.setAmount(userAccount.getAmount()-order.grandTotal());
            userAccount.save();
            amazonAccount.setAmount(currentAmount+order.grandTotal());
            amazonAccount.save();
            ArrayList<OrderItem> orderItems = (ArrayList<OrderItem>) order.getOrderItems();
            ItemDao itemDao = new ItemDao();
            for(OrderItem orderItem: orderItems){
                int id = orderItem.getItem().getId();
                Item item = itemDao.getItembyId(id);
                item.setQuantity(item.getQuantity()-orderItem.getQuantity());
                item.save();
            }
            order.setOrderStatus(OrderStatus.MONEY_PAID);
            order.save();

        }else{
            transaction = transactionDao.createTransaction(order,userAccount,0);
        }
        return new JSONObject().put("status", Status.OK.getStatusCode())
                .put("transaction", transaction.toJSON());
    }

    public Collection<Order> getAllOrders(int id){
        //array //Goto Orders Table and get all the orders with user id
        return new OrderDao().getOrdersByUserId(id);
    }

}