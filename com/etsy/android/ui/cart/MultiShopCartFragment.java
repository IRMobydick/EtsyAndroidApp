package com.etsy.android.ui.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.etsy.android.lib.core.http.body.FormBody;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.apiv3.cart.CartGroupAction;
import com.etsy.android.lib.models.apiv3.cart.CartPage;
import com.etsy.android.messaging.CartRefreshDelegate;
import com.etsy.android.ui.cart.viewholders.CartGroupBottomDecoration;
import com.etsy.android.uikit.EndlessRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;
import com.etsy.android.uikit.view.FullImageView;
import java.util.List;
import java.util.Map;

public class MultiShopCartFragment extends EndlessRecyclerViewListFragment<IBaseRecyclerViewElement> implements CartRefreshDelegate, aa {
    public static final String MOCK_DATA = "mock_data";
    private CartRefreshDelegate mCartRefreshDelegate;

    /* renamed from: com.etsy.android.ui.cart.MultiShopCartFragment.1 */
    class C05951 extends EtsyApiV3RequestJob<CartPage> {
        final /* synthetic */ MultiShopCartFragment f2381a;

        C05951(MultiShopCartFragment multiShopCartFragment) {
            this.f2381a = multiShopCartFragment;
        }

        public void m3697a(@NonNull List<CartPage> list, int i, @NonNull EtsyV3Result<CartPage> etsyV3Result) {
            CartPage cartPage = (CartPage) etsyV3Result.m1057h();
            MultiShopCartAdapter adapter = this.f2381a.getAdapter();
            if (this.f2381a.mSwipeRefreshLayout.isRefreshing()) {
                this.f2381a.mSwipeRefreshLayout.setRefreshing(false);
                adapter.clear();
            }
            adapter.addPage(cartPage);
            CartRefreshDelegate.sendBroadcast(this.f2381a.getActivity(), cartPage.getCartCount(), cartPage.getSavedCount(), false, 1);
            this.f2381a.onLoadSuccess();
        }

        public void m3695a(int i, @Nullable String str, @NonNull EtsyV3Result<CartPage> etsyV3Result) {
            this.f2381a.mSwipeRefreshLayout.setRefreshing(false);
            this.f2381a.showErrorView();
        }
    }

    /* renamed from: com.etsy.android.ui.cart.MultiShopCartFragment.2 */
    class C05962 extends EtsyApiV3RequestJob<CartPage> {
        final /* synthetic */ CartGroupAction f2382a;
        final /* synthetic */ int f2383b;
        final /* synthetic */ MultiShopCartFragment f2384c;

        C05962(MultiShopCartFragment multiShopCartFragment, CartGroupAction cartGroupAction, int i) {
            this.f2384c = multiShopCartFragment;
            this.f2382a = cartGroupAction;
            this.f2383b = i;
        }

        public void m3701a(@NonNull List<CartPage> list, int i, @NonNull EtsyV3Result<CartPage> etsyV3Result) {
            if (this.f2382a.getRefreshNeeded()) {
                CartPage cartPage = (CartPage) etsyV3Result.m1057h();
                this.f2384c.getAdapter().updatePage(this.f2383b, cartPage);
                this.f2384c.showActionLoading(false);
                if (cartPage != null) {
                    CartRefreshDelegate.sendBroadcast(this.f2384c.getActivity(), cartPage.getCartCount(), cartPage.getSavedCount(), true, 1);
                }
            }
        }

        public void m3699a(int i, @Nullable String str, @NonNull EtsyV3Result<CartPage> etsyV3Result) {
            Toast.makeText(this.f2384c.getActivity(), str, 1).show();
            this.f2384c.showActionLoading(false);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCartRefreshDelegate = new CartRefreshDelegate(getActivity(), this);
        BaseRecyclerViewAdapter multiShopCartAdapter = new MultiShopCartAdapter(getActivity(), getImageBatch(), getAnalyticsContext(), this);
        if (bundle != null) {
            multiShopCartAdapter.onRestoreInstanceState(bundle);
        }
        this.mAdapter = multiShopCartAdapter;
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mRecyclerView.addItemDecoration(new CartGroupBottomDecoration(getContext()));
        return onCreateView;
    }

    public void onResume() {
        super.onResume();
        this.mCartRefreshDelegate.onResume();
        if (this.mAdapter.getItemCount() == 0) {
            onRefresh();
        }
    }

    public void onPause() {
        super.onPause();
        this.mCartRefreshDelegate.onPause();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        MultiShopCartAdapter multiShopCartAdapter = (MultiShopCartAdapter) this.mAdapter;
        if (multiShopCartAdapter != null) {
            multiShopCartAdapter.onSaveInstanceState(bundle);
        }
    }

    public void onRefresh() {
        this.mAdapter.clear();
        this.mEmptyView.setVisibility(8);
        resetAndLoadContent();
    }

    MultiShopCartAdapter getAdapter() {
        return (MultiShopCartAdapter) this.mAdapter;
    }

    protected void onLoadContent() {
        EtsyApiV3Request a = EtsyApiV3Request.m1454a(CartPage.class, getApiPath());
        if (getArguments().containsKey(MOCK_DATA)) {
            a.m1385a("mock", "true");
        }
        getRequestQueue().m1695a(EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) a.m1393d()).m1423a(new C05951(this), (Fragment) this).m1426c());
    }

    private void onLoadSuccess() {
        setLoading(false);
        if (isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            setRefreshing(false);
        }
        setContentExhausted(true);
        if (isEmpty()) {
            showEmptyView();
        } else {
            showListView();
        }
    }

    @NonNull
    public String getTrackingName() {
        return "multi_shop_cart";
    }

    public String getApiPath() {
        return "/etsyapps/v3/bespoke/member/carts";
    }

    public void performAction(int i, CartGroupAction cartGroupAction) {
        EtsyApiV3Request a = EtsyApiV3Request.m1454a(CartPage.class, cartGroupAction.getPath());
        a.m1384a(cartGroupAction.getRequestMethod());
        Map params = cartGroupAction.getParams();
        if (params != null) {
            if (cartGroupAction.getRequestMethod().equals(BaseHttpRequest.POST)) {
                a.m1383a(((FormBody) new FormBody().m1339a(params)).m1345f());
            } else {
                a.m1387a(params);
            }
        }
        getRequestQueue().m1695a(EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) a.m1393d()).m1423a(new C05962(this, cartGroupAction, i), (Fragment) this).m1426c());
    }

    protected void onRetry() {
        onRefresh();
    }

    public void showActionLoading(boolean z) {
        if (z) {
            displayLoadingView(true);
            this.mRecyclerView.setAlpha(0.5f);
            return;
        }
        displayLoadingView(false);
        this.mRecyclerView.setAlpha(FullImageView.ASPECT_RATIO_SQUARE);
    }

    public void onCartCountsUpdated(int i, int i2, boolean z, int i3) {
        if (z && i3 == 2) {
            onRefresh();
        }
    }
}
