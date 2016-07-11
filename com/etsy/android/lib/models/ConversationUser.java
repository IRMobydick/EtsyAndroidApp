package com.etsy.android.lib.models;

import com.etsy.android.lib.models.apiv3.ActivityFeedSubject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class ConversationUser extends BaseModel {
    private static final long serialVersionUID = -9052258840892090235L;
    private String mAvatarUrl;
    private String mDisplayName;
    private long mUserId;
    private String mUserName;

    public ConversationUser() {
        this.mDisplayName = StringUtils.EMPTY;
        this.mAvatarUrl = StringUtils.EMPTY;
        this.mUserName = StringUtils.EMPTY;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public String getUsername() {
        return this.mUserName;
    }

    public long getUserId() {
        return this.mUserId;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public void setAvatarUrl(String str) {
        this.mAvatarUrl = str;
    }

    public void setUserName(String str) {
        this.mUserName = str;
    }

    public void setUserId(long j) {
        this.mUserId = j;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.AVATAR_URL.equals(currentName)) {
                    this.mAvatarUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ActivityFeedSubject.DISPLAY_NAME.equals(currentName)) {
                    this.mDisplayName = BaseModel.parseString(jsonParser);
                } else if ("username".equals(currentName)) {
                    this.mUserName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.USER_ID.equals(currentName)) {
                    this.mUserId = jsonParser.getValueAsLong();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
