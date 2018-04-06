package com.ooad.web.model;

import com.ooad.web.model.Offer.EmptyOffer;
import com.ooad.web.model.Offer.Offer;
import org.json.JSONArray;

public class FreeItem extends Item {

    public FreeItem(Item item){
        super(item.getId(),
                item.getName(),
                item.getPrice(),
                item.getUrl(),
                item.getQuantity(),
                item.getSeller(),
                item.getDescription(),
                item.getBrand(),
                item.getHeight(),
                item.getWidth(),
                item.getItemDetails(),
                item.getOffer(),
                item.getSubCategory(),
                item.getItemBarcode(),
                item.getItemColour());
    }

    @Override
    public float getPrice(){
        return 0;
    }

    @Override
    public Offer getOffer(){
        return new EmptyOffer();
    }

    @Override
    public int getEffectivePrice(){
        return 0;
    }
}
