package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;

public class BuyXGetLowestFreeOffer extends Offer {
    private int x;
    @Override
    public int applyOffer(CartItem c, Cart cart) {
        int count = 0;
        int leastPrice= (int) c.getItem().getPrice();
        for (CartItem ci: cart.getCartItems()) {
            if(ci.isOfferApplied()) continue;

            if(ci.getItem().getOffer().getOfferCode() == 204){
                ci.setOfferApplied(true);
                if(ci.getItem().getPrice() < leastPrice)
                    leastPrice = (int) ci.getItem().getPrice();
                count = count+ci.getQuantity();
            }
        }
        if(count >= x) {
            return leastPrice;
        }
        return 0;
    }

    @Override
    public int getOfferCode() {
        return 204;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public BuyXGetLowestFreeOffer(int id, int x) {
        super(id);
        this.x= x;

    }
}
