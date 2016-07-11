package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class ShippingTimes extends BaseModel {
    private static final long serialVersionUID = -5002800549794387842L;
    private String mLabel;
    private int mMaxProcessingDays;
    private int mMinProcessingDays;

    public ShippingTimes() {
        this.mLabel = StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("min_processing_days".equals(currentName)) {
                    this.mMinProcessingDays = jsonParser.getValueAsInt();
                } else if ("max_processing_days".equals(currentName)) {
                    this.mMaxProcessingDays = jsonParser.getValueAsInt();
                } else if (ResponseConstants.LABEL.equals(currentName)) {
                    this.mLabel = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public int getMaxProcessingDays() {
        return this.mMaxProcessingDays;
    }

    public int getMinProcessingDays() {
        return this.mMinProcessingDays;
    }

    public void setMinProcessingDays(int i) {
        this.mMinProcessingDays = i;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public String toString() {
        return this.mLabel;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ShippingTimes)) {
            return false;
        }
        boolean z = ((ShippingTimes) obj).getMaxProcessingDays() == getMaxProcessingDays() && ((ShippingTimes) obj).getMinProcessingDays() == getMinProcessingDays() && getLabel().equalsIgnoreCase(((ShippingTimes) obj).getLabel());
        return z;
    }

    public int hashCode() {
        return (this.mLabel.length() + getMaxProcessingDays()) + getMinProcessingDays();
    }
}
