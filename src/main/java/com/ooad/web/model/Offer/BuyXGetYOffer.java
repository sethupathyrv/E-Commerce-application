package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;
import com.ooad.web.model.FreeItem;
import org.json.JSONObject;

import java.util.Date;

public class BuyXGetYOffer extends Offer {
    private int x;
    private int y;

    public BuyXGetYOffer(int id, Date startDate, Date endDate, int x, int y) {
        super(id,startDate,endDate);
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
        if(!isOfferValid()) return 0;
        if(c.getQuantity() >=  x){
            int promotion = (int) (c.getItem().getPrice()*y);
            c.setOfferApplied(true);
            CartItem c1 = new CartItem(-1,new FreeItem(c.getItem()),y,true);
            cart.getTestItems().add(c1);
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
