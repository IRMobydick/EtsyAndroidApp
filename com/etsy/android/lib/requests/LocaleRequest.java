package com.etsy.android.lib.requests;

import android.support.v4.util.ArrayMap;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.PersistentEtsyApiV3Request;
import com.etsy.android.lib.core.http.request.aa;
import com.etsy.android.lib.core.http.url.p009a.EtsyV3Urls;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.EtsyLocale;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.etsy.android.lib.util.CurrencyUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class LocaleRequest extends EtsyRequest<EtsyLocale> {
    private static final String PREFERENCE_ENDPOINT = "/locale/preference";

    public LocaleRequest(String str, RequestMethod requestMethod, Class<EtsyLocale> cls) {
        super(str, requestMethod, cls);
    }

    public static LocaleRequest getLocale() {
        return new LocaleRequest(PREFERENCE_ENDPOINT, RequestMethod.GET, EtsyLocale.class);
    }

    public static LocaleRequest updateCurrency(String str) {
        return updateLocale(StringUtils.EMPTY, str);
    }

    public static LocaleRequest updateLocale(String str, String str2) {
        RequestMethod requestMethod = RequestMethod.POST;
        Map arrayMap = new ArrayMap();
        arrayMap.put(ResponseConstants.CURRENCY_CODE, str2);
        arrayMap.put("language_code", str);
        LocaleRequest localeRequest = new LocaleRequest(PREFERENCE_ENDPOINT, requestMethod, EtsyLocale.class);
        localeRequest.addParams(arrayMap);
        return localeRequest;
    }

    public static void setUserLocale() {
        Map hashMap = new HashMap();
        hashMap.put(ResponseConstants.CURRENCY_CODE, CurrencyUtil.m3091i());
        Object language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        if ("en".equals(language)) {
            language = "GB".equals(country) ? "en-GB" : "en-US";
        }
        hashMap.put("language_code", language);
        hashMap.put(ResponseConstants.REGION_CODE, country);
        aj.m1101a().m1124j().m1663a((PersistentEtsyApiV3Request) aa.m1400a((EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(EmptyResult.class, EtsyV3Urls.m1500b()).m1382a(1)).m1387a(hashMap)).m1432b(true)).m1393d()).m1399b());
    }
}
