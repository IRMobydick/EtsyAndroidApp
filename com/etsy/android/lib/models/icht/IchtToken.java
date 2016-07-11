package com.etsy.android.lib.models.icht;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class IchtToken extends BaseModel {
    private static final long serialVersionUID = 1383807715471833082L;
    private int mErrorCode;
    private String mStatus;
    private String mToken;

    public String getToken() {
        return this.mToken;
    }

    public boolean wasSuccess() {
        return BaseMessage.TYPE_SUCCESS.equals(this.mStatus);
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ActivityFeedEntity.DATA.equals(currentName)) {
                    this.mToken = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.STATUS.equals(currentName)) {
                    this.mStatus = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.RESPONSE.equals(currentName)) {
                    this.mErrorCode = jsonParser.getValueAsInt();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
