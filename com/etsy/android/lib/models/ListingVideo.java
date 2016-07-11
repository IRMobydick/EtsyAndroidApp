package com.etsy.android.lib.models;

import com.etsy.android.lib.logger.EtsyDebug;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ListingVideo extends BaseModel {
    private static final String TAG;
    private String mQuality;
    private String mUrl;

    static {
        TAG = EtsyDebug.m1891a(ListingVideo.class);
    }

    public String getQuality() {
        return this.mQuality;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.URL.equals(currentName)) {
                    this.mUrl = BaseModel.parseString(jsonParser);
                } else if ("quality".equals(currentName)) {
                    this.mQuality = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
