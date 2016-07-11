package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public class ShippingAddressPreference extends BaseModel {
    public static final String COUNTRY = "country";
    private Country mCountry;
    private String mPostalCode;

    public ShippingAddressPreference() {
        this.mCountry = null;
        this.mPostalCode = StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (COUNTRY.equals(currentName)) {
                    this.mCountry = (Country) BaseModel.parseObject(jsonParser, Country.class);
                } else if (ResponseConstants.POSTAL_CODE.equals(currentName)) {
                    this.mPostalCode = BaseModel.parseString(jsonParser).substring(0, 5);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public Country getCountry() {
        return this.mCountry;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public void setCountry(Country country) {
        this.mCountry = country;
    }

    public void setPostalCode(String str) {
        this.mPostalCode = str;
    }
}
