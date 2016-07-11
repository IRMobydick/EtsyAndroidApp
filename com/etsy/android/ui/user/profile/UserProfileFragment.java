package com.etsy.android.ui.user.profile;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.UserProfilePage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.adapters.UserProfileAdapter;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.util.TabletSupportUtil;
import java.util.List;
import org.parceler.Parcels;

public class UserProfileFragment extends BaseRecyclerViewListFragment<Pair<?, Integer>> {
    private static final String PROFILE_PAGE_DATA = "profile_page_data";
    private boolean mIsSignedIn;
    private EtsyId mUserId;

    /* renamed from: com.etsy.android.ui.user.profile.UserProfileFragment.1 */
    class C08901 implements EtsyJobResponse {
        final /* synthetic */ UserProfileFragment f3612a;

        C08901(UserProfileFragment userProfileFragment) {
            this.f3612a = userProfileFragment;
        }

        public void m5070a(int i, String str, EtsyResult etsyResult) {
            this.f3612a.mAdapter.clear();
            this.f3612a.stopLoad();
            this.f3612a.showErrorView();
        }
    }

    /* renamed from: com.etsy.android.ui.user.profile.UserProfileFragment.2 */
    class C08912 implements EtsyJobResponse {
        final /* synthetic */ UserProfileFragment f3613a;

        C08912(UserProfileFragment userProfileFragment) {
            this.f3613a = userProfileFragment;
        }

        public void m5071a(EtsyResult etsyResult) {
            this.f3613a.mAdapter.clear();
            this.f3613a.stopLoad();
            this.f3613a.showEmptyView();
        }
    }

    /* renamed from: com.etsy.android.ui.user.profile.UserProfileFragment.3 */
    class C08923 implements EtsyJobResponse<UserProfilePage> {
        final /* synthetic */ UserProfileFragment f3614a;

        C08923(UserProfileFragment userProfileFragment) {
            this.f3614a = userProfileFragment;
        }

        public void m5072a(List<UserProfilePage> list, int i, EtsyResult<UserProfilePage> etsyResult) {
            UserProfilePage userProfilePage = (UserProfilePage) list.get(0);
            this.f3614a.mAdapter.clear();
            ((UserProfileAdapter) this.f3614a.mAdapter).setData(userProfilePage);
            this.f3614a.stopLoad();
            this.f3614a.showListView();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mIsSignedIn = aj.m1101a().m1118d();
        this.mUserId = (EtsyId) getArguments().getSerializable(ResponseConstants.USER_ID);
        boolean z = !(this.mUserId == null || this.mUserId.hasId()) || (this.mIsSignedIn && SharedPreferencesUtility.m3126a(getActivity(), this.mUserId));
        this.mAdapter = new UserProfileAdapter(getActivity(), getImageBatch(), getAnalyticsContext(), z);
        setHasOptionsMenu(true);
    }

    public int getLayoutId() {
        return R.fragment_recyclerview_list;
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActivity().setTitle(R.user_profile);
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        UserProfileAdapter userProfileAdapter = (UserProfileAdapter) this.mAdapter;
        LayoutManager layoutManager = userProfileAdapter.getLayoutManager(getActivity());
        layoutManager.setSpanSizeLookup(userProfileAdapter.spanSizeLookup());
        this.mSwipeRefreshLayout.setColorSchemeResources(R.orange);
        this.mRecyclerView.setLayoutManager(layoutManager);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setAdapter(this.mAdapter);
        ItemAnimator itemAnimator = this.mRecyclerView.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        this.mRecyclerView.addItemDecoration(userProfileAdapter.getMarginDividerDecoration());
        if (bundle == null || !bundle.containsKey(PROFILE_PAGE_DATA)) {
            loadContent();
        } else {
            userProfileAdapter.setData((UserProfilePage) Parcels.m7495a(bundle.getParcelable(PROFILE_PAGE_DATA)));
        }
        return onCreateView;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Object data = ((UserProfileAdapter) this.mAdapter).getData();
        if (data != null) {
            bundle.putParcelable(PROFILE_PAGE_DATA, Parcels.m7494a(data));
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.menu_share);
        if (findItem != null) {
            findItem.setVisible(false);
        }
    }

    public void onLoadContent() {
        fetchPageInfo();
    }

    public void onPause() {
        super.onPause();
        configureListingStateReceiver(true);
    }

    public void onResume() {
        super.onResume();
        if (this.mIsSignedIn != aj.m1101a().m1118d()) {
            this.mIsSignedIn = !this.mIsSignedIn;
            this.mAdapter.clear();
            loadContent();
        }
        configureListingStateReceiver(false);
    }

    public void onDestroy() {
        super.onDestroy();
        configureListingStateReceiver(false);
    }

    private void configureListingStateReceiver(boolean z) {
        if (this.mAdapter != null) {
            UserProfileAdapter userProfileAdapter = (UserProfileAdapter) this.mAdapter;
            if (z) {
                LocalBroadcastManager.getInstance(getActivity()).registerReceiver(userProfileAdapter.getListingStateChangedReceiver(), new IntentFilter(EtsyAction.STATE_CHANGE.getAction()));
            } else {
                LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(userProfileAdapter.getListingStateChangedReceiver());
            }
        }
    }

    private void fetchPageInfo() {
        getRequestQueue().m1697a((Object) this, HttpRequestJobBuilder.m1712a(UserProfilePage.class, String.format(this.mIsSignedIn ? "/etsyapps/v3/bespoke/member/users/%s/profile-page" : "/etsyapps/v3/bespoke/public/users/%s/profile-page", new Object[]{this.mUserId})).m1744a("is_tablet", Boolean.toString(new TabletSupportUtil(getActivity()).m5621a())).m1743a(new C08923(this)).m1741a(new C08912(this)).m1742a(new C08901(this)).m1737a());
    }

    void stopLoad() {
        this.mSwipeRefreshLayout.setRefreshing(false);
        setLoading(false);
        setRefreshing(false);
    }
}
