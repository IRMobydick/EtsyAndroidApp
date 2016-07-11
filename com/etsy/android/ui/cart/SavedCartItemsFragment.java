package com.etsy.android.ui.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.messaging.CartRefreshDelegate;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.ui.cardview.p014a.SavedCartClickHandler;
import com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment;
import java.util.List;
import org.parceler.Parcels;

public class SavedCartItemsFragment extends CardRecyclerViewBaseFragment implements CartRefreshDelegate {
    public static final String NEXT_LINK = "next_link";
    public static final String PAGE = "page";
    CartRefreshDelegate mCartRefreshDelegate;
    SavedCartClickHandler mSavedCartClickListener;

    /* renamed from: com.etsy.android.ui.cart.SavedCartItemsFragment.1 */
    class C06021 extends EtsyApiV3RequestJob<Page> {
        final /* synthetic */ SavedCartItemsFragment f2390a;

        C06021(SavedCartItemsFragment savedCartItemsFragment) {
            this.f2390a = savedCartItemsFragment;
        }

        public void m3709a(@NonNull List<Page> list, int i, @NonNull EtsyV3Result<Page> etsyV3Result) {
            this.f2390a.populateOnSuccess((Page) list.get(0), etsyV3Result.m1070p());
        }

        public void m3707a(int i, @Nullable String str, @NonNull EtsyV3Result<Page> etsyV3Result) {
            this.f2390a.showErrorView();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mCartRefreshDelegate = new CartRefreshDelegate(getActivity(), this);
        this.mSavedCartClickListener = new SavedCartClickHandler("cart_saved_view", getActivity(), getAdapter(), getAnalyticsContext());
        getAdapter().getViewHolderFactory().m3644a(39, this.mSavedCartClickListener);
        setLayoutManager(GridLayoutManager.class);
        return onCreateView;
    }

    public void onResume() {
        super.onResume();
        this.mCartRefreshDelegate.onResume();
        if (getAdapter().getItemCount() == 0) {
            onRefresh();
        }
    }

    public void onPause() {
        super.onPause();
        this.mCartRefreshDelegate.onPause();
    }

    protected void onLoadContent() {
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.containsKey(PAGE)) {
            getRequestQueue().m1695a(EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) EtsyApiV3Request.m1454a(Page.class, getApiPath()).m1393d()).m1423a(new C06021(this), (Fragment) this).m1426c());
            return;
        }
        populateOnSuccess((Page) Parcels.m7495a(arguments.getParcelable(PAGE)), arguments.getString(NEXT_LINK));
        getArguments().remove(PAGE);
        getArguments().remove(NEXT_LINK);
    }

    private String getApiPath() {
        String apiNextLink = getApiNextLink();
        if (TextUtils.isEmpty(apiNextLink)) {
            return "/etsyapps/v3/bespoke/member/carts/saved-for-later";
        }
        return apiNextLink;
    }

    protected void populateOnSuccess(Page page, String str) {
        CardViewFactoryRecyclerViewAdapter adapter = getAdapter();
        boolean isRefreshing = this.mSwipeRefreshLayout.isRefreshing();
        if (isRefreshing) {
            adapter.clear();
        }
        this.mSwipeRefreshLayout.setRefreshing(false);
        MessageCard messageCard = page.getMessageCard();
        if (messageCard != null) {
            setServerMessage(messageCard);
        } else {
            int itemCount = adapter.getItemCount();
            adapter.addPage(page);
            int itemCount2 = adapter.getItemCount() - itemCount;
            if (isRefreshing) {
                adapter.notifyDataSetChanged();
            } else {
                adapter.notifyItemRangeInserted(itemCount, itemCount2);
            }
        }
        onLoadSuccessWithNextLinkPagination(str);
    }

    public void onRefresh() {
        getAdapter().clear();
        this.mEmptyView.setVisibility(8);
        resetAndLoadContent();
    }

    @NonNull
    public String getTrackingName() {
        return "cart_saved_view";
    }

    public void onCartCountsUpdated(int i, int i2, boolean z, int i3) {
        if (!z) {
            return;
        }
        if (i3 != 2) {
            onRefresh();
        } else if (i3 != 2) {
        } else {
            if (i2 == 0) {
                onRefresh();
            } else if (!TextUtils.isEmpty(getApiNextLink())) {
                onLoadContent();
            }
        }
    }
}
