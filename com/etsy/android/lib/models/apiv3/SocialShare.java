package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class SocialShare extends BaseModel {
    private static final long serialVersionUID = -9104453666017192663L;
    private boolean mShouldShowSocialInvitesPrompt;

    public boolean shouldShowSocialInvitesPrompt() {
        return this.mShouldShowSocialInvitesPrompt;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                parseModel(jsonParser, currentName);
            }
        }
    }

    protected void parseModel(JsonParser jsonParser, String str) {
        if (ResponseConstants.SOCIAL_INVITES_FLAG.equals(str)) {
            this.mShouldShowSocialInvitesPrompt = jsonParser.getValueAsBoolean();
        } else {
            jsonParser.skipChildren();
        }
    }
}
