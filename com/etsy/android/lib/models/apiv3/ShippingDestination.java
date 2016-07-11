package com.etsy.android.lib.models.apiv3;

import android.text.TextUtils;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ShippingDestination extends BaseModel {
    static final /* synthetic */ boolean $assertionsDisabled;
    private Country mCountry;
    private String mCountryCode;
    private String mPostalCode;

    static {
        $assertionsDisabled = !ShippingDestination.class.desiredAssertionStatus();
    }

    public ShippingDestination() {
        this.mCountryCode = null;
        this.mCountry = null;
        this.mPostalCode = null;
    }

    public ShippingDestination(Country country, String str, String str2) {
        this.mCountryCode = null;
        this.mCountry = null;
        this.mPostalCode = null;
        this.mCountry = country;
        this.mPostalCode = str;
        this.mCountryCode = str2;
    }

    public Country getCountry() {
        return this.mCountry;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public boolean isSet() {
        if (this.mCountry == null) {
            return false;
        }
        if (this.mCountry.isUs() && TextUtils.isEmpty(this.mPostalCode)) {
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ShippingDestination)) {
            return false;
        }
        ShippingDestination shippingDestination = (ShippingDestination) obj;
        if (this.mCountry == null && shippingDestination.getCountry() != null) {
            return false;
        }
        if ((this.mPostalCode != null || shippingDestination.getPostalCode() == null) && this.mCountry.equals(shippingDestination.mCountry) && this.mPostalCode.equals(shippingDestination.mPostalCode)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if ($assertionsDisabled) {
            return 42;
        }
        throw new AssertionError("hashCode not designed");
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.COUNTRY_CODE.equals(currentName)) {
                    this.mCountryCode = BaseModel.parseString(jsonParser);
                } else if (StructuredShopShippingEstimate.TYPE_COUNTRY.equals(currentName)) {
                    this.mCountry = (Country) BaseModel.parseObject(jsonParser, Country.class);
                } else if (ResponseConstants.POSTAL_CODE.equals(currentName)) {
                    this.mPostalCode = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
