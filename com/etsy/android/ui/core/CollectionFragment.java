package com.etsy.android.ui.core;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.requests.FavoriteListingsRequest;
import com.etsy.android.lib.requests.ListingsRequest;
import com.etsy.android.lib.util.ac;
import com.etsy.android.ui.EtsyLoadingListFragment;
import com.etsy.android.ui.adapters.ListingRowGenerator;
import com.etsy.android.ui.adapters.MultiColumnListingAdapter;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.util.ab;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class CollectionFragment extends EtsyLoadingListFragment {
    private static final String TAG;
    private MultiColumnListingAdapter mAdapter;
    private Collection mCollection;
    private View mEditButton;
    private View mHeader;
    private boolean mIsPrivate;
    private boolean mIsRefresh;
    private int mMaxItemCount;
    private int mOffset;
    private EtsyJobResponse mOnEmptyHandler;
    private EtsyJobResponse<Listing> mOnSuccessHandler;

    /* renamed from: com.etsy.android.ui.core.CollectionFragment.1 */
    class C06471 extends TrackingOnClickListener {
        final /* synthetic */ CollectionFragment f2687a;

        C06471(CollectionFragment collectionFragment, ITrackedObject... iTrackedObjectArr) {
            this.f2687a = collectionFragment;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            Nav.m4681a(this.f2687a.mActivity).m4444a(600, this.f2687a).m4500b(this.f2687a.mCollection);
        }
    }

    /* renamed from: com.etsy.android.ui.core.CollectionFragment.2 */
    class C06482 implements ListingRowGenerator {
        final /* synthetic */ CollectionFragment f2688a;

        C06482(CollectionFragment collectionFragment) {
            this.f2688a = collectionFragment;
        }

        public void m3949a(Listing listing) {
            Nav.m4682a(this.f2688a.mActivity).m4683a().m4467a(listing.getListingId());
        }
    }

    /* renamed from: com.etsy.android.ui.core.CollectionFragment.3 */
    class C06493 implements EtsyJobResponse {
        final /* synthetic */ CollectionFragment f2689a;

        C06493(CollectionFragment collectionFragment) {
            this.f2689a = collectionFragment;
        }

        public void m3950a(EtsyResult etsyResult) {
            this.f2689a.showEmptyView();
        }
    }

    /* renamed from: com.etsy.android.ui.core.CollectionFragment.4 */
    class C06504 implements EtsyJobResponse<Listing> {
        final /* synthetic */ CollectionFragment f2690a;

        C06504(CollectionFragment collectionFragment) {
            this.f2690a = collectionFragment;
        }

        public void m3951a(List<Listing> list, int i, EtsyResult<Listing> etsyResult) {
            if (etsyResult == null || !etsyResult.m1049a() || etsyResult.m1056g() == null) {
                this.f2690a.handleError(etsyResult);
                return;
            }
            EventTracker.m2016a(this.f2690a.getAnalyticsContext(), etsyResult.m1056g(), this.f2690a.isYou() ? "your_favorites" : "profile_favorites", i);
            if (this.f2690a.mIsRefresh) {
                this.f2690a.mAdapter.clear();
            }
            this.f2690a.mAdapter.addAll(etsyResult.m1056g());
            this.f2690a.mAdapter.notifyDataSetChanged();
            this.f2690a.showViews(etsyResult);
        }
    }

    public CollectionFragment() {
        this.mOffset = 0;
        this.mIsPrivate = false;
        this.mIsRefresh = false;
        this.mOnEmptyHandler = new C06493(this);
        this.mOnSuccessHandler = new C06504(this);
    }

    static {
        TAG = CollectionFragment.class.getName();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        setPullToRefreshEnabled(true);
        this.mCollection = (Collection) getArguments().getSerializable(Collection.TYPE_COLLECTION);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mHeader = layoutInflater.inflate(2130903118, null);
        this.mEditButton = this.mHeader.findViewById(2131755451);
        if (this.mEditButton != null) {
            if (isYou()) {
                this.mEditButton.setVisibility(0);
                this.mEditButton.setOnClickListener(new C06471(this, this.mCollection));
            } else {
                this.mEditButton.setVisibility(8);
            }
        }
        this.mListView.addHeaderView(this.mHeader);
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setEmptyText((int) R.empty_favorites);
        if (this.mAdapter == null) {
            this.mAdapter = createAdapter();
            showLoadingView();
            onLoadMoreItems();
        } else if (ac.m3186a(this.mAdapter) == 0) {
            showEmptyView();
        } else if (this.mMaxItemCount > ac.m3186a(this.mAdapter)) {
            startEndless();
            showListView();
        } else {
            showListView();
        }
        this.mListView.setDivider(null);
        this.mListView.setPadding(0, 0, getResources().getDimensionPixelOffset(2131361960), 0);
        this.mAdapter.notifyDataSetChanged();
        setListAdapter(this.mAdapter);
    }

    public void onResume() {
        super.onResume();
        this.mActivity.supportInvalidateOptionsMenu();
        this.mActivity.setTitle(R.collection);
    }

    public void onFragmentResume() {
        super.onFragmentResume();
        this.mActivity.supportInvalidateOptionsMenu();
        this.mActivity.setTitle(R.collection);
    }

    private MultiColumnListingAdapter createAdapter() {
        MultiColumnListingAdapter multiColumnListingAdapter = new MultiColumnListingAdapter(this.mActivity, getImageBatch(), 2130903323, 2131558408);
        multiColumnListingAdapter.addExtraPaddingToTopRow(true);
        multiColumnListingAdapter.setOnListingClickListener(new C06482(this));
        return multiColumnListingAdapter;
    }

    private void setResult(int i, Collection collection) {
        Intent intent = this.mActivity.getIntent();
        intent.putExtra(Collection.TYPE_COLLECTION, collection);
        this.mActivity.setResult(i, intent);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 600 && intent != null) {
            Collection collection = (Collection) intent.getSerializableExtra(Collection.TYPE_COLLECTION);
            switch (i2) {
                case 611:
                    if (this.mCollection.equals(collection)) {
                        setResult(611, collection);
                        this.mActivity.finish();
                    }
                case 612:
                    if (this.mCollection.equals(collection)) {
                        setResult(612, collection);
                        this.mCollection.update(collection);
                        this.mActivity.supportInvalidateOptionsMenu();
                        fillHeader(this.mCollection);
                    }
                default:
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mAdapter.notifyDataSetChanged();
    }

    public void onLoadMoreItems() {
        loadFavorites(false);
    }

    protected void onPullToRefresh() {
        stopEndless();
        this.mOffset = 0;
        loadFavorites(true);
    }

    private void loadFavorites(boolean z) {
        this.mIsRefresh = z;
        getRequestQueue().m1697a((Object) this, EtsyJobBuilder.m1307a(ListingsRequest.findAllCollectionListings(this.mCollection.getKey())).m1325b(24).m1316a(this.mOffset).m1326b(ListingsRequest.LISTING_CARD_INCLUDES).m1322a(ListingsRequest.LISTING_CARD_FIELDS).m1321a(this.mOnSuccessHandler).m1319a(ab.m5090a(this)).m1320a(ab.m5091b(this)).m1324a());
    }

    protected void onRetryClicked() {
        showLoadingView();
        onLoadMoreItems();
    }

    private boolean isYou() {
        if (this.mCollection == null) {
            return false;
        }
        User creator = this.mCollection.getCreator();
        if (creator == null || !creator.getUserId().hasId()) {
            return false;
        }
        return aj.m1101a().m1126m().equals(creator.getUserId());
    }

    private void showViews(EtsyResult etsyResult) {
        stopPullToRefresh();
        this.mMaxItemCount = etsyResult.m1055f();
        this.mOffset += 24;
        if (ac.m3186a(this.mAdapter) == 0) {
            showEmptyView();
        } else if (ac.m3186a(this.mAdapter) >= this.mMaxItemCount) {
            showListView();
            stopEndless();
        } else {
            showListView();
            startEndless();
        }
    }

    private void handleError(EtsyResult etsyResult) {
        EtsyDebug.m1912c(TAG, "handleError");
        stopPullToRefresh();
        if (ac.m3186a(this.mAdapter) > 0) {
            showEndlessError();
        } else if (etsyResult == null || etsyResult.m1036q() != FavoriteListingsRequest.PRIVATE_ERROR_CODE) {
            showErrorView();
        } else {
            showPrivateView();
        }
    }

    public void showEmptyView() {
        if (isYou()) {
            showListView();
            return;
        }
        if (this.mIsPrivate) {
            setEmptyText((int) R.private_favorites);
        }
        super.showEmptyView();
    }

    public void showListView() {
        super.showListView();
        fillHeader(this.mCollection);
    }

    private void showPrivateView() {
        this.mIsPrivate = true;
        setEmptyText((int) R.private_favorites);
        showEmptyView();
    }

    private void fillHeader(Collection collection) {
        ((TextView) this.mHeader.findViewById(2131755450)).setText(collection.getName());
        this.mHeader.findViewById(2131755454).setVisibility(collection.isPrivate() ? 0 : 8);
        ((TextView) this.mHeader.findViewById(2131755453)).setText(getResources().getQuantityString(R.item_lowercase_quantity, collection.getListingsCount(), new Object[]{Integer.valueOf(collection.getListingsCount())}));
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.menu_share);
        if (findItem == null) {
            return;
        }
        if (this.mCollection == null || !this.mCollection.isPublic() || this.mCollection.getListingsCount() <= 0) {
            findItem.setVisible(false);
        } else {
            findItem.setVisible(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.menu_share /*2131755083*/:
                shareCollection();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private boolean shareCollection() {
        String str = StringUtils.EMPTY;
        if (this.mCollection.getRepresentativeListings().size() > 0) {
            str = ((Listing) this.mCollection.getRepresentativeListings().get(0)).getImage().getImageUrlForPixelWidth(((Integer) BaseModelImage.IMG_SIZE_340.first).intValue());
        }
        EtsyLogger.m1966a().m1988a("share_collection", ResponseConstants.SHARE, "collection_key", this.mCollection.getKey());
        String url = this.mCollection.getUrl();
        Nav.m4682a(getActivity()).m4683a().m4490a(getString(R.share_collection_subject), getString(R.share_collection_message) + " " + url, url, str);
        return true;
    }
}
