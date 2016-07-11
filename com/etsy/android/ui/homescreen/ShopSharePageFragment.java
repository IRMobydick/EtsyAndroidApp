package com.etsy.android.ui.homescreen;

import android.content.res.Resources;
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
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ShopShareEventTracker;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.homescreen.LandingPageInfo;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.lib.models.shopshare.ShopShareActivityFeed;
import com.etsy.android.lib.models.shopshare.ShopShareCard;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.parceler.Parcels;

public class ShopSharePageFragment extends CardRecyclerViewBaseFragment {
    private static final int LIMIT = 12;
    private static final String TAG;
    private static final int TRIGGER_POSITION = 6;
    LandingPageInfo mPageLink;

    /* renamed from: com.etsy.android.ui.homescreen.ShopSharePageFragment.1 */
    class C07191 implements EtsyJobResponse {
        final /* synthetic */ ShopSharePageFragment f3003a;

        C07191(ShopSharePageFragment shopSharePageFragment) {
            this.f3003a = shopSharePageFragment;
        }

        public void m4272a(int i, String str, EtsyResult etsyResult) {
            ShopShareEventTracker.m2089a("feed.not_displayed", "shop_shares_feed");
            this.f3003a.showErrorView();
        }
    }

    /* renamed from: com.etsy.android.ui.homescreen.ShopSharePageFragment.2 */
    class C07202 implements EtsyJobResponse {
        final /* synthetic */ ShopSharePageFragment f3004a;

        C07202(ShopSharePageFragment shopSharePageFragment) {
            this.f3004a = shopSharePageFragment;
        }

        public void m4273a(EtsyResult etsyResult) {
            this.f3004a.showEmptyView();
        }
    }

    /* renamed from: com.etsy.android.ui.homescreen.ShopSharePageFragment.3 */
    class C07213 implements EtsyJobResponse<ShopShareActivityFeed> {
        final /* synthetic */ Map f3005a;
        final /* synthetic */ ShopSharePageFragment f3006b;

        C07213(ShopSharePageFragment shopSharePageFragment, Map map) {
            this.f3006b = shopSharePageFragment;
            this.f3005a = map;
        }

        public void m4274a(List<ShopShareActivityFeed> list, int i, EtsyResult<ShopShareActivityFeed> etsyResult) {
            EtsyDebug.m1912c(ShopSharePageFragment.TAG, "Success");
            if (((BaseActivity) this.f3006b.getActivity()) != null) {
                int i2;
                if (!this.f3005a.containsKey("offset_activity_id")) {
                    ShopShareEventTracker.m2089a(String.format("feed.displayed", new Object[0]), "shop_shares_feed");
                }
                this.f3006b.showContentView();
                CardViewFactoryRecyclerViewAdapter adapter = this.f3006b.getAdapter();
                boolean isRefreshing = this.f3006b.mSwipeRefreshLayout.isRefreshing();
                if (isRefreshing) {
                    adapter.clear();
                }
                this.f3006b.mSwipeRefreshLayout.setRefreshing(false);
                int itemCount = adapter.getItemCount();
                ShopShareActivityFeed shopShareActivityFeed = (ShopShareActivityFeed) list.get(0);
                List<ShopShareCard> shopShareCards = shopShareActivityFeed.getShopShareCards();
                for (ShopShareCard addItem : shopShareCards) {
                    adapter.addItem(addItem);
                }
                int size = shopShareCards.size();
                if (shopShareActivityFeed.hasMoreActivity()) {
                    i2 = size;
                } else {
                    MessageCard messageCard = new MessageCard();
                    Resources resources = this.f3006b.getResources();
                    messageCard.setTitle(resources.getString(R.shop_share_feed_end_title));
                    messageCard.setDescription(resources.getString(R.shop_share_feed_end_description));
                    messageCard.setLink("trending_items");
                    messageCard.setLinkTitle(resources.getString(R.shop_share_feed_end_link_title));
                    adapter.addItem(messageCard);
                    i2 = size + 1;
                    this.f3006b.setContentExhausted(true);
                }
                if (isRefreshing) {
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.notifyItemRangeInserted(itemCount, i2);
                }
                this.f3006b.onLoadSuccessWithOffsetPagination(shopShareCards.size(), ShopSharePageFragment.LIMIT);
            }
        }
    }

