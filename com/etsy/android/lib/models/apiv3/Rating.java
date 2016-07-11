package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class Rating extends BaseModel {
    protected int mCount;
    protected double mRating;
    protected int mSellerFeedbackCount;
    protected double mStars;

    public int getRatingCount() {
        return this.mCount;
    }

    public double getRating() {
        return this.mRating;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.SELLER_FEEDBACK_COUNT.equals(currentName)) {
                    this.mSellerFeedbackCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.RATING.equals(currentName)) {
                    this.mRating = jsonParser.getValueAsDouble();
                } else if (ResponseConstants.COUNT.equals(currentName)) {
                    this.mCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.STARS.equals(currentName)) {
                    this.mStars = jsonParser.getValueAsDouble();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
