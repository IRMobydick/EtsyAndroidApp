package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class RevenueItem extends BaseModel {
    protected static final long serialVersionUID = -4315055152912281460L;
    protected double mCount;
    protected String mCurrencyCode;
    protected long mTimestamp;

    public RevenueItem() {
        this.mCurrencyCode = StringUtils.EMPTY;
    }

    public double getCount() {
        return this.mCount;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public void setCurrencyCode(String str) {
        this.mCurrencyCode = str;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.COUNT.equals(currentName)) {
                    this.mCount = jsonParser.getValueAsDouble();
                } else if ("timestamp".equals(currentName)) {
                    this.mTimestamp = jsonParser.getValueAsLong();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
