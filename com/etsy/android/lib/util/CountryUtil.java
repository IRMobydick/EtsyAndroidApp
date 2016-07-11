package com.etsy.android.lib.util;

import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.requests.CountriesRequest;
import com.etsy.android.lib.requests.EtsyRequest;

/* renamed from: com.etsy.android.lib.util.t */
public class CountryUtil extends EtsyRequestJob<Country> {
    final CountryUtil f2064a;

    public CountryUtil(CountryUtil countryUtil) {
        this.f2064a = countryUtil;
    }

    protected EtsyRequest<Country> m3422a() {
        return CountriesRequest.findAllCountry();
    }

    protected void m3423a(EtsyResult etsyResult) {
        if (etsyResult.m1049a() && etsyResult.m1058i()) {
            m3027a(etsyResult.m1056g());
            if (this.f2064a != null) {
                this.f2064a.onCountriesLoaded(m3034d());
                return;
            }
            return;
        }
        if (this.f2064a != null) {
            this.f2064a.onCountriesError();
        }
        EtsyDebug.m1912c(CountryUtil.f1955a, "Error loading countries");
    }
}
