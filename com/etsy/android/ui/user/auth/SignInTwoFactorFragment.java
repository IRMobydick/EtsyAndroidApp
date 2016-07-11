package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.ui.user.auth.SignInBaseFragment.XAuthTask;
import java.util.HashMap;

/* renamed from: com.etsy.android.ui.user.auth.m */
class SignInTwoFactorFragment extends XAuthTask {
    final /* synthetic */ SignInTwoFactorFragment f3587a;

    private SignInTwoFactorFragment(SignInTwoFactorFragment signInTwoFactorFragment) {
        this.f3587a = signInTwoFactorFragment;
        super();
    }

    protected void onPreExecute() {
        this.f3587a.mDialogUtil.m5629a((int) R.signing_in);
    }

    protected XAuthTask getNewTask() {
        return new SignInTwoFactorFragment(this.f3587a);
    }

    protected void handleNoResult() {
        this.f3587a.mDialogUtil.m5631a(this.f3587a.mTxtError, (int) R.error_two_factor_code);
        EventTracker.m2028b(this.f3587a.getAnalyticsContext(), this.mAuth.m5029c(), new HashMap());
    }

    protected void handleUnsuccessfulXAuth(HashMap<String, Object> hashMap) {
        EventTracker.m2028b(this.f3587a.getAnalyticsContext(), this.mAuth.m5029c(), (HashMap) hashMap);
    }

    protected void handleSuccessfulXAuthResult() {
        EventTracker.m2047f(this.f3587a.getAnalyticsContext(), this.mAuth.m5029c());
    }
}
