package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ReviewResponse extends BaseModel {
    private static final long serialVersionUID = 5105434964070945926L;
    protected Date mCreateDate;
    protected String mResponseMessage;

    public ReviewResponse() {
        this.mResponseMessage = StringUtils.EMPTY;
    }

    public String getMessage() {
        return this.mResponseMessage;
    }

    public Date getCreateDate() {
        return this.mCreateDate;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE.equals(currentName)) {
                    currentName = BaseModel.parseString(jsonParser);
                    if (currentName != null) {
                        this.mResponseMessage = currentName.trim();
                    }
                } else if (ResponseConstants.CREATE_DATE.equals(currentName)) {
                    this.mCreateDate = BaseModel.parseIntoDate(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
