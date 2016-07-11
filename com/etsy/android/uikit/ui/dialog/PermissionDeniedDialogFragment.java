package com.etsy.android.uikit.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.fonts.StandardFontIcon;
import com.etsy.android.uikit.nav.ActivityNavigator;
import com.etsy.android.uikit.ui.core.TrackingBaseFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;
import com.google.android.gms.gcm.Task;

public class PermissionDeniedDialogFragment extends TrackingBaseFragment {
    private static final double DIM_AMOUNT = 0.5d;
    private static final String TAG;
    protected IDialogFragment mParentDialog;
    private String mPermission;

    /* renamed from: com.etsy.android.uikit.ui.dialog.PermissionDeniedDialogFragment.1 */
    class C09751 implements OnGlobalLayoutListener {
        final /* synthetic */ FrameLayout f4079a;
        final /* synthetic */ ImageView f4080b;
        final /* synthetic */ PermissionDeniedDialogFragment f4081c;

        C09751(PermissionDeniedDialogFragment permissionDeniedDialogFragment, FrameLayout frameLayout, ImageView imageView) {
            this.f4081c = permissionDeniedDialogFragment;
            this.f4079a = frameLayout;
            this.f4080b = imageView;
        }

        public void onGlobalLayout() {
            ViewTreeObserverHelper.m5639b(this.f4079a.getViewTreeObserver(), (OnGlobalLayoutListener) this);
            this.f4080b.setLayoutParams(new LayoutParams(this.f4079a.getWidth(), this.f4079a.getHeight()));
            this.f4080b.setScaleType(ScaleType.CENTER);
            this.f4080b.setAdjustViewBounds(false);
            this.f4080b.invalidate();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.dialog.PermissionDeniedDialogFragment.2 */
    class C09762 extends TrackingOnClickListener {
        final /* synthetic */ PermissionDeniedDialogFragment f4082a;

        C09762(PermissionDeniedDialogFragment permissionDeniedDialogFragment) {
            this.f4082a = permissionDeniedDialogFragment;
        }

        public void onViewClick(View view) {
            ActivityNavigator.m4430a(this.f4082a.getActivity());
            if (this.f4082a.mParentDialog != null) {
                this.f4082a.mParentDialog.dismiss();
            }
        }
    }

    static {
        TAG = EtsyDebug.m1891a(PermissionDeniedDialogFragment.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPermission = getArguments().getString("denied_permission");
        if (this.mPermission == null || !("android.permission.ACCESS_FINE_LOCATION".equals(this.mPermission) || "android.permission.RECORD_AUDIO".equals(this.mPermission))) {
            EtsyDebug.m1894a(new RuntimeException("Either Location or Microphone permission must be specified"));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.fragment_swiper_permanently_denied_dialog, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            this.mParentDialog = (IDialogFragment) getParentFragment();
            this.mParentDialog.hideHeader();
            this.mParentDialog.setWindowAnimation(R.DialogAnimBottom);
            this.mParentDialog.setWindowBackgroundDim(0.5f);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        setupBackground(view);
        showPermanentlyDenied(view);
    }

    private void setupBackground(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.image_background);
        FrameLayout frameLayout = (FrameLayout) imageView.getParent();
        ViewTreeObserver viewTreeObserver = frameLayout.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new C09751(this, frameLayout, imageView));
        }
    }

    private void showPermanentlyDenied(View view) {
        TextView textView = (TextView) view.findViewById(R.permission_message);
        TextView textView2 = (TextView) view.findViewById(R.permission_to_request);
        IconView iconView = (IconView) view.findViewById(R.permission_icon);
        String str = this.mPermission;
        Object obj = -1;
        switch (str.hashCode()) {
            case -1888586689:
                if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    obj = 1;
                    break;
                }
                break;
            case 1831139720:
                if (str.equals("android.permission.RECORD_AUDIO")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                textView.setText(getString(R.permission_microphone_denied));
                textView2.setText(getString(R.permisson_to_request_microphone));
                iconView.setIcon(StandardFontIcon.MICROPHONE);
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                textView.setText(getString(R.permission_location_denied));
                textView2.setText(getString(R.permisson_location_to_request));
                iconView.setIcon(StandardFontIcon.LOCATION);
                break;
        }
        ((TextView) view.findViewById(R.dialog_yes)).setOnClickListener(new C09762(this));
    }
}
