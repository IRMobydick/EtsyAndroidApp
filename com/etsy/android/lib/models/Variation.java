package com.etsy.android.lib.models;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Variation extends BaseModel {
    protected static final long serialVersionUID = 8582916799134364809L;
    protected String mFormattedName;
    protected String mFormattedValue;
    protected boolean mIsValid;
    protected List<Option> mOptions;
    protected long mPropertyId;
    protected Option mSelectedOption;
    protected long mValueId;

    public Variation() {
        this.mFormattedName = StringUtils.EMPTY;
        this.mFormattedValue = StringUtils.EMPTY;
        this.mSelectedOption = null;
        this.mOptions = new ArrayList(0);
    }

    public long getPropertyId() {
        return this.mPropertyId;
    }

    public String getFormattedName() {
        return this.mFormattedName;
    }

    public String getFormattedValue() {
        return this.mFormattedValue;
    }

    public long getValueId() {
        return this.mValueId;
    }

    public boolean isValid() {
        return this.mIsValid;
    }

    public boolean hasOptionSet() {
        return this.mSelectedOption != null || (!TextUtils.isEmpty(this.mFormattedValue) && this.mValueId > 0);
    }

    public Option getSelectedOption() {
        return this.mSelectedOption;
    }

    public void setOption(Option option) {
        this.mSelectedOption = option;
        this.mFormattedValue = option.getFormattedValue();
        this.mValueId = option.getValueId();
    }

    public void setOption(String str, long j) {
        this.mFormattedValue = str;
        this.mValueId = j;
        this.mSelectedOption = null;
    }

    public List<Option> getOptions() {
        return this.mOptions;
    }

    public boolean hasPriceDiff() {
        for (Option hasPriceDiff : this.mOptions) {
            if (hasPriceDiff.hasPriceDiff()) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return String.format("%s variation has %d options (id: %d)", new Object[]{this.mFormattedName, Integer.valueOf(this.mOptions.size()), Long.valueOf(this.mPropertyId)});
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("value_id".equals(currentName)) {
                    this.mValueId = jsonParser.getValueAsLong();
                } else if ("property_id".equals(currentName)) {
                    this.mPropertyId = jsonParser.getValueAsLong();
                } else if ("formatted_name".equals(currentName)) {
                    this.mFormattedName = BaseModel.parseString(jsonParser);
                } else if ("formatted_value".equals(currentName)) {
                    this.mFormattedValue = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.OPTIONS.equals(currentName)) {
                    this.mOptions = BaseModel.parseArray(jsonParser, Option.class);
                } else if ("is_valid".equals(currentName)) {
                    this.mIsValid = jsonParser.getValueAsBoolean();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.VARIATION_PROPERTY_ID, Long.valueOf(this.mPropertyId));
        return hashMap;
    }
}
