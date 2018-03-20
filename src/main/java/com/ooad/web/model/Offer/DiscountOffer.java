package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;

public class DiscountOffer extends Offer {
    private float percentage;

    public DiscountOffer(int id,float discountPercentage) {
        super(id);
        this.percentage = discountPercentage;
    }

    @Override
    public int applyOffer(CartItem c, Cart cart) {
        Float price = c.getItem().getPrice();
        Float discountPrice = (price*percentage)/100;
        c.setOfferApplied(true);
        c.saveCartItem();
        return (int) (( discountPrice)*c.getQuantity());
    }

    public int applyOffer(CartItem c) {
        return 0;
    }

    @Override
    public int getOfferCode() {
        return 201;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
