package com.etsy.android.lib.models.apiv3.bughunt;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class Leader extends BaseModel {
    private String mAvatarUrl;
    private String mName;
    private int mScore;
    private String mUsername;

    public int getScore() {
        return this.mScore;
    }

    public void setScore(int i) {
        this.mScore = i;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("fullname".equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.AVATAR_URL.equals(currentName)) {
                    this.mAvatarUrl = BaseModel.parseStringURL(jsonParser);
                } else if ("score".equals(currentName)) {
                    this.mScore = jsonParser.getValueAsInt();
                } else if ("username".equals(currentName)) {
                    this.mUsername = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
