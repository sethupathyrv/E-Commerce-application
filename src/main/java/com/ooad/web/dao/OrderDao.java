package com.ooad.web.dao;

import com.ooad.web.model.*;
import com.ooad.web.utils.Database;

import java.sql.*;

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
                o =new Order(u,oid,null);
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
                    "orderStatus = ?");
            ps.setInt(1,0 );
            ps.setInt(2, o.getItemsSubToatal());
            ps.setInt(3,o.getOrderStatus().getStatusCode());
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
}
