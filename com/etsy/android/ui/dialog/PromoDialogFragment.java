package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.HashMap;

public class PromoDialogFragment extends EtsyFragment {
    private static final double DIM_AMOUNT = 0.2d;
    private TrackingOnClickListener mDialogClickListener;
    private int mLayoutId;
    private OnClickListener mOnClickListener;
    private IDialogFragment mParentDialog;
    private String mPromoPref;

    /* renamed from: com.etsy.android.ui.dialog.PromoDialogFragment.1 */
    class C06741 extends TrackingOnClickListener {
        final /* synthetic */ PromoDialogFragment f2937a;

        C06741(PromoDialogFragment promoDialogFragment) {
            this.f2937a = promoDialogFragment;
        }

        public void onViewClick(View view) {
            if (view.getId() == 2131755760) {
                if (!TextUtils.isEmpty(this.f2937a.mPromoPref)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("promo_version", this.f2937a.mPromoPref);
                    EtsyLogger.m1966a().m1997b("update_beta_dismissed", NotificationCompatApi21.CATEGORY_PROMO, hashMap);
                    SharedPreferencesUtility.m3123a(this.f2937a.mActivity, this.f2937a.mPromoPref, false);
                }
                this.f2937a.mParentDialog.dismiss();
            } else if (view.getId() == 2131755759) {
                if (this.f2937a.mOnClickListener != null) {
                    this.f2937a.mOnClickListener.onClick(view);
                }
                this.f2937a.mParentDialog.dismiss();
            }
        }
    }

    public static PromoDialogFragment newInstance(int i, String str, OnClickListener onClickListener) {
        PromoDialogFragment promoDialogFragment = new PromoDialogFragment();
        promoDialogFragment.setLayoutOptions(i, str, onClickListener);
        return promoDialogFragment;
    }

    public void setLayoutOptions(int i, String str, OnClickListener onClickListener) {
        this.mLayoutId = i;
        this.mPromoPref = str;
        this.mOnClickListener = onClickListener;
        this.mDialogClickListener = new C06741(this);
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mDialogClickListener;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903222, null);
        if (this.mLayoutId > 0) {
            View inflate2 = layoutInflater.inflate(this.mLayoutId, null);
            View findViewById = inflate.findViewById(2131755760);
            findViewById.bringToFront();
            findViewById.setOnClickListener(this.mDialogClickListener);
            FrameLayout frameLayout = (FrameLayout) inflate.findViewById(2131755759);
            frameLayout.addView(inflate2);
            frameLayout.setOnClickListener(this.mDialogClickListener);
        }
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mParentDialog = (IDialogFragment) getParentFragment();
        this.mParentDialog.hideHeader();
        this.mParentDialog.setDialogGravity(80);
        this.mParentDialog.setWindowAnimation(R.DialogAnimBottom);
        this.mParentDialog.setWindowMode(WindowMode.WRAP);
        this.mParentDialog.setWindowBackgroundDim(0.2f);
    }
}
