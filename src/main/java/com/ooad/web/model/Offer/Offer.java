/*
 * Created by Sandeep Tadepalli on 13/02/18 13:12
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;

public abstract class Offer {
    public abstract int applyOffer(CartItem c, Cart cart);
    public abstract int getOfferCode();
    private int id;

    Offer(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }


}
