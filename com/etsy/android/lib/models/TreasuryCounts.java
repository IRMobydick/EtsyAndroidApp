package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class TreasuryCounts extends BaseModel {
    private int mClicks;
    private int mViews;

    public int getViews() {
        return this.mViews;
    }

    public int getClicks() {
        return this.mClicks;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.VIEWS.equals(currentName)) {
                    this.mViews = jsonParser.getValueAsInt();
                } else if ("clicks".equals(currentName)) {
                    this.mClicks = jsonParser.getValueAsInt();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
