package com.etsy.android.uikit.ui.user.auth;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.apiv3.ForgotPasswordRequest;
import com.etsy.android.lib.util.bl;

/* renamed from: com.etsy.android.uikit.ui.user.auth.a */
class ForgotPasswordFragment extends EtsyRequestJob<EmptyResult> {
    final /* synthetic */ ForgotPasswordFragment f4126a;
    private final String f4127c;

    public ForgotPasswordFragment(ForgotPasswordFragment forgotPasswordFragment, String str) {
        this.f4126a = forgotPasswordFragment;
        this.f4127c = str;
    }

    protected EtsyRequest<EmptyResult> m5477a() {
        return ForgotPasswordRequest.postEmailAddress(this.f4127c);
    }

    protected void b_() {
        if (this.f4126a.mSubmitButton != null) {
            this.f4126a.mSubmitButton.showLoading();
        }
    }

    protected void m5478a(EtsyResult<EmptyResult> etsyResult) {
        if (etsyResult.m1049a()) {
            bl.m3355a(this.f4126a.getActivity(), R.forgot_password_sent);
            Fragment parentFragment = this.f4126a.getParentFragment();
            if (parentFragment != null && (parentFragment instanceof DialogFragment)) {
                ((DialogFragment) parentFragment).dismissAllowingStateLoss();
                return;
            }
            return;
        }
        CharSequence c = etsyResult.m1052c();
        if (this.f4126a.mTextEmail != null) {
            this.f4126a.mTextEmail.setError(c);
        }
        if (this.f4126a.mSubmitButton != null) {
            this.f4126a.mSubmitButton.hideLoading();
        }
    }
}
