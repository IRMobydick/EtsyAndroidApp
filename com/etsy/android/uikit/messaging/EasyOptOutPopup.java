package com.etsy.android.uikit.messaging;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.ui.toast.PersistentToastPopup;

/* renamed from: com.etsy.android.uikit.messaging.a */
public class EasyOptOutPopup extends PersistentToastPopup {
    protected CharSequence f3988a;
    private CharSequence f3989g;

    public EasyOptOutPopup(Activity activity) {
        super(activity);
    }

    @LayoutRes
    public int m5365a() {
        return R.popup_easy_opt_out;
    }

    public void m5366a(CharSequence charSequence) {
        this.f3988a = charSequence;
    }

    public void m5368b(CharSequence charSequence) {
        this.f3989g = charSequence;
    }

    public void m5367a(boolean z) {
        ((EasyOptOutToastView) this.c).setTextContent(this.f3988a, this.f3989g);
        super.m5356a(z);
    }
}
