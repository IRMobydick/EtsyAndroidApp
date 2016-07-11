package com.etsy.android.lib.logger;

import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.CurrencyUtil;
import java.util.HashMap;
import java.util.Locale;

final class EventTracker$12 extends HashMap<String, Object> {
    final /* synthetic */ Locale val$defaultLocale;

    EventTracker$12(Locale locale) {
        this.val$defaultLocale = locale;
        put(EtsyRequest.PARAM_LANGUAGE, this.val$defaultLocale.getLanguage());
        put(EtsyRequest.PARAM_REGION, this.val$defaultLocale.getCountry());
        put(EtsyRequest.PARAM_CURRENCY, CurrencyUtil.m3091i());
    }
}
