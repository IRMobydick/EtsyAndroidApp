package com.etsy.android.ui.search.v2;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri.Builder;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.contentproviders.EtsyDatabaseUtil;
import com.etsy.android.contentproviders.SearchSuggestionCursorProvider;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.ui.search.SearchViewHelper;
import com.etsy.android.uikit.adapter.BaseRecyclerViewCursorAdapter;
import com.etsy.android.uikit.viewholder.ItemDividerDecoration;
import org.apache.commons.lang3.StringUtils;

public class SearchSuggestionsLayout extends LinearLayout implements OnQueryTextListener, ae {
    private static final String TAG;
    private TextView mHistoryClearBtn;
    private TextView mHistoryTitle;
    private SearchSuggestionsAdapter mRecentSearchesAdapter;
    private RecyclerView mRecyclerView;
    private SearchViewHelper mSearchViewHelper;

    /* renamed from: com.etsy.android.ui.search.v2.SearchSuggestionsLayout.1 */
    class C07921 implements OnClickListener {
        final /* synthetic */ SearchSuggestionsLayout f3299a;

        C07921(SearchSuggestionsLayout searchSuggestionsLayout) {
            this.f3299a = searchSuggestionsLayout;
        }

        public void onClick(View view) {
            EtsyDatabaseUtil.m751d(this.f3299a.getContext());
            this.f3299a.refreshUiState();
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.SearchSuggestionsLayout.2 */
    class C07932 implements SearchSuggestionCursorProvider {
        final /* synthetic */ CharSequence f3300a;
        final /* synthetic */ SearchSuggestionsLayout f3301b;

        C07932(SearchSuggestionsLayout searchSuggestionsLayout, CharSequence charSequence) {
            this.f3301b = searchSuggestionsLayout;
            this.f3300a = charSequence;
        }

        public void m4861a(Cursor cursor) {
            this.f3301b.mHistoryTitle.setVisibility(8);
            this.f3301b.mHistoryClearBtn.setVisibility(8);
            this.f3301b.mRecyclerView.setAdapter(new SearchSuggestionsAdapter(this.f3301b.getContext(), cursor, this.f3300a.toString(), this.f3301b));
        }
    }

    static {
        TAG = EtsyDebug.m1891a(SearchSuggestionsLayout.class);
    }

    public SearchSuggestionsLayout(Context context) {
        super(context);
        init(context);
    }

    public SearchSuggestionsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SearchSuggestionsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @TargetApi(21)
    public SearchSuggestionsLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void init(Context context) {
        setOrientation(1);
        LayoutInflater.from(context).inflate(2130903275, this, true);
        this.mHistoryTitle = (TextView) findViewById(2131755907);
        this.mHistoryClearBtn = (TextView) findViewById(2131755908);
        this.mHistoryClearBtn.setOnClickListener(new C07921(this));
        this.mRecyclerView = (RecyclerView) findViewById(2131755909);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.mRecyclerView.addItemDecoration(new ItemDividerDecoration(getResources().getDrawable(2130837855)));
    }

    private void resetRecentSearchesAdapter() {
        if (this.mRecentSearchesAdapter != null) {
            this.mRecentSearchesAdapter.closeCursor();
        }
        SearchableInfo searchableInfo = ((SearchManager) getContext().getSystemService("search")).getSearchableInfo(new ComponentName(getContext(), SearchV2Activity.class));
        Builder fragment = new Builder().scheme("content").authority(searchableInfo.getSuggestAuthority()).query(StringUtils.EMPTY).fragment(StringUtils.EMPTY);
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        if (searchableInfo.getSuggestSelection() == null) {
            fragment.appendPath(StringUtils.EMPTY);
        }
        fragment.appendPath("search_suggest_query");
        this.mRecentSearchesAdapter = new SearchSuggestionsAdapter(getContext(), getContext().getContentResolver().query(fragment.build(), null, null, new String[]{StringUtils.EMPTY}, null), StringUtils.EMPTY, this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SearchSuggestionCursorProvider.m763a();
        if (this.mRecentSearchesAdapter != null) {
            this.mRecentSearchesAdapter.closeCursor();
        }
        if (this.mRecyclerView.getAdapter() != null && this.mRecyclerView.getAdapter() != this.mRecentSearchesAdapter) {
            ((BaseRecyclerViewCursorAdapter) this.mRecyclerView.getAdapter()).closeCursor();
        }
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public boolean onQueryTextChange(String str) {
        refreshUiState(str);
        return false;
    }

    public void onQuerySelected(String str) {
        if (this.mSearchViewHelper != null) {
            this.mSearchViewHelper.m4736a(str, true);
        }
    }

    public void setSearchViewHelper(SearchViewHelper searchViewHelper) {
        this.mSearchViewHelper = searchViewHelper;
    }

    public void refreshUiState() {
        if (this.mSearchViewHelper != null) {
            refreshUiState(this.mSearchViewHelper.m4733a());
        } else {
            EtsyDebug.m1916d(TAG, "No SearchViewHelper set for SearchSuggestionsLayout");
        }
    }

    private void refreshUiState(@Nullable CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            SearchSuggestionCursorProvider.m763a();
            if ((EtsyDatabaseUtil.m749c(getContext()) > 0 ? 1 : 0) != 0) {
                resetRecentSearchesAdapter();
                this.mHistoryTitle.setVisibility(0);
                this.mHistoryClearBtn.setVisibility(0);
                this.mRecyclerView.setAdapter(this.mRecentSearchesAdapter);
                setVisibility(0);
                return;
            }
            setVisibility(4);
            return;
        }
        setVisibility(0);
        SearchSuggestionCursorProvider.m764a(getContext(), charSequence.toString(), new C07932(this, charSequence));
    }
}
