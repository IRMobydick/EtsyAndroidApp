package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;

public class IntWrapper extends BaseModel {
    private static final long serialVersionUID = -8397722969199068107L;
    public long value;

    public void parseData(JsonParser jsonParser) {
        this.value = jsonParser.getValueAsLong();
    }
}
