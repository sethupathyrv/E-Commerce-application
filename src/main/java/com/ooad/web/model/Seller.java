/*
 * Created by Sandeep Tadepalli on 13/02/18 13:12
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.utils.Constants;
import org.json.JSONObject;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.*;

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

    public JSONObject addItem(JSONObject item, InputStream fileInputStream) {
        final String itemName=item.getString("name");
        final float itemPrice=item.getFloat("price");
        final JSONObject errors=new JSONObject();
        String imageUrl="";
        if(itemName==null){
            errors.put("name","name should not be null");
        }
        else if(itemPrice==0.0f) {
            errors.put("price", "price should not be null");
        }
        try{
            int read = 0;
            byte[] bytes = new byte[1024];
            String filePath = Constants.FILE_UPLOAD_PATH;
            final String path = String.valueOf(System.currentTimeMillis());

            OutputStream out = new FileOutputStream(new File(filePath+path));
            while ((read = fileInputStream.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();

        } catch (IOException e){
            errors.put("file","file could not be saved");
        }
        imageUrl="/files/path";
        final ItemDao itemDao=new ItemDao();
        final boolean valid=itemDao.createItem(itemName,itemPrice,imageUrl,this.id);
        JSONObject jsonObject=new JSONObject();
        if(valid){
            jsonObject.put("status", Response.Status.CREATED.getStatusCode());
            jsonObject.put("errors","");
            return jsonObject;
        }
        errors.put("item","item addition failed");
        jsonObject.put("errors",errors);
        jsonObject.put("status", Response.Status.BAD_REQUEST.getStatusCode());
        return jsonObject;
    }
}
