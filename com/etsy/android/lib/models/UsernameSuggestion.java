package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class UsernameSuggestion extends BaseModel {
    private String mStrategy;
    private String mSuggestion;

    public UsernameSuggestion() {
        this.mStrategy = StringUtils.EMPTY;
        this.mSuggestion = StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.SUGGESTION.equals(currentName)) {
                    this.mSuggestion = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.STRATEGY.equals(currentName)) {
                    this.mStrategy = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public String getStrategy() {
        return this.mStrategy;
    }

    public String getSuggestion() {
        return this.mSuggestion;
    }
}
