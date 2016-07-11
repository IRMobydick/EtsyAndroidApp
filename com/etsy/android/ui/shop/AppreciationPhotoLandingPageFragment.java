package com.etsy.android.ui.shop;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.AppreciationPhotoFeature;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.adapters.AppreciationPhotoLandingPageAdapter;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.FavoriteUtil;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;
import org.parceler.Parcels;

public class AppreciationPhotoLandingPageFragment extends EtsyFragment {
    private static final String AP_LANDING_DATA = "ap_landing_data";
    private AppreciationPhotoLandingPageAdapter mAdapter;
    private AppreciationPhotoCardHelper mCardHelper;
    private AppreciationPhotoFeature mData;
    private View mErrorView;
    private FavoriteUtil mFavUtil;
    private boolean mIsSignedIn;
    private View mLoadingView;
    private RecyclerView mRecyclerView;
    private View mRetryButton;

    /* renamed from: com.etsy.android.ui.shop.AppreciationPhotoLandingPageFragment.1 */
    class C08011 extends GridLayoutManager {
        final /* synthetic */ AppreciationPhotoLandingPageFragment f3381a;

        C08011(AppreciationPhotoLandingPageFragment appreciationPhotoLandingPageFragment, Context context, int i) {
            this.f3381a = appreciationPhotoLandingPageFragment;
            super(context, i);
        }

        protected int getExtraLayoutSpace(State state) {
            return Callback.DEFAULT_DRAG_ANIMATION_DURATION;
        }
    }

    /* renamed from: com.etsy.android.ui.shop.AppreciationPhotoLandingPageFragment.2 */
    class C08022 extends TrackingOnClickListener {
        final /* synthetic */ AppreciationPhotoLandingPageFragment f3382a;

        C08022(AppreciationPhotoLandingPageFragment appreciationPhotoLandingPageFragment) {
            this.f3382a = appreciationPhotoLandingPageFragment;
        }

        public void onViewClick(View view) {
            this.f3382a.fetchData();
        }
    }

    /* renamed from: com.etsy.android.ui.shop.AppreciationPhotoLandingPageFragment.3 */
    class C08033 implements EtsyJobResponse<AppreciationPhotoFeature> {
        final /* synthetic */ AppreciationPhotoLandingPageFragment f3383a;

        C08033(AppreciationPhotoLandingPageFragment appreciationPhotoLandingPageFragment) {
            this.f3383a = appreciationPhotoLandingPageFragment;
        }

        public void m4961a(List<AppreciationPhotoFeature> list, int i, EtsyResult<AppreciationPhotoFeature> etsyResult) {
            if (this.f3383a.getActivity() != null && this.f3383a.mAdapter != null) {
                this.f3383a.mData = (AppreciationPhotoFeature) list.get(0);
                this.f3383a.mAdapter.setData(this.f3383a.mData);
                this.f3383a.showDataView();
                this.f3383a.getActivity().invalidateOptionsMenu();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.shop.AppreciationPhotoLandingPageFragment.4 */
    class C08044 implements EtsyJobResponse {
        final /* synthetic */ AppreciationPhotoLandingPageFragment f3384a;

        C08044(AppreciationPhotoLandingPageFragment appreciationPhotoLandingPageFragment) {
            this.f3384a = appreciationPhotoLandingPageFragment;
        }

        public void m4962a(int i, String str, EtsyResult etsyResult) {
            this.f3384a.showErrorView();
        }
    }

    /* renamed from: com.etsy.android.ui.shop.AppreciationPhotoLandingPageFragment.5 */
    class C08055 implements EtsyJobResponse {
        final /* synthetic */ AppreciationPhotoLandingPageFragment f3385a;

        C08055(AppreciationPhotoLandingPageFragment appreciationPhotoLandingPageFragment) {
            this.f3385a = appreciationPhotoLandingPageFragment;
        }

        public void m4963a(EtsyResult etsyResult) {
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        View inflate = layoutInflater.inflate(2130903187, viewGroup, false);
        this.mCardHelper = new AppreciationPhotoCardHelper();
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.recycler_view);
        this.mLoadingView = inflate.findViewById(R.loading_view);
        this.mErrorView = inflate.findViewById(R.no_internet);
        this.mRetryButton = this.mErrorView.findViewById(R.btn_retry_internet);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        int integer = getResources().getInteger(2131558400);
        LayoutManager c08011 = new C08011(this, getActivity(), integer);
        this.mAdapter = new AppreciationPhotoLandingPageAdapter(getActivity(), this.mCardHelper, getImageBatch(), getAnalyticsContext(), integer);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(this.mAdapter.getDecoration());
        c08011.setSpanSizeLookup(this.mAdapter.spanSizeLookup());
        this.mRecyclerView.setLayoutManager(c08011);
        this.mFavUtil = new FavoriteUtil(getActivity(), this, "appreciation_photo_page", getAnalyticsContext());
        this.mCardHelper.m4974a(this, this.mFavUtil);
        this.mRetryButton.setOnClickListener(new C08022(this));
        this.mIsSignedIn = aj.m1101a().m1118d();
        if (bundle == null || !bundle.containsKey(AP_LANDING_DATA)) {
            fetchData();
        } else {
            this.mData = (AppreciationPhotoFeature) Parcels.m7495a(bundle.getParcelable(AP_LANDING_DATA));
            this.mAdapter.setData(this.mData);
        }
        setHasOptionsMenu(true);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mData != null) {
            bundle.putParcelable(AP_LANDING_DATA, Parcels.m7494a(this.mData));
        }
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
            fetchData();
        }
        configureListingStateReceiver(false);
    }

    public void onDestroy() {
        super.onDestroy();
        configureListingStateReceiver(false);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.menu_share);
        if (findItem == null) {
            return;
        }
        if (this.mData != null) {
            findItem.setVisible(true);
        } else {
            findItem.setVisible(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.menu_share) {
            return super.onOptionsItemSelected(menuItem);
        }
        SocialShareUtil.m5156a("appreciation_photo_page", ShareType.APPRECIATION_PHOTO, getActivity().getLocalClassName());
        Nav.m4681a(this.mActivity).m4479a(this.mData);
        return true;
    }

    private void configureListingStateReceiver(boolean z) {
        if (this.mAdapter != null) {
            if (z) {
                LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mAdapter.getStateBroadcastReceiver(), new IntentFilter(EtsyAction.STATE_CHANGE.getAction()));
            } else {
                LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mAdapter.getStateBroadcastReceiver());
            }
        }
    }

    private void showErrorView() {
        this.mRecyclerView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
        this.mErrorView.setVisibility(0);
    }

    private void showLoadingView() {
        this.mRecyclerView.setVisibility(8);
        this.mErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
    }

    private void showDataView() {
        this.mErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(8);
        this.mRecyclerView.setVisibility(0);
    }

    private void fetchData() {
        showLoadingView();
        getRequestQueue().m1699a(getLandingPageData((EtsyId) getArguments().getSerializable(ResponseConstants.TRANSACTION_ID)));
    }

    private EtsyRequestJob getLandingPageData(EtsyId etsyId) {
        return HttpRequestJobBuilder.m1712a(AppreciationPhotoFeature.class, (this.mIsSignedIn ? "/etsyapps/v3/bespoke/member/transaction/" : "/etsyapps/v3/bespoke/public/transaction/") + etsyId + "/appreciation-photo").m1741a(new C08055(this)).m1742a(new C08044(this)).m1743a(new C08033(this)).m1737a();
    }
}
