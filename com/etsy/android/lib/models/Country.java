package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Country extends BaseModel implements Comparable<Country> {
    public static final Set<String> PRIMARY_ISO_CODES;
    private static final String TAG;
    private static final long serialVersionUID = -2753968618245666341L;
    protected int mCountryId;
    protected boolean mIsPrimary;
    protected String mIsoCountryCode;
    protected float mLatitude;
    protected float mLongitude;
    protected String mName;
    protected String mWorldBankCountryCode;

    static {
        TAG = EtsyDebug.m1891a(Country.class);
        PRIMARY_ISO_CODES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"US", "CA", "AU", "GB", "DE", "FR", "NL", "IT", "ES", "RU", "PT", "GR", "IE", "NZ", "JP"})));
    }

    public Country() {
        this.mName = StringUtils.EMPTY;
        this.mIsoCountryCode = StringUtils.EMPTY;
        this.mIsPrimary = false;
        this.mWorldBankCountryCode = StringUtils.EMPTY;
    }

    public Country(int i) {
        this.mName = StringUtils.EMPTY;
        this.mIsoCountryCode = StringUtils.EMPTY;
        this.mIsPrimary = false;
        this.mWorldBankCountryCode = StringUtils.EMPTY;
        this.mCountryId = i;
    }

    public int getCountryId() {
        return this.mCountryId;
    }

    public String getName() {
        return this.mName;
    }

    public String getIsoCountryCode() {
        return this.mIsoCountryCode;
    }

    public boolean isPrimary() {
        return this.mIsPrimary;
    }

    public String getWorldBankCode() {
        return this.mWorldBankCountryCode;
    }

    public float getLongitude() {
        return this.mLongitude;
    }

    public float getLatitude() {
        return this.mLatitude;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.COUNTRY_ID.equals(currentName)) {
                    this.mCountryId = jsonParser.getValueAsInt();
                } else if (ResponseConstants.ISO_COUNTRY_CODE.equals(currentName)) {
                    this.mIsoCountryCode = BaseModel.parseString(jsonParser);
                    if (PRIMARY_ISO_CODES.contains(this.mIsoCountryCode)) {
                        this.mIsPrimary = true;
                    }
                } else if (ResponseConstants.WORLD_BANK_COUNTRY_CODE.equals(currentName)) {
                    this.mWorldBankCountryCode = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.LAT.equals(currentName)) {
                    try {
                        this.mLatitude = jsonParser.getFloatValue();
                    } catch (Throwable e) {
                        EtsyDebug.m1897a(TAG, "Error parsing latitude", e);
                    }
                } else if (ResponseConstants.LON.equals(currentName)) {
                    try {
                        this.mLongitude = jsonParser.getFloatValue();
                    } catch (Throwable e2) {
                        EtsyDebug.m1897a(TAG, "Error parsing longitude", e2);
                    }
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public boolean isUs() {
        return Locale.US.getISO3Country().equals(getWorldBankCode());
    }

    public int compareTo(Country country) {
        if (this.mIsPrimary) {
            if (country.isPrimary()) {
                return bh.m3330a(this.mName, country.getName());
            }
            return -1;
        } else if (country.isPrimary()) {
            return 1;
        } else {
            return bh.m3330a(this.mName, country.getName());
        }
    }

    public String toString() {
        return this.mName;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Country) && this.mCountryId == ((Country) obj).getCountryId();
    }

    public int hashCode() {
        return this.mCountryId;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.COUNTRY_ID, Integer.valueOf(this.mCountryId));
        return hashMap;
    }
}
