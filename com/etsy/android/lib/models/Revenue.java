package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class Revenue extends BaseModel {
    private static final long serialVersionUID = 2584698239763238664L;
    private double mRevenue;
    private String mRevenueCurrencyCode;

    public Revenue() {
        this.mRevenueCurrencyCode = StringUtils.EMPTY;
    }

    public double getRevenue() {
        return this.mRevenue;
    }

    public String getRevenueCurrencyCode() {
        return this.mRevenueCurrencyCode;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("revenue".equals(currentName)) {
                    this.mRevenue = jsonParser.getValueAsDouble();
                } else if ("revenue_currency_code".equals(currentName)) {
                    this.mRevenueCurrencyCode = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
