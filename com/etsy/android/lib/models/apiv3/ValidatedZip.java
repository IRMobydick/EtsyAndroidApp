package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ValidatedZip extends BaseModel {
    protected String mCity;
    protected boolean mIsEligibleForMarket;
    protected int mMarket;
    protected String mState;
    protected String mZip;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.CITY.equals(currentName)) {
                    this.mCity = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.STATE.equals(currentName)) {
                    this.mState = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.ZIP.equals(currentName)) {
                    this.mZip = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.USER_ADDRESS_ID.equals(currentName)) {
                    this.mMarket = jsonParser.getIntValue();
                } else if (ResponseConstants.IS_ELIGIBLE.equals(currentName)) {
                    this.mIsEligibleForMarket = jsonParser.getBooleanValue();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public String getCity() {
        return this.mCity;
    }

    public String getState() {
        return this.mState;
    }

    public String getZip() {
        return this.mZip;
    }

    public int getMarket() {
        return this.mMarket;
    }

    public boolean isEligibleForMarket() {
        return this.mIsEligibleForMarket;
    }
}
