package com.etsy.android.lib.models;

import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopTask extends BaseModel {
    protected static final String NAME_BILL_OVERDUE = "bill_overdue";
    protected static final String NAME_PAYMENT_DENIED = "payment_denied";
    protected static final long serialVersionUID = 5450494864709068115L;
    protected String mName;
    protected boolean mNeedsAttention;
    protected int mPriority;
    protected String mSubTitleComplete;
    protected String mSubTitleIncomplete;
    protected EtsyId mTaskId;
    protected String mTitleComplete;
    protected String mTitleIncomplete;

    public ShopTask() {
        this.mName = StringUtils.EMPTY;
        this.mTitleIncomplete = StringUtils.EMPTY;
        this.mSubTitleIncomplete = StringUtils.EMPTY;
        this.mTitleComplete = StringUtils.EMPTY;
        this.mSubTitleComplete = StringUtils.EMPTY;
        this.mTaskId = new EtsyId();
    }

    public String getTaskDescription() {
        return this.mTitleIncomplete;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.TASK_ID.equals(currentName)) {
                    this.mTaskId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if ("title_incomplete".equals(currentName)) {
                    this.mTitleIncomplete = BaseModel.parseString(jsonParser);
                } else if ("subtitle_incomplete".equals(currentName)) {
                    this.mSubTitleIncomplete = BaseModel.parseString(jsonParser);
                } else if ("title_complete".equals(currentName)) {
                    this.mTitleComplete = BaseModel.parseString(jsonParser);
                } else if ("subtitle_complete".equals(currentName)) {
                    this.mSubTitleComplete = BaseModel.parseString(jsonParser);
                } else if ("needs_attention".equals(currentName)) {
                    this.mNeedsAttention = jsonParser.getBooleanValue();
                } else if ("priority".equals(currentName)) {
                    this.mPriority = jsonParser.getIntValue();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
