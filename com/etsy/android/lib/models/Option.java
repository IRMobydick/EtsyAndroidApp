package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Option extends BaseModel {
    protected static final long serialVersionUID = -6056371838306858721L;
    protected EtsyMoney mConvertedPrice;
    protected String mFormattedValue;
    protected boolean mIsAvailable;
    protected EtsyMoney mPrice;
    protected EtsyMoney mPriceDiff;
    protected long mValueId;
    protected long mVariationPropertyId;

    public Option() {
        this.mFormattedValue = StringUtils.EMPTY;
        this.mPriceDiff = new EtsyMoney();
        this.mPrice = new EtsyMoney();
        this.mConvertedPrice = new EtsyMoney();
    }

    public long getValueId() {
        return this.mValueId;
    }

    public boolean isAvailable() {
        return this.mIsAvailable;
    }

    public boolean hasPriceDiff() {
        return this.mPriceDiff.getAmount().compareTo(new BigDecimal(0)) == 1;
    }

    public EtsyMoney getPrice() {
        return this.mPrice;
    }

    public EtsyMoney getConvertedPrice() {
        return this.mConvertedPrice;
    }

    public String getFormattedValue() {
        return this.mFormattedValue;
    }

    public void setVariationPropertyId(long j) {
        this.mVariationPropertyId = j;
    }

    public long getVariationPropertyId() {
        return this.mVariationPropertyId;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("value_id".equals(currentName)) {
                    this.mValueId = jsonParser.getValueAsLong();
                } else if ("formatted_value".equals(currentName)) {
                    this.mFormattedValue = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.IS_AVAILABLE.equals(currentName)) {
                    this.mIsAvailable = jsonParser.getValueAsBoolean(false);
                } else if (ResponseConstants.PRICE.equals(currentName)) {
                    this.mPrice = this.mPrice.withAmount(BaseModel.parseString(jsonParser));
                } else if ("price_diff".equals(currentName)) {
                    this.mPriceDiff = this.mPriceDiff.withAmount(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.CONVERTED_PRICE.equals(currentName)) {
                    this.mConvertedPrice = this.mConvertedPrice.withAmount(BaseModel.parseString(jsonParser));
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.OPTION_VALUE_ID, Long.valueOf(this.mValueId));
        return hashMap;
    }
}
