package com.ooad.web.model.Offer;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;
import com.ooad.web.model.FreeItem;
import com.ooad.web.model.Item;

public class BundledOffer extends Offer{

    private FreeItem freeItem;
    public BundledOffer(int id,int barcode) {
        super(id);
        this.freeItem = new FreeItem(new ItemDao().getItembyBarcode(barcode));
    }


    @Override
    public int applyOffer(CartItem c, Cart cart) {
     if(c.isOfferApplied()){
         return 0;
     }else {
         CartItem freeCartItem = new CartItem(0,freeItem,1,true);
         cart.addCartItem(freeCartItem);
         return 0;
     }
    }

    public FreeItem getFreeItem() {
        return freeItem;
    }

    @Override
    public int getOfferCode() {
        return 205;
    }
}
