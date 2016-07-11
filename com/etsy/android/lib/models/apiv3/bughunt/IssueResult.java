package com.etsy.android.lib.models.apiv3.bughunt;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Date;

public class IssueResult extends BaseModel {
    private Date mCreateDate;
    private String mEtsyVersion;
    private String mJiraKey;
    private String mMessage;
    private long mPlatform;
    private String mPlatformVersion;
    private String mState;
    private long mTaskId;
    private long mUserId;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.USER_ID.equals(currentName)) {
                    this.mUserId = jsonParser.getValueAsLong();
                } else if (ResponseConstants.TASK_ID.equals(currentName)) {
                    this.mTaskId = jsonParser.getValueAsLong();
                } else if (ResponseConstants.STATE.equals(currentName)) {
                    this.mState = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE.equals(currentName)) {
                    this.mMessage = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.PLATFORM.equals(currentName)) {
                    this.mPlatform = jsonParser.getValueAsLong();
                } else if (ResponseConstants.ETSY_VERSION.equals(currentName)) {
                    this.mEtsyVersion = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.PLATFORM_VERSION.equals(currentName)) {
                    this.mPlatformVersion = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.JIRA_KEY.equals(currentName)) {
                    this.mJiraKey = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.CREATE_DATE.equals(currentName)) {
                    this.mCreateDate = BaseModel.parseIntoDate(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
