package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;

public class SearchSimplifiedQueries extends BaseModel {
    private List<String> mQueries;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.RESULTS.equals(currentName)) {
                    this.mQueries = BaseModel.parseStringArray(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public List<String> getQueries() {
        return this.mQueries != null ? this.mQueries : new ArrayList();
    }
}
