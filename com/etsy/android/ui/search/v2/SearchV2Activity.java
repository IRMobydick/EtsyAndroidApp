package com.etsy.android.ui.search.v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.etsy.android.contentproviders.EtsyDatabaseUtil;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.EtsyFragmentNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.search.SearchViewHelper;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

public class SearchV2Activity extends BOENavDrawerActivity implements SearchViewHelper {
    static final int MODE_RESULTS = 2;
    static final int MODE_SUGGEST = 1;
    static final int MODE_TAXONOMY = 0;
    private static final String SAVED_MODE = "mode";
    @Nullable
    String mAnchorListingId;
    SearchSuggestionsPager mInputLayout;
    private final ao mListeners;
    private boolean mSearchInitiatedFromWithinApp;
    SearchViewHelper mSearchViewHelper;
    TabLayout mTabLayout;
    int mViewMode;

    public SearchV2Activity() {
        this.mSearchInitiatedFromWithinApp = false;
        this.mAnchorListingId = null;
        this.mListeners = new ao(this);
    }

    public static void addSearchResultsFragment(FragmentActivity fragmentActivity, String str, TaxonomyNode taxonomyNode) {
        Nav.m4682a(fragmentActivity).m4684b().m4622a(2131755339).m4629a(str, null, null, taxonomyNode);
    }

    public static void addCategoryPageFragment(FragmentActivity fragmentActivity, TaxonomyNode taxonomyNode, @Nullable String str) {
        EtsyFragmentNavigator a = Nav.m4682a(fragmentActivity).m4684b().m4622a(2131755339);
        if (taxonomyNode != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("SEARCH_TAXONOMY_NODE", Parcels.m7494a((Object) taxonomyNode));
            bundle.putString("ANCHOR_LISTING_ID", str);
            a.m4647f(bundle);
            return;
        }
        a.m4673y();
    }

