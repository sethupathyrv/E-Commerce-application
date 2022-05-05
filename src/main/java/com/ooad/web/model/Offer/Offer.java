package com.ooad.web.model.Offer;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;
import org.json.JSONObject;

import java.util.Date;

public abstract class Offer {
    public abstract int applyOffer(CartItem c, Cart cart);
    public abstract int getOfferCode();
    public abstract JSONObject toJSON();
    protected int id;
    protected String dealId;
    protected Date startDate;
    protected Date endDate;


    Offer(int id, String dealId, Date startDate, Date endDate){
        this.id = id;
        this.dealId = dealId;
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

    public String getDealId() { return dealId;}

    public boolean isOfferValid(){
        Date currentDate = new Date();
        if(startDate.compareTo(currentDate) <= 0 && endDate.compareTo(currentDate) >=0 ){
            return true;
        }
        return false;
    }
    public void save(Offer offer){
        ItemDao itemDao = new ItemDao();
        itemDao.saveOffer(offer);
    }
}
