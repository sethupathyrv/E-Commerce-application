package com.ooad.web.dao;

import com.ooad.web.model.User;
import com.ooad.web.utils.Constants;
import com.ooad.web.utils.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public JSONObject validateLogin(final String email, String password) {

        try {
            final JSONObject status = new JSONObject();
            final Connection con = Database.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT  * FROM  Users WHERE emailId=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    status.put("status", Constants.STATUS_OK);
                    status.append("token", null);
                    status.append("errors", null);
                } else {
                    status.put("status", Constants.STATUS_BAD_REQUEST);
                    final JSONArray errors = new JSONArray();
                    errors.put(new JSONObject().put("psword", Constants.ERROR_WRONG_PASSWD));
                    status.append("errors", errors);
                }
            } else {
                status.append("status", Constants.STATUS_BAD_REQUEST);
                final JSONArray errors = new JSONArray();
                errors.put(new JSONObject().put("email", Constants.ERROR_USER_EXIST));
            }
            con.close();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public User getUser(String email) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Users WHERE emailId=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("emailId"),
                        rs.getString("password"),
                        rs.getBoolean("isEnabled"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createUser(User user) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO Users(userName,emailId,password,isEnabled) VALUES (?,?,?,?)");//add user to database
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmailId());
            ps.setString(3, user.getPassword());
            ps.setBoolean(4, user.isEnbaled());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
