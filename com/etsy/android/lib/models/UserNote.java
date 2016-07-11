package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class UserNote extends BaseModel {
    public static final String SUBJECT_TYPE_RECEIPT = "receipt";
    private static final long serialVersionUID = 2678113532200873467L;
    protected String mNote;
    protected String mSubjectType;
    protected EtsyId mUserNoteId;

    public UserNote() {
        this.mSubjectType = StringUtils.EMPTY;
        this.mNote = StringUtils.EMPTY;
        this.mUserNoteId = new EtsyId();
    }

    public String getNote() {
        return this.mNote;
    }

    public EtsyId getNoteId() {
        return this.mUserNoteId;
    }

    public void setNote(String str) {
        this.mNote = str;
    }

    public boolean isPrivateReceiptNote() {
        return SUBJECT_TYPE_RECEIPT.equalsIgnoreCase(this.mSubjectType);
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("user_note_id".equals(currentName)) {
                    this.mUserNoteId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if ("subject_type".equals(currentName)) {
                    this.mSubjectType = BaseModel.parseString(jsonParser);
                } else if ("note".equals(currentName)) {
                    this.mNote = BaseModel.parseString(jsonParser).trim();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.USER_NOTE_ID, this.mUserNoteId.getId());
        return hashMap;
    }
}
