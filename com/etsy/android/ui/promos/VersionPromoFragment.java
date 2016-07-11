package com.etsy.android.ui.promos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import com.etsy.android.lib.R;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.ui.util.EtsyPromoUtil;

public abstract class VersionPromoFragment extends EtsyFragment implements OnClickListener {
    private VersionPromo mPromo;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPromo = (VersionPromo) getArguments().getSerializable("version_promo");
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            EtsyDialogFragment etsyDialogFragment = (EtsyDialogFragment) parentFragment;
            etsyDialogFragment.setWindowAnimation(R.DialogAnimBottom);
            etsyDialogFragment.enableTouchInterceptPadding(getResources().getDimensionPixelSize(2131361936));
            etsyDialogFragment.setDividerShown(false);
        }
    }

    public boolean handleBackPressed() {
        onPromoDismissed();
        return super.handleBackPressed();
    }

    protected void onPromoDismissed() {
        EtsyPromoUtil.m5145a(getAnalyticsContext(), getActivity(), this.mPromo);
    }
}
