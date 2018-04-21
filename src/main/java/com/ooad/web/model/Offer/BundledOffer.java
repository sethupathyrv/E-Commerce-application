package com.ooad.web.model.Offer;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.model.Cart;
import com.ooad.web.model.CartItem;
import com.ooad.web.model.FreeItem;
import com.ooad.web.model.Item;
import org.json.JSONObject;

import java.util.Date;

public class BundledOffer extends Offer{

    private FreeItem freeItem;
    public BundledOffer(int id,String dealId, Date startDate, Date endDate,int barcode) {
        super(id,dealId,startDate,endDate);
        this.freeItem = new FreeItem(new ItemDao().getItembyBarcode(barcode));
    }


    @Override
    public int applyOffer(CartItem c, Cart cart) {
        if(!isOfferValid()) return 0;
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

    @Override
    public JSONObject toJSON() {
        return new JSONObject().put("offerCode",getOfferCode())
                .put("freeItem","freeItem");
    }
}
