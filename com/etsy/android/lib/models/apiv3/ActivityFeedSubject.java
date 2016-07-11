package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ActivityFeedSubject extends BaseModel {
    public static final String DISPLAY_NAME = "display_name";
    public static final String IS_CURRENT_USER = "is_current_user";
    public static final String IS_PERSONALIZED = "is_personalized";
    private static final long serialVersionUID = -6514866103756356729L;
    private String mDisplayName;
    private String mImageUrl;
    private boolean mIsCurrentUser;
    private boolean mIsPersonalized;

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public boolean isPersonalized() {
        return this.mIsPersonalized;
    }

    public boolean isCurrentUser() {
        return this.mIsCurrentUser;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (DISPLAY_NAME.equals(currentName)) {
                    this.mDisplayName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.IMAGE_URL.equals(currentName)) {
                    this.mImageUrl = BaseModel.parseStringURL(jsonParser);
                } else if (IS_CURRENT_USER.equals(currentName)) {
                    this.mIsCurrentUser = jsonParser.getValueAsBoolean();
                } else if (IS_PERSONALIZED.equals(currentName)) {
                    this.mIsPersonalized = jsonParser.getValueAsBoolean();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
