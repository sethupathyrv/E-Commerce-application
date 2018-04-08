package com.ooad.web.dao;

import com.ooad.web.model.*;
import com.ooad.web.utils.Constants;
import com.ooad.web.utils.Database;
import com.ooad.web.utils.TokenAuth;
import org.json.JSONObject;

import javax.ws.rs.core.Response.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

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
                    if(rs.getBoolean("isEnabled")) {
                        final User user = getUser(email);
                        status.put("status", Status.OK.getStatusCode());
                        status.put("user", user.toJSON());
                        status.put("token", TokenAuth.generateToken(user));
                        status.put("errors", "");
                    }else{
                        status.put("status", Status.BAD_REQUEST.getStatusCode());
                        final JSONObject errors = new JSONObject();
                        errors.put("verification","Email not verified. Please verify it" );
                        status.put("errors", errors);
                    }
                } else {
                    status.put("status", Status.BAD_REQUEST.getStatusCode());
                    final JSONObject errors = new JSONObject();
                    errors.put("psword", Constants.ERROR_WRONG_PASSWD);
                    status.put("errors", errors);
                }
            } else {
                status.put("status", Status.BAD_REQUEST.getStatusCode());
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
            User u = null;
            if (rs.next()) {
                u = new User(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("emailId"),
                        rs.getString("password"),
                        rs.getBoolean("isEnabled"),
                        rs.getInt("PayBalance"), rs.getInt("defaultAddressId"));
            }
            con.close();
            return u;
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
            User u = null;
            if (rs.next()) {
                u=new User(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("emailId"),
                        rs.getString("password"),
                        rs.getBoolean("isEnabled"),
                        rs.getInt("PayBalance"), rs.getInt("defaultAddressId"));
            }
            con.close();
            return u;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public JSONObject validateRegister(final String userName, final String email, final String password) {
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
            con.close();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
*/
    public User createUser(final String userName, final String email, final String password, String EmailVerificationHash) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO Users(userName,emailId,password,EmailVerificationHash) VALUES (?,?,?,?)");//add user to database
            ps.setString(1, userName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4,EmailVerificationHash);
            ps.executeUpdate();
            con.close();
            return getUser(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createDummyAccount(int userId, String userName, String accountNumber) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Accounts(userId, name, number, amount) VALUES (?,?,?,?)");
            ps.setInt(1,userId);
            ps.setString(2,userName);
            ps.setInt(3, Integer.parseInt(accountNumber));
            ps.setInt(4,77777);
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean save(User user) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET defaultAddressId=?,PayBalance=? WHERE id=?");
            ps.setInt(1,user.getDefaultAddressId() );
            ps.setInt(2,user.getAmazonPayBalance());
            ps.setInt(3,user.getId() );
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

    public UserAccount getUserAccountFromUserId(int userId){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Accounts WHERE userId=?");
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                UserAccount ua = new UserAccount(rs.getInt("id"),rs.getString("name"),rs.getInt("number"),rs.getInt("amount"));
                con.close();
                return ua;
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



    public boolean save(UserAccount userAccount) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Accounts SET amount = ? WHERE id = ?");
            ps.setInt(1,userAccount.getAmount() );
            ps.setInt(2,userAccount.getId());
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


    public boolean verifyEmailHash(String email,String hash) {
        boolean verified = false;
        try {
            Connection con  = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT emailVerificationHash FROM users WHERE emailId=?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String dbhash = rs.getString("emailVerificationHash");
                if(dbhash.equals(hash)){
                    verified=true;
                }
            }
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return verified;
    }

    public void updateStatus(String email) {
        try {
            Connection con  = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET isEnabled=? WHERE emailId=?");
            ps.setBoolean(1,true);
            ps.setString(2,email);
            ps.executeUpdate();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmailExists(String email) {
        boolean isexist=false;
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE emailId=?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                isexist=true;
            }
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isexist;
    }








    public ArrayList<WishListItem> getWishList(User u){
        return getWishListByUserId(u.getId());
    }

    public ArrayList<WishListItem> getWishListByUserId(int id){
        ArrayList<WishListItem> wishListItems = new ArrayList<WishListItem>();
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM WishList WHERE userId = ? ORDER BY id DESC ");
            ps.setInt(1,id );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                wishListItems.add(wishListItemBuilder(rs));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishListItems;
    }

    public WishListItem getWishListItem(int id){
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM WishList WHERE id=?");
            ps.setInt(1,id );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return wishListItemBuilder(rs);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void removeWishListItem(int id){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM WishList WHERE id = ?");
            ps.setInt(1,id );
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeWishListItem(WishListItem w){
        removeWishListItem(w.getId());
    }

    public void emptyWishList(User u){
        emptyWishList(u.getId());
    }

    public void emptyWishList(int userId){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM WishList WHERE userId = ?");
            ps.setInt(1,userId );
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public WishListItem addItemToWishList(User u,Item item){
        return addItemToWishList(u.getId(),item.getId());
    }

    public WishListItem addItemToWishList(User u,int itemId){
        return addItemToWishList(u.getId(),itemId );
    }

    public WishListItem addItemToWishList(int userId,int itemId){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO WishList(userId, itemId) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,userId );
            ps.setInt(2,itemId );
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                return new WishListItem(id,Item.find(itemId));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
     }

    private WishListItem wishListItemBuilder(ResultSet rs){
        if(rs == null){
            return null;
        }
        try {
            final Item item = Item.find(rs.getInt("itemId"));
            final int id = rs.getInt("id");
            return new WishListItem(id,item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void moveToCart(User u ,WishListItem w) {
        removeWishListItem(w);
        CartDao cartDao = new CartDao();
        cartDao.insertItem(u.getId(),w.getItem().getId() ,1 );
    }
}
