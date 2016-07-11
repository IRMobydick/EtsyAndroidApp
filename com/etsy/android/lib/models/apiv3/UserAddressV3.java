package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class UserAddressV3 extends BaseModel {
    protected String mCity;
    protected EtsyId mCountryId;
    protected String mCountryName;
    protected String mFirstLine;
    protected boolean mIsAvailableForMarket;
    protected String mName;
    protected String mPhone;
    protected String mSecondLine;
    protected String mState;
    protected EtsyId mUserAddressId;
    protected EtsyId mUserId;
    protected String mZip;

    public UserAddressV3() {
        this.mName = StringUtils.EMPTY;
        this.mFirstLine = StringUtils.EMPTY;
        this.mSecondLine = StringUtils.EMPTY;
        this.mCity = StringUtils.EMPTY;
        this.mState = StringUtils.EMPTY;
        this.mZip = StringUtils.EMPTY;
        this.mPhone = StringUtils.EMPTY;
        this.mCountryName = StringUtils.EMPTY;
        this.mUserId = new EtsyId();
        this.mUserAddressId = new EtsyId();
        this.mCountryId = new EtsyId();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.CITY.equals(currentName)) {
                    this.mCity = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.STATE.equals(currentName)) {
                    this.mState = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.FIRST_LINE.equals(currentName)) {
                    this.mFirstLine = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SECOND_LINE.equals(currentName)) {
                    this.mSecondLine = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.ZIP.equals(currentName)) {
                    this.mZip = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.POSTAL_CODE.equals(currentName)) {
                    this.mZip = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.PHONE.equals(currentName)) {
                    this.mPhone = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.COUNTRY_NAME.equals(currentName)) {
                    this.mCountryName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.COUNTRY_ID.equals(currentName)) {
                    this.mCountryId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.USER_ID.equals(currentName)) {
                    this.mUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.USER_ADDRESS_ID.equals(currentName)) {
                    this.mUserAddressId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.IS_AVAILABLE_FOR_MARKET.equals(currentName)) {
                    this.mIsAvailableForMarket = jsonParser.getBooleanValue();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public EtsyId getUserAddressId() {
        return this.mUserAddressId;
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

    public String getPhone() {
        return this.mPhone;
    }

    public EtsyId getCountryId() {
        return this.mCountryId;
    }

    public String getCountryName() {
        return this.mCountryName;
    }

    public boolean isAvailableForMarket() {
        return this.mIsAvailableForMarket;
    }

    public HashMap<String, Object> getApiData() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put(ResponseConstants.USER_ID, getUserId().getId());
        hashMap.put(ResponseConstants.USER_ADDRESS_ID, getUserAddressId().getId());
        hashMap.put(ResponseConstants.NAME, getName());
        hashMap.put(ResponseConstants.FIRST_LINE, getFirstLine());
        hashMap.put(ResponseConstants.SECOND_LINE, getSecondLine());
        hashMap.put(ResponseConstants.CITY, getCity());
        hashMap.put(ResponseConstants.STATE, getState());
        hashMap.put(ResponseConstants.ZIP, getZip());
        hashMap.put(ResponseConstants.COUNTRY_ID, getCountryId().getId());
        hashMap.put(ResponseConstants.COUNTRY_NAME, getCountryName());
        hashMap.put(ResponseConstants.PHONE, getPhone());
        return hashMap;
    }
}
