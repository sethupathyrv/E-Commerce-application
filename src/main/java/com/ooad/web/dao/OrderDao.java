package com.ooad.web.dao;

import com.ooad.web.model.*;
import com.ooad.web.utils.Database;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OrderDao {
    public Order createEmptyOrder(User u,int addressId){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Orders(userId, itemsSubTotal, shippingCharges, deliveryAddressId, orderStatus) VALUES " +
                    "(?,?,?,?,?)",  Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, u.getId());
            ps.setInt(2,0 );
            ps.setInt(3,0 );
            //TODO get the addressId
            ps.setInt(4,addressId);
            ps.setInt(5,0 );
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            Order o = null;
            if(rs.next()){
                int oid = rs.getInt(1);
                o =new Order(u,oid,UserAddress.find(addressId));
            }
            con.close();
            return o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OrderItem createOrderItem(Item item, Order o, float price, int quantity) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO OrderItems(orderId, itemId, itemPrice, quantity)" +
                    "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,o.getId() );
            ps.setInt(2,item.getId() );
            ps.setFloat(3,price );
            ps.setInt(4, quantity);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            OrderItem oi = null;
            if(rs.next()){
                oi=new OrderItem(rs.getInt(1),item,o,price,quantity);
            }
            con.close();
            return oi;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveOrder(Order o) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Orders SET deliveryAddressId = ?,itemsSubTotal = ?," +
                    "orderStatus = ? WHERE id = ?");
            ps.setInt(1,o.getDeliveryAddress().getId());
            ps.setInt(2, o.getItemsSubToatal());
            ps.setInt(3,o.getOrderStatus().getStatusCode());
            ps.setInt(4,o.getId());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Order getOrderById(int orderId){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Orders WHERE id = ?");
            ps.setInt(1,orderId);
            ResultSet rs = ps.executeQuery();
            Order o= null;
            if(rs.next()) {
                 o = orderBuilder(rs);
            }
            con.close();
            return o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Order orderBuilder(ResultSet rs) throws  NullPointerException,SQLException{
        if(rs == null) throw  new NullPointerException("Result Set cant be null");
        final int orderId = rs.getInt("id");
        final int userId = rs.getInt("userId");
        final int itemSubTotal = rs.getInt("itemsSubTotal");
        final int deliveryAddressId = rs.getInt("deliveryAddressId");
        final int orderStatus = rs.getInt("orderStatus");
        UserAddress deliveryAddress = UserAddress.find(deliveryAddressId);
        User u = User.find(userId);
        OrderStatus os = OrderStatus.getStatusByCode(orderStatus);
        Order o = new Order(u,orderId,deliveryAddress);
        Collection<OrderItem>orderItems = getOrderItems(orderId,o);
        o.setItemsSubToatal(itemSubTotal);
        o.setOrderItems(orderItems);
        o.setOrderStatus(os);
        return o;
    }

    private Collection<OrderItem> getOrderItems(int orderId,Order o) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM OrderItems WHERE orderId = ?");
            ps.setInt(1,orderId );
            ResultSet rs = ps.executeQuery();
            Collection<OrderItem> orderItems = new ArrayList<OrderItem>();
            while(rs.next()){
                orderItems.add(orderItemBuilder(rs,o));
            }
            con.close();
            return orderItems;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private OrderItem orderItemBuilder(ResultSet rs,Order o) {
        try {
            int id = rs.getInt("id");
            int itemId = rs.getInt("itemId");
            float itemPrice = rs.getFloat("itemPrice");
            int quantity = rs.getInt("quantity");
            Item item = Item.find(itemId);
            return new OrderItem(id,item,o,itemPrice,quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
