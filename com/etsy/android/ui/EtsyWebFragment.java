package com.etsy.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.cart.CartEventTracker;
import com.etsy.android.ui.cart.CartFragment;
import com.etsy.android.ui.cart.CartUtil;
import com.etsy.android.ui.cart.googlewallet.GoogleWalletHelperBase;
import com.etsy.android.ui.cart.googlewallet.GoogleWalletWebViewHelper;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.webview.WebChromeProgressBarClient;
import com.etsy.android.uikit.webview.a;
import com.etsy.android.util.EtsyBuildHelper;
import com.google.android.gms.gcm.Task;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class EtsyWebFragment extends EtsyFragment {
    private Cart mCart;
    private View mErrorView;
    private GoogleWalletWebViewHelper mGoogleWalletWebViewHelper;
    private HashMap<String, String> mParameters;
    private int mRedirectId;
    private WebView mWebView;

    /* renamed from: com.etsy.android.ui.EtsyWebFragment.1 */
    class C05191 extends TrackingOnClickListener {
        final /* synthetic */ EtsyWebFragment f2095a;

        C05191(EtsyWebFragment etsyWebFragment) {
            this.f2095a = etsyWebFragment;
        }

        public void onViewClick(View view) {
            this.f2095a.loadWebView();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.mRedirectId = getArguments().getInt("redirect_id");
        this.mParameters = (HashMap) getArguments().getSerializable("parameters");
        this.mCart = (Cart) getArguments().getSerializable(CartFragment.CHECKED_OUT_CART);
        getActivity().getWindow().setSoftInputMode(16);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903253, null);
        this.mWebView = (WebView) inflate.findViewById(2131755869);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(2131755870);
        a.a(this.mWebView, new EtsyWebFragment(this, progressBar), new WebChromeProgressBarClient(progressBar));
        if (this.mGoogleWalletWebViewHelper != null) {
            this.mGoogleWalletWebViewHelper.m3831a(this.mWebView);
        }
        this.mErrorView = inflate.findViewById(R.no_internet);
        this.mErrorView.findViewById(R.btn_retry_internet).setOnClickListener(new C05191(this));
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        loadWebView();
    }

    private void loadWebView() {
        this.mWebView.setVisibility(0);
        this.mErrorView.setVisibility(8);
        this.mWebView.clearView();
        if (this.mParameters != null && this.mParameters.containsKey(ResponseConstants.URL)) {
            this.mWebView.loadUrl((String) this.mParameters.get(ResponseConstants.URL));
        } else if (this.mCart == null || this.mCart.getLastPaymentMethod() == null || !this.mCart.getLastPaymentMethod().isGoogleWallet() || !GoogleWalletHelperBase.m3806a(getAnalyticsContext())) {
            if (!(EtsyBuildHelper.m5709d() || this.mCart == null || this.mCart.getLastPaymentMethod() == null || !this.mCart.getLastPaymentMethod().isDirectCheckout())) {
                getActivity().getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD, AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
            }
            this.mWebView.postUrl(a.a(), a.a(this.mParameters, this.mRedirectId));
        } else {
            this.mWebView.postUrl(a.a(this.mCart), a.a(this.mParameters));
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mGoogleWalletWebViewHelper = ((EtsyWebActivity) activity).getGoogleWalletHelper();
    }

    public void onStop() {
        super.onStop();
        if (this.mRedirectId == 1) {
            CartUtil.m3893b();
            CartUtil.m3887a(this.mActivity.getApplicationContext(), getRequestQueue());
        }
    }

    public void onDestroy() {
        getActivity().getWindow().clearFlags(AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
        super.onDestroy();
        this.mGoogleWalletWebViewHelper = null;
    }

    public boolean handleBackPressed() {
        if (!this.mWebView.canGoBack()) {
            return super.handleBackPressed();
        }
        this.mWebView.goBack();
        return true;
    }

    public void handleGoogleWalletResult(int i, int i2, Intent intent) {
        switch (i) {
            case 1002:
                handleMaskedWalletChanged(i2, intent);
            case 1003:
                handleFullWalletLoad(i2, intent);
            default:
        }
    }

    private void handleFullWalletLoad(int i, Intent intent) {
        CartEventTracker.m3719a(getAnalyticsContext(), this.mCart, "cart_review", i);
        switch (i) {
            case StringUtils.INDEX_NOT_FOUND /*-1*/:
                this.mGoogleWalletWebViewHelper.m3835c(intent);
            default:
                this.mGoogleWalletWebViewHelper.m3807a(this.mGoogleWalletWebViewHelper.m3808b(intent), getActivity());
        }
    }

    private void handleMaskedWalletChanged(int i, Intent intent) {
        CartEventTracker.m3724b(getAnalyticsContext(), this.mCart, "cart_review", i);
        switch (i) {
            case StringUtils.INDEX_NOT_FOUND /*-1*/:
                this.mWebView.postUrl(a.a(this.mCart), a.a(GoogleWalletHelperBase.m3803a(GoogleWalletHelperBase.m3802a(intent))));
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mGoogleWalletWebViewHelper.m3830a();
            default:
        }
    }
}
