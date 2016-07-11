package com.etsy.android.ui.homescreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.ui.cardview.CardViewFactoryRecyclerViewAdapter;
import com.etsy.android.ui.cardview.CardViewHolderFactory;
import com.etsy.android.ui.nav.NotificationActivity;
import com.etsy.android.ui.util.EtsySocialShareUtil;
import com.etsy.android.uikit.EndlessRecyclerViewListFragment;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.foresee.sdk.configuration.MeasureConfiguration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CardRecyclerViewBaseFragment extends EndlessRecyclerViewListFragment<IBaseRecyclerViewElement> {
    private static final String SAVED_API_NEXT_LINK = "saved_api_next_link";
    private String mApiNextLink;
    private int mLoadTriggerPosition;
    protected boolean mRemoveCacheOnStateChange;
    protected ArrayList<String> mRequestCachedKeysUrls;
    BroadcastReceiver mStateChangeBroadCastReceiver;

    /* renamed from: com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment.1 */
    class C07071 extends TrackingOnClickListener {
        final /* synthetic */ CardRecyclerViewBaseFragment f2989a;

        C07071(CardRecyclerViewBaseFragment cardRecyclerViewBaseFragment) {
            this.f2989a = cardRecyclerViewBaseFragment;
        }

        public void onViewClick(View view) {
            this.f2989a.onRefresh();
        }
    }

    /* renamed from: com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment.2 */
    class C07082 extends BroadcastReceiver {
        final /* synthetic */ CardRecyclerViewBaseFragment f2990a;

        C07082(CardRecyclerViewBaseFragment cardRecyclerViewBaseFragment) {
            this.f2990a = cardRecyclerViewBaseFragment;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(EtsyAction.STATE_CHANGE.getAction())) {
                this.f2990a.removeCachedResponses();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment.3 */
    class C07093 implements Runnable {
        final /* synthetic */ CardRecyclerViewBaseFragment f2991a;

        C07093(CardRecyclerViewBaseFragment cardRecyclerViewBaseFragment) {
            this.f2991a = cardRecyclerViewBaseFragment;
        }

        public void run() {
            this.f2991a.getAdapter().addLoadingIndicator();
        }
    }

    /* renamed from: com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment.4 */
    class C07104 extends TrackingOnClickListener {
        final /* synthetic */ CardRecyclerViewBaseFragment f2992a;

        C07104(CardRecyclerViewBaseFragment cardRecyclerViewBaseFragment) {
            this.f2992a = cardRecyclerViewBaseFragment;
        }

        public void onViewClick(View view) {
            this.f2992a.onRefresh();
        }
    }

    /* renamed from: com.etsy.android.ui.homescreen.CardRecyclerViewBaseFragment.5 */
    class C07115 extends TrackingOnClickListener {
        final /* synthetic */ MessageCard f2993a;
        final /* synthetic */ CardRecyclerViewBaseFragment f2994b;

        C07115(CardRecyclerViewBaseFragment cardRecyclerViewBaseFragment, MessageCard messageCard) {
            this.f2994b = cardRecyclerViewBaseFragment;
            this.f2993a = messageCard;
        }

        public void onViewClick(View view) {
            Intent intent = new Intent(this.f2994b.getActivity(), NotificationActivity.class);
            intent.setData(Uri.parse(this.f2993a.getDeepLinkUrl()));
            this.f2994b.getActivity().startActivity(intent);
        }
    }

    public CardRecyclerViewBaseFragment() {
        this.mRequestCachedKeysUrls = new ArrayList();
        this.mRemoveCacheOnStateChange = true;
        this.mApiNextLink = null;
        this.mLoadTriggerPosition = MeasureConfiguration.DISABLED;
        this.mStateChangeBroadCastReceiver = new C07082(this);
    }

    protected void initAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new CardViewFactoryRecyclerViewAdapter(getActivity(), getImageBatch(), "RecyclerViewBaseFragment", getAnalyticsContext());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initAdapter();
        getAdapter().setScrollLoadTriggerListener(this);
        setColorSchemeColors(new int[]{getResources().getColor(R.brand_orange)});
        if (bundle != null) {
            this.mApiNextLink = bundle.getString(SAVED_API_NEXT_LINK, null);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mLoadingView = onCreateView.findViewById(R.loading_view);
        ItemAnimator itemAnimator = this.mRecyclerView.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        initEmptyView(onCreateView);
        return onCreateView;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mApiNextLink != null) {
            bundle.putString(SAVED_API_NEXT_LINK, this.mApiNextLink);
        }
    }

    protected CardViewFactoryRecyclerViewAdapter getAdapter() {
        return (CardViewFactoryRecyclerViewAdapter) this.mAdapter;
    }

    protected void initEmptyView(View view) {
        this.mEmptyView = view.findViewById(R.empty_view);
        this.mEmptyText = (TextView) view.findViewById(R.empty_view_text);
        this.mEmptySubtext = (TextView) view.findViewById(R.empty_view_subtext);
        this.mEmptyButton = (Button) view.findViewById(R.empty_button);
        this.mEmptyImage = (ImageView) view.findViewById(R.empty_image);
    }

    public int getLayoutId() {
        return R.fragment_recyclerview_list;
    }

    public void showErrorView() {
        if (this.mEmptyImage != null && this.mEmptyText != null && this.mEmptyButton != null) {
            showEmptyView();
            this.mEmptyImage.setImageResource(R.error_sorry_girl);
            this.mEmptyText.setText(R.whoops_somethings_wrong);
            this.mEmptyButton.setText(R.try_again);
            this.mEmptyButton.setVisibility(0);
            this.mEmptyButton.setOnClickListener(new C07071(this));
        }
    }

    public void showContentView() {
        if (this.mLoadingView != null && this.mRecyclerView != null && this.mEmptyView != null) {
            this.mLoadingView.setVisibility(8);
            this.mRecyclerView.setVisibility(0);
            this.mEmptyView.setVisibility(8);
        }
    }

    public void showEmptyView() {
        if (this.mLoadingView != null && this.mRecyclerView != null && this.mEmptyView != null) {
            this.mLoadingView.setVisibility(8);
            this.mRecyclerView.setVisibility(8);
            this.mEmptyView.setVisibility(0);
        }
    }

    public void showLoadingView() {
        if (this.mLoadingView != null && this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
            this.mLoadingView.setVisibility(0);
        }
    }

    protected CharSequence getEmptyMessage() {
        return getString(R.empty_default);
    }

    protected int getSpanCount() {
        if (!getActivity().getResources().getBoolean(R.is_phone)) {
            return 12;
        }
        if (getResources().getConfiguration().orientation == 2) {
            return 4;
        }
        return 2;
    }

    public void onStart() {
        super.onStart();
        if (this.mRemoveCacheOnStateChange) {
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mStateChangeBroadCastReceiver, new IntentFilter(EtsyAction.STATE_CHANGE.getAction()));
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mRemoveCacheOnStateChange) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mStateChangeBroadCastReceiver);
        }
    }

    public void onPause() {
        super.onPause();
        configureListingStateReceiver(true);
    }

    public void onResume() {
        super.onResume();
        configureListingStateReceiver(false);
    }

    public void onDestroy() {
        super.onDestroy();
        configureListingStateReceiver(false);
    }

    private void configureListingStateReceiver(boolean z) {
        if (this.mAdapter != null) {
            if (z) {
                LocalBroadcastManager.getInstance(getActivity()).registerReceiver(getAdapter().getStateBroadcastReceiver(), new IntentFilter(EtsyAction.STATE_CHANGE.getAction()));
            } else {
                LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(getAdapter().getStateBroadcastReceiver());
            }
        }
    }

    public void removeCachedResponses() {
        Iterator it = this.mRequestCachedKeysUrls.iterator();
        while (it.hasNext()) {
            getRequestQueue().m1701a((String) it.next());
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 601 || intent == null) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        ListingLike listingLike = (ListingLike) intent.getSerializableExtra(ActivityFeedEntity.LISTING);
        ListingCollection listingCollection = (ListingCollection) intent.getSerializableExtra(Collection.TYPE_COLLECTION);
        if (listingLike != null && listingCollection != null && intent.getBooleanExtra("should_show_social_invites_prompt", false)) {
            EtsySocialShareUtil.m5158a(getActivity(), getAnalyticsContext(), listingCollection, listingLike);
        }
    }

    public void startEndless() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.post(new C07093(this));
        }
    }

    public void stopEndless() {
        getAdapter().removeLoadingIndicator();
    }

    public void showEndlessError() {
    }

    public void removeEndlessError() {
    }

    public void showListView() {
        showContentView();
    }

    public void setLoading(boolean z) {
        super.setLoading(z);
        if (!z) {
            stopEndless();
        }
    }

    protected void onLoadSuccess(List list, int i) {
        throw new UnsupportedOperationException("Use the onLoadSuccess() methods defined in CardRecyclerViewBaseFragment instead of this base class method.");
    }

    protected void onLoadSuccessWithoutPagination() {
        onLoadSuccessWithNextLinkPagination(null);
    }

    protected void onLoadSuccessWithNextLinkPagination(String str) {
        setLoading(false);
        if (isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            setRefreshing(false);
        }
        this.mApiNextLink = str;
        if (TextUtils.isEmpty(this.mApiNextLink)) {
            setContentExhausted(true);
        } else {
            initLoadTrigger();
        }
        if (isEmpty()) {
            showEmptyView();
        } else {
            showListView();
        }
    }

    public int getLoadTriggerPosition() {
        return this.mLoadTriggerPosition;
    }

    protected void onLoadSuccessWithOffsetPagination(int i, int i2) {
        setLoading(false);
        if (isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            setRefreshing(false);
        }
        setApiOffset(getApiOffset() + i);
        if (getApiOffset() >= i2) {
            setContentExhausted(true);
        } else {
            initLoadTrigger();
        }
        if (isEmpty()) {
            showEmptyView();
        } else {
            showListView();
        }
    }

    private void initLoadTrigger() {
        if (this.mLoadTriggerPosition == MeasureConfiguration.DISABLED) {
            this.mLoadTriggerPosition = getAdapter().getItemCount() / 2;
        }
    }

    protected void resetAndLoadContent() {
        this.mApiNextLink = null;
        super.resetAndLoadContent();
    }

    public String getApiNextLink() {
        return this.mApiNextLink;
    }

    protected void setLayoutManager(Class<? extends LayoutManager> cls) {
        if (cls == StaggeredGridLayoutManager.class) {
            int spanCount = getSpanCount();
            if (!getActivity().getResources().getBoolean(R.is_phone)) {
                spanCount = 3;
            }
            this.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, 1));
            this.mRecyclerView.setHasFixedSize(false);
            CardViewFactoryRecyclerViewAdapter cardViewFactoryRecyclerViewAdapter = (CardViewFactoryRecyclerViewAdapter) this.mRecyclerView.getAdapter();
            if (cardViewFactoryRecyclerViewAdapter != null) {
                CardViewHolderFactory cardViewHolderFactory = (CardViewHolderFactory) cardViewFactoryRecyclerViewAdapter.getViewHolderFactory();
                if (cardViewHolderFactory != null) {
                    cardViewHolderFactory.m3654b(true);
                }
            }
        } else if (cls == GridLayoutManager.class) {
            LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), getSpanCount());
            this.mRecyclerView.setLayoutManager(gridLayoutManager);
            getAdapter().setMaxSpanSize(gridLayoutManager.getSpanCount());
            gridLayoutManager.setSpanSizeLookup(getAdapter().getSpanSizeLookup());
        } else {
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    protected void setServerMessage(MessageCard messageCard) {
        if (messageCard != null) {
            int image = messageCard.getImage();
            if (image == 1) {
                this.mEmptyImage.setVisibility(0);
                this.mEmptyImage.setImageResource(2130837866);
            } else if (image == 2) {
                this.mEmptyImage.setVisibility(0);
                this.mEmptyImage.setImageResource(R.empty_activity);
            } else if (image == 5) {
                this.mEmptyImage.setVisibility(0);
                this.mEmptyImage.setImageResource(R.empty_basket);
            } else if (image == 4) {
                this.mEmptyImage.setVisibility(0);
                this.mEmptyImage.setImageResource(R.empty_shelves);
            } else {
                this.mEmptyImage.setVisibility(8);
            }
            if (TextUtils.isEmpty(messageCard.getTitle())) {
                this.mEmptyText.setVisibility(8);
            } else {
                this.mEmptyText.setVisibility(0);
                this.mEmptyText.setText(messageCard.getTitle());
            }
            if (messageCard.getDescription() == null) {
                this.mEmptySubtext.setVisibility(8);
            } else {
                this.mEmptySubtext.setVisibility(0);
                this.mEmptySubtext.setText(messageCard.getDescription());
            }
            if (messageCard.isTryAgain()) {
                this.mEmptyButton.setVisibility(0);
                this.mEmptyButton.setText(getString(R.try_again));
                this.mEmptyButton.setOnClickListener(new C07104(this));
            } else if (messageCard.getDeepLinkUrl() != null) {
                this.mEmptyButton.setVisibility(0);
                this.mEmptyButton.setText(messageCard.getLinkTitle());
                this.mEmptyButton.setOnClickListener(new C07115(this, messageCard));
            } else {
                this.mEmptyButton.setVisibility(8);
            }
        }
    }
}
