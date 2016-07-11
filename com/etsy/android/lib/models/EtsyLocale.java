package com.etsy.android.lib.models;

import com.etsy.android.lib.requests.EtsyRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class EtsyLocale extends BaseModel {
    EtsyCurrency mCurrency;

    public EtsyCurrency getCurrency() {
        return this.mCurrency;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (EtsyRequest.PARAM_CURRENCY.equals(currentName)) {
                    this.mCurrency = (EtsyCurrency) BaseModel.parseObject(jsonParser, EtsyCurrency.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
