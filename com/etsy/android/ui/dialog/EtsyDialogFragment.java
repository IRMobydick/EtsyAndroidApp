package com.etsy.android.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.aa;
import com.etsy.android.lib.util.ab;
import com.etsy.android.uikit.IEtsyFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.ui.dialog.RetainedNestingDialogFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.MaxHeightFrameLayout;
import com.etsy.android.uikit.view.TouchObservableDialog;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;

public class EtsyDialogFragment extends RetainedNestingDialogFragment implements IDialogFragment {
    private static final int NOT_PENDING_TOUCH_INTERCEPT_PADDING = -1;
    public static final String OPT_X_BUTTON = "x";
    private static final float WINDOW_HEIGHT_RATIO_LARGE_LANDSCAPE = 0.9f;
    private static final float WINDOW_HEIGHT_RATIO_LARGE_PORTRAIT = 0.9f;
    private static final float WINDOW_HEIGHT_RATIO_MEDIUM = 0.56f;
    private static final float WINDOW_HEIGHT_RATIO_MEDIUM_LANDSCAPE = 0.83f;
    private static final float WINDOW_HEIGHT_RATIO_SMALL = 0.45f;
    private static final float WINDOW_WIDTH_RATIO_LARGE_LANDSCAPE = 0.66f;
    private static final float WINDOW_WIDTH_RATIO_LARGE_PORTRAIT = 0.9f;
    private Button mBtnOk;
    private Button mBtnSecondary;
    private View mDialogCard;
    private OnDismissListener mDismissListener;
    private ab mDisplayUtil;
    private View mDivider;
    private Fragment mFragment;
    private int mGravity;
    private View mHeader;
    private boolean mIsReCreation;
    private boolean mIsSpannableTitle;
    private int mMaxHeight;
    private int mMaxWidth;
    private OnClickListener mOkClickListener;
    private String mOkText;
    private int mPendingTouchInterceptPadding;
    private OnClickListener mSecondaryClickListener;
    private String mSecondaryText;
    private boolean mSecondaryWillDismiss;
    private Spannable mSubTitle;
    private String mTitle;
    private Rect mTouchInterceptRect;
    private TextView mTxtSubTitle;
    private TextView mTxtTitle;
    private TextView mTxtTitleSans;
    private MaxHeightFrameLayout mView;
    private boolean mWillDismiss;
    private int mWindowAnimation;
    private WindowMode mWindowMode;
    private View mXButton;
    private int mYMargin;

    /* renamed from: com.etsy.android.ui.dialog.EtsyDialogFragment.1 */
    class C06691 extends TrackingOnClickListener {
        final /* synthetic */ EtsyDialogFragment f2931a;

        C06691(EtsyDialogFragment etsyDialogFragment) {
            this.f2931a = etsyDialogFragment;
        }

