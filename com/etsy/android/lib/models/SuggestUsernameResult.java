package com.etsy.android.lib.models;

import com.etsy.android.lib.convos.Draft;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class SuggestUsernameResult extends BaseModel {
    private List<UsernameSuggestion> mSuggestions;

    public void parseData(JsonParser jsonParser) {
        this.mSuggestions = new ArrayList();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.SUGGESTIONS.equals(currentName)) {
                    this.mSuggestions = BaseModel.parseArray(jsonParser, UsernameSuggestion.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public List<String> getSuggestions() {
        List<String> arrayList = new ArrayList();
        for (UsernameSuggestion suggestion : this.mSuggestions) {
            arrayList.add(suggestion.getSuggestion());
        }
        return arrayList;
    }

    public String getLogString() {
        StringBuilder stringBuilder = new StringBuilder(StringUtils.EMPTY);
        int i = 0;
        for (UsernameSuggestion usernameSuggestion : this.mSuggestions) {
            stringBuilder.append(usernameSuggestion.getStrategy()).append(Draft.IMAGE_DELIMITER).append(usernameSuggestion.getSuggestion());
            if (i != this.mSuggestions.size() - 1) {
                stringBuilder.append(",");
            }
            i++;
        }
        return stringBuilder.toString();
    }
}
