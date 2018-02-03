/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.dao;

import com.google.common.collect.ImmutableList;
import com.ooad.web.model.ItemCategory;
import com.ooad.web.utils.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemCategoryDao {
    public Collection<ItemCategory> getAllCategories() {
        try {
            final List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();
            final Connection con = Database.getConnection();
            final Statement st = con.createStatement();
            final String getAllQuery = "SELECT * FROM Category WHERE isEnabled = 1";
            final ResultSet rs = st.executeQuery(getAllQuery);
            while (rs.next()) {
                final ItemCategory itemCategory = itemCategoryBuilder(rs);
                itemCategories.add(itemCategory);
            }
            return ImmutableList.copyOf(itemCategories);
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


}