        public void onViewClick(View view) {
            if (this.f2931a.mOkClickListener != null) {
                this.f2931a.mOkClickListener.onClick(view);
            }
            if (this.f2931a.mWillDismiss) {
                this.f2931a.dismiss();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.dialog.EtsyDialogFragment.2 */
    class C06702 extends TrackingOnClickListener {
        final /* synthetic */ EtsyDialogFragment f2932a;

        C06702(EtsyDialogFragment etsyDialogFragment) {
            this.f2932a = etsyDialogFragment;
        }

        public void onViewClick(View view) {
            if (this.f2932a.mSecondaryClickListener != null) {
                this.f2932a.mSecondaryClickListener.onClick(view);
            }
            if (this.f2932a.mSecondaryWillDismiss) {
                this.f2932a.dismiss();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.dialog.EtsyDialogFragment.3 */
    class C06713 implements Runnable {
        final /* synthetic */ int f2933a;
        final /* synthetic */ EtsyDialogFragment f2934b;

        C06713(EtsyDialogFragment etsyDialogFragment, int i) {
            this.f2934b = etsyDialogFragment;
            this.f2933a = i;
        }

        public void run() {
            Rect rect = new Rect();
            this.f2934b.mView.getHitRect(rect);
            rect.top -= this.f2933a;
            rect.left -= this.f2933a;
            rect.right += this.f2933a;
            rect.bottom += this.f2933a;
            this.f2934b.mTouchInterceptRect = rect;
            if (this.f2934b.getDialog() != null) {
                ((TouchObservableDialog) this.f2934b.getDialog()).setTouchInterceptRect(this.f2934b.mTouchInterceptRect);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.dialog.EtsyDialogFragment.4 */
    /* synthetic */ class C06724 {
        static final /* synthetic */ int[] f2935a;

        static {
            f2935a = new int[WindowMode.values().length];
            try {
                f2935a[WindowMode.LARGE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public static EtsyDialogFragment newInstance(Fragment fragment) {
        EtsyDialogFragment etsyDialogFragment = new EtsyDialogFragment();
        etsyDialogFragment.setFragment(fragment);
        return etsyDialogFragment;
    }

    public EtsyDialogFragment() {
        this.mIsSpannableTitle = false;
        this.mTitle = StringUtils.EMPTY;
        this.mSubTitle = new SpannableString(StringUtils.EMPTY);
        this.mWindowMode = WindowMode.STANDARD;
        this.mGravity = 17;
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    public void onCreate(Bundle bundle) {
        boolean z;
        boolean z2 = false;
        super.onCreate(bundle);
        setCancelable(false);
        this.mIsReCreation = bundle != null;
        if (getArguments() != null) {
            z = getArguments().getBoolean("USE_DIM", false);
        } else {
            z = false;
        }
        if (getArguments() != null) {
            z2 = getArguments().getBoolean("DIALOG_SEARCH", false);
        }
        this.mPendingTouchInterceptPadding = NOT_PENDING_TOUCH_INTERCEPT_PADDING;
        this.mMaxWidth = getResources().getDimensionPixelSize(R.dialog_max_width);
        if (z2) {
            setStyle(1, 2131427813);
        } else {
            setStyle(1, z ? R.EtsyLibDialogStyle : R.EtsyLibDialogStyle_NoDim);
        }
        this.mDisplayUtil = new ab(getActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = (MaxHeightFrameLayout) layoutInflater.inflate(R.fragment_dialog, viewGroup, false);
        this.mTxtTitle = (TextView) this.mView.findViewById(R.txt_title);
        this.mTxtTitleSans = (TextView) this.mView.findViewById(R.txt_title_sans);
        this.mTxtSubTitle = (TextView) this.mView.findViewById(R.txt_subtitle);
        this.mHeader = this.mView.findViewById(R.dialog_header);
        this.mDialogCard = this.mView.findViewById(R.dialog_card);
        this.mBtnOk = (Button) this.mView.findViewById(R.btn_ok);
        this.mXButton = this.mView.findViewById(R.btn_x);
        this.mBtnSecondary = (Button) this.mView.findViewById(R.btn_secondary);
        this.mDivider = this.mView.findViewById(R.divider);
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle == null) {
            getChildFragmentManager().beginTransaction().replace(R.inner_fragment_container, this.mFragment).commit();
        }
        updateTitle();
        updateOkButton();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        setCancelable(false);
        if (this.mPendingTouchInterceptPadding != NOT_PENDING_TOUCH_INTERCEPT_PADDING) {
            enableTouchInterceptPadding(this.mPendingTouchInterceptPadding);
            this.mPendingTouchInterceptPadding = NOT_PENDING_TOUCH_INTERCEPT_PADDING;
        }
        return new TouchObservableDialog(getActivity(), this, getTheme(), R.inner_fragment_container);
    }

    public void onResume() {
        super.onResume();
        if (this.mIsReCreation) {
            dismissAllowingStateLoss();
        } else {
            layoutWindow();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mFragment != null) {
            this.mFragment.onActivityResult(i, i2, intent);
        }
    }

    public void setTitle(String str) {
        this.mTitle = str;
        updateTitle();
    }

    public void setSubTitle(String str) {
        this.mSubTitle = new SpannableString(str);
        updateTitle();
    }

    public void setSubTitle(Spannable spannable) {
        this.mSubTitle = spannable;
        updateTitle();
    }

    public void hideHeaderAndClearBackground() {
        hideHeader();
        this.mDialogCard.setBackgroundColor(getResources().getColor(R.transparent));
    }

    public void hideHeader() {
        this.mHeader.setVisibility(8);
        this.mDivider.setVisibility(8);
    }

    public void showHeader() {
        this.mHeader.setVisibility(0);
        this.mDivider.setVisibility(0);
    }

    public void setWindowBackgroundDim(float f) {
        if (aa.m3167a()) {
            Window window = getDialog().getWindow();
            window.addFlags(2);
            window.setDimAmount(f);
        }
    }

    public void setOkButtonVisibility(int i) {
        if (this.mBtnOk != null) {
            this.mBtnOk.setVisibility(i);
        }
    }

    public void setOkButtonEnabled(boolean z) {
        if (this.mBtnOk != null) {
            this.mBtnOk.setEnabled(z);
        }
    }

    public void setOkButton(String str, OnClickListener onClickListener, boolean z) {
        this.mOkText = str;
        this.mOkClickListener = onClickListener;
        this.mWillDismiss = z;
        updateOkButton();
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mDismissListener = onDismissListener;
    }

    public void setOkClickListener(OnClickListener onClickListener, boolean z) {
        this.mOkClickListener = onClickListener;
        this.mWillDismiss = z;
    }

    private void updateOkButton() {
        if (this.mXButton != null && this.mBtnOk != null) {
            if (TextUtils.isEmpty(this.mOkText)) {
                this.mBtnOk.setVisibility(8);
                this.mXButton.setVisibility(8);
                return;
            }
            View view;
            if (OPT_X_BUTTON.equalsIgnoreCase(this.mOkText)) {
                this.mXButton.setVisibility(0);
                this.mBtnOk.setVisibility(8);
                view = this.mXButton;
            } else {
                this.mBtnOk.setText(this.mOkText);
                this.mBtnOk.setVisibility(0);
                this.mXButton.setVisibility(8);
                view = this.mBtnOk;
            }
            view.setOnClickListener(new C06691(this));
        }
    }

    public void setSecondaryButton(@StringRes int i, OnClickListener onClickListener, boolean z) {
        this.mSecondaryText = getString(i);
        this.mSecondaryClickListener = onClickListener;
        this.mSecondaryWillDismiss = z;
        updateSecondaryButton();
    }

    public void setSecondaryButtonVisibility(int i) {
        if (this.mBtnSecondary != null) {
            this.mBtnSecondary.setVisibility(i);
        }
    }

    private void updateSecondaryButton() {
        if (this.mBtnSecondary != null) {
            this.mBtnSecondary.setText(this.mSecondaryText);
            this.mBtnSecondary.setVisibility(0);
            this.mBtnSecondary.setOnClickListener(new C06702(this));
        }
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public void setMaxWidth(int i) {
        this.mMaxWidth = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxHeight = i;
    }

    public void setWindowAnimation(int i) {
        this.mWindowAnimation = i;
    }

    public int getWindowAnimation() {
        return this.mWindowAnimation;
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public void dismiss() {
        dismiss(true);
    }

    public void dismiss(boolean z) {
        super.dismiss();
        if (z && this.mDismissListener != null) {
            this.mDismissListener.onDismiss(getDialog());
        }
        if (this.mFragment instanceof IEtsyFragment) {
            ((IEtsyFragment) this.mFragment).handleBackPressed();
        }
    }

    public void dismissAllowingStateLoss() {
        dismissAllowingStateLoss(true);
    }

    public void dismissAllowingStateLoss(boolean z) {
        super.dismissAllowingStateLoss();
        if (z && this.mDismissListener != null) {
            this.mDismissListener.onDismiss(getDialog());
        }
        if (this.mFragment instanceof IEtsyFragment) {
            ((IEtsyFragment) this.mFragment).handleBackPressed();
        }
    }

    private void updateTitle() {
        if (!TextUtils.isEmpty(this.mTitle)) {
            if (this.mTxtTitle != null) {
                if (this.mIsSpannableTitle) {
                    this.mTxtTitle.setText(Html.fromHtml(this.mTitle), BufferType.SPANNABLE);
                } else {
                    this.mTxtTitle.setText(this.mTitle);
                }
            }
            if (this.mTxtTitleSans != null) {
                if (this.mIsSpannableTitle) {
                    this.mTxtTitleSans.setText(Html.fromHtml(this.mTitle), BufferType.SPANNABLE);
                } else {
                    this.mTxtTitleSans.setText(this.mTitle);
                }
            }
        }
        if (this.mTxtSubTitle == null) {
            return;
        }
        if (TextUtils.isEmpty(this.mSubTitle.toString())) {
            this.mTxtSubTitle.setVisibility(8);
            return;
        }
        this.mTxtSubTitle.setText(this.mSubTitle);
        this.mTxtSubTitle.setVisibility(0);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        layoutWindow();
    }

    public void setWindowMode(WindowMode windowMode) {
        if (this.mWindowMode != windowMode) {
            this.mWindowMode = windowMode;
            layoutWindow();
        }
    }

    public void setDialogGravity(int i) {
        this.mGravity = i;
    }

    public void setWindowYMargin(int i) {
        this.mYMargin = i;
    }

    public void layoutWindow() {
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            LayoutParams attributes = window.getAttributes();
            attributes.gravity = this.mGravity;
            this.mDisplayUtil.m3179b();
            if (this.mWindowMode != WindowMode.STANDARD) {
                attributes.width = Math.min(this.mMaxWidth, this.mDisplayUtil.m3182e());
            }
            if (this.mWindowMode == WindowMode.WRAP_ALL) {
                attributes.width = -2;
            }
            if (this.mWindowMode == WindowMode.SMALL || this.mWindowMode == WindowMode.MEDIUM) {
                float f = WINDOW_HEIGHT_RATIO_SMALL;
                if (this.mWindowMode == WindowMode.MEDIUM) {
                    f = this.mDisplayUtil.m3184g() ? WINDOW_HEIGHT_RATIO_MEDIUM_LANDSCAPE : WINDOW_HEIGHT_RATIO_MEDIUM;
                }
                this.mMaxHeight = (int) (f * ((float) this.mDisplayUtil.m3183f()));
            } else if (this.mWindowMode == WindowMode.LARGE) {
                this.mMaxHeight = getMinHeight(WindowMode.LARGE);
                this.mMaxWidth = getMinWidth(WindowMode.LARGE);
                attributes.width = this.mMaxWidth;
                attributes.height = -2;
            } else if (this.mWindowMode == WindowMode.WRAP || this.mWindowMode == WindowMode.WRAP_ALL) {
                attributes.height = -2;
            }
            if (this.mMaxHeight > 0) {
                this.mView.setMaxHeight(this.mMaxHeight);
            }
            if (this.mYMargin > 0) {
                attributes.y = this.mYMargin;
            }
            if (this.mWindowAnimation != 0) {
                window.setWindowAnimations(this.mWindowAnimation);
            }
            window.setAttributes(attributes);
        }
    }

    public int getMinHeight(WindowMode windowMode) {
        switch (C06724.f2935a[windowMode.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                if (this.mDisplayUtil.m3184g()) {
                }
                return (int) (((float) this.mDisplayUtil.m3183f()) * WINDOW_WIDTH_RATIO_LARGE_PORTRAIT);
            default:
                return 0;
        }
    }

    public int getMinWidth(WindowMode windowMode) {
        switch (C06724.f2935a[windowMode.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return (int) ((this.mDisplayUtil.m3184g() ? WINDOW_WIDTH_RATIO_LARGE_LANDSCAPE : WINDOW_WIDTH_RATIO_LARGE_PORTRAIT) * ((float) this.mDisplayUtil.m3182e()));
            default:
                return 0;
        }
    }

    public void setIsSpannableTitle(boolean z) {
        this.mIsSpannableTitle = z;
    }

    public void enableTouchInterceptPadding(int i) {
        if (getDialog() == null) {
            this.mPendingTouchInterceptPadding = i;
        } else {
            this.mView.post(new C06713(this, i));
        }
    }

    public void setDividerShown(boolean z) {
        this.mDivider.setVisibility(z ? 0 : 4);
    }
}
