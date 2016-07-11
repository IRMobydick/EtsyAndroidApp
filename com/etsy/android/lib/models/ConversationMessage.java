package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ConversationMessage extends BaseModel {
    private static final long serialVersionUID = -8171593043464603467L;
    private EtsyId mConversationId;
    private long mCreationDate;
    private boolean mHasImages;
    private List<ConversationImage> mImages;
    private String mLanguage;
    private String mMessage;
    private int mMessageOrder;
    private String mTranslatedMessage;
    private EtsyId mUserId;

    public ConversationMessage() {
        this.mMessage = StringUtils.EMPTY;
        this.mLanguage = StringUtils.EMPTY;
        this.mTranslatedMessage = StringUtils.EMPTY;
        this.mUserId = new EtsyId();
        this.mConversationId = new EtsyId();
        this.mImages = new ArrayList(0);
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public EtsyId getConversationId() {
        return this.mConversationId;
    }

    public int getMessageOrder() {
        return this.mMessageOrder;
    }

    public boolean getHasImages() {
        return this.mHasImages;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getTranslatedMessage() {
        return this.mTranslatedMessage;
    }

    public void setTranslatedMessage(String str) {
        this.mTranslatedMessage = str;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public long getCreationDate() {
        return this.mCreationDate;
    }

    public List<ConversationImage> getImages() {
        return this.mImages;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.USER_ID.equals(currentName)) {
                    this.mUserId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.CONVERSATION_ID.equals(currentName)) {
                    this.mConversationId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.MESSAGE_ORDER.equals(currentName)) {
                    this.mMessageOrder = jsonParser.getValueAsInt();
                } else if ("has_images".equals(currentName)) {
                    this.mHasImages = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.CREATION_TSZ.equals(currentName)) {
                    this.mCreationDate = jsonParser.getValueAsLong() * 1000;
                } else if (ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE.equals(currentName)) {
                    this.mMessage = BaseModel.parseString(jsonParser);
                } else if (EtsyRequest.PARAM_LANGUAGE.equals(currentName)) {
                    this.mLanguage = BaseModel.parseString(jsonParser).trim();
                } else if (Includes.IMAGES.equals(currentName)) {
                    this.mImages = BaseModel.parseArray(jsonParser, ConversationImage.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.TARGET_USER_ID, this.mUserId);
        return hashMap;
    }
}
