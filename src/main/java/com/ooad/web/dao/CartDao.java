package com.ooad.web.dao;

import com.ooad.web.model.CartItem;
import com.ooad.web.model.Item;
import com.ooad.web.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CartDao {
    public boolean insertItem(final int userId, final int itemId, final int quantity) {
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Cart(itemId, userId, quantity) VALUES (?,?,?)");
            ps.setInt(1, itemId);
            ps.setInt(2, userId);
            ps.setInt(3, quantity);
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

    public boolean removeItem(int cartId) {
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Cart WHERE id = ?");
            ps.setInt(1, cartId);
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

    public boolean emptyCart(int userId) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Cart WHERE userId = ?");
            ps.setInt(1, userId);
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

    public Collection<CartItem> createCartItems(int userId) {
        try {
            ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Cart WHERE userId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cartItems.add(cartItemBuilder(rs));
            }
            con.close();
            return cartItems;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CartItem cartItemBuilder(ResultSet rs) throws SQLException, NullPointerException {
        if (rs == null) throw new NullPointerException("cart Item not found");
        final int itemId = rs.getInt("itemId");
        final int quantity = rs.getInt("quantity");
        final int id = rs.getInt("id");
        final boolean offerApplied = rs.getBoolean("offerApplied");
        ItemDao itemDao = new ItemDao();
        final Item item = itemDao.getItembyId(itemId);
        return new CartItem(id, item, quantity,offerApplied);
    }

    public CartItem getCartItemById(int id) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Cart WHERE  id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return cartItemBuilder(rs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean save(CartItem cartItem) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Cart SET quantity = ? WHERE id = ?");
            ps.setInt(1,cartItem.getQuantity() );
            ps.setInt(2,cartItem.getId() );
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