    static {
        TAG = EtsyDebug.m1891a(ShopSharePageFragment.class);
    }

    public int getLayoutId() {
        return 2130903509;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mPageLink = (LandingPageInfo) Parcels.m7495a(getArguments().getParcelable(ResponseConstants.PAGE_LINK));
        if (this.mPageLink != null) {
            getActivity().setTitle(this.mPageLink.getPageTitle());
            int spanCount = getSpanCount();
            LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
            this.mRecyclerView.setLayoutManager(gridLayoutManager);
            CardViewFactoryRecyclerViewAdapter adapter = getAdapter();
            adapter.setMaxSpanSize(spanCount);
            adapter.setPageInView("shop_shares_feed");
            gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
            if (bundle != null) {
                getAdapter().onRestoreInstanceState(bundle);
            }
            if (this.mAdapter.getItemCount() == 0) {
                resetAndLoadContent();
            }
            if (shouldShowOnboarding()) {
                showOnboarding();
            }
        }
        return onCreateView;
    }

    protected void onLoadContent() {
        Map hashMap = new HashMap();
        if (!isRefreshing()) {
            String offsetActivityId = getOffsetActivityId();
            if (!offsetActivityId.isEmpty()) {
                hashMap.put("offset_activity_id", offsetActivityId);
            }
        }
        hashMap.put("story_limit", Integer.toString(LIMIT));
        String a = getRequestQueue().m1697a((Object) this, HttpRequestJobBuilder.m1712a(ShopShareActivityFeed.class, this.mPageLink.getAPIPath()).m1746a(true).m1745a(hashMap).m1743a(new C07213(this, hashMap)).m1741a(new C07202(this)).m1742a(new C07191(this)).m1737a());
        if (a != null) {
            this.mRequestCachedKeysUrls.add(a);
        }
    }

    public void onScrolledToLoadTrigger() {
        ShopShareEventTracker.m2089a("feed.paginated", "shop_shares_feed");
        loadContent();
    }

    public int getLoadTriggerPosition() {
        return TRIGGER_POSITION;
    }

    protected void onLoadSuccessWithOffsetPagination(int i, int i2) {
        setLoading(false);
        if (isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            setRefreshing(false);
        }
        if (isEmpty()) {
            showEmptyView();
        } else {
            showListView();
        }
    }

    private String getOffsetActivityId() {
        ShopShareCard shopShareCard;
        ShopShareCard shopShareCard2 = new ShopShareCard();
        List<IBaseRecyclerViewElement> arrayList = new ArrayList(getAdapter().getItems());
        Collections.reverse(arrayList);
        for (IBaseRecyclerViewElement iBaseRecyclerViewElement : arrayList) {
            try {
                shopShareCard = (ShopShareCard) iBaseRecyclerViewElement;
                break;
            } catch (ClassCastException e) {
                EtsyDebug.m1900a(TAG, e);
            }
        }
        shopShareCard = shopShareCard2;
        return shopShareCard.getActivityId().getId();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getAdapter().onSaveInstanceState(bundle);
    }

    private boolean shouldShowOnboarding() {
        return this.mPageLink.getBooleanOption("show_onboarding") && SharedPreferencesUtility.m3127a(getActivity(), "shop-share-mobile-onboarding") && getFragmentManager().findFragmentByTag("shopShareOnboarding") == null;
    }

    private void showOnboarding() {
        Nav.m4682a(getActivity()).m4686d().m4427e();
    }
}
