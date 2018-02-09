package com.ooad.web.model;

public class User {
    private int id;
    private String userName;
    private String emailId;
    private String password;
    private boolean isEnbaled;

    public User(int id, String userName, String emailId, String password, boolean isEnbaled) {
        this.id = id;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.isEnbaled = isEnbaled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", isEnbaled=" + isEnbaled +
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

    public boolean isEnbaled() {
        return isEnbaled;
    }
}
