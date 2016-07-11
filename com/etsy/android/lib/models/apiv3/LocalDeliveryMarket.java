package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class LocalDeliveryMarket extends BaseModel {
    protected boolean mIsAvailable;
    protected int mMarket;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.IS_AVAILABLE.equals(currentName)) {
                    this.mIsAvailable = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.MARKET.equals(currentName)) {
                    this.mMarket = jsonParser.getValueAsInt();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public int getMarket() {
        return this.mMarket;
    }

    public boolean isAvailable() {
        return this.mIsAvailable;
    }
}
