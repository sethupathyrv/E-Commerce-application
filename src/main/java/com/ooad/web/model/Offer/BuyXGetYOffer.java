package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;
import org.json.JSONObject;

public class BuyXGetYOffer extends Offer {
    private int x;
    private int y;

    public BuyXGetYOffer(int id,int x,int y) {
        super(id);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public int applyOffer(CartItem c, Cart cart) {
        int multAdd = c.getQuantity()/(x+y);
        if(multAdd > 0){
            int promotion = (int) (c.getItem().getPrice()*multAdd*y);
            c.setOfferApplied(true);
            return promotion;
        }
        return 0;
    }

    @Override
    public int getOfferCode() {
        return 203;
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject().put("x",getX() )
                .put("y",getY() )
                .put("offerCode",getOfferCode());
    }
}
