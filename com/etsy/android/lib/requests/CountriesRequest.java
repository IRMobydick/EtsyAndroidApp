package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class CountriesRequest extends EtsyRequest<Country> {
    private static final long serialVersionUID = -4920262228307284102L;

    public CountriesRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, Country.class);
    }

    public static CountriesRequest findAllCountry() {
        return new CountriesRequest("/countries", RequestMethod.GET);
    }
}
