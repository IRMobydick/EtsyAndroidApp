package com.etsy.android.ui.favorites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.Menu;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.util.bh;
import com.etsy.android.ui.view.TabsActivity;
import com.etsy.android.uikit.util.TabletSupportUtil;

public class FavoritesActivity extends TabsActivity {
    private static final int TAB_COUNT = 3;
    public static final int TAB_LISTING = 0;
    public static final int TAB_SHOPS = 1;
    public static final int TAB_TREASURIES = 2;
    private TabletSupportUtil mTabletUtil;
    private EtsyId mUserId;
    private String mUserName;

    /* renamed from: com.etsy.android.ui.favorites.FavoritesActivity.1 */
    class C06811 extends FragmentPagerAdapter {
        final /* synthetic */ FavoritesActivity f2944a;

        C06811(FavoritesActivity favoritesActivity, FragmentManager fragmentManager) {
            this.f2944a = favoritesActivity;
            super(fragmentManager);
        }

        public int getCount() {
            return FavoritesActivity.TAB_COUNT;
        }

        public Fragment getItem(int i) {
            boolean isYou = this.f2944a.isYou();
            if (i == FavoritesActivity.TAB_SHOPS && isYou) {
                return new MyFavoriteShopsFragment();
            }
            String str;
            Bundle bundle = new Bundle();
            bundle.putSerializable(ResponseConstants.USER_ID, this.f2944a.mUserId);
            bundle.putInt(FindsModule.FIELD_TYPE, i);
            switch (i) {
                case FavoritesActivity.TAB_LISTING /*0*/:
                    if (!isYou) {
                        str = "profile_favorite_listings";
                        break;
                    }
                    str = "your_favorite_items";
                    break;
                case FavoritesActivity.TAB_SHOPS /*1*/:
                    str = "profile_favorite_shops";
                    break;
                default:
                    if (!isYou) {
                        str = "profile_favorite_treasuries";
                        break;
                    }
                    str = "your_favorite_treasuries";
                    break;
            }
            bundle.putString("TRACKING_NAME", str);
            Fragment favoriteItemsFragment = new FavoriteItemsFragment();
            favoriteItemsFragment.setArguments(bundle);
            return favoriteItemsFragment;
        }

        public CharSequence getPageTitle(int i) {
            switch (i) {
                case FavoritesActivity.TAB_LISTING /*0*/:
                    return this.f2944a.getString(R.items);
                case FavoritesActivity.TAB_SHOPS /*1*/:
                    return this.f2944a.getString(R.shops);
                case FavoritesActivity.TAB_TREASURIES /*2*/:
                    return this.f2944a.getString(R.treasuries);
                default:
                    return null;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUserName = getIntent().getStringExtra("username");
        this.mUserId = (EtsyId) getIntent().getSerializableExtra(ResponseConstants.USER_ID);
        if (this.mUserId == null) {
            this.mUserId = new EtsyId();
        }
        this.mTabletUtil = new TabletSupportUtil(this);
        if (showNameInTitle()) {
            String string = getString(R.user_s_favorites);
            Object[] objArr = new Object[TAB_SHOPS];
            objArr[TAB_LISTING] = this.mUserName;
            setTitle(String.format(string, objArr));
        } else if (isYou()) {
            setTitle((int) R.favorites);
        } else {
            setTitle((int) R.user_single_favorites);
        }
        setupTabs();
        if (bundle == null) {
            selectTab(getIntent().getIntExtra(FindsModule.FIELD_TYPE, TAB_LISTING));
            this.mNavTracker.m4696a(isYou());
        }
    }

    public boolean isYou() {
        return !this.mUserId.hasId() || aj.m1101a().m1126m().equals(this.mUserId);
    }

    protected PagerAdapter getViewPagerAdapter() {
        return new C06811(this, getSupportFragmentManager());
    }

    protected void logPageAtPosition(int i) {
        this.mNavTracker.m4689a(i, isYou());
    }

    private boolean showNameInTitle() {
        return this.mTabletUtil.m5621a() && bh.m3340a(this.mUserName) && !isYou();
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820551, menu);
        return true;
    }

    public boolean isTopLevelActivity() {
        if (getIntent().hasExtra("NAV_TOP_LEVEL_DRAWER") && getIntent().getBooleanExtra("NAV_TOP_LEVEL_DRAWER", false)) {
            return true;
        }
        return super.isTopLevelActivity();
    }
}
