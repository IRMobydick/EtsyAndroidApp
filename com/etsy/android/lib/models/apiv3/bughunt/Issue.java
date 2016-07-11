package com.etsy.android.lib.models.apiv3.bughunt;

import com.appboy.models.cards.Card;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class Issue extends BaseModel {
    private String mCreated;
    private String mKey;
    private String mSummary;

    public String getSummary() {
        return this.mSummary;
    }

    public String getCreated() {
        return this.mCreated;
    }

    public String getKey() {
        return this.mKey;
    }

    public void setSummary(String str) {
        this.mSummary = str;
    }

    public void setCreated(String str) {
        this.mCreated = str;
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("summary".equals(currentName)) {
                    this.mSummary = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.KEY.equals(currentName)) {
                    this.mKey = BaseModel.parseString(jsonParser);
                } else if (Card.CREATED.equals(currentName)) {
                    this.mCreated = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
