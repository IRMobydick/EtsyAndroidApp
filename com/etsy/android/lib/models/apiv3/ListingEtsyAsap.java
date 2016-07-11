package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class ListingEtsyAsap extends BaseModel {
    protected boolean mOffersEtsyAsap;
    protected boolean mOffersEtsyAsapToday;

    public ListingEtsyAsap() {
        this.mOffersEtsyAsap = false;
        this.mOffersEtsyAsapToday = false;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.ETSY_ASAP_AVAILABLE.equals(currentName)) {
                    this.mOffersEtsyAsap = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.ETSY_ASAP_AVAILABLE_TODAY.equals(currentName)) {
                    this.mOffersEtsyAsapToday = jsonParser.getValueAsBoolean();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public boolean offersEtsyAsap() {
        return this.mOffersEtsyAsap;
    }

    public boolean offersEtsyAsapToday() {
        return this.mOffersEtsyAsapToday;
    }
}
