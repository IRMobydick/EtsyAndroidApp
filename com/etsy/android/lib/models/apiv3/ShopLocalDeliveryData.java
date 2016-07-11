package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ShopLocalDeliveryData extends BaseModel {
    protected boolean mIsEligible;
    protected boolean mIsEnabled;
    protected int mMarket;
    protected String mPhone;
    protected UserAddressV3 mPickupAddress;
    protected String mPickupNotes;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.IS_ELIGIBLE.equals(currentName)) {
                    this.mIsEligible = jsonParser.getBooleanValue();
                } else if (ResponseConstants.IS_ENABLED.equals(currentName)) {
                    this.mIsEnabled = jsonParser.getBooleanValue();
                } else if (ResponseConstants.MARKET.equals(currentName)) {
                    this.mMarket = jsonParser.getIntValue();
                } else if (ResponseConstants.PHONE.equals(currentName)) {
                    this.mPhone = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.PICKUP_NOTES.equals(currentName)) {
                    this.mPickupNotes = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.PICKUP_ADDRESS.equals(currentName)) {
                    this.mPickupAddress = (UserAddressV3) BaseModel.parseObject(jsonParser, UserAddressV3.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public boolean isEligible() {
        return this.mIsEligible;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public int getMarket() {
        return this.mMarket;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public String getPickupNotes() {
        return this.mPickupNotes;
    }

    public UserAddressV3 getPickupAddress() {
        return this.mPickupAddress;
    }
}
