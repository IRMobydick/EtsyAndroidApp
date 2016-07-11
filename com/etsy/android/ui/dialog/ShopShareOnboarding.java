package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.etsy.android.lib.logger.ShopShareEventTracker;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ay;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class ShopShareOnboarding extends EtsyFragment {
    private static final double DIM_AMOUNT = 0.8d;
    private EtsyDialogFragment parentDialog;

    /* renamed from: com.etsy.android.ui.dialog.ShopShareOnboarding.1 */
    class C06751 extends TrackingOnClickListener {
        final /* synthetic */ ShopShareOnboarding f2938a;

        C06751(ShopShareOnboarding shopShareOnboarding) {
            this.f2938a = shopShareOnboarding;
        }

        public void onViewClick(View view) {
            ShopShareEventTracker.m2089a("feed.onboarding.dismissed", "shop_shares_feed");
            SharedPreferencesUtility.m3136c(this.f2938a.getActivity(), "shop-share-mobile-onboarding");
            ay.m3290b("shop-share-mobile-onboarding");
            this.f2938a.parentDialog.dismiss();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903231, null);
        ShopShareEventTracker.m2089a("feed.onboarding.displayed", "shop_shares_feed");
        ((Button) inflate.findViewById(2131755799)).setOnClickListener(getOnClickListener());
        this.parentDialog = (EtsyDialogFragment) getParentFragment();
        this.parentDialog.hideHeaderAndClearBackground();
        this.parentDialog.setWindowMode(WindowMode.WRAP);
        this.parentDialog.setWindowBackgroundDim(0.8f);
        return inflate;
    }

    public OnClickListener getOnClickListener() {
        return new C06751(this);
    }
}
