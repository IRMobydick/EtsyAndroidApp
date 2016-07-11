package com.etsy.android.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.al;
import com.etsy.android.lib.logger.EtsyAdjust;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyFacebookTracker;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.EventTracker;
import com.etsy.android.lib.logger.ITrackingView;
import com.etsy.android.lib.logger.LiveActivityCounter;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.messaging.EtsyRouteHelper;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.toolbar.AdminToolbar;
import com.etsy.android.lib.util.AndroidIssuesUtil;
import com.etsy.android.lib.util.bl;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.messaging.EasyOptOutDelegate;
import com.etsy.android.ui.cart.CartFragment;
import com.etsy.android.ui.cart.CartUtil;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.nav.NavTracker;
import com.etsy.android.uikit.AppBarHelper;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.navigationview.EtsyNavigationView;
import com.etsy.android.uikit.ui.core.NetworkLoaderActivity;
import com.etsy.android.uikit.util.FragmentBackstackUtil;
import com.etsy.android.util.AppboyUtil;
import com.etsy.android.util.BOEOptionsMenuItemHelper;
import com.etsy.android.util.NfcHelper;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public abstract class BOENavDrawerActivity extends NetworkLoaderActivity {
    private static final String ARG_SOE_INSTALL_STATE = "soe_install_state";
    private static final String SIGN_IN_ACTION = "sign_in_action";
    private static final String TAG;
    private TextView mCartBadge;
    private IconView mCartIcon;
    private DrawerLayout mDrawerLayout;
    @Nullable
    private EasyOptOutDelegate mEasyOptOutDelegate;
    private OnBackStackChangedListener mFragmentBackStackListener;
    private boolean mIsRestarted;
    private boolean mIsRetaining;
    private FrameLayout mNavContentView;
    private NavTracker mNavTracker;
    private BOENavigationMenuManager mNavigationMenuManager;
    private EtsyNavigationView mNavigationView;
    private final BroadcastReceiver mOnUserPrefsUpdated;
    private int mPrevBackstackCount;
    private EtsyAction mSignInAction;
    private al mSignInListener;
    private View mToolbarLayout;
    private BroadcastReceiver mUpdateCartBadgeReceiver;

    /* renamed from: com.etsy.android.ui.BOENavDrawerActivity.1 */
    class C05081 implements OnBackStackChangedListener {
        final /* synthetic */ BOENavDrawerActivity f2084a;

        C05081(BOENavDrawerActivity bOENavDrawerActivity) {
            this.f2084a = bOENavDrawerActivity;
        }

        public void onBackStackChanged() {
            this.f2084a.mPrevBackstackCount = FragmentBackstackUtil.m5536a(this.f2084a, this.f2084a.getSupportFragmentManager(), this.f2084a.mPrevBackstackCount);
        }
    }

    /* renamed from: com.etsy.android.ui.BOENavDrawerActivity.2 */
    class C05092 implements OnNdefPushCompleteCallback {
        final /* synthetic */ BOENavDrawerActivity f2085a;

        C05092(BOENavDrawerActivity bOENavDrawerActivity) {
            this.f2085a = bOENavDrawerActivity;
        }

        public void onNdefPushComplete(NfcEvent nfcEvent) {
        }
    }

    /* renamed from: com.etsy.android.ui.BOENavDrawerActivity.3 */
    class C05103 implements OnClickListener {
        final /* synthetic */ BOENavDrawerActivity f2086a;

        C05103(BOENavDrawerActivity bOENavDrawerActivity) {
            this.f2086a = bOENavDrawerActivity;
        }

        public void onClick(View view) {
            this.f2086a.goToCart();
        }
    }

    /* renamed from: com.etsy.android.ui.BOENavDrawerActivity.4 */
    class C05114 implements OnLongClickListener {
        final /* synthetic */ BOENavDrawerActivity f2087a;

        C05114(BOENavDrawerActivity bOENavDrawerActivity) {
            this.f2087a = bOENavDrawerActivity;
        }

        public boolean onLongClick(View view) {
            bl.m3354a((int) R.menu_cart, view);
            return true;
        }
    }

    /* renamed from: com.etsy.android.ui.BOENavDrawerActivity.5 */
    class C05125 extends BroadcastReceiver {
        final /* synthetic */ BOENavDrawerActivity f2088a;

        C05125(BOENavDrawerActivity bOENavDrawerActivity) {
            this.f2088a = bOENavDrawerActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if ("com.etsy.android.cart.UPDATE_CART".equalsIgnoreCase(intent.getAction())) {
                this.f2088a.setCartBadge(CartUtil.m3883a());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.BOENavDrawerActivity.6 */
    class C05136 implements al {
        final /* synthetic */ BOENavDrawerActivity f2089a;

        C05136(BOENavDrawerActivity bOENavDrawerActivity) {
            this.f2089a = bOENavDrawerActivity;
        }

        public void onSignedInChanged(Context context, boolean z) {
            CartUtil.m3886a(this.f2089a.getApplicationContext(), 0);
            this.f2089a.getRequestQueue().m1699a(new CartUtil(this.f2089a.getApplicationContext()));
            if (z) {
                this.f2089a.onUserSignedIn();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.BOENavDrawerActivity.7 */
    class C05147 extends BroadcastReceiver {
        final /* synthetic */ BOENavDrawerActivity f2090a;

        C05147(BOENavDrawerActivity bOENavDrawerActivity) {
            this.f2090a = bOENavDrawerActivity;
        }

        public void onReceive(Context context, Intent intent) {
            this.f2090a.onUserPrefsUpdated();
        }
    }

    /* renamed from: com.etsy.android.ui.BOENavDrawerActivity.8 */
    /* synthetic */ class C05158 {
        static final /* synthetic */ int[] f2091a;

        static {
            f2091a = new int[EtsyAction.values().length];
            try {
                f2091a[EtsyAction.FOLLOW.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2091a[EtsyAction.CONTACT_USER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2091a[EtsyAction.FAVORITE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f2091a[EtsyAction.MANAGE_ITEM_COLLECTIONS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f2091a[EtsyAction.PURCHASE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f2091a[EtsyAction.ADD_TO_CART.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public BOENavDrawerActivity() {
        this.mIsRestarted = false;
        this.mPrevBackstackCount = 0;
        this.mFragmentBackStackListener = new C05081(this);
        this.mUpdateCartBadgeReceiver = new C05125(this);
        this.mSignInListener = new C05136(this);
        this.mOnUserPrefsUpdated = new C05147(this);
    }

    static {
        TAG = EtsyDebug.m1891a(BOENavDrawerActivity.class);
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

    protected int getGraphikTheme() {
        return 2131428186;
    }

    public void onCreate(Bundle bundle) {
        EtsyDebug.m1906b(TAG, "onCreate");
        super.onCreate(bundle);
        super.setContentView((int) R.activity_navigation_base);
        this.mNavContentView = (FrameLayout) findViewById(R.nav_content_frame);
        this.mDrawerLayout = (DrawerLayout) findViewById(R.nav_drawer_layout);
        this.mDrawerLayout.setScrimColor(getResources().getColor(R.drawer_scrim));
        this.mNavigationView = (EtsyNavigationView) findViewById(R.nav_drawer_window);
        this.mNavigationMenuManager = new BOENavigationMenuManager(this, this.mNavigationView, this.mDrawerLayout);
        this.mNavigationView.setNavigationItemSelectedListener(this.mNavigationMenuManager);
        this.mNavTracker = new NavTracker();
        this.mIsRestarted = bundle != null;
        if (this.mIsRestarted) {
            this.mPrevBackstackCount = bundle.getInt("backstackCount");
            FragmentBackstackUtil.m5537a(getSupportFragmentManager());
            if (bundle.containsKey(SIGN_IN_ACTION)) {
                this.mSignInAction = EtsyAction.values()[bundle.getInt(SIGN_IN_ACTION)];
            }
        }
        getSupportFragmentManager().addOnBackStackChangedListener(this.mFragmentBackStackListener);
        CartUtil.m3887a(getApplicationContext(), getRequestQueue());
        if (NfcHelper.m5681a((Context) this)) {
            NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this);
            NfcHelper.m5679a((Activity) this, EtsyRouteHelper.m2280a());
            defaultAdapter.setOnNdefPushCompleteCallback(new C05092(this), this, new Activity[0]);
        }
        AppBarHelper appBarHelper;
        if (isTopLevelActivity()) {
            appBarHelper = getAppBarHelper();
            if (appBarHelper != null) {
                appBarHelper.setNavigationIcon(2130837919);
            }
        } else {
            appBarHelper = getAppBarHelper();
            if (appBarHelper != null) {
                appBarHelper.setNavigationIcon(2130837970);
            }
        }
        if (!this.mIsRestarted) {
            this.mEasyOptOutDelegate = new EasyOptOutDelegate(this);
        }
    }

    protected void onRestart() {
        EtsyDebug.m1906b(TAG, "onRestart");
        super.onRestart();
        this.mIsRestarted = true;
    }

    protected void onStart() {
        EtsyDebug.m1906b(TAG, "onStart");
        super.onStart();
        if (LiveActivityCounter.m2052c()) {
            EtsyLogger.m1966a().m1993a(this.mIsRestarted);
            EventTracker.m2020a("app", getAnalyticsContext());
            EtsyDebug.m1906b(TAG, "onStart - foregrounded");
            LiveActivityCounter.m2050a();
        } else if (getLastCustomNonConfigurationInstance() == null) {
            LiveActivityCounter.m2050a();
        }
        this.mIsRestarted = false;
        if (this.mNavigationMenuManager != null) {
            this.mNavigationMenuManager.m3498b();
        }
        AppboyUtil.m5684a((Activity) this);
    }

    protected void onResume() {
        EtsyDebug.m1906b(TAG, "onResume");
        super.onResume();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        instance.registerReceiver(this.mOnUserPrefsUpdated, new IntentFilter("com.etsy.android.lib.action.PREFS_UPDATED"));
        EtsyAdjust.m1887f();
        EtsyFacebookTracker.m1925a((Context) this);
        AdminToolbar.m2995b(getClass().getSimpleName());
        instance.registerReceiver(this.mUpdateCartBadgeReceiver, new IntentFilter("com.etsy.android.cart.UPDATE_CART"));
        aj.m1101a().m1112a(this.mSignInListener);
        this.mNavigationMenuManager.m3499c();
        invalidateOptionsMenu();
    }

    protected void onPause() {
        EtsyDebug.m1906b(TAG, "onPause");
        super.onPause();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        instance.unregisterReceiver(this.mOnUserPrefsUpdated);
        EtsyAdjust.m1888g();
        EtsyFacebookTracker.m1930b((Context) this);
        instance.unregisterReceiver(this.mUpdateCartBadgeReceiver);
        aj.m1101a().m1115b(this.mSignInListener);
    }

    protected void onStop() {
        EtsyDebug.m1906b(TAG, "onStop");
        this.mIsRetaining = false;
        if (!isChangingConfigurations()) {
            LiveActivityCounter.m2051b();
            LiveActivityCounter.m2053d();
        }
        if (this.mNavigationMenuManager != null) {
            this.mNavigationMenuManager.m3500d();
        }
        AppboyUtil.m5693b((Activity) this);
        super.onStop();
    }

    public void onDestroy() {
        EtsyDebug.m1906b(TAG, "onDestroy");
        if (!this.mIsRetaining) {
            FragmentBackstackUtil.m5541b(getSupportFragmentManager());
        }
        getSupportFragmentManager().removeOnBackStackChangedListener(this.mFragmentBackStackListener);
        getRequestQueue().m1700a((Object) this);
        super.onDestroy();
    }

    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        EtsyDebug.m1906b(TAG, "onPostCreate");
        getAppBarHelper().addExtraUpPadding(this);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        EtsyDebug.m1906b(TAG, "onActivityResult");
        switch (i) {
            case 300:
                break;
            case 301:
                if (i2 == 311) {
                    onUserSignedInForAction(this.mSignInAction);
                    this.mSignInAction = null;
                    break;
                }
                finish();
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
        if (i2 == 311) {
            EtsyAction fromAction = EtsyAction.fromAction(intent.getAction());
            if (fromAction != null) {
                Bundle bundleExtra = intent.getBundleExtra(fromAction.getName());
                switch (C05158.f2091a[fromAction.ordinal()]) {
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        setIntent(EtsyRouteHelper.m2279a(fromAction, intent));
                    case Task.NETWORK_STATE_ANY /*2*/:
                        if (bundleExtra != null) {
                            intent.putExtras(bundleExtra);
                        }
                        Nav.m4682a((FragmentActivity) this).m4683a().m4516d(intent.getExtras());
                        setIntent(null);
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        setIntent(EtsyRouteHelper.m2279a(fromAction, intent));
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        Nav.m4682a((FragmentActivity) this).m4683a().m4510c(bundleExtra);
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        Cart cart = (Cart) bundleExtra.getSerializable(CartFragment.CHECKED_OUT_CART);
                        if (cart != null) {
                            Nav.m4682a((FragmentActivity) this).m4683a().m4455a(cart);
                        }
                        setIntent(null);
                    default:
                        setIntent(null);
                }
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        EtsyDebug.m1906b(TAG, "onConfigurationChanged");
        invalidateOptionsMenu();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        EtsyDebug.m1906b(TAG, "onSaveInstanceState - changing configuration: ");
        bundle.putInt("backstackCount", this.mPrevBackstackCount);
        if (this.mSignInAction != null) {
            bundle.putInt(SIGN_IN_ACTION, this.mSignInAction.ordinal());
        }
        super.onSaveInstanceState(bundle);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        EtsyDebug.m1906b(TAG, "onRestoreInstanceState");
    }

    public Object onRetainCustomNonConfigurationInstance() {
        EtsyDebug.m1906b(TAG, "onRetainCustomNonConfigurationInstance");
        this.mIsRetaining = true;
        return Boolean.valueOf(true);
    }

    public final boolean onCreateOptionsMenu(Menu menu) {
        boolean onCreateOptionsMenuWithIcons = onCreateOptionsMenuWithIcons(menu);
        BOEOptionsMenuItemHelper.m5676a(EtsyApplication.get().getResources(), menu);
        MenuItem findItem = menu.findItem(2131756552);
        if (findItem != null) {
            findItem.setActionView(buildCartActionBarView());
        }
        if (AndroidIssuesUtil.m3164a()) {
            BOEOptionsMenuItemHelper.m5677a(menu);
        }
        return onCreateOptionsMenuWithIcons;
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                return navigateUpAsBack();
            case 2131756551:
                goToSearch();
                return true;
            case 2131756552:
                goToCart();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onBackPressed() {
        FragmentBackstackUtil.m5538a(getSupportFragmentManager(), Nav.m4682a((FragmentActivity) this));
    }

    public void setTitle(CharSequence charSequence) {
        getAppBarHelper().setTitle(charSequence);
    }

    public void setTitle(@StringRes int i) {
        setTitle(getString(i));
    }

    public TrackingBaseActivity getFragmentActivity() {
        return this;
    }

    public boolean isTopLevelActivity() {
        return true;
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

    protected void setNavStyleModal() {
        getAppBarHelper().setNavigationIcon((int) R.ic_close_24dp);
        this.mDrawerLayout.setDrawerLockMode(1);
    }

    public boolean popOrGoBack() {
        return FragmentBackstackUtil.m5542b(getSupportFragmentManager(), Nav.m4682a((FragmentActivity) this));
    }

    public boolean navigateUp() {
        if (!isTopLevelActivity()) {
            return FragmentBackstackUtil.m5539a((Activity) this, getSupportFragmentManager(), Nav.m4682a((FragmentActivity) this));
        }
        toggleDrawer();
        return true;
    }

    public boolean navigateUpAsBack() {
        if (!isTopLevelActivity()) {
            return FragmentBackstackUtil.m5540a(getSupportFragmentManager(), getIntent(), Nav.m4682a((FragmentActivity) this));
        }
        toggleDrawer();
        return true;
    }

    private void goToCart() {
        Nav.m4682a((FragmentActivity) this).m4683a().m4541o();
    }

    private View buildCartActionBarView() {
        View inflate = getLayoutInflater().inflate(2130903065, null);
        this.mCartBadge = (TextView) inflate.findViewById(2131755295);
        this.mCartIcon = (IconView) inflate.findViewById(2131755294);
        setCartBadge(CartUtil.m3883a());
        inflate.setOnClickListener(new C05103(this));
        inflate.setOnLongClickListener(new C05114(this));
        return inflate;
    }

    public void setCartBadge(int i) {
        if (this.mCartBadge == null || this.mCartIcon == null) {
            invalidateOptionsMenu();
        } else if (i > 0) {
            this.mCartBadge.setText(String.valueOf(i));
            this.mCartBadge.setVisibility(0);
            this.mCartIcon.setIcon(EtsyFontIcons.CART);
        } else {
            this.mCartBadge.setVisibility(8);
            this.mCartIcon.setIcon(EtsyFontIcons.CART_EMPTY);
        }
    }

    protected void onUserSignedIn() {
    }

    protected void onUserPrefsUpdated() {
    }

    public boolean onSearchRequested() {
        goToSearch();
        return false;
    }

    private void goToSearch() {
        Nav.m4682a((FragmentActivity) this).m4683a().m4540n();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !isDrawerOpen()) {
            return super.onKeyDown(i, keyEvent);
        }
        closeDrawer();
        return true;
    }

    protected void requireSignIn(EtsyAction etsyAction) {
        if (aj.m1101a().m1118d()) {
            onUserSignedInForAction(etsyAction);
            return;
        }
        this.mSignInAction = etsyAction;
        ((EtsyActivityNavigator) Nav.m4682a((FragmentActivity) this).m4683a().m3013a((ITrackingView) this)).m4451a(etsyAction);
    }

    protected void onUserSignedInForAction(EtsyAction etsyAction) {
    }
}
