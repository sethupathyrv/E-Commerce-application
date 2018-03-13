package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;

public class PriceOffer extends Offer {
    private int priceCut;

    public PriceOffer(int id,int priceCut) {
        super(id);
        this.priceCut = priceCut;
    }

    @Override
    public int applyOffer(CartItem c, Cart cart) {
        Float price = c.getItem().getPrice();
        c.setOfferApplied(true);
        c.saveCartItem();
        return ((int) (price-priceCut))*c.getQuantity();
    }

    @Override
    public int getOfferCode() {
        return 202;
    }

    public int getPriceCut() {
        return priceCut;
    }

    public void setPriceCut(int priceCut) {
        this.priceCut = priceCut;
    }
}
