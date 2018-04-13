/*
 * Created by Sandeep Tadepalli on 13/02/18 13:12
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;
import org.json.JSONObject;

import java.util.Date;

public abstract class Offer {
    public abstract int applyOffer(CartItem c, Cart cart);
    public abstract int getOfferCode();
    public abstract JSONObject toJSON();
    protected int id;
    protected Date startDate;
    protected Date endDate;


    Offer(int id, Date startDate, Date endDate){
        this.id = id;
        this.startDate = startDate;
        this.endDate =  endDate;
    }
    public int getId(){
        return this.id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isOfferValid(){
        Date currentDate = new Date();
        if(startDate.compareTo(currentDate) <= 0 && endDate.compareTo(currentDate) >=0 ){
            return true;
        }
        return false;
    }
}
