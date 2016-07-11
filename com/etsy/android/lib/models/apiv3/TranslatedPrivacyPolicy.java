package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class TranslatedPrivacyPolicy extends BaseModel {
    private String mTranslatedOtherPolicy;

    public TranslatedPrivacyPolicy() {
        this.mTranslatedOtherPolicy = StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.PRIVACY_OTHER_TEXT.equals(currentName)) {
                    this.mTranslatedOtherPolicy = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public String getTranslatedOtherPolicy() {
        return this.mTranslatedOtherPolicy;
    }
}
