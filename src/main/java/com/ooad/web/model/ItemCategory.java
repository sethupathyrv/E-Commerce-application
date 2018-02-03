/*
 * Created by Sandeep Tadepalli on 04/02/18 03:15
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

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

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
