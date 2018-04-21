package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;
import com.ooad.web.model.Item;
import org.json.JSONObject;

import java.util.Date;

public class DiscountOffer extends Offer {
    private float percentage;

    public DiscountOffer(int id, String dealId,Date startDate, Date endDate, float discountPercentage) {
        super(id,dealId,startDate,endDate);
        this.percentage = discountPercentage;
    }

    @Override
    public int applyOffer(CartItem c, Cart cart) {
        if(!isOfferValid()) return 0;
        Float price = c.getItem().getPrice();
        Float discountPrice = (price*percentage)/100;
        c.setOfferApplied(true);
        c.saveCartItem();
        return (int) (( discountPrice)*c.getQuantity());
    }

    public int applyOffer(CartItem c) {
        return 0;
    }

    public int applyOffer(Item i){
        Float price = i.getPrice();
        int discountPrice = (int) ((price*percentage)/100);
        return discountPrice;
    }

    @Override
    public int getOfferCode() {
        return 201;
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject().put("percentage",this.percentage )
                .put("offerCode",getOfferCode() );
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
