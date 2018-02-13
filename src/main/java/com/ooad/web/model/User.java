package com.ooad.web.model;

import org.json.JSONObject;

public class User {

    private int id;
    private String userName;
    private String emailId;
    private String password;
    private boolean isEnabled;

    public User(int id, String userName, String emailId, String password, boolean isEnbaled) {
        this.id = id;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.isEnabled = isEnbaled;
    }

    @Override
    public String toString() {
        return "User{" +
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
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("email", emailId);
        userJsonObject.put("username", userName);
        return userJsonObject;
    }


}
