package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;

public class DiscountOffer extends Offer {
    private float percentage;

    public DiscountOffer(float discountPercentage) {
        super();
        this.percentage = discountPercentage;
    }

    @Override
    public void applyOffer(int quantity, Cart cart) {

    }

}
