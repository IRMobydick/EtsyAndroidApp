package com.etsy.android.ui.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.util.CheckAlphaUpdateUtil;
import com.etsy.android.lib.util.ay;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.homescreen.HomescreenTabsActivity;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.promos.VersionPromo;
import com.etsy.android.ui.util.EtsyPromoUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;
import com.etsy.android.util.EtsyBuildHelper;
import com.etsy.android.util.ForeSeeUtil;
import com.etsy.android.util.SessionManager;

public abstract class BaseLaunchActivity extends BOENavDrawerActivity {
    private CheckAlphaUpdateUtil mCheckAlphaUpdate;
    public boolean mForeSeeInitSuccess;
    private boolean mHasSyncedUser;
    protected boolean mIsFirstView;
    private VersionPromo mPendingPromo;

    /* renamed from: com.etsy.android.ui.core.BaseLaunchActivity.1 */
    class C06441 implements OnGlobalLayoutListener {
        final /* synthetic */ View f2683a;
        final /* synthetic */ BaseLaunchActivity f2684b;

        C06441(BaseLaunchActivity baseLaunchActivity, View view) {
            this.f2684b = baseLaunchActivity;
            this.f2683a = view;
        }

        public void onGlobalLayout() {
            ViewTreeObserverHelper.m5639b(this.f2683a.getViewTreeObserver(), (OnGlobalLayoutListener) this);
            this.f2684b.showUpdateOrVersionPromo();
            if ((this.f2684b instanceof HomescreenTabsActivity) && this.f2684b.mForeSeeInitSuccess) {
                ForeSeeUtil.m5712a();
            }
        }
    }

    /* renamed from: com.etsy.android.ui.core.BaseLaunchActivity.2 */
    class C06462 implements CheckAlphaUpdateUtil {
        final /* synthetic */ BaseLaunchActivity f2686a;

        /* renamed from: com.etsy.android.ui.core.BaseLaunchActivity.2.1 */
        class C06451 extends TrackingOnClickListener {
            final /* synthetic */ C06462 f2685a;

            C06451(C06462 c06462, ITrackedObject... iTrackedObjectArr) {
                this.f2685a = c06462;
                super(iTrackedObjectArr);
            }

            public void onViewClick(View view) {
                this.f2685a.f2686a.requestAlphaUpgrade();
            }
        }

        C06462(BaseLaunchActivity baseLaunchActivity) {
            this.f2686a = baseLaunchActivity;
        }

        public void m3948a(AppBuild appBuild) {
            if (ay.m3289a(this.f2686a, appBuild)) {
                EtsyPromoUtil.m5144a(this.f2686a, new C06451(this, appBuild), appBuild.getVersion());
            }
        }
    }

    public BaseLaunchActivity() {
        this.mForeSeeInitSuccess = false;
    }

    public void onCreate(Bundle bundle) {
        SessionManager.m5720a(getIntent(), (Activity) this);
        super.onCreate(bundle);
        if (bundle == null) {
            this.mIsFirstView = true;
            setupPromos();
        }
        ((EtsyApplication) getApplicationContext()).setLaunchedForUI(true);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mIsFirstView = false;
        if (intent.getBooleanExtra("HOME_RESET", false) && intent.getBooleanExtra("FORCED_SIGNOUT", false)) {
            showForcedSignOutDialog();
        }
        closeDrawer();
        intent.removeExtra("HOME_RESET");
        intent.removeExtra("HOME_INDEX");
        intent.removeExtra("FORCED_SIGNOUT");
    }

    private void showForcedSignOutDialog() {
        Nav.m4682a((FragmentActivity) this).m4686d().m4412a(null, (int) R.ok, 0, 0, getString(R.auth_forced_signout_dialog_title)).setSubTitle(getString(R.auth_forced_signout_dialog));
    }

    private void setupPromos() {
        if (this.mIsFirstView) {
            this.mForeSeeInitSuccess = ForeSeeUtil.m5713a(getApplication());
            View decorView = getWindow().getDecorView();
            ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnGlobalLayoutListener(new C06441(this, decorView));
            }
        }
        this.mIsFirstView = false;
    }

    private void showUpdateOrVersionPromo() {
        if (EtsyBuildHelper.m5707b() && !EtsyBuildHelper.m5708c()) {
            showAlphaUpgradePromo();
        }
        if (!EtsyPromoUtil.m5148b(getApplicationContext()) || EtsyBuildHelper.m5707b()) {
            getVersionPromo();
        } else {
            EtsyPromoUtil.m5146a(getFragmentActivity());
        }
    }

    private void getVersionPromo() {
        VersionPromo a = EtsyPromoUtil.m5142a(getFragmentActivity());
        if (a == null) {
            return;
        }
        if (!a.requiresUserSync() || this.mHasSyncedUser) {
            showVersionPromo(a);
        } else {
            this.mPendingPromo = a;
        }
    }

    private void showVersionPromo(VersionPromo versionPromo) {
        if (versionPromo != null) {
            if (isDrawerOpen()) {
                closeDrawer();
            }
            Nav.m4681a(getFragmentActivity()).m4484a(versionPromo);
        }
    }

    protected void onUserSignedIn() {
        this.mHasSyncedUser = true;
        showUpdateOrVersionPromo();
    }

    protected void onUserPrefsUpdated() {
        this.mHasSyncedUser = true;
        if (this.mPendingPromo != null) {
            showVersionPromo(this.mPendingPromo);
            this.mPendingPromo = null;
        }
    }

    @NonNull
    public String getTrackingName() {
        return "home";
    }

    private void showAlphaUpgradePromo() {
        if (EtsyPromoUtil.m5147b()) {
            createCheckAlphaUtil();
            this.mCheckAlphaUpdate.m3414a(new C06462(this));
        }
    }

    private void requestAlphaUpgrade() {
        if (EtsyPromoUtil.m5147b()) {
            createCheckAlphaUtil();
            this.mCheckAlphaUpdate.m3415a(null);
        }
    }

    private void createCheckAlphaUtil() {
        if (this.mCheckAlphaUpdate == null) {
            this.mCheckAlphaUpdate = new CheckAlphaUpdateUtil(this);
        }
    }
}