    private void addSearchRedirectFragment(Bundle bundle) {
        Nav.m4682a((FragmentActivity) this).m4684b().m4622a(2131755339).m4650g(bundle);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903078);
        this.mTabLayout = getAppBarHelper().addTabLayout();
        this.mInputLayout = (SearchSuggestionsPager) findViewById(2131755340);
        this.mInputLayout.setupWithTabLayout(this.mTabLayout);
        this.mSearchViewHelper = new SearchViewHelper(this).m4743a(SearchV2Activity.class).m4739a().m4745b().m4744a(true).m4746b(-1).m4740a((int) R.search_hint).m4741a(this.mListeners).m4742a(this.mListeners).m4747c();
        this.mInputLayout.setSearchViewHelperProvider(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this.mListeners);
        Intent intent = getIntent();
        if (bundle != null) {
            switch (bundle.getInt(SAVED_MODE)) {
                case MODE_TAXONOMY /*0*/:
                    enterTaxonomyMode();
                case MODE_SUGGEST /*1*/:
                    enterSuggestMode();
                case MODE_RESULTS /*2*/:
                    enterResultsMode();
                default:
            }
        } else if (intent != null && "android.intent.action.SEARCH".equals(intent.getAction())) {
            handleExternalSearch(intent);
        } else if (intent != null && intent.hasExtra("SEARCH_CATEGORY_REDIRECT")) {
            addSearchRedirectFragment(intent.getExtras());
            enterTaxonomyMode();
        } else if (intent == null || !intent.hasExtra("SEARCH_TAXONOMY_NODE")) {
            addCategoryPageFragment(this, null, null);
            enterTaxonomyMode();
        } else {
            TaxonomyNode taxonomyNode = (TaxonomyNode) Parcels.m7495a(intent.getParcelableExtra("SEARCH_TAXONOMY_NODE"));
            if (intent.hasExtra("ANCHOR_LISTING_ID")) {
                this.mAnchorListingId = intent.getStringExtra("ANCHOR_LISTING_ID");
            }
            if ("SEARCH_TYPE_CATEGORY".equals(intent.getStringExtra("SEARCH_TYPE"))) {
                addCategoryPageFragment(this, taxonomyNode, this.mAnchorListingId);
                enterTaxonomyMode();
                return;
            }
            performTaxonomySearch(taxonomyNode);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if ("android.intent.action.SEARCH".equals(intent.getAction())) {
            handleInternalSearch(intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (isTopLevelActivity()) {
            if (this.mViewMode != MODE_SUGGEST) {
                return super.onOptionsItemSelected(menuItem);
            }
            if (handleBackLocally() || popOrGoBack()) {
                return true;
            }
            return false;
        } else if (this.mSearchInitiatedFromWithinApp) {
            return popOrGoBack();
        } else {
            Nav.m4682a((FragmentActivity) this).m4677a(true);
            return true;
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(SAVED_MODE, this.mViewMode);
    }

    private void handleInternalSearch(Intent intent) {
        String a = this.mSearchViewHelper.m4734a(intent);
        int tabSelected = this.mInputLayout.getTabSelected();
        intent.getStringExtra("SEARCH_TYPE");
        this.mSearchInitiatedFromWithinApp = getIntent().getBooleanExtra("SEARCH_INITIATED_FROM_WITHIN_APP", false);
        performTabSearch(a, tabSelected);
    }

    private void handleExternalSearch(Intent intent) {
        String a = this.mSearchViewHelper.m4734a(intent);
        MutableSearchOptions b = this.mSearchViewHelper.m4737b(intent);
        this.mSearchInitiatedFromWithinApp = intent.getBooleanExtra("SEARCH_INITIATED_FROM_WITHIN_APP", false);
        this.mAnchorListingId = intent.getStringExtra("ANCHOR_LISTING_ID");
        this.mSearchViewHelper.m4735a(a);
        enterResultsMode();
        String stringExtra = intent.getStringExtra("SEARCH_MAX_PRICE");
        if (stringExtra != null) {
            if (b == null) {
                b = new MutableSearchOptions();
            }
            b.m4765b(stringExtra);
        }
        stringExtra = intent.getStringExtra("SEARCH_MIN_PRICE");
        if (stringExtra != null) {
            if (b == null) {
                b = new MutableSearchOptions();
            }
            b.m4759a(stringExtra);
        }
        Nav.m4682a((FragmentActivity) this).m4684b().m4622a(2131755339).m4629a(a, b, this.mAnchorListingId, intent.hasExtra("SEARCH_TAXONOMY_NODE") ? (TaxonomyNode) Parcels.m7495a(intent.getParcelableExtra("SEARCH_TAXONOMY_NODE")) : null);
    }

    private void performTabSearch(String str, int i) {
        EtsyFragmentNavigator a = Nav.m4682a((FragmentActivity) this).m4684b().m4622a(2131755339);
        enterResultsMode();
        switch (i) {
            case MODE_TAXONOMY /*0*/:
                saveQueryToSearchHistory(str);
                a.m4629a(str, null, null, null);
            case MODE_SUGGEST /*1*/:
                a.m4654h(str);
            case MODE_RESULTS /*2*/:
                a.m4652g(str);
            default:
        }
    }

    private void performTaxonomySearch(TaxonomyNode taxonomyNode) {
        enterResultsMode();
        Nav.m4682a((FragmentActivity) this).m4684b().m4622a(2131755339).m4629a(StringUtils.EMPTY, null, null, taxonomyNode);
    }

    void updateActionBarAfterModeChange() {
        invalidateOptionsMenu();
        getAppBarHelper().setNavigationIcon(this.mViewMode == MODE_SUGGEST ? 2130837970 : 2130837919);
    }

    private void enterResultsMode() {
        this.mViewMode = MODE_RESULTS;
        this.mInputLayout.setVisibility(8);
        this.mTabLayout.setVisibility(8);
        updateActionBarAfterModeChange();
    }

    void enterSuggestMode() {
        this.mViewMode = MODE_SUGGEST;
        this.mTabLayout.setVisibility(MODE_TAXONOMY);
        this.mInputLayout.setVisibility(MODE_TAXONOMY);
        int tabSelected = this.mInputLayout.getTabSelected();
        if (tabSelected == 0 || tabSelected == -1) {
            this.mInputLayout.setCurrentItem(MODE_TAXONOMY);
        }
        updateActionBarAfterModeChange();
    }

    private void enterTaxonomyMode() {
        this.mViewMode = MODE_TAXONOMY;
        this.mTabLayout.setVisibility(8);
        this.mInputLayout.setVisibility(8);
        this.mSearchViewHelper.m4735a(StringUtils.EMPTY);
        this.mSearchViewHelper.m4738b();
        updateActionBarAfterModeChange();
    }

    private void saveQueryToSearchHistory(String str) {
        if (!TextUtils.isEmpty(str) && SharedPreferencesUtility.m3154l(this)) {
            EtsyDatabaseUtil.m747b((Context) this, str);
        }
    }

    public SearchViewHelper getSearchViewHelper() {
        return this.mSearchViewHelper;
    }

    private boolean isShowingInitialSearchInput() {
        return getSupportFragmentManager().getBackStackEntryCount() == 0;
    }

    public void onBackPressed() {
        if (!handleBackLocally()) {
            super.onBackPressed();
        }
    }

    private boolean handleBackLocally() {
        if (this.mViewMode != MODE_SUGGEST) {
            return false;
        }
        if (isShowingInitialSearchInput()) {
            enterTaxonomyMode();
            return true;
        }
        enterResultsMode();
        return true;
    }

    static SearchViewHelper getSearchViewHelper(Activity activity) {
        if (activity instanceof SearchViewHelper) {
            return ((SearchViewHelper) activity).getSearchViewHelper();
        }
        throw new IllegalStateException("Containing activity must implement SearchViewHelper.Provider");
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820557, menu);
        menu.findItem(2131756552).setVisible(this.mViewMode == MODE_RESULTS);
        return super.onCreateOptionsMenuWithIcons(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(2131756552).setVisible(this.mViewMode == MODE_RESULTS);
        return super.onPrepareOptionsMenu(menu);
    }

    @NonNull
    public String getTrackingName() {
        return "search";
    }
}
