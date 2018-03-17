/*
 * Created by Shravan on  14/2/18 2:55 PM.
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.dao;

import com.ooad.web.model.Seller;
import com.ooad.web.utils.Constants;
import com.ooad.web.utils.Database;
import com.ooad.web.utils.TokenAuth;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SellerDao {
    public JSONObject validateSellerLogin(String email, String password) {
        try {
            final JSONObject status = new JSONObject();
            final Connection con = Database.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT  * FROM  Sellers WHERE emailId=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    final Seller seller = getSeller(email);
                    status.put("status", Response.Status.OK.getStatusCode());
                    status.put("seller", seller.toJSON());
                    status.put("token", TokenAuth.generateSellerToken(seller));
                    status.put("errors", "");
                } else {
                    status.put("status", Response.Status.BAD_REQUEST.getStatusCode());
                    final JSONObject errors = new JSONObject();
                    errors.put("psword", Constants.ERROR_WRONG_PASSWD);
                    status.put("errors", errors);
                }
            } else {
                status.put("status", Response.Status.BAD_REQUEST.getStatusCode());
                final JSONObject errors = new JSONObject();
                errors.put("email", Constants.ERROR_NO_USER);
                status.put("errors", errors);
            }
            con.close();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public Seller getSeller(String email) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Sellers WHERE emailId=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            Seller s = null;
            if (rs.next()) {
                s=new Seller(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("emailId"),
                        rs.getString("password"),
                        rs.getBoolean("isEnabled"));
            }
            con.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Seller getSeller(int userId) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Sellers WHERE id=?");
            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();
            Seller s = null;
            if (rs.next()) {
                s= new Seller(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("emailId"),
                        rs.getString("password"),
                        rs.getBoolean("isEnabled"));
            }
            con.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public JSONObject validateSellerRegister(String userName, String email, String password) {
        JSONObject status = new JSONObject();
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM  Sellers WHERE emailId = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JSONArray errors = new JSONArray();
                errors.put(new JSONObject().put("email", Constants.ERROR_USER_EXIST));
                status.put("status", Response.Status.BAD_REQUEST.getStatusCode());
                status.put("errors", errors);
            } else {
                final Seller seller = createSeller(userName, email, password);
                final JSONObject sellerJsonObject = seller.toJSON();
                status.put("status", Response.Status.CREATED.getStatusCode());
                status.put("seller", sellerJsonObject);
                status.put("token", TokenAuth.generateSellerToken(seller));
            }
            con.close();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Seller createSeller(final String userName, final String email, final String password) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO Sellers(userName,emailId,password) VALUES (?,?,?)");//add seller to database
            ps.setString(1, userName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
            con.close();
            return getSeller(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
