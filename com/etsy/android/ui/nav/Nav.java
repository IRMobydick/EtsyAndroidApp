package com.etsy.android.ui.nav;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.uikit.nav.FragmentNavigator.FragmentTransactionMode;
import com.etsy.android.uikit.nav.NavBase;

/* renamed from: com.etsy.android.ui.nav.e */
public class Nav extends NavBase {
    Nav(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public static Nav m4682a(FragmentActivity fragmentActivity) {
        return new Nav(fragmentActivity);
    }

    public EtsyActivityNavigator m4683a() {
        return new EtsyActivityNavigator(this.a);
    }

    public static EtsyActivityNavigator m4681a(Activity activity) {
        return new EtsyActivityNavigator(activity);
    }

    public EtsyFragmentNavigator m4684b() {
        return new EtsyFragmentNavigator(this.a, FragmentTransactionMode.ADD);
    }

    public EtsyFragmentNavigator m4685c() {
        return new EtsyFragmentNavigator(this.a, FragmentTransactionMode.REPLACE);
    }

    public DialogNavigator m4686d() {
        return new DialogNavigator(this.a);
    }
}
