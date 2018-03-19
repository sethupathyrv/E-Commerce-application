package com.ooad.web.dao;

import com.ooad.web.model.Order;
import com.ooad.web.model.Transaction;
import com.ooad.web.model.UserAccount;
import com.ooad.web.utils.Database;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.Calendar;

public class TransactionDao {
    public Transaction createTransaction(Order o, UserAccount ua,int status){
        try {
            Connection con = Database.getConnection();
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            PreparedStatement ps = con.prepareStatement("INSERT INTO Transactions(orderId,amount,accountId,date,status) " +
                    "VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,o.getId());
            ps.setInt(2,o.getItemsSubToatal());
            ps.setInt(3,ua.getId());
            ps.setDate(4,date);
            ps.setInt(5,status);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
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
  /*  private Transaction transactionBuilder(ResultSet rs) throws NullPointerException, SQLException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int id = rs.getInt("id");
        final int orderId = rs.getInt("orderId");
        final int amount = rs.getInt("amount");
        final int accountId = rs.getInt("accountId");
        final Date date = rs.getDate("date");
        final int status = rs.getInt("status");
        OrderDao orderDao = new OrderDao();
        Order o = orderDao.getOrderById(orderId);
        return new Transaction(id,amount,)
    }*/
}
