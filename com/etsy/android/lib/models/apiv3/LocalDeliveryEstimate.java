package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class LocalDeliveryEstimate extends BaseModel {
    protected Long mEstimatedDeliveryDate;
    protected String mFormattedDate;
    protected String mFormattedTime;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.ESTIMATED_DELIVERY_DATE.equals(currentName)) {
                    this.mEstimatedDeliveryDate = Long.valueOf(jsonParser.getLongValue());
                } else if (ResponseConstants.FORMATTED_DATE.equals(currentName)) {
                    this.mFormattedDate = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.FORMATTED_TIME.equals(currentName)) {
                    this.mFormattedTime = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public Long getEstimatedDeliveryDate() {
        return this.mEstimatedDeliveryDate;
    }

    public String getFormattedDate() {
        return this.mFormattedDate;
    }

    public String getFormattedTime() {
        return this.mFormattedTime;
    }
}
