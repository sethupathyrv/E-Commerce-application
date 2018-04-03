package com.ooad.web.dao;

import com.ooad.web.model.*;
import com.ooad.web.utils.Database;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
            PreparedStatement ps = con.prepareStatement("INSERT INTO OrderItems(orderId, itemId, itemPrice, quantity,status)" +
                    "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,o.getId() );
            ps.setInt(2,item.getId() );
            ps.setFloat(3,price );
            ps.setInt(4, quantity);
            ps.setInt(5,OrderItemStatus.WAITING_FOR_SELLER.getStatusCode());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            OrderItem oi = null;
            if(rs.next()){
                oi=new OrderItem(rs.getInt(1),item,o,price,quantity,OrderItemStatus.WAITING_FOR_SELLER);
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
            int status = rs.getInt("status");
            Item item = Item.find(itemId);
            return new OrderItem(id,item,o,itemPrice,quantity,OrderItemStatus.getOrderItemStatus(status));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private OrderItem orderItem(int id, int orderId) {
        Order o= Order.find(orderId);
        return o.getOrderItemById(id);
    }

    public Collection<OrderItem> getSellerOrderItems(Seller seller) {
        ArrayList<OrderItem> sellerOrderItems = new ArrayList<OrderItem>();
        try {
            Connection con=Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT OI.* FROM OrderItems AS OI JOIN Items as I ON OI.itemId = I.id  WHERE I.sellerId = ?");
            ps.setInt(1,seller.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int orderId = rs.getInt("orderId");
                OrderItem oi = orderItem(id,orderId);
                sellerOrderItems.add(oi);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sellerOrderItems;
    }


    public OrderItem getOrderItem(int id) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT orderId FROM OrderItems WHERE id= ?");
            ps.setInt(1,id );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int orderId = rs.getInt("orderId");
                return orderItem(id,orderId );
            }
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveOrderItem(OrderItem orderItem) {
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE OrderItems SET status = ? WHERE id = ?");
            ps.setInt(1,orderItem.getOrderItemStatus().getStatusCode());
            ps.setInt(2,orderItem.getId() );
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

    public Collection<Order> getOrdersByUserId(int userId){
        try {
            Connection con = Database.getConnection();
            final ArrayList<Order> orders = new ArrayList<Order>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Orders,OrderItems WHERE Orders.userId = ? AND OrderItems.orderId = Orders.id ");
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                final Order o = orderBuilder(rs);
                orders.add(o);
            }
            con.close();
            return orders;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
