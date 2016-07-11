package com.etsy.android.ui.homescreen;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class HomescreenFragment extends CardRecyclerViewBaseFragment {
    public static final String KEY_PAGE_ID = "page_id";
    private static final String KEY_RECENTLY_VIEWED_LISTINGS = "rv_listings";
    private static final int MAX_RECENTLY_VIEWED_LISTING_PARAMS = 12;
    public static final String NUM_TABS = "num_tabs";
    public static final int PAGE_ETSY_PICKS = 2;
    public static final int PAGE_RECOMMENDED = 1;
    public static final int PAGE_YOUR_ACTIVITY = 0;
    private static final String TAG;
    boolean mIsSignedIn;
    boolean mLoaded;
    private int mNumTabs;
    private int mPageId;
    private String mPageInView;
    private ArrayList<String> mRecentlyViewedListingIds;
    String[] mRequestPathsSignedIn;
    String[] mRequestPathsSignedOut;
    boolean mResumed;

    /* renamed from: com.etsy.android.ui.homescreen.HomescreenFragment.1 */
    class C07121 implements EtsyJobResponse {
        final /* synthetic */ HomescreenFragment f2995a;

        C07121(HomescreenFragment homescreenFragment) {
            this.f2995a = homescreenFragment;
        }

        public void m4265a(int i, String str, EtsyResult etsyResult) {
            this.f2995a.onLoadFailure();
            EtsyDebug.m1912c(HomescreenFragment.TAG, str);
        }
    }

    /* renamed from: com.etsy.android.ui.homescreen.HomescreenFragment.2 */
    class C07132 implements EtsyJobResponse {
        final /* synthetic */ HomescreenFragment f2996a;

        C07132(HomescreenFragment homescreenFragment) {
            this.f2996a = homescreenFragment;
        }

        public void m4266a(EtsyResult etsyResult) {
            this.f2996a.onLoadSuccessWithoutPagination();
            this.f2996a.mEmptyText.setText(this.f2996a.getEmptyMessage());
            EtsyDebug.m1912c(HomescreenFragment.TAG, "EMPTY");
        }
    }

    /* renamed from: com.etsy.android.ui.homescreen.HomescreenFragment.3 */
    class C07143 implements EtsyJobResponse<Page> {
        final /* synthetic */ HomescreenFragment f2997a;

        C07143(HomescreenFragment homescreenFragment) {
            this.f2997a = homescreenFragment;
        }

        public void m4267a(List<Page> list, int i, EtsyResult<Page> etsyResult) {
            boolean z = false;
            this.f2997a.showContentView();
            CardViewFactoryRecyclerViewAdapter adapter = this.f2997a.getAdapter();
            if (this.f2997a.mSwipeRefreshLayout != null) {
                if (this.f2997a.mSwipeRefreshLayout.isRefreshing()) {
                    adapter.clear();
                }
                this.f2997a.mSwipeRefreshLayout.setRefreshing(false);
            }
            Page page = (Page) etsyResult.m1056g().get(0);
            adapter.addPage(page);
            HomescreenFragment homescreenFragment = this.f2997a;
            if (adapter.getItemCount() > 0) {
                z = true;
            }
            homescreenFragment.mLoaded = z;
            this.f2997a.onLoadSuccessWithoutPagination();
            this.f2997a.showServerMessage(page.getMessageCard());
        }
    }

    /* renamed from: com.etsy.android.ui.homescreen.HomescreenFragment.4 */
    class C07154 extends TrackingOnClickListener {
        final /* synthetic */ MessageCard f2998a;
        final /* synthetic */ HomescreenFragment f2999b;

        C07154(HomescreenFragment homescreenFragment, MessageCard messageCard) {
            this.f2999b = homescreenFragment;
            this.f2998a = messageCard;
        }

        public void onViewClick(View view) {
            Nav.m4682a(this.f2999b.getActivity()).m4683a().m4514c(this.f2998a.getLink());
        }
    }

    /* renamed from: com.etsy.android.ui.homescreen.HomescreenFragment.5 */
    class C07165 extends TrackingOnClickListener {
        final /* synthetic */ HomescreenFragment f3000a;

        C07165(HomescreenFragment homescreenFragment) {
            this.f3000a = homescreenFragment;
        }

        public void onViewClick(View view) {
            this.f3000a.mEmptyView.setVisibility(8);
            ((EtsyActivityNavigator) Nav.m4682a(this.f3000a.getActivity()).m4683a().m3013a(this.f3000a)).m4495a(true);
        }
    }

    public HomescreenFragment() {
        this.mPageId = -1;
        this.mNumTabs = -1;
        this.mPageInView = StringUtils.EMPTY;
        this.mIsSignedIn = false;
        this.mResumed = false;
        this.mLoaded = false;
        this.mRequestPathsSignedIn = new String[]{"/etsyapps/v3/bespoke/member/homescreen/youractivity", "/etsyapps/v3/bespoke/member/homescreen/ourpicksforyou", "/etsyapps/v3/bespoke/member/homescreen/bestofetsy"};
        this.mRequestPathsSignedOut = new String[]{"/etsyapps/v3/bespoke/public/homescreen/youractivity", "/etsyapps/v3/bespoke/public/homescreen/ourpicksforyou", "/etsyapps/v3/bespoke/public/homescreen/bestofetsy"};
        this.mRecentlyViewedListingIds = new ArrayList();
    }

    static {
        TAG = EtsyDebug.m1891a(HomescreenFragment.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPageId = getArguments().getInt(KEY_PAGE_ID);
        this.mNumTabs = getArguments().getInt(NUM_TABS);
        this.mPageInView = getArguments().getString("TRACKING_NAME");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        CardViewFactoryRecyclerViewAdapter adapter = getAdapter();
        if (bundle != null) {
            this.mRecentlyViewedListingIds = bundle.getStringArrayList(KEY_RECENTLY_VIEWED_LISTINGS);
            boolean z = adapter.onRestoreInstanceState(bundle) && adapter.getItemCount() > 0;
            this.mLoaded = z;
        }
        int spanCount = getSpanCount();
        LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
        this.mRecyclerView.setLayoutManager(gridLayoutManager);
        adapter.setMaxSpanSize(spanCount);
        adapter.setPageInView(this.mPageInView);
        gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        this.mIsSignedIn = aj.m1101a().m1118d();
        return onCreateView;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putStringArrayList(KEY_RECENTLY_VIEWED_LISTINGS, this.mRecentlyViewedListingIds);
        getAdapter().onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    public void onResume() {
        super.onResume();
        this.mResumed = true;
        if (!this.mLoaded || aj.m1101a().m1118d() != this.mIsSignedIn) {
            this.mIsSignedIn = aj.m1101a().m1118d();
            forceRefresh(true);
        }
    }

    public void onPause() {
        super.onPause();
        this.mResumed = false;
    }

    public void forceRefresh(boolean z) {
        removeCachedResponses();
        if (this.mResumed && z) {
            this.mLoaded = false;
            getAdapter().clear();
            resetAndLoadContent();
        }
    }

    public void onRefresh() {
        this.mEmptyView.setVisibility(8);
        removeCachedResponses();
        super.onRefresh();
    }

    public void setRecentlyViewedListings(ArrayList<String> arrayList) {
        this.mRecentlyViewedListingIds = arrayList;
    }

    protected void onLoadContent() {
        if (this.mPageId == -1) {
            EtsyDebug.m1912c(TAG, "Page ID has not been set for this tab yet.");
            return;
        }
        String str = this.mRequestPathsSignedOut[this.mPageId];
        if (this.mIsSignedIn) {
            str = this.mRequestPathsSignedIn[this.mPageId];
        } else if (this.mPageId == PAGE_RECOMMENDED) {
            onLoadSuccessWithoutPagination();
            showSignInView();
            return;
        }
        HttpRequestJobBuilder a = HttpRequestJobBuilder.m1712a(Page.class, str).m1746a(true).m1745a(getRecentlyViewedListingIdsParam());
        String str2 = "is_tablet";
        if (getActivity().getResources().getBoolean(R.is_phone)) {
            str = "false";
        } else {
            str = "true";
        }
        str = getRequestQueue().m1697a((Object) this, a.m1744a(str2, str).m1743a(new C07143(this)).m1741a(new C07132(this)).m1742a(new C07121(this)).m1737a());
        if (str != null) {
            this.mRequestCachedKeysUrls.add(str);
        }
    }

    private Map<String, String> getRecentlyViewedListingIdsParam() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = this.mRecentlyViewedListingIds.size() > MAX_RECENTLY_VIEWED_LISTING_PARAMS ? MAX_RECENTLY_VIEWED_LISTING_PARAMS : this.mRecentlyViewedListingIds.size();
        Map hashMap = new HashMap();
        for (int i = 0; i < size; i += PAGE_RECOMMENDED) {
            stringBuilder.append((String) this.mRecentlyViewedListingIds.get(i));
            if (i < this.mRecentlyViewedListingIds.size() - 1) {
                stringBuilder.append(",");
            }
        }
        if (stringBuilder.toString().length() > 0) {
            hashMap.put(ResponseConstants.LISTING_IDS, stringBuilder.toString());
        }
        return hashMap;
    }

    void showServerMessage(MessageCard messageCard) {
        if (messageCard != null) {
            setServerMessage(messageCard);
            if (messageCard.getLink() == null) {
                this.mEmptyButton.setVisibility(8);
            } else {
                this.mEmptyButton.setVisibility(0);
                this.mEmptyButton.setText(messageCard.getLinkTitle());
                this.mEmptyButton.setOnClickListener(new C07154(this, messageCard));
            }
            showEmptyView();
        }
    }

    void showSignInView() {
        this.mEmptyText.setText(R.homescreen_empty_signed_out);
        this.mEmptyText.setVisibility(0);
        this.mEmptySubtext.setVisibility(8);
        this.mEmptyImage.setVisibility(0);
        this.mEmptyImage.setImageResource(2130837868);
        this.mEmptyButton.setVisibility(0);
        this.mEmptyButton.setText(R.get_started);
        this.mEmptyButton.setOnClickListener(new C07165(this));
        showEmptyView();
    }
}
