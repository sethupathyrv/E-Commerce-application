package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;
import org.json.JSONObject;

import java.util.Date;

public class EmptyOffer extends Offer {
    public EmptyOffer() {
        super(-1,null,null);
    }

    public EmptyOffer(int id){
        super(id,null,null);
    }

    public EmptyOffer(int id, Date startDate, Date endDate){
        super(id,startDate,endDate);
    }

    @Override
    public int applyOffer(CartItem c, Cart cart) {
        return 0;
    }

    public int applyOffer(CartItem c){
        return 0;
    }

    public int applyOffer(){
        return 0;
    }

    @Override
    public int getOfferCode() {
        return -1;
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject().put("offerCode",getOfferCode());
    }
}
