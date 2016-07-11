package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class WhenMade extends BaseModel {
    public static final String MADE_TO_ORDER_VAL = "made_to_order";
    private String mFormatted;
    private boolean mIsVintage;
    private String mName;

    public WhenMade() {
        this.mName = StringUtils.EMPTY;
        this.mFormatted = StringUtils.EMPTY;
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
                } else if (ResponseConstants.IS_VINTAGE.equals(currentName)) {
                    this.mIsVintage = jsonParser.getValueAsBoolean();
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

    public boolean isVintage() {
        return this.mIsVintage;
    }
}
