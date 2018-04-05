package com.ooad.web.dao;

import com.ooad.web.model.Order;
import com.ooad.web.model.Transaction;
import com.ooad.web.model.UserAccount;
import com.ooad.web.utils.Database;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.Calendar;

public class TransactionDao {
    public Transaction createTransaction(Order o, UserAccount ua,int status,int amount){
        try {
            Connection con = Database.getConnection();
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            PreparedStatement ps = con.prepareStatement("INSERT INTO Transactions(orderId,amount,accountId,date,status) " +
                    "VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,o.getId());
            ps.setInt(2,amount);
            ps.setInt(3,ua.getId());
            ps.setDate(4,date);
            ps.setInt(5,status);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                Transaction transaction = new Transaction(rs.getInt(1),o.getItemsSubToatal(),o,ua,date,status);
                con.close();
                return transaction;
            }
            con.close();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Transaction createTransaction(Order o,UserAccount ua,int status){
        return createTransaction(o,ua,status,o.getItemsSubToatal());
    }
}
