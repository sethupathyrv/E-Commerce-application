package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;
import org.json.JSONObject;

import java.util.Date;

public class PriceOffer extends Offer {
    private int priceCut;

    public PriceOffer(int id,String dealId, Date startDate, Date endDate,int priceCut) {
        super(id,dealId,startDate,endDate);
        this.priceCut = priceCut;
    }

    @Override
    public int applyOffer(CartItem c, Cart cart) {
        if(!isOfferValid()) return 0;
        Float price = c.getItem().getPrice();
        c.setOfferApplied(true);
        c.saveCartItem();
        return ((int) (priceCut))*c.getQuantity();
    }

    public int applyOffer(CartItem c){
        return 0;
    }

    @Override
    public int getOfferCode() {
        return 202;
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject().put("offerCode",getOfferCode() )
                .put("priceCut",getPriceCut());
    }

    public int getPriceCut() {
        return priceCut;
    }

    public void setPriceCut(int priceCut) {
        this.priceCut = priceCut;
    }
}
