package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;

public class StatsSource extends BaseModel {
    private static final long serialVersionUID = 2584698239763238664L;
    private List<Source> mSources;
    private int mTotalCount;

    public StatsSource() {
        this.mSources = new ArrayList(0);
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public List<Source> getSources() {
        return this.mSources;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("total_count".equals(currentName)) {
                    this.mTotalCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.SOURCES.equals(currentName)) {
                    this.mSources = BaseModel.parseArray(jsonParser, Source.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
