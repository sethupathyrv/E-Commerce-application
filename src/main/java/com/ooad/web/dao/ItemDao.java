/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.dao;

import com.ooad.web.model.Item;
import com.ooad.web.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ooad.web.model.Item;
import com.ooad.web.model.Seller;
import com.ooad.web.model.User;
import com.ooad.web.utils.Database;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemDao {
    public boolean createItem (final String name, final float price, final String url){
        try{
            Connection con = Database.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO Items(name,price,url) VALUES (?,?,?)");//add user to database
            ps.setString(1, name);
            ps.setFloat(2, price);
            ps.setString(3, url);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Item getItembyId(int id) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Items WHERE id=?");
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return itemBuilder(rs);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Item> getLastFiveItems(){
        try {
            Connection con = Database.getConnection();
            final List<Item> items = new ArrayList<Item>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Items ORDER BY id DESC LIMIT 5");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final Item item = itemBuilder(rs);
                items.add(item);
            }
            con.close();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Item itemBuilder(ResultSet rs) throws NullPointerException, SQLException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        final float price = rs.getFloat("price");
        final String url = rs.getString("url");
        final int sellerId = rs.getInt("sellerId");
        SellerDao sellerDao = new SellerDao();
        Seller seller = sellerDao.getSeller(sellerId);
        return new Item(id,name,price,url,seller);


    }
}
