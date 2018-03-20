/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.dao;

import com.ooad.web.model.Item;
import com.ooad.web.model.ItemCategory;
import com.ooad.web.model.ItemSubCategory;
import com.ooad.web.model.Offer.*;
import com.ooad.web.model.Seller;
import com.ooad.web.utils.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemDao {
    public boolean createItem(final String name, final float price, final String url, final int sellerId,
                              String description, final String brand, float height, float width,int quantity,
                              int subCategoryId,int offerId,int itemBarcode) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO Items(name,price,url,sellerId,description,brand,height,width,quantity,subCategoryId,offerId,itemBarcode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, name);
            ps.setFloat(2, price);
            ps.setString(3, url);
            ps.setInt(4, sellerId);
            ps.setString(5, description);
            ps.setString(6, brand);
            ps.setFloat(7, height);
            ps.setFloat(8, width);
            ps.setInt(9,quantity);
            ps.setInt(10,subCategoryId);
            ps.setInt(11,offerId);
            ps.setInt(12,itemBarcode);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Item getItembyId(int id) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Items WHERE id=?");
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Item item= itemBuilder(rs);
                con.close();
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Item getItembyBarcode(int barcode){
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Items WHERE itemBarcode= ?");
            ps.setInt(1,barcode );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Item item = itemBuilder(rs);
                con.close();
                return item;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Item> getLastFiveItems() {
        try {
            Connection con = Database.getConnection();
            final List<Item> items = new ArrayList<Item>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Items ORDER BY id DESC LIMIT 5");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final Item item = itemBuilder(rs);
                items.add(item);
            }
            con.close();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean saveItem(Item item){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("UPDATE Items SET name = ? ,price = ?,url = ?," +
                            "sellerId = ? ,description = ? ,brand = ?,height = ?,width = ? "+
                            ",quantity = ? WHERE id = ?");
            ps.setString(1, item.getName());
            ps.setFloat(2, item.getPrice());
            ps.setString(3, item.getUrl());
            ps.setInt(4, item.getSeller().getId());
            ps.setString(5, item.getDescription());
            ps.setString(6, item.getBrand());
            ps.setFloat(7, item.getHeight());
            ps.setFloat(8, item.getWidth());
            ps.setInt(9,item.getQuantity());
            ps.setInt(10,item.getId());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Item itemBuilder(ResultSet rs) throws NullPointerException, SQLException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        final float price = rs.getFloat("price");
        final String url = rs.getString("url");
        final String itemDescription = rs.getString("description");
        final String brand = rs.getString("brand");
        final int sellerId = rs.getInt("sellerId");
        final int quantity = rs.getInt("quantity");
        final float height = rs.getFloat("height");
        final float width = rs.getFloat("width");
        final int offerId = rs.getInt("offerId");
        final int subCategoryId = rs.getInt("SubCategoryId");
        final int itemBarcode = rs.getInt("itemBarcode");
        SellerDao sellerDao = new SellerDao();
        Seller seller = sellerDao.getSeller(sellerId);
        return new Item(id, name, price, url, quantity, seller, itemDescription, brand, height,
                width, getItemDetails(id),getOffer(offerId),getItemSubCategory(subCategoryId),itemBarcode);
    }
    private ItemSubCategory getItemSubCategory(int subCategoryId){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("select SubCategories.id as sid,SubCategories.displayName as sdn,Categories.* " +
                    "from SubCategories,Categories where SubCategories.id=? and SubCategories.categoryId=Categories.id;");
            ps.setInt(1,subCategoryId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                final int id = rs.getInt("id");
                final String name = rs.getString("name");
                final String displayName = rs.getString("displayName");
                final boolean isEnabled = rs.getBoolean("isEnabled");
                ItemCategory itemCategory = new ItemCategory(id,name,displayName,isEnabled);
                final int sid = rs.getInt("sid");
                final String sdn = rs.getString("sdn");
                ItemSubCategory itemSubCategory = new ItemSubCategory(sid,sdn,true,itemCategory);
                con.close();
                return itemSubCategory;
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
    private Offer getOffer(int offerId){
        if(offerId == -1) return new EmptyOffer();
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Offers WHERE id = ?");
            ps.setInt(1,offerId );
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int offerType = rs.getInt("offerType");
                if (offerType == 201) {
                    float discountPercentage = rs.getFloat("discountPercentage");
                    Offer o = new DiscountOffer(offerId, discountPercentage);
                    con.close();
                    return o;
                } else if (offerType == 202) {
                    int price = rs.getInt("price");
                    Offer o = new PriceOffer(offerId, price);
                    con.close();
                    return o;
                } else if (offerType == 203) {
                    int x = rs.getInt("x");
                    int y = rs.getInt("y");
                    Offer o = new BuyXGetYOffer(offerId, x, y);
                    con.close();
                    return o;
                } else if (offerType == 204) {
                    int x = rs.getInt("x");
                    Offer o = new BuyXGetLowestFreeOffer(offerId, x);
                    con.close();
                    return o;
                } else if(offerType == 205) {

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONArray getItemDetails(final int itemId){
        JSONArray itemDetailsArray = new JSONArray();
        if (itemId == 0) throw new NullPointerException("Item Id can't be null");
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ItemDetails WHERE itemId = ?");
            ps.setInt(1,itemId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                JSONObject itemDetails = new JSONObject();
                itemDetails.put("id",rs.getInt("id") );
                itemDetails.put("key",rs.getString("key") );
                itemDetails.put("value",rs.getString("value") );
                itemDetailsArray.put(itemDetails);
            }
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemDetailsArray;
    }
    public Collection<Item> getItemsFromSubCategory(String CategoryName,String SubCategoryName){
        try {
            Connection con = Database.getConnection();
            final List<Item> items = new ArrayList<Item>();
            PreparedStatement ps = con.prepareStatement("SELECT Items.* FROM Categories,SubCategories,Items WHERE Categories.name=? " +
                    "AND Categories.Id = SubCategories.categoryId and SubCategories.displayName=?" +
                    "AND  Items.SubCategoryId=SubCategories.Id;");
            ps.setString(1,CategoryName);
            ps.setString(2,SubCategoryName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Item item = itemBuilder(rs);
                items.add(item);
            }
            con.close();
            return items;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int createOffer(int offerType,int discountPercentage,int priceOffer){
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps= con.prepareStatement("INSERT INTO Offers (offerType, discountPercentage, price) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,offerType );
            ps.setInt(2,discountPercentage );
            ps.setInt(3,priceOffer );
            int r = ps.executeUpdate();
            if(r>0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    con.close();
                    return id;
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
