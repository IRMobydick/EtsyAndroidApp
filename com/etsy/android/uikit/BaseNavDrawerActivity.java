package com.etsy.android.uikit;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.posts.PostManagerKicker;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.LiveActivityCounter;
import com.etsy.android.lib.toolbar.AdminToolbar;
import com.etsy.android.uikit.nav.BaseNavigationMenuManager;
import com.etsy.android.uikit.nav.NavBase;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.navigationview.EtsyNavigationView;
import com.etsy.android.uikit.util.FragmentBackstackUtil;

public abstract class BaseNavDrawerActivity extends TrackingBaseActivity {
    private static final String TAG;
    protected DrawerLayout mDrawerLayout;
    private OnBackStackChangedListener mFragmentBackStackListener;
    protected boolean mIsRestarted;
    protected FrameLayout mNavContentView;
    protected BaseNavigationMenuManager mNavigationMenuManager;
    protected EtsyNavigationView mNavigationView;
    protected int mPrevBackstackCount;

    /* renamed from: com.etsy.android.uikit.BaseNavDrawerActivity.1 */
    class C09171 implements OnBackStackChangedListener {
        final /* synthetic */ BaseNavDrawerActivity f3893a;

        C09171(BaseNavDrawerActivity baseNavDrawerActivity) {
            this.f3893a = baseNavDrawerActivity;
        }

        public void onBackStackChanged() {
            IEtsyFragment c = FragmentBackstackUtil.m5543c(this.f3893a.getSupportFragmentManager());
            if (c != null) {
                c.onFragmentResume();
            }
            this.f3893a.mPrevBackstackCount = FragmentBackstackUtil.m5536a(this.f3893a, this.f3893a.getSupportFragmentManager(), this.f3893a.mPrevBackstackCount);
        }
    }

    protected abstract BaseNavigationMenuManager createNavigationMenuManager();

    protected abstract int getLayoutId();

    protected abstract int getNavBackgroundId();

    public abstract boolean isTopLevelActivity();

    protected abstract void logAppActive();

    protected abstract boolean shouldUseNavDrawer();

    public BaseNavDrawerActivity() {
        this.mPrevBackstackCount = 0;
        this.mIsRestarted = false;
        this.mFragmentBackStackListener = new C09171(this);
    }

    static {
        TAG = EtsyDebug.m1891a(BaseNavDrawerActivity.class);
    }

    public void setContentView(int i) {
        getLayoutInflater().inflate(i, this.mNavContentView);
    }

    public void setContentView(View view) {
        this.mNavContentView.addView(view);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        this.mNavContentView.addView(view, layoutParams);
    }

    public void onCreate(Bundle bundle) {
        EtsyDebug.m1906b(TAG, "onCreate");
        super.onCreate(bundle);
        super.setContentView(getLayoutId());
        this.mNavContentView = (FrameLayout) findViewById(R.nav_content_frame);
        this.mDrawerLayout = (DrawerLayout) findViewById(R.nav_drawer_layout);
        this.mDrawerLayout.setScrimColor(getResources().getColor(R.drawer_scrim));
        this.mNavigationView = (EtsyNavigationView) findViewById(R.nav_drawer_window);
        this.mNavigationView.setBackgroundColor(getResources().getColor(getNavBackgroundId()));
        if (shouldUseNavDrawer()) {
            this.mNavigationMenuManager = createNavigationMenuManager();
            this.mNavigationView.setNavigationItemSelectedListener(this.mNavigationMenuManager);
        } else {
            disableNavDrawer();
        }
        getSupportFragmentManager().addOnBackStackChangedListener(this.mFragmentBackStackListener);
        this.mIsRestarted = bundle != null;
        if (this.mIsRestarted) {
            this.mPrevBackstackCount = bundle.getInt("backstackCount");
            FragmentBackstackUtil.m5537a(getSupportFragmentManager());
        }
    }

    protected void onStart() {
        EtsyDebug.m1906b(TAG, "onStart");
        if (LiveActivityCounter.m2052c()) {
            EtsyLogger.m1966a().m1993a(this.mIsRestarted);
            PostManagerKicker.m1674a();
            logAppActive();
            EtsyDebug.m1906b(TAG, "onStart - foregrounded");
        }
        if (getLastCustomNonConfigurationInstance() == null) {
            LiveActivityCounter.m2050a();
        }
        if (this.mNavigationMenuManager != null) {
            this.mNavigationMenuManager.m3480b();
        }
        this.mIsRestarted = false;
        super.onStart();
    }

    protected void onStop() {
        EtsyDebug.m1906b(TAG, "onStop");
        if (!isChangingConfigurations()) {
            LiveActivityCounter.m2051b();
            LiveActivityCounter.m2053d();
        }
        if (this.mNavigationMenuManager != null) {
            this.mNavigationMenuManager.m3484d();
        }
        super.onStop();
    }

    protected void onResume() {
        super.onResume();
        if (this.mNavigationMenuManager != null) {
            this.mNavigationMenuManager.m3483c();
        }
        AdminToolbar.m2995b(getClass().getSimpleName());
    }

    public void onDestroy() {
        super.onDestroy();
        getRequestQueue().m1700a((Object) this);
        getSupportFragmentManager().removeOnBackStackChangedListener(this.mFragmentBackStackListener);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        EtsyDebug.m1906b(TAG, "onSaveInstanceState - changing configuration: ");
        bundle.putInt("backstackCount", this.mPrevBackstackCount);
        super.onSaveInstanceState(bundle);
    }

    public FragmentActivity getFragmentActivity() {
        return this;
    }

    public void setTitle(CharSequence charSequence) {
        getAppBarHelper().setTitle(charSequence);
    }

    public void setTitle(@StringRes int i) {
        setTitle(getString(i));
    }

    public void onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer();
        } else {
            FragmentBackstackUtil.m5538a(getSupportFragmentManager(), NavBase.m4675b(this));
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        return true;
    }

    public void disableNavDrawer() {
        this.mDrawerLayout.setDrawerLockMode(1);
    }

    public void enableNavDrawer() {
        this.mDrawerLayout.setDrawerLockMode(0);
    }

    public boolean navigateUpAsBack() {
        if (!shouldUseNavDrawer() || !isTopLevelActivity()) {
            return FragmentBackstackUtil.m5540a(getSupportFragmentManager(), getIntent(), NavBase.m4675b(this));
        }
        toggleDrawer();
        return true;
    }

    public boolean popOrGoBack() {
        return FragmentBackstackUtil.m5542b(getSupportFragmentManager(), NavBase.m4675b(this));
    }

    protected void toggleDrawer() {
        if (isDrawerOpen()) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }

    protected boolean isDrawerOpen() {
        return this.mDrawerLayout != null && this.mDrawerLayout.isDrawerOpen(this.mNavigationView);
    }

    protected void closeDrawer() {
        if (isDrawerOpen()) {
            this.mDrawerLayout.closeDrawer(this.mNavigationView);
        }
    }

    protected void openDrawer() {
        if (this.mDrawerLayout.getDrawerLockMode(this.mNavigationView) == 0 && this.mDrawerLayout != null && !isDrawerOpen()) {
            this.mDrawerLayout.openDrawer(this.mNavigationView);
        }
    }
}
