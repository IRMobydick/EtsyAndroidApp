package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class SellerDetails extends BaseModel {
    private static final long serialVersionUID = -5013462844915116567L;
    protected String mAddressLine1;
    protected String mAddressLine2;
    protected String mCity;
    protected String mCountry;
    protected String mEmail;
    protected String mFirstName;
    protected String mFormattedString;
    protected String mLastName;
    protected String mPhone;
    protected String mPostalCode;
    protected String mState;
    protected String mVatNumber;

    public SellerDetails() {
        this.mFirstName = StringUtils.EMPTY;
        this.mLastName = StringUtils.EMPTY;
        this.mAddressLine1 = StringUtils.EMPTY;
        this.mAddressLine2 = StringUtils.EMPTY;
        this.mCity = StringUtils.EMPTY;
        this.mState = StringUtils.EMPTY;
        this.mPostalCode = StringUtils.EMPTY;
        this.mCountry = StringUtils.EMPTY;
        this.mVatNumber = StringUtils.EMPTY;
        this.mEmail = StringUtils.EMPTY;
        this.mPhone = StringUtils.EMPTY;
        this.mFormattedString = StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -2053263135:
                        if (currentName.equals(ResponseConstants.POSTAL_CODE)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case -1958633228:
                        if (currentName.equals(ResponseConstants.FORMATTED_STRING)) {
                            obj = 11;
                            break;
                        }
                        break;
                    case -769510831:
                        if (currentName.equals(ResponseConstants.EMAIL_ADDRESS)) {
                            obj = 10;
                            break;
                        }
                        break;
                    case -612351174:
                        if (currentName.equals(ResponseConstants.PHONE_NUMBER)) {
                            obj = 9;
                            break;
                        }
                        break;
                    case -404257102:
                        if (currentName.equals(ResponseConstants.ADDRESS_LINE_1)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -404257101:
                        if (currentName.equals(ResponseConstants.ADDRESS_LINE_2)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -160985414:
                        if (currentName.equals(ResponseConstants.FIRST_NAME)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 3053931:
                        if (currentName.equals(ResponseConstants.CITY)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 109757585:
                        if (currentName.equals(ResponseConstants.STATE)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 957831062:
                        if (currentName.equals(StructuredShopShippingEstimate.TYPE_COUNTRY)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case 1033462047:
                        if (currentName.equals(ResponseConstants.VAT_NUMBER)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case 2013122196:
                        if (currentName.equals(ResponseConstants.LAST_NAME)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mFirstName = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mLastName = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mAddressLine1 = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mAddressLine2 = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mCity = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mState = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mPostalCode = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.mCountry = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mVatNumber = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                        this.mPhone = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                        this.mEmail = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                        this.mFormattedString = BaseModel.parseString(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public void setFirstName(String str) {
        this.mFirstName = str;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public void setLastName(String str) {
        this.mLastName = str;
    }

    public String getAddressLine1() {
        return this.mAddressLine1;
    }

    public void setAddressLine1(String str) {
        this.mAddressLine1 = str;
    }

    public String getAddressLine2() {
        return this.mAddressLine2;
    }

    public void setAddressLine2(String str) {
        this.mAddressLine2 = str;
    }

    public String getCity() {
        return this.mCity;
    }

    public void setCity(String str) {
        this.mCity = str;
    }

    public String getState() {
        return this.mState;
    }

    public void setState(String str) {
        this.mState = str;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public void setPostalCode(String str) {
        this.mPostalCode = str;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public void setCountry(String str) {
        this.mCountry = str;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public void setEmail(String str) {
        this.mEmail = str;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public void setPhone(String str) {
        this.mPhone = str;
    }

    public String getVatNumber() {
        return this.mVatNumber;
    }

    public void setVatNumber(String str) {
        this.mVatNumber = str;
    }

    @NonNull
    public String getFormattedDetails() {
        return this.mFormattedString;
    }

    public boolean hasDetails() {
        return bh.m3343b(getFormattedDetails());
    }
}
