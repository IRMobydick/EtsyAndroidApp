package com.etsy.android.lib.models;

import android.support.annotation.NonNull;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public abstract class BaseFieldModel extends BaseModel {
    protected abstract boolean parseField(JsonParser jsonParser, @NonNull String str);

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || currentName == null || !parseField(jsonParser, currentName)) {
                jsonParser.skipChildren();
            }
        }
    }
}
