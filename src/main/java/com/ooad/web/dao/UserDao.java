package com.ooad.web.dao;

import com.ooad.web.model.User;
import com.ooad.web.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private Connection con;
    public UserDao() {
        try {
            con = Database.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean validateLogin(String em,String pw){
        boolean canLogin = false;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT  * FROM  users where emailId=? and password=?");
            ps.setString(1,em);
            ps.setString(2,pw);
            ResultSet rs = ps.executeQuery();
            canLogin = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canLogin;
    }

    public User getUser(String email){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE emailId=?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                User user = new User(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("emailId"),
                        rs.getString("password"),
                        rs.getBoolean("isEnabled"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createUser(User user){
        try {
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO users(userName,emailId,password,isEnabled) values (?,?,?,?)");//add user to database
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getEmailId());
            ps.setString(3,user.getPassword());
            ps.setBoolean(4,user.isEnbaled());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
