package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Country;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;

public class CartShippingDetails extends BaseModel {
    private List<Country> mShippingCountryList;
    private ShippingDestination mShippingDestination;
    private List<ShippingOption> mShippingOptionList;

    public CartShippingDetails(ShippingDestination shippingDestination, List<Country> list, List<ShippingOption> list2) {
        this.mShippingDestination = shippingDestination;
        this.mShippingCountryList = list;
        this.mShippingOptionList = list2;
    }

    public ShippingDestination getShippingDestination() {
        return this.mShippingDestination;
    }

    public List<Country> getShippingCountryList() {
        return this.mShippingCountryList;
    }

    public List<ShippingOption> getShippingOptionList() {
        return this.mShippingOptionList;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ListingShippingDetails.DEFAULT_ADDRESS.equals(currentName)) {
                    this.mShippingDestination = (ShippingDestination) BaseModel.parseObject(jsonParser, ShippingDestination.class);
                } else if (ListingShippingDetails.COUNTRIES.equals(currentName)) {
                    this.mShippingCountryList = BaseModel.parseArray(jsonParser, Country.class);
                } else if ("shipping_options".equals(currentName)) {
                    this.mShippingOptionList = BaseModel.parseArray(jsonParser, ShippingOption.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
