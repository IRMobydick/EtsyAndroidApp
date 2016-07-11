package com.etsy.android.lib.models;

import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;

public class ShortenedUrl extends BaseModel {
    private static final long serialVersionUID = 7397399633460971137L;
    private String mLongUrl;
    private String mShortUrl;

    public ShortenedUrl(String str) {
        this.mLongUrl = str;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -2027693268:
                        if (currentName.equals(ResponseConstants.SHORT_URL)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 116079:
                        if (currentName.equals(ResponseConstants.URL)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mLongUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mShortUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public String getShortUrl() {
        if (bh.m3340a(this.mShortUrl)) {
            return this.mShortUrl;
        }
        return this.mLongUrl;
    }

    public String getLongUrl() {
        return this.mLongUrl;
    }

    public boolean isShortened() {
        return (this.mShortUrl == null || this.mShortUrl.equals(this.mLongUrl)) ? false : true;
    }
}
