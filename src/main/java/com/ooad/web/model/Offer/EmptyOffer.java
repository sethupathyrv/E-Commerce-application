package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;

public class EmptyOffer extends Offer {
    public EmptyOffer() {
        super(-1);
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
}
