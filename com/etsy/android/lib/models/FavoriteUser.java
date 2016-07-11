package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class FavoriteUser extends User {
    private static final long serialVersionUID = 4146158149850402898L;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("TargetUser".equals(currentName)) {
                    super.parseData(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
