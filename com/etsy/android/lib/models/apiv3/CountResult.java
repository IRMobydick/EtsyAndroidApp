package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class CountResult extends BaseModel {
    private long mCount;

    public long getCount() {
        return this.mCount;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.COUNT.equals(currentName)) {
                    this.mCount = jsonParser.getLongValue();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
