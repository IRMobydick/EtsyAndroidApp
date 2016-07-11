package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.ar;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;

/* renamed from: com.etsy.android.ui.user.auth.j */
public class SignInBaseFragment extends SignInBaseFragment {
    protected final String f3567c;
    final /* synthetic */ SignInBaseFragment f3568d;

    public SignInBaseFragment(SignInBaseFragment signInBaseFragment, String str, String str2, String str3, String str4, String str5) {
        this.f3568d = signInBaseFragment;
        super(signInBaseFragment, str, str3, str4, str5);
        this.f3567c = str2;
    }

    public ar m5040a() {
        return aj.m1101a().m1120f().m1177a(this.b, this.f3567c, this.e, this.g, this.h);
    }

    public SignInFlow m5041c() {
        return SignInFlow.LINK;
    }
}
l /* synthetic */ SignInBaseFragment f3585b;

        SignInBaseFragment(SignInBaseFragment signInBaseFragment, SignInBaseFragment signInBaseFragment2) {
            this.f3585b = signInBaseFragment;
            this.f3584a = signInBaseFragment2;
        }

        public void m5049a(String str) {
            if (str == null) {
                this.f3585b.f3566i.mDialogUtil.m5628a();
                this.f3585b.f3566i.showError((int) R.external_error_connecting, this.f3585b.f3563f);
                return;
            }
            this.f3585b.f3565h = str;
            this.f3584a.m5023a();
        }
    }

    public SignInBaseFragment(SignInBaseFragment signInBaseFragment, String str, String str2, String str3, String str4) {
        this.f3566i = signInBaseFragment;
        super(str);
        this.f3562e = str2;
        this.f3563f = ExternalAccountUtil.m3093a(str2);
        this.f3564g = str3;
        this.f3565h = str4;
    }

    public ar m5033a() {
        return aj.m1101a().m1120f().m1176a(this.b, this.f3562e, this.f3564g, this.f3565h);
    }

    public void m5034a(SignInBaseFragment signInBaseFragment) {
        this.f3566i.mActivity.getExternalAccountDelegate().m1192a(this.f3563f, this.f3565h, new SignInBaseFragment(this, signInBaseFragment));
    }

    public String m5036d() {
        return this.f3562e;
    }

    public AccountType m5037e() {
        return this.f3563f;
    }

    public String m5038f() {
        return this.f3564g;
    }

    public String m5039g() {
        return this.f3565h;
    }

    public SignInFlow m5035c() {
        return SignInFlow.EXTERNAL_SIGN_IN;
    }
}
