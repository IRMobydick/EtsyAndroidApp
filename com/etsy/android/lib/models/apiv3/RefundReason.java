package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class RefundReason extends BaseModel {
    public static final String NAME_OTHER = "OTHER";
    protected String mFormatted;
    protected String mName;

    public RefundReason() {
        this.mName = StringUtils.EMPTY;
        this.mFormatted = StringUtils.EMPTY;
    }

    public RefundReason(String str, String str2) {
        this();
        this.mName = str;
        this.mFormatted = str2;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.FORMATTED.equals(currentName)) {
                    this.mFormatted = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public String getName() {
        return this.mName;
    }

    public String getFormattedValue() {
        return this.mFormatted;
    }

    public String toString() {
        return this.mFormatted;
    }
}
