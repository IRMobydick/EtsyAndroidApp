package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class TrackingEvent extends BaseModel {
    private static final long serialVersionUID = 7298364778845540194L;
    private String mDate;
    private String mEvent;
    private String mLocation;

    public TrackingEvent() {
        this.mEvent = StringUtils.EMPTY;
        this.mLocation = StringUtils.EMPTY;
        this.mDate = StringUtils.EMPTY;
    }

    public String getEvent() {
        return this.mEvent;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public String getDateString() {
        return this.mDate;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("date".equals(currentName)) {
                    this.mDate = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if (NotificationCompatApi21.CATEGORY_EVENT.equals(currentName)) {
                    this.mEvent = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.LOCATION.equals(currentName)) {
                    this.mLocation = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
