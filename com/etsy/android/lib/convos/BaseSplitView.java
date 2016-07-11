package com.etsy.android.lib.convos;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;

@Deprecated
public class BaseSplitView extends FrameLayout {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final float SIDE_PANE_DEFAULT = 0.32f;
    private int mEmptyLayoutId;
    private View mEmptyView;
    private int mErrorLayoutId;
    private View mErrorView;
    private boolean mIsTwoPane;
    private LayoutInflater mLayoutInflater;
    private float mMainPaneWeight;
    private float mSidePaneWeight;

    /* renamed from: com.etsy.android.lib.convos.BaseSplitView.1 */
    class C04381 extends TrackingOnClickListener {
        final /* synthetic */ OnClickListener f1305a;
        final /* synthetic */ BaseSplitView f1306b;

        C04381(BaseSplitView baseSplitView, OnClickListener onClickListener) {
            this.f1306b = baseSplitView;
            this.f1305a = onClickListener;
        }

        public void onViewClick(View view) {
            this.f1306b.mErrorView.setVisibility(8);
            this.f1305a.onClick(view);
        }
    }

    static {
        $assertionsDisabled = !BaseSplitView.class.desiredAssertionStatus() ? true : $assertionsDisabled;
    }

    public BaseSplitView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public BaseSplitView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        this.mIsTwoPane = new TabletSupportUtil(getContext()).m5626f();
        this.mLayoutInflater = LayoutInflater.from(getContext());
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.BaseSplitView);
        if ($assertionsDisabled || obtainStyledAttributes != null) {
            this.mEmptyLayoutId = obtainStyledAttributes.getResourceId(R.BaseSplitView_empty_layout, 0);
            this.mErrorLayoutId = obtainStyledAttributes.getResourceId(R.BaseSplitView_no_internet_layout, 0);
            this.mSidePaneWeight = obtainStyledAttributes.getFraction(R.BaseSplitView_side_panel_fraction, 1, 1, SIDE_PANE_DEFAULT);
            this.mMainPaneWeight = FullImageView.ASPECT_RATIO_SQUARE - this.mSidePaneWeight;
            obtainStyledAttributes.recycle();
            View linearLayout;
            View frameLayout;
            if (this.mIsTwoPane) {
                linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(new LayoutParams(-1, -1));
                frameLayout = new FrameLayout(getContext());
                frameLayout.setId(R.fragment_container);
                frameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, -1, this.mSidePaneWeight));
                View frameLayout2 = new FrameLayout(getContext());
                frameLayout2.setId(R.fragment_detail_container);
                frameLayout2.setLayoutParams(new LinearLayout.LayoutParams(0, -1, this.mMainPaneWeight));
                linearLayout.addView(frameLayout);
                linearLayout.addView(frameLayout2);
                addView(linearLayout);
                setupEmptyView(frameLayout2);
                setupErrorView();
                return;
            }
            linearLayout = new FrameLayout(getContext());
            linearLayout.setId(R.fragment_container);
            linearLayout.setLayoutParams(new LayoutParams(-1, -1));
            frameLayout = new FrameLayout(getContext());
            frameLayout.setId(R.fragment_detail_container);
            frameLayout.setLayoutParams(new LayoutParams(0, 0));
            frameLayout.setVisibility(8);
            addView(frameLayout);
            addView(linearLayout);
            return;
        }
        throw new AssertionError();
    }

    private void setupEmptyView(FrameLayout frameLayout) {
        this.mEmptyView = this.mLayoutInflater.inflate(this.mEmptyLayoutId, this, $assertionsDisabled);
        frameLayout.addView(this.mEmptyView);
    }

    private void setupErrorView() {
        this.mErrorView = this.mLayoutInflater.inflate(this.mErrorLayoutId, this, $assertionsDisabled);
        addView(this.mErrorView);
    }

    public void setErrorViewRetryListener(OnClickListener onClickListener) {
        View findViewById = this.mErrorView.findViewById(R.btn_retry_internet);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C04381(this, onClickListener));
        }
    }

    public void showBase() {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
    }

    public void showErrorView() {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(0);
        }
    }

    public void showEmptyView(FragmentManager fragmentManager) {
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(0);
            Fragment findFragmentById = fragmentManager.findFragmentById(R.fragment_detail_container);
            if (findFragmentById != null) {
                fragmentManager.beginTransaction().remove(findFragmentById).commitAllowingStateLoss();
            }
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
    }

    public boolean isTwoPane() {
        return this.mIsTwoPane;
    }
}
