package com.etsy.android.lib.models;

import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class LocalDeliveryDetails extends BaseModel {
    protected EtsyId mDeliveryDetailsId;
    protected int mMarket;
    protected String mRequestedTimeFormatted;
    protected boolean mRequestedTimeIsNow;

    public LocalDeliveryDetails() {
        this.mDeliveryDetailsId = new EtsyId();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.MARKET.equals(currentName)) {
                    this.mMarket = jsonParser.getValueAsInt();
                } else if (ResponseConstants.DELIVERY_DETAILS_ID.equals(currentName)) {
                    this.mDeliveryDetailsId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.REQUESTED_TIME_FORMATTED.equals(currentName)) {
                    this.mRequestedTimeFormatted = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.REQUESTED_TIME_IS_NOW.equals(currentName)) {
                    this.mRequestedTimeIsNow = jsonParser.getValueAsBoolean();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
