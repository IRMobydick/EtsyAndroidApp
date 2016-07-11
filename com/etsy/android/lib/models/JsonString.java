package com.etsy.android.lib.models;

import com.etsy.android.lib.logger.EtsyDebug;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringUtils;

public class JsonString extends BaseModel {
    private static final String TAG;
    private static final long serialVersionUID = 8806610668463280833L;
    protected String mData;

    static {
        TAG = EtsyDebug.m1891a(JsonString.class);
    }

    public JsonString() {
        this.mData = StringUtils.EMPTY;
    }

    public String getData() {
        return this.mData;
    }

    public void parseData(JsonParser jsonParser) {
        this.mData = jsonParser.readValueAsTree().toString();
    }
}
