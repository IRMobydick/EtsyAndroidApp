package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class Conversation extends BaseModel {
    private static final long serialVersionUID = 4754473692230150695L;
    private long mConversationId;
    private boolean mHasAttachments;
    private boolean mIsCustomShop;
    private boolean mIsRead;
    private String mLastMessage;
    private long mLastMessageDate;
    private String mLastMessageMe;
    private long mLastMessageMeDate;
    private String mLastMessageOther;
    private long mLastMessageOtherDate;
    private long mLastUpdateDate;
    private int mMessageCount;
    private ConversationUser mOtherUser;
    private String mTitle;

    public Conversation() {
        this.mTitle = StringUtils.EMPTY;
    }

    public long getConversationId() {
        return this.mConversationId;
    }

    public int getMessageCount() {
        return this.mMessageCount;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setIsRead(Boolean bool) {
        this.mIsRead = bool.booleanValue();
    }

    public boolean isRead() {
        return this.mIsRead;
    }

    public String getLastMessage() {
        return this.mLastMessage;
    }

    public boolean hasAttachments() {
        return this.mHasAttachments;
    }

    public boolean isCustomShop() {
        return this.mIsCustomShop;
    }

    public String getLastMessageMe() {
        return this.mLastMessageMe;
    }

    public String getLastMessageOther() {
        return this.mLastMessageOther;
    }

    public long getLastMessageDate() {
        return this.mLastMessageDate;
    }

    public long getLastMessageMeDate() {
        return this.mLastMessageMeDate;
    }

    public long getLastMessageOtherDate() {
        return this.mLastMessageOtherDate;
    }

    public ConversationUser getOtherUser() {
        return this.mOtherUser;
    }

    public long getLastUpdateDate() {
        return this.mLastUpdateDate;
    }

    public void setConversationId(long j) {
        this.mConversationId = j;
    }

    public void setMessageCount(int i) {
        this.mMessageCount = i;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setRead(boolean z) {
        this.mIsRead = z;
    }

    public void setOtherUser(ConversationUser conversationUser) {
        this.mOtherUser = conversationUser;
    }

    public void setLastUpdateDate(long j) {
        this.mLastUpdateDate = j;
    }

    public void setLastMessage(String str) {
        this.mLastMessage = str;
    }

    public void setLastMessageMe(String str) {
        this.mLastMessageMe = str;
    }

    public void setLastMessageOther(String str) {
        this.mLastMessageOther = str;
    }

    public void setLastMessageDate(long j) {
        this.mLastMessageDate = j;
    }

    public void setLastMessageMeDate(long j) {
        this.mLastMessageMeDate = j;
    }

    public void setLastMessageOtherDate(long j) {
        this.mLastMessageOtherDate = j;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.CONVERSATION_ID.equals(currentName)) {
                    this.mConversationId = jsonParser.getValueAsLong();
                } else if ("message_count".equals(currentName)) {
                    this.mMessageCount = jsonParser.getValueAsInt();
                } else if ("is_read".equals(currentName)) {
                    this.mIsRead = jsonParser.getValueAsBoolean();
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if ("last_message".equals(currentName)) {
                    this.mLastMessage = BaseModel.parseString(jsonParser);
                } else if ("last_me_message_excerpt".equals(currentName)) {
                    this.mLastMessageMe = BaseModel.parseString(jsonParser);
                } else if ("last_other_message_excerpt".equals(currentName)) {
                    this.mLastMessageOther = BaseModel.parseString(jsonParser);
                } else if ("last_message_date".equals(currentName)) {
                    this.mLastMessageDate = jsonParser.getValueAsLong() * 1000;
                } else if ("last_message_me_date".equals(currentName)) {
                    this.mLastMessageMeDate = jsonParser.getValueAsLong() * 1000;
                } else if ("last_message_other_date".equals(currentName)) {
                    this.mLastMessageOtherDate = jsonParser.getValueAsLong() * 1000;
                } else if ("last_updated_tsz".equals(currentName)) {
                    this.mLastUpdateDate = jsonParser.getValueAsLong() * 1000;
                } else if ("has_attachments".equals(currentName)) {
                    this.mHasAttachments = jsonParser.getValueAsBoolean();
                } else if ("other_user".equals(currentName)) {
                    this.mOtherUser = (ConversationUser) BaseModel.parseObject(jsonParser, ConversationUser.class);
                } else if ("is_custom_shop".equals(currentName)) {
                    this.mIsCustomShop = jsonParser.getValueAsBoolean();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.CONVO_ID, Long.valueOf(this.mConversationId));
        return hashMap;
    }
}
