package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Country;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;

public class ListingShippingDetails extends BaseModel {
    public static final String COUNTRIES = "countries";
    public static final String DEFAULT_ADDRESS = "default_address";
    public static final String ETSY_ASAP = "etsy_asap";
    public static final String STANDARD_OPTION = "standard_option";
    private List<Country> mCountries;
    private ListingEtsyAsap mListingEtsyAsap;
    private boolean mOffersEtsyAsap;
    private boolean mOffersEtsyAsapToday;
    private ShippingAddressPreference mShippingAddress;
    private ShippingOption mShippingOption;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (COUNTRIES.equals(currentName)) {
                    this.mCountries = BaseModel.parseArray(jsonParser, Country.class);
                } else if (DEFAULT_ADDRESS.equals(currentName)) {
                    this.mShippingAddress = (ShippingAddressPreference) BaseModel.parseObject(jsonParser, ShippingAddressPreference.class);
                } else if (STANDARD_OPTION.equals(currentName)) {
                    this.mShippingOption = (ShippingOption) BaseModel.parseObject(jsonParser, ShippingOption.class);
                } else if (ETSY_ASAP.equals(currentName)) {
                    this.mListingEtsyAsap = (ListingEtsyAsap) BaseModel.parseObject(jsonParser, ListingEtsyAsap.class);
                    if (this.mListingEtsyAsap != null) {
                        this.mOffersEtsyAsap = this.mListingEtsyAsap.offersEtsyAsap();
                        this.mOffersEtsyAsapToday = this.mListingEtsyAsap.offersEtsyAsapToday();
                    }
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public List<Country> getCountries() {
        return this.mCountries;
    }

    public ShippingAddressPreference getShippingAddress() {
        return this.mShippingAddress;
    }

    public ShippingOption getShippingOption() {
        return this.mShippingOption;
    }

    public boolean offersEtsyAsap() {
        return this.mOffersEtsyAsap;
    }

    public boolean offersEtsyAsapToday() {
        return this.mOffersEtsyAsapToday;
    }

    public void setOffersEtsyAsap(boolean z) {
        this.mOffersEtsyAsap = z;
    }

    public void setOffersEtsyAsapToday(boolean z) {
        this.mOffersEtsyAsapToday = z;
    }

    public void setShippingAddress(ShippingAddressPreference shippingAddressPreference) {
        this.mShippingAddress = shippingAddressPreference;
    }
}
