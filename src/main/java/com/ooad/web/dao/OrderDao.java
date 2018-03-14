package com.ooad.web.dao;

import com.ooad.web.model.Item;
import com.ooad.web.model.Order;
import com.ooad.web.model.OrderItem;
import com.ooad.web.model.User;
import com.ooad.web.utils.Database;

import java.sql.*;

public class OrderDao {
    public Order createEmptyOrder(User u){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Orders(userId, itemsSubTotal, shippingCharges, deliveryAddressId, orderStatus) VALUES " +
                    "(?,?,?,?,?)");
            ps.setInt(1, u.getId());
            ps.setInt(2,0 );
            ps.setInt(3,0 );
            ps.setInt(4,u.getDefaultAddressId() );
            ps.setInt(5,0 );
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int oid = rs.getInt(1);
                return new Order(u,oid,null);
            }
            con.close();

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
                    "VALUES (?,?,?,?)");
            ps.setInt(1,o.getId() );
            ps.setInt(2,item.getId() );
            ps.setFloat(3,price );
            ps.setInt(4, quantity);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                return new OrderItem(rs.getInt(1),item,o,price,quantity);
            }
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
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
