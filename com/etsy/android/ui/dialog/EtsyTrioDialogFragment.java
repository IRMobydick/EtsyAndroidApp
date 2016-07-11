package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class EtsyTrioDialogFragment extends EtsyFragment {
    private EtsyTrioDialogFragment mCallback;
    private TrackingOnClickListener mClickListener;
    private int mNeutralText;
    private int mNoText;
    private IDialogFragment mParentDialog;
    private int mYesText;

    /* renamed from: com.etsy.android.ui.dialog.EtsyTrioDialogFragment.1 */
    class C06731 extends TrackingOnClickListener {
        final /* synthetic */ EtsyTrioDialogFragment f2936a;

        C06731(EtsyTrioDialogFragment etsyTrioDialogFragment) {
            this.f2936a = etsyTrioDialogFragment;
        }

        public void onViewClick(View view) {
            switch (view.getId()) {
                case R.dialog_no /*2131755651*/:
                    if (this.f2936a.mCallback != null) {
                        this.f2936a.mCallback.m3595c();
                    }
                    this.f2936a.mParentDialog.dismissAllowingStateLoss(false);
                case R.dialog_neutral /*2131755652*/:
                    if (this.f2936a.mCallback != null) {
                        this.f2936a.mCallback.m3594b();
                    }
                    this.f2936a.mParentDialog.dismissAllowingStateLoss(false);
                case R.dialog_yes /*2131755653*/:
                    if (this.f2936a.mCallback != null) {
                        this.f2936a.mCallback.m3593a();
                    }
                    this.f2936a.mParentDialog.dismissAllowingStateLoss(false);
                default:
            }
        }
    }

    public static EtsyTrioDialogFragment newInstance(EtsyTrioDialogFragment etsyTrioDialogFragment, int i, int i2, int i3) {
        EtsyTrioDialogFragment etsyTrioDialogFragment2 = new EtsyTrioDialogFragment();
        etsyTrioDialogFragment2.init(etsyTrioDialogFragment, i, i2, i3);
        return etsyTrioDialogFragment2;
    }

    public void init(EtsyTrioDialogFragment etsyTrioDialogFragment, int i, int i2, int i3) {
        this.mCallback = etsyTrioDialogFragment;
        this.mYesText = i;
        this.mNoText = i2;
        this.mNeutralText = i3;
        this.mClickListener = new C06731(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.fragment_dialog_trio, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mParentDialog = (IDialogFragment) getParentFragment();
        this.mParentDialog.setOkButtonVisibility(8);
        this.mParentDialog.setWindowMode(WindowMode.WRAP);
        this.mParentDialog.setWindowBackgroundDim(0.6f);
        TextView textView = (TextView) getView().findViewById(R.dialog_no);
        textView.setOnClickListener(this.mClickListener);
        if (this.mNoText != 0) {
            textView.setText(this.mNoText);
        } else {
            textView.setVisibility(8);
        }
        textView = (TextView) getView().findViewById(R.dialog_yes);
        textView.setOnClickListener(this.mClickListener);
        if (this.mYesText != 0) {
            textView.setText(this.mYesText);
        } else {
            textView.setVisibility(8);
        }
        textView = (TextView) getView().findViewById(R.dialog_neutral);
        textView.setOnClickListener(this.mClickListener);
        if (this.mNeutralText > 0) {
            textView.setText(this.mNeutralText);
        } else {
            textView.setVisibility(8);
        }
    }
}
