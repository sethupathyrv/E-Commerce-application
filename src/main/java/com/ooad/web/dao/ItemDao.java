/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.dao;

import com.ooad.web.model.Item;
import com.ooad.web.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ItemDao {
    public boolean createItem (final String name, final float price, final String url){
        try{
            Connection con = Database.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO items(name,price,url) VALUES (?,?,?)");//add user to database
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
}
