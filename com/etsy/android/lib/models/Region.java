package com.etsy.android.lib.models;

import android.support.annotation.NonNull;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class Region extends BaseModel implements Comparable<Region> {
    private static final long serialVersionUID = -5304078965172970685L;
    private boolean mIsDead;
    private String mRegionCode;
    private int mRegionId;
    private String mRegionName;

    public Region() {
        this.mRegionName = StringUtils.EMPTY;
        this.mRegionCode = StringUtils.EMPTY;
    }

    public Region(String str, String str2) {
        this.mRegionName = StringUtils.EMPTY;
        this.mRegionCode = StringUtils.EMPTY;
        this.mRegionName = str;
        this.mRegionCode = str2;
    }

    public int getRegionId() {
        return this.mRegionId;
    }

    public String getRegionName() {
        return this.mRegionName;
    }

    @NonNull
    public String getRegionCode() {
        return bh.m3343b(this.mRegionCode) ? this.mRegionCode : String.valueOf(this.mRegionId);
    }

    public boolean getIsDead() {
        return this.mIsDead;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("region_id".equals(currentName)) {
                    this.mRegionId = jsonParser.getValueAsInt();
                } else if ("region_name".equals(currentName)) {
                    this.mRegionName = BaseModel.parseString(jsonParser);
                } else if ("is_dead".equals(currentName)) {
                    this.mIsDead = jsonParser.getValueAsBoolean();
                }
            }
        }
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Region) && ObjectUtils.equals(getRegionCode(), ((Region) obj).getRegionCode());
    }

    public int hashCode() {
        return ((((this.mRegionId + 527) * 31) + ObjectUtils.hashCode(getRegionCode())) * 31) + ObjectUtils.hashCode(this.mRegionName);
    }

    public int compareTo(Region region) {
        if (region == null) {
            return -1;
        }
        return bh.m3330a(this.mRegionName, region.getRegionName());
    }

    public String toString() {
        return this.mRegionName;
    }
}
