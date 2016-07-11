package com.etsy.android.ui.search.v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.apiv3.SearchCategoryRedirectPage;
import com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.parceler.Parcels;

public class SearchCategoryPageRedirectFragment extends CardRecyclerViewBaseFragment {
    private static final String TAG;

    /* renamed from: com.etsy.android.ui.search.v2.SearchCategoryPageRedirectFragment.1 */
    class C07791 extends EtsyApiV3RequestJob<SearchCategoryRedirectPage> {
        final /* synthetic */ SearchCategoryPageRedirectFragment f3254a;

        C07791(SearchCategoryPageRedirectFragment searchCategoryPageRedirectFragment) {
            this.f3254a = searchCategoryPageRedirectFragment;
        }

        public void m4794a(@NonNull List<SearchCategoryRedirectPage> list, int i, @NonNull EtsyV3Result<SearchCategoryRedirectPage> etsyV3Result) {
            if (!list.isEmpty()) {
                Object obj = (SearchCategoryRedirectPage) list.get(0);
                FragmentTransaction beginTransaction;
                Fragment searchTaxonomyCategoryPageFragment;
                Bundle bundle;
                if (obj.isCategoryLandingPage()) {
                    beginTransaction = this.f3254a.getFragmentManager().beginTransaction();
                    searchTaxonomyCategoryPageFragment = new SearchTaxonomyCategoryPageFragment();
                    bundle = new Bundle();
                    bundle.putParcelable("SEARCH_CATEGORY_REDIRECT", Parcels.m7494a(obj));
                    searchTaxonomyCategoryPageFragment.setArguments(bundle);
                    beginTransaction.remove(this.f3254a);
                    beginTransaction.add(2131755339, searchTaxonomyCategoryPageFragment, SearchTaxonomyCategoryPageFragment.TAG);
                    beginTransaction.commit();
                    return;
                }
                beginTransaction = this.f3254a.getFragmentManager().beginTransaction();
                searchTaxonomyCategoryPageFragment = new SearchResultsListingsFragment();
                bundle = new Bundle();
                bundle.putParcelable("SEARCH_CATEGORY_REDIRECT", Parcels.m7494a(obj));
                searchTaxonomyCategoryPageFragment.setArguments(bundle);
                beginTransaction.remove(this.f3254a);
                beginTransaction.add(2131755339, searchTaxonomyCategoryPageFragment, SearchResultsListingsFragment.TAG);
                beginTransaction.commit();
            }
        }

        public void m4792a(int i, @Nullable String str, @NonNull EtsyV3Result<SearchCategoryRedirectPage> etsyV3Result) {
            this.f3254a.showErrorView();
        }
    }

    static {
        TAG = EtsyDebug.m1891a(SearchCategoryPageRedirectFragment.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        loadContent();
        return onCreateView;
    }

    protected void onLoadContent() {
        Bundle arguments = getArguments();
        if (arguments.containsKey("SEARCH_CATEGORY_REDIRECT") && arguments.containsKey("SEARCH_CATEGORY_REDIRECT_QUERY_PARAMS")) {
            HashMap hashMap = (HashMap) arguments.getSerializable("SEARCH_CATEGORY_REDIRECT_QUERY_PARAMS");
            EtsyApiV3Request a = EtsyApiV3Request.m1454a(SearchCategoryRedirectPage.class, String.format("/etsyapps/v3/bespoke/member/category-landing-redirect/%s", new Object[]{arguments.getString("SEARCH_CATEGORY_REDIRECT")}));
            a.m1387a((Map) hashMap);
            EtsyApiV3RequestJob a2 = EtsyApiV3RequestJob.m1464a((EtsyApiV3Request) a.m1393d());
            a2.m1423a(new C07791(this), (Fragment) this);
            getRequestQueue().m1696a((Object) this, a2.m1426c());
            return;
        }
        showErrorView();
    }
}
