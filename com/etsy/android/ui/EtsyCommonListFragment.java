package com.etsy.android.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.bl;
import com.etsy.android.uikit.IEtsyCommonListFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;

@Deprecated
public abstract class EtsyCommonListFragment extends EtsyListFragment implements IEtsyCommonListFragment {
    private Button mBtnRetry;
    private BroadcastReceiver mCurrencyUpdateListener;
    private Button mEmptyButton;
    private ImageView mEmptyImage;
    private TextView mEmptySubtext;
    protected TextView mEmptyText;
    protected View mEmptyView;
    protected View mErrorView;
    private Animation mFadeAnimation;
    private boolean mIsEmptyViewShowing;
    private boolean mIsErrorViewShowing;
    private boolean mIsListViewShowing;
    private boolean mIsLoadingViewShowing;
    private final int mLayoutResourceId;
    protected ListView mListView;
    protected View mLoadingView;
    private OnClickListener mOnRetryClickListener;
    protected boolean mWillCurrencyRefresh;

    /* renamed from: com.etsy.android.ui.EtsyCommonListFragment.1 */
    class C05161 extends BroadcastReceiver {
        final /* synthetic */ EtsyCommonListFragment f2092a;

        C05161(EtsyCommonListFragment etsyCommonListFragment) {
            this.f2092a = etsyCommonListFragment;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.etsy.android.CURRENCY_UPDATED")) {
                this.f2092a.onRetryClicked();
                bl.m3365b(context, (int) R.currency_updating_preference);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.EtsyCommonListFragment.2 */
    class C05172 extends TrackingOnClickListener {
        final /* synthetic */ EtsyCommonListFragment f2093a;

        C05172(EtsyCommonListFragment etsyCommonListFragment) {
            this.f2093a = etsyCommonListFragment;
        }

        public void onViewClick(View view) {
            this.f2093a.onRetryClicked();
        }
    }

    public EtsyCommonListFragment(int i) {
        this.mWillCurrencyRefresh = false;
        this.mCurrencyUpdateListener = new C05161(this);
        this.mOnRetryClickListener = new C05172(this);
        this.mLayoutResourceId = i;
    }

    public EtsyCommonListFragment() {
        this.mWillCurrencyRefresh = false;
        this.mCurrencyUpdateListener = new C05161(this);
        this.mOnRetryClickListener = new C05172(this);
        this.mLayoutResourceId = 2130903209;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(this.mLayoutResourceId, null);
        onCreateListView(inflate);
        onCreateCommonViews(inflate);
        return inflate;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public void onStart() {
        super.onStart();
        if (this.mWillCurrencyRefresh) {
            LocalBroadcastManager.getInstance(this.mActivity).registerReceiver(this.mCurrencyUpdateListener, new IntentFilter("com.etsy.android.CURRENCY_UPDATED"));
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mWillCurrencyRefresh) {
            LocalBroadcastManager.getInstance(this.mActivity).unregisterReceiver(this.mCurrencyUpdateListener);
        }
    }

    protected void onCreateListView(View view) {
        this.mListView = (ListView) view.findViewById(16908298);
    }

    protected void onCreateCommonViews(View view) {
        this.mEmptyView = view.findViewById(R.empty_view);
        this.mEmptyText = (TextView) view.findViewById(R.empty_view_text);
        this.mEmptySubtext = (TextView) view.findViewById(R.empty_view_subtext);
        this.mEmptyButton = (Button) view.findViewById(R.empty_button);
        this.mEmptyImage = (ImageView) view.findViewById(R.empty_image);
        this.mErrorView = view.findViewById(R.no_internet);
        this.mBtnRetry = (Button) view.findViewById(R.btn_retry_internet);
        this.mBtnRetry.setOnClickListener(this.mOnRetryClickListener);
        this.mLoadingView = view.findViewById(R.loading_view);
        this.mFadeAnimation = AnimationUtils.loadAnimation(getActivity(), R.fade_in);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mIsListViewShowing) {
            showListView();
        } else if (this.mIsEmptyViewShowing) {
            showEmptyView();
        } else if (this.mIsLoadingViewShowing) {
            showLoadingView();
        } else if (this.mIsErrorViewShowing) {
            showErrorView();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mIsListViewShowing = false;
        this.mIsEmptyViewShowing = false;
        this.mIsLoadingViewShowing = false;
        this.mIsErrorViewShowing = false;
        if (this.mListView != null && this.mListView.getVisibility() == 0) {
            this.mIsListViewShowing = true;
            this.mIsEmptyViewShowing = false;
            this.mIsLoadingViewShowing = false;
            this.mIsErrorViewShowing = false;
        } else if (this.mEmptyView != null && this.mEmptyView.getVisibility() == 0) {
            this.mIsListViewShowing = false;
            this.mIsEmptyViewShowing = true;
            this.mIsLoadingViewShowing = false;
            this.mIsErrorViewShowing = false;
        } else if (this.mLoadingView != null && this.mLoadingView.getVisibility() == 0) {
            this.mIsListViewShowing = false;
            this.mIsEmptyViewShowing = false;
            this.mIsLoadingViewShowing = true;
            this.mIsErrorViewShowing = false;
        } else if (this.mErrorView != null && this.mErrorView.getVisibility() == 0) {
            this.mIsListViewShowing = false;
            this.mIsEmptyViewShowing = false;
            this.mIsLoadingViewShowing = false;
            this.mIsErrorViewShowing = true;
        }
    }

    protected void onRetryClicked() {
    }

    protected Animation getFadeAnimation() {
        return this.mFadeAnimation;
    }

    public void showListView() {
        if (this.mListView.getVisibility() != 0) {
            this.mListView.startAnimation(this.mFadeAnimation);
        }
        this.mListView.setVisibility(0);
        this.mErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
        this.mEmptyView.setVisibility(8);
    }

    public void showLoadingView() {
        this.mListView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mEmptyView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
    }

    public void showEmptyView() {
        this.mListView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
        this.mEmptyView.setVisibility(0);
    }

    public void showErrorView() {
        this.mListView.setVisibility(8);
        this.mEmptyView.setVisibility(8);
        this.mErrorView.setVisibility(0);
        this.mLoadingView.setVisibility(8);
    }

    public void hideAllViews() {
        this.mListView.setVisibility(8);
        this.mEmptyView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
    }

    public void hideRetryButton() {
        this.mBtnRetry.setVisibility(8);
    }

    public void setEmptyText(int i) {
        this.mEmptyText.setText(i);
    }

    public void setEmptyText(CharSequence charSequence) {
        this.mEmptyText.setText(charSequence);
    }

    public void setEmptySubtext(int i) {
        this.mEmptySubtext.setVisibility(0);
        this.mEmptySubtext.setText(i);
    }

    public void setEmptyImage(int i) {
        this.mEmptyImage.setImageResource(i);
    }

    public void setEmptySubtextVisibility(boolean z) {
        this.mEmptySubtext.setVisibility(z ? 0 : 8);
    }

    public void setEmptyButtonTextAndClick(int i, OnClickListener onClickListener) {
        this.mEmptyButton.setVisibility(0);
        this.mEmptyButton.setText(i);
        this.mEmptyButton.setOnClickListener(onClickListener);
    }

    public void setEmptyButtonVisibility(boolean z) {
        this.mEmptyButton.setVisibility(z ? 0 : 8);
    }

    public void setEmptyImageVisibility(boolean z) {
        this.mEmptyImage.setVisibility(z ? 0 : 8);
    }

    public View getEmptyView() {
        return this.mEmptyView;
    }
}
