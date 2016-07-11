package com.etsy.android.lib.models;

import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class ReceiptShippingDetails extends BaseModel {
    protected EtsyId mDeliveryDetailsId;
    protected String mRequestedEndTime;
    protected String mRequestedStartTime;
    protected String mRequestedTimeFormatted;
    protected Boolean mRequestedTimeIsInPast;
    protected Boolean mRequestedTimeIsNow;
    protected Boolean mRequestedTimeIsToday;
    protected String mShippingMethod;

    public ReceiptShippingDetails() {
        this.mShippingMethod = StringUtils.EMPTY;
        this.mDeliveryDetailsId = new EtsyId();
        this.mRequestedStartTime = StringUtils.EMPTY;
        this.mRequestedEndTime = StringUtils.EMPTY;
        this.mRequestedTimeFormatted = StringUtils.EMPTY;
    }

    public String getShippingMethod() {
        return this.mShippingMethod;
    }

    public String getRequestedStartTime() {
        return this.mRequestedStartTime;
    }

    public String getRequestedEndTime() {
        return this.mRequestedEndTime;
    }

    public String getRequestedTimeFormatted() {
        return this.mRequestedTimeFormatted;
    }

    public Boolean getRequestedTimeIsNow() {
        return this.mRequestedTimeIsNow;
    }

    public Boolean getRequestedTimeIsInPast() {
        return this.mRequestedTimeIsInPast;
    }

    public Boolean isRequestedTimeIsToday() {
        return this.mRequestedTimeIsToday;
    }

    public EtsyId getDeliveryDetailsId() {
        return this.mDeliveryDetailsId;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.SHIPPING_METHOD.equals(currentName)) {
                    this.mShippingMethod = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.DELIVERY_DETAILS_ID.equals(currentName)) {
                    this.mDeliveryDetailsId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.REQUESTED_START_TIME.equals(currentName)) {
                    this.mRequestedStartTime = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.REQUESTED_END_TIME.equals(currentName)) {
                    this.mRequestedEndTime = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.REQUESTED_TIME_FORMATTED.equals(currentName)) {
                    this.mRequestedTimeFormatted = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.REQUESTED_TIME_IS_TODAY.equals(currentName)) {
                    this.mRequestedTimeIsToday = Boolean.valueOf(jsonParser.getBooleanValue());
                } else if (ResponseConstants.REQUESTED_TIME_IS_NOW.equals(currentName)) {
                    this.mRequestedTimeIsNow = Boolean.valueOf(jsonParser.getBooleanValue());
                } else if (ResponseConstants.REQUESTED_TIME_IS_IN_PAST.equals(currentName)) {
                    this.mRequestedTimeIsInPast = Boolean.valueOf(jsonParser.getBooleanValue());
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
