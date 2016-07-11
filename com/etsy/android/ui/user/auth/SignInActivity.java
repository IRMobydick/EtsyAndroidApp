package com.etsy.android.ui.user.auth;

import android.content.Context;
import com.etsy.android.GetUserInfoAndCurrencyBatchJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.LocaleRequest;

/* renamed from: com.etsy.android.ui.user.auth.d */
class SignInActivity extends GetUserInfoAndCurrencyBatchJob {
    final /* synthetic */ SignInActivity f3577a;

    public SignInActivity(SignInActivity signInActivity, Context context) {
        this.f3577a = signInActivity;
        super(context);
    }

    protected void m5043a(EtsyResult<EmptyResult> etsyResult) {
        super.m728a((EtsyResult) etsyResult);
        LocaleRequest.setUserLocale();
        this.f3577a.onFetchedUser();
    }
}
