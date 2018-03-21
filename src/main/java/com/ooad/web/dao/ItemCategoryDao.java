/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.dao;

import com.google.common.collect.ImmutableList;
import com.ooad.web.model.ItemCategory;
import com.ooad.web.model.ItemSubCategory;
import com.ooad.web.utils.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemCategoryDao {
    public Collection<ItemCategory> getAllCategories() {
        try {
            final List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();
            final Connection con = Database.getConnection();
            final Statement st = con.createStatement();
            final String getAllQuery = "SELECT * FROM Categories WHERE isEnabled = 1";
            final ResultSet rs = st.executeQuery(getAllQuery);
            while (rs.next()) {
                final ItemCategory itemCategory = itemCategoryBuilder(rs);
                itemCategories.add(itemCategory);
            }
            con.close();
            return itemCategories;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ItemCategory getCategory(int CategoryId) {
        try {
            final List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();
            final Connection con = Database.getConnection();
            final Statement st = con.createStatement();
            final String getAllQuery = "SELECT * FROM Categories WHERE isEnabled = 1 AND id="+CategoryId;
            final ResultSet rs = st.executeQuery(getAllQuery);
            while (rs.next()) {
                ItemCategory itemCategory = itemCategoryBuilder(rs);
                con.close();
                return itemCategory;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private ItemCategory itemCategoryBuilder(ResultSet rs) throws SQLException, NullPointerException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        final String displayName = rs.getString("displayName");
        final boolean isEnabled = rs.getBoolean("isEnabled");
        return new ItemCategory(id, name, displayName, isEnabled);
    }
    private ItemSubCategory itemsubCategoryBuilder(ResultSet rs,ItemCategory itemCategory) throws SQLException, NullPointerException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int id = rs.getInt("id");
        final String displayName = rs.getString("displayName");
        final boolean isEnabled = rs.getBoolean("isEnabled");
        return new ItemSubCategory(id,displayName,isEnabled,itemCategory);
    }

    public Collection<ItemSubCategory> getAllsubCategories(int CategoryId) {
        try {
            final List<ItemSubCategory> itemsubCategories = new ArrayList<ItemSubCategory>();
            final Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM SubCategories WHERE isEnabled=1 AND categoryId=?");
            ps.setInt(1,CategoryId);
            ItemCategory itemCategory = getCategory(CategoryId);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final ItemSubCategory itemsubCategory = itemsubCategoryBuilder(rs,itemCategory);
                itemsubCategories.add(itemsubCategory);
            }
            con.close();
            return  itemsubCategories;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public JSONObject createCategory(JSONObject re) {
        final String category = re.getString("category");
        JSONObject subCategory = re.getJSONObject("subcategory");
        JSONArray subcategories = subCategory.getJSONArray("subcategory");

        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO categories(name, displayName) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,category);
            ps.setString(2,category);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                int id = rs.getInt(1);
                createSubCategory(subcategories,id);
                con.close();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createSubCategory(JSONArray subCategories, int id) {
        try {
                Connection con = Database.getConnection();
                PreparedStatement ps = null;
                ps = con.prepareStatement("INSERT into subcategories(displayName, categoryId) VALUES (?,?) ");
                for(int i=0;i<subCategories.length();i++) {
                    ps.setString(1, subCategories.getString(i));
                    ps.setInt(2, id);
                    ps.addBatch();
                }
                ps.executeBatch();
                con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
