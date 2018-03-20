package com.ooad.web.dao;

import com.ooad.web.model.UserAddress;
import com.ooad.web.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAddressDao {
    public boolean addAddress(final String fullName,final String mobileNumber,final String pincode,final String streetAddress,final String landmark,final String city,final String state,final int userId ) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = null;
            ps = con
                    .prepareStatement("INSERT INTO UserAddresses(fullName,mobileNumber,pincode,streetAddress,landmark,city,state,userId) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1,fullName);
            ps.setString(2,mobileNumber);
            ps.setString(3,pincode);
            ps.setString(4,streetAddress);
            ps.setString(5,landmark);
            ps.setString(6,city);
            ps.setString(7,state);
            ps.setInt(8,userId);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAddress(int id){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = null;
            ps = con.prepareStatement("DELETE  FROM UserAddresses WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    return false;
    }
    public boolean updateAddress(UserAddress userAddress){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = null;
            ps = con.prepareStatement("UPDATE UserAddresses SET fullName = ? ,mobileNumber = ?, pincode = ?," +
                            "streetAddress = ? ,landmark = ? ,city = ?,state = ? WHERE id = ?");
            ps.setString(1,userAddress.getFullname());
            ps.setString(2,userAddress.getMobilenumber());
            ps.setString(3,userAddress.getPincode());
            ps.setString(4,userAddress.getStreetAddress());
            ps.setString(5, userAddress.getLandmark());
            ps.setString(6, userAddress.getCity());
            ps.setString(7, userAddress.getState());
            ps.setInt(8,userAddress.getId());
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

    public Collection<UserAddress> getAddressfromUserId(int userId) {
        try {
            Connection con = Database.getConnection();
            final List<UserAddress> addresses = new ArrayList<UserAddress>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UserAddresses WHERE userId=?");
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final UserAddress userAddress= addressBuilder(rs);
                addresses.add(userAddress);
            }
            con.close();
            return addresses;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<UserAddress>();
        }
    }

    public UserAddress getAddressfromId(int id){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UserAddresses WHERE id = ?");
            ps.setInt(1,id );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                UserAddress u = addressBuilder(rs);
                con.close();
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserAddress addressBuilder(ResultSet rs) throws NullPointerException, SQLException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int id = rs.getInt("id");
        final String name = rs.getString("fullName");
        final String number = rs.getString("mobileNumber");
        final String pincode = rs.getString("pincode");
        final String streetAddress = rs.getString("streetAddress");
        final String landmark = rs.getString("landmark");
        final String city = rs.getString("city");
        final String state = rs.getString("state");

        return new UserAddress(id, name, number,pincode,streetAddress,landmark,city,state);
    }


}

