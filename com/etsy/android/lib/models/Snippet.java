package com.etsy.android.lib.models;

import android.database.Cursor;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Snippet extends BaseModel {
    protected String mContent;
    protected EtsyId mId;
    protected String mTitle;

    public Snippet() {
        this.mId = new EtsyId();
        this.mTitle = StringUtils.EMPTY;
        this.mContent = StringUtils.EMPTY;
    }

    public Snippet(Cursor cursor) {
        this.mId = new EtsyId();
        this.mTitle = StringUtils.EMPTY;
        this.mContent = StringUtils.EMPTY;
        if (cursor != null) {
            this.mId.setId(cursor.getString(1));
            this.mTitle = cursor.getString(2);
            this.mContent = cursor.getString(3);
        }
    }

    public EtsyId getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId.setId(str);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getContent() {
        return this.mContent;
    }

    public void setContent(String str) {
        this.mContent = str;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("convos_canned_response_id".equals(currentName)) {
                    this.mId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser).trim();
                } else if ("content".equals(currentName)) {
                    this.mContent = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE.equals(currentName)) {
                    this.mContent = BaseModel.parseString(jsonParser).trim();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.SNIPPET_ID, this.mId.getId());
        return hashMap;
    }
}
