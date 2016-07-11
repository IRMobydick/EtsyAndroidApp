package com.etsy.android.ui.homescreen;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.etsy.android.contentproviders.EtsyDatabaseUtil;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecentlyViewedListingsFragment extends LandingPageFragment implements LoaderCallbacks<Cursor>, BaseRecyclerViewAdapter<IBaseRecyclerViewElement> {
    private static final String KEY_RECENTLY_VIEWED_LISTINGS = "rv_listings";
    private static final int LOADER_RECENTLY_VIEWED = 1;
    private static final int MENU_OPTION_EDIT_MODE = 32;
    private static final int MENU_OPTION_EDIT_MODE_DONE = 48;
    private static final String TAG;
    private boolean mInEditMode;
    private boolean mLoaded;
    ArrayList<String> mRecentlyViewedListingIds;

    public RecentlyViewedListingsFragment() {
        this.mRecentlyViewedListingIds = new ArrayList();
        this.mLoaded = false;
        this.mInEditMode = false;
    }

    static {
        TAG = EtsyDebug.m1891a(RecentlyViewedListingsFragment.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        getAdapter().setItemRemovedListener(this);
        setHasOptionsMenu(true);
        if (bundle != null) {
            ArrayList stringArrayList = bundle.getStringArrayList(KEY_RECENTLY_VIEWED_LISTINGS);
            if (stringArrayList == null) {
                stringArrayList = this.mRecentlyViewedListingIds;
            }
            this.mRecentlyViewedListingIds = stringArrayList;
        }
        if (this.mRecentlyViewedListingIds.size() == 0) {
            this.mLoaded = false;
            getLoaderManager().initLoader(LOADER_RECENTLY_VIEWED, null, this);
        } else {
            resetAndLoadContent();
        }
        return onCreateView;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList(KEY_RECENTLY_VIEWED_LISTINGS, this.mRecentlyViewedListingIds);
    }

    public boolean canLoadContent() {
        return super.canLoadContent() && this.mRecentlyViewedListingIds.size() > 0;
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        switch (i) {
            case LOADER_RECENTLY_VIEWED /*1*/:
                return new CursorLoader(getActivity(), HistoryQuery.f3007a, null, null, null, "view_time DESC LIMIT 100");
            default:
                return null;
        }
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        this.mRecentlyViewedListingIds.clear();
        while (cursor.moveToNext()) {
            this.mRecentlyViewedListingIds.add(cursor.getString(LOADER_RECENTLY_VIEWED));
        }
        if (this.mRecentlyViewedListingIds.size() == 0) {
            setContentExhausted(true);
        }
        if (!this.mLoaded) {
            this.mLoaded = true;
            resetAndLoadContent();
        }
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    protected Map<String, String> getParams() {
        if (this.mPageLink.getRequestMethod() != 0) {
            return null;
        }
        return getRecentlyViewedListingIdsParam();
    }

    protected Map<String, String> getBodyParams() {
        if (this.mPageLink.getRequestMethod() != LOADER_RECENTLY_VIEWED) {
            return null;
        }
        return getRecentlyViewedListingIdsParam();
    }

    private Map<String, String> getRecentlyViewedListingIdsParam() {
        StringBuilder stringBuilder = new StringBuilder();
        Map hashMap = new HashMap();
        for (int i = 0; i < this.mRecentlyViewedListingIds.size(); i += LOADER_RECENTLY_VIEWED) {
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

    protected CharSequence getEmptyMessage() {
        return Html.fromHtml(getActivity().getString(R.history_empty_html));
    }

    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
        if (this.mInEditMode) {
            menu.add(0, MENU_OPTION_EDIT_MODE_DONE, 0, R.done);
        } else {
            menu.add(0, MENU_OPTION_EDIT_MODE, 0, R.edit_mode);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == MENU_OPTION_EDIT_MODE) {
            this.mInEditMode = true;
            getAdapter().setCanRemoveItems(this.mInEditMode);
            getAdapter().notifyDataSetChanged();
        } else if (menuItem.getItemId() == MENU_OPTION_EDIT_MODE_DONE) {
            this.mInEditMode = false;
            getAdapter().setCanRemoveItems(this.mInEditMode);
            getAdapter().notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void itemRemoved(IBaseRecyclerViewElement iBaseRecyclerViewElement) {
        if (iBaseRecyclerViewElement instanceof ListingCard) {
            String id = ((ListingCard) iBaseRecyclerViewElement).getListingId().getId();
            Toast.makeText(getActivity(), getString(R.listing_removed_from_history), 0).show();
            EtsyDatabaseUtil.m740a(getActivity(), id);
        }
    }
}
