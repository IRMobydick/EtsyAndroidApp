package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.core.ar;

/* renamed from: com.etsy.android.ui.user.auth.b */
public class RegisterFragment extends SignInBaseFragment {
    final /* synthetic */ RegisterFragment f3569a;
    private final String f3570j;
    private final String f3571k;
    private final String f3572l;
    private final String f3573m;
    private final String f3574n;
    private final String f3575o;

    public RegisterFragment(RegisterFragment registerFragment, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.f3569a = registerFragment;
        super(registerFragment, str, str2, str9, str10, str11);
        this.f3570j = str3;
        this.f3571k = str4;
        this.f3572l = str5;
        this.f3573m = str6;
        this.f3574n = str7;
        this.f3575o = str8;
    }

    public ar m5042a() {
        return this.f3569a.mAuthManager.m1179a(this.b, this.c, this.f3570j, this.f3571k, this.f3572l, this.f3573m, this.f3574n, this.f3575o, this.e, this.g, this.h);
    }
}
