/*
 * Created by Sandeep Tadepalli on 13/02/18 13:12
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

import org.json.JSONObject;

public class Seller {
    private int id;
    private String userName;
    private String emailId;
    private String password;
    private boolean isEnabled;

    public Seller(int id, String userName, String emailId, String password, boolean isEnbaled) {
        this.id = id;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.isEnabled = isEnbaled;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", isEnbaled=" + isEnabled +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }


    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public JSONObject toJSON() {
        JSONObject sellerJsonObject = new JSONObject();
        sellerJsonObject.put("id", id);
        sellerJsonObject.put("username", userName);
        return sellerJsonObject;
    }

    public JSONObject addItem(int id,String name, float price, String url) {

        //Pre processing Storing the file into server
        //Check for errors
        //Call Add Item from ITem DAO
        //If true return jsonobject with true
        //Else Item Addition failed try again.
        //return the json object
        return null;
    }


}
