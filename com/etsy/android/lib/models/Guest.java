package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class Guest extends BaseModel {
    private static final long serialVersionUID = 6750819900415913308L;
    private String mCheckoutUrl;
    private String mGuestId;
    private int mNumListingsMerged;

    public Guest() {
        this.mGuestId = StringUtils.EMPTY;
        this.mNumListingsMerged = 0;
    }

    public String getGuestId() {
        return this.mGuestId;
    }

    public String getCheckoutUrl() {
        return this.mCheckoutUrl;
    }

    public int getNumListingsMerged() {
        return this.mNumListingsMerged;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("guest_id".equals(currentName)) {
                    this.mGuestId = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else if ("checkout_url".equals(currentName)) {
                    this.mCheckoutUrl = BaseModel.parseStringURL(jsonParser);
                } else if ("listings_merged".equals(currentName)) {
                    this.mNumListingsMerged = jsonParser.getValueAsInt();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
