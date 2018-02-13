package com.ooad.web.dao;

import com.ooad.web.model.User;
import com.ooad.web.utils.Constants;
import com.ooad.web.utils.Database;
import com.ooad.web.utils.TokenAuth;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public JSONObject validateLogin(final String email, String password) {
        try {
//            System.out.println(email+password);
            final JSONObject status = new JSONObject();
            final Connection con = Database.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT  * FROM  Users WHERE emailId=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    final User user = getUser(email);
                    status.put("status", Status.OK.getStatusCode());
                    status.put("user", user.toJSON());
                    status.put("token", TokenAuth.generateToken(user));
                    status.put("errors", "");
                } else {
                    status.put("status", Status.BAD_REQUEST.getStatusCode());
                    final JSONObject errors = new JSONObject();
                    errors.put("psword", Constants.ERROR_WRONG_PASSWD);
                    status.put("errors", errors);
                }
            } else {
                status.put("status", Status.BAD_REQUEST.getStatusCode());
                System.out.println(status);
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

    public User getUser(int userId) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Users WHERE id=?");
            ps.setString(1, String.valueOf(userId));
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

    public JSONObject validateRegister(final String userName, final String email, final String password) {
        JSONObject status = new JSONObject();
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM  Users WHERE emailId = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JSONArray errors = new JSONArray();
                errors.put(new JSONObject().put("email", Constants.ERROR_USER_EXIST));
                status.put("status", Status.BAD_REQUEST.getStatusCode());
                status.put("errors", errors);
            } else {
                final User user = createUser(userName, email, password);
                final JSONObject userJsonObject = user.toJSON();
                status.put("status", Status.CREATED.getStatusCode());
                status.put("user", userJsonObject);
                status.put("token", TokenAuth.generateToken(user));
            }
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User createUser(final String userName, final String email, final String password) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO Users(userName,emailId,password) VALUES (?,?,?)");//add user to database
            ps.setString(1, userName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
            con.close();
            return getUser(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
