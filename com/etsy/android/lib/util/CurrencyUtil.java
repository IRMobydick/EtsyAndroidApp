package com.etsy.android.lib.util;

import android.content.Context;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.EtsyLocale;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.LocaleRequest;

/* renamed from: com.etsy.android.lib.util.y */
public class CurrencyUtil extends EtsyRequestJob<EtsyLocale> {
    private final String f2068a;
    private final Context f2069c;

    public CurrencyUtil(Context context, String str) {
        this.f2069c = context;
        this.f2068a = str;
    }

    protected EtsyRequest<EtsyLocale> m3430a() {
        return LocaleRequest.updateCurrency(this.f2068a);
    }

    protected void m3431a(EtsyResult<EtsyLocale> etsyResult) {
        if (!etsyResult.m1049a() && this.f2069c != null) {
            bl.m3365b(this.f2069c, R.currency_save_error);
        }
    }
}
