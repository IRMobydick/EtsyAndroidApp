package com.etsy.android.lib.models;

import com.etsy.android.lib.models.apiv3.ActivityFeedSubject;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class ShippingCarrier extends BaseModel {
    private EtsyId mCarrierId;
    private String mName;
    private String mTrackingNumber;

    public ShippingCarrier() {
        this.mName = StringUtils.EMPTY;
        this.mTrackingNumber = StringUtils.EMPTY;
        this.mCarrierId = new EtsyId();
    }

    public ShippingCarrier(String str) {
        this();
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public EtsyId getId() {
        return this.mCarrierId;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("carrier_id".equals(currentName)) {
                    this.mCarrierId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.PROVIDER_ID.equals(currentName)) {
                    this.mCarrierId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ActivityFeedSubject.DISPLAY_NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if ("provider_name".equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if ("tracking_num".equals(currentName)) {
                    this.mTrackingNumber = BaseModel.parseStringURL(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public String toString() {
        return this.mName;
    }
}
