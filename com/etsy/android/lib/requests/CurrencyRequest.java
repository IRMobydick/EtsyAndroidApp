package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.EtsyCurrency.Currencies;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class CurrencyRequest extends EtsyRequest<Currencies> {
    public CurrencyRequest(String str, RequestMethod requestMethod, Class<Currencies> cls) {
        super(str, requestMethod, cls);
    }

    public static CurrencyRequest getBrowseCurrencies() {
        return new CurrencyRequest("/currencies/browse", RequestMethod.GET, Currencies.class);
    }

    public static CurrencyRequest getListCurrencies() {
        return new CurrencyRequest("/currencies/list", RequestMethod.GET, Currencies.class);
    }
}
