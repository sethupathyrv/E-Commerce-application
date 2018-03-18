package com.ooad.web.model;

import org.json.JSONObject;

public class ItemSubCategory {
    private final int id;
    private final String displayName;
    private final boolean isEnabled;
    private final ItemCategory itemCategory;

    public ItemSubCategory(int id, String displayName, boolean isEnabled,ItemCategory itemCategory) {
        this.id = id;
        this.displayName = displayName;
        this.isEnabled = isEnabled;
        this.itemCategory = itemCategory;
    }

    public int getId() {
        return id;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public JSONObject toJSON(){
        final JSONObject j = new JSONObject();
        j.put("id",this.id );
        j.put("displayName",this.displayName);
        j.put("ItemCategory",itemCategory.toJSON());
        return j;
    }
}
