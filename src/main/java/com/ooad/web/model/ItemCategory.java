/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

import org.json.JSONObject;

public class ItemCategory {
    private final int id;
    private final String name;
    private final String displayName;
    private final boolean isEnabled;

    public ItemCategory(int id, String name, String displayName, boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.isEnabled = isEnabled;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }

    public JSONObject toJSON(){
        final JSONObject j = new JSONObject();
        j.put("id",this.id );
        j.put("name",this.name);
        j.put("displayName",this.displayName);
        return j;
    }
}
