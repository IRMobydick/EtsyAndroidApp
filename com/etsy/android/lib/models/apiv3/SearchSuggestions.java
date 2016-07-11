package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;

public class SearchSuggestions extends BaseModel {
    private List<SearchSuggestion> mResults;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.RESULTS.equals(currentName)) {
                    this.mResults = BaseModel.parseArray(jsonParser, SearchSuggestion.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public List<SearchSuggestion> getResults() {
        return this.mResults != null ? this.mResults : new ArrayList();
    }
}
