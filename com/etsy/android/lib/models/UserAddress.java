package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class UserAddress extends BaseModel {
    protected static final String TAG;
    protected static final long serialVersionUID = 6834864859786949576L;
    protected String mCity;
    protected Country mCountry;
    protected EtsyId mCountryId;
    protected String mFirstLine;
    protected boolean mIsDefaultShipping;
    protected String mName;
    protected String mSecondLine;
    protected String mState;
    protected EtsyId mUserAddressId;
    protected EtsyId mUserId;
    protected String mZip;

    static {
        TAG = EtsyDebug.m1891a(UserAddress.class);
    }

    public UserAddress() {
        this.mName = StringUtils.EMPTY;
        this.mFirstLine = StringUtils.EMPTY;
        this.mSecondLine = StringUtils.EMPTY;
        this.mCity = StringUtils.EMPTY;
        this.mState = StringUtils.EMPTY;
        this.mZip = StringUtils.EMPTY;
        this.mUserAddressId = new EtsyId();
        this.mUserId = new EtsyId();
        this.mCountryId = new EtsyId();
    }

    public EtsyId getUserAddressId() {
        return this.mUserAddressId;
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public String getName() {
        return this.mName;
    }

    public String getFirstLine() {
        return this.mFirstLine;
    }

    public String getSecondLine() {
        return this.mSecondLine;
    }

    public String getCity() {
        return this.mCity;
    }

    public String getState() {
        return this.mState;
    }

    public String getZip() {
        return this.mZip;
    }

    public EtsyId getCountryId() {
        return this.mCountryId;
    }

    public boolean isDefaultShipping() {
        return this.mIsDefaultShipping;
    }

    @Nullable
    public Country getCountry() {
        return this.mCountry;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                parseAddressField(jsonParser, currentName);
            }
        }
    }

    protected void parseAddressField(JsonParser jsonParser, String str) {
        if (ResponseConstants.USER_ADDRESS_ID.equals(str)) {
            this.mUserAddressId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (ResponseConstants.USER_ID.equals(str)) {
            this.mUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (ResponseConstants.NAME.equals(str)) {
            this.mName = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.FIRST_LINE.equals(str)) {
            this.mFirstLine = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.SECOND_LINE.equals(str)) {
            this.mSecondLine = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.CITY.equals(str)) {
            this.mCity = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.STATE.equals(str)) {
            this.mState = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.ZIP.equals(str)) {
            this.mZip = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.COUNTRY_ID.equals(str)) {
            this.mCountryId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
        } else if (ResponseConstants.IS_DEFAULT_SHIPPING.equals(str)) {
            this.mIsDefaultShipping = jsonParser.getValueAsBoolean();
        } else if (Includes.COUNTRY.equals(str)) {
            this.mCountry = (Country) BaseModel.parseObject(jsonParser, Country.class);
        } else {
            EtsyDebug.m1908b(TAG, "Field %s not found on UserAddress Model", str);
            jsonParser.skipChildren();
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(2);
        hashMap.put(AnalyticsLogAttribute.USER_ADDRESS_ID, this.mUserAddressId.getId());
        hashMap.put(AnalyticsLogAttribute.TARGET_USER_ID, this.mUserId.getId());
        return hashMap;
    }
}
