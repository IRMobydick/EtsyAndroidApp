package com.etsy.android.lib.models.apiv3;

import android.support.annotation.Nullable;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Region;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.foresee.mobileReplay.perfLog.PerfDbContentProvider;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.ObjectUtils;
import org.parceler.Parcel;

@Parcel
public class StructuredShopShippingEstimate extends BaseModel {
    public static final String TYPE_COUNTRY = "country";
    public static final String TYPE_REGION = "region";
    public static final String UNIT_DAYS = "days";
    public static final String UNIT_WEEKS = "weeks";
    private static final long serialVersionUID = 704751790733306931L;
    protected Integer mCountryId;
    protected String mDisplayName;
    protected int mMax;
    protected int mMin;
    protected String mRegionCode;
    protected String mType;
    protected String mUnit;

    public StructuredShopShippingEstimate(StructuredShopShippingEstimate structuredShopShippingEstimate) {
        this.mMin = structuredShopShippingEstimate.getMin();
        this.mMax = structuredShopShippingEstimate.getMax();
        this.mDisplayName = structuredShopShippingEstimate.getDisplayName();
        this.mRegionCode = structuredShopShippingEstimate.getRegionCode();
        this.mCountryId = structuredShopShippingEstimate.getCountryId();
        this.mUnit = structuredShopShippingEstimate.getUnit();
        this.mType = structuredShopShippingEstimate.getType();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1566082984:
                        if (currentName.equals(ResponseConstants.REGION_CODE)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -1017451932:
                        if (currentName.equals(ResponseConstants.COUNTRY_ID)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 107876:
                        if (currentName.equals(PerfDbContentProvider.COL_MAX)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 108114:
                        if (currentName.equals(PerfDbContentProvider.COL_MIN)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 3575610:
                        if (currentName.equals(FindsModule.FIELD_TYPE)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case 3594628:
                        if (currentName.equals(ResponseConstants.UNIT)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 1615086568:
                        if (currentName.equals(ActivityFeedSubject.DISPLAY_NAME)) {
                            obj = 2;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mMin = jsonParser.getIntValue();
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mMax = jsonParser.getIntValue();
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mDisplayName = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        if (this.mCountryId != null) {
                            break;
                        }
                        this.mRegionCode = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mUnit = BaseModel.parseString(jsonParser);
                        if (!(UNIT_DAYS.equals(this.mUnit) || UNIT_WEEKS.equals(this.mUnit))) {
                            this.mUnit = null;
                            break;
                        }
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mCountryId = Integer.valueOf(jsonParser.getValueAsInt());
                        this.mRegionCode = null;
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mType = BaseModel.parseString(jsonParser);
                        if (!(TYPE_COUNTRY.equals(this.mType) || TYPE_REGION.equals(this.mType))) {
                            this.mType = null;
                            break;
                        }
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public int getMin() {
        return this.mMin;
    }

    public void setMin(int i) {
        this.mMin = i;
    }

    public int getMax() {
        return this.mMax;
    }

    public void setMax(int i) {
        this.mMax = i;
    }

    @Nullable
    @JsonIgnore
    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(@Nullable String str) {
        this.mDisplayName = str;
    }

    @Nullable
    public String getRegionCode() {
        return this.mRegionCode;
    }

    public void setRegionCode(String str) {
        this.mRegionCode = str;
        this.mCountryId = null;
    }

    @Nullable
    public String getUnit() {
        return this.mUnit;
    }

    public void setUnit(@Nullable String str) {
        this.mUnit = str;
    }

    @Nullable
    public Integer getCountryId() {
        return this.mCountryId;
    }

    public void setCountryId(int i) {
        this.mCountryId = Integer.valueOf(i);
        this.mRegionCode = null;
    }

    @Nullable
    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    @JsonIgnore
    public boolean isSet() {
        return this.mMin > 0 && this.mMax > 0 && this.mUnit != null && this.mType != null && bh.m3343b(this.mDisplayName);
    }

    @Nullable
    @JsonIgnore
    public Region toRegion() {
        if (TYPE_REGION.equals(this.mType)) {
            return new Region(this.mDisplayName, this.mRegionCode);
        }
        return null;
    }

    @Nullable
    @JsonIgnore
    public Country toCountry() {
        if (TYPE_COUNTRY.equals(this.mType)) {
            return new Country(this.mCountryId.intValue());
        }
        return null;
    }

    public boolean equals(Object obj) {
        return (obj instanceof StructuredShopShippingEstimate) && this.mMin == ((StructuredShopShippingEstimate) obj).getMin() && this.mMax == ((StructuredShopShippingEstimate) obj).getMax() && ObjectUtils.equals(this.mDisplayName, ((StructuredShopShippingEstimate) obj).getDisplayName()) && ObjectUtils.equals(this.mUnit, ((StructuredShopShippingEstimate) obj).getUnit()) && ObjectUtils.equals(this.mType, ((StructuredShopShippingEstimate) obj).getType()) && ((TYPE_REGION.equals(this.mType) && ObjectUtils.equals(this.mRegionCode, ((StructuredShopShippingEstimate) obj).getRegionCode())) || (TYPE_COUNTRY.equals(this.mType) && ObjectUtils.equals(this.mCountryId, ((StructuredShopShippingEstimate) obj).getCountryId())));
    }

    public boolean equals(Country country) {
        return TYPE_COUNTRY.equals(getType()) && (ObjectUtils.equals(getCountryId(), Integer.valueOf(country.getCountryId())) || ObjectUtils.equals(getDisplayName(), country.getName()));
    }

    public boolean equals(Region region) {
        return TYPE_REGION.equals(getType()) && (ObjectUtils.equals(getRegionCode(), region.getRegionCode()) || ObjectUtils.equals(getDisplayName(), region.getRegionName()));
    }

    public int hashCode() {
        return ((((((((((((this.mMin + 527) * 31) + this.mMax) * 31) + ObjectUtils.hashCode(this.mDisplayName)) * 31) + ObjectUtils.hashCode(this.mRegionCode)) * 31) + ObjectUtils.hashCode(this.mCountryId)) * 31) + ObjectUtils.hashCode(this.mUnit)) * 31) + ObjectUtils.hashCode(this.mType);
    }
}
