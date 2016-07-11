package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class TranslatedFAQ extends BaseModel {
    protected String mAnswer;
    protected String mQuestion;

    public TranslatedFAQ() {
        this.mQuestion = StringUtils.EMPTY;
        this.mAnswer = StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (!(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || ResponseConstants.TRANSLATED_REVIEW.equals(currentName))) {
                jsonParser.skipChildren();
            }
        }
    }

    public String getTranslatedQuestion() {
        return "blah";
    }

    public String getTranslatedAnswer() {
        return "blah";
    }
}
