/*
 * Created by Sandeep Tadepalli on 13/02/18 13:12
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.model.Offer;

import com.ooad.web.model.Cart;

public abstract class Offer {
    public abstract void applyOffer(int quantity,Cart cart);
}
