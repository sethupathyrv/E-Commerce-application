package com.ooad.web.dao;

import com.ooad.web.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
