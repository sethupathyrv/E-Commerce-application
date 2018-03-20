package com.ooad.web.model;

import com.ooad.web.dao.UserAddressDao;
import org.json.JSONObject;

public class UserAddress {
    private final int id;
    private String fullname;
    private String mobilenumber;
    private String pincode;
    private String streetAddress;
    private  String landmark;
    private  String city;
    private  String state;


    public UserAddress(int id, String fullname, String mobilenumber, String pincode, String streetAddress, String landmark, String city, String state) {
        this.id = id;
        this.fullname = fullname;
        this.mobilenumber = mobilenumber;
        this.pincode = pincode;
        this.streetAddress = streetAddress;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
    }
    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getPincode() {
        return pincode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", mobilenumber='" + mobilenumber + '\'' +
                ", pincode='" + pincode + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", landmark='" + landmark + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
    public JSONObject toJSON() {
        JSONObject userAddressJsonObject = new JSONObject();
        userAddressJsonObject.put("id", id);
        userAddressJsonObject.put("fullname", fullname);
        userAddressJsonObject.put("mobilenumber", mobilenumber);
        userAddressJsonObject.put("pincode", pincode);
        userAddressJsonObject.put("streetAddress", streetAddress);
        userAddressJsonObject.put("landmark", landmark);
        userAddressJsonObject.put("city", city);
        userAddressJsonObject.put("state", state);
        return userAddressJsonObject;
    }

    public static UserAddress find(int addressId) {
        UserAddressDao userAddressDao = new UserAddressDao();
        return userAddressDao.getAddressfromId(addressId);
    }
}

