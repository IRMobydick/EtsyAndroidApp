package com.etsy.android.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.al;
import com.etsy.android.lib.core.img.ImageDownloadListener;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.convos.ConvoBaseActivity;
import com.etsy.android.ui.favorites.FavoritesActivity;
import com.etsy.android.ui.homescreen.HomescreenTabsActivity;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.ui.user.PurchasesActivity;
import com.etsy.android.ui.user.UserActivity;
import com.etsy.android.ui.user.UserBadgeCountManager;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.uikit.nav.BaseNavigationMenuManager;
import com.etsy.android.uikit.navigationview.EtsyNavigationView;
import com.etsy.android.uikit.ui.bughunt.BugHuntNav;
import com.etsy.android.util.EtsyBuildHelper;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/* renamed from: com.etsy.android.ui.a */
public class BOENavigationMenuManager extends BaseNavigationMenuManager implements al {
    private static final SparseArray<Drawable> f2106g;
    private static final Map<String, Integer> f2107h;
    BroadcastReceiver f2108a;
    private BitmapDrawable f2109i;
    private boolean f2110j;

    /* renamed from: com.etsy.android.ui.a.1 */
    class BOENavigationMenuManager extends ImageDownloadListener {
        final /* synthetic */ BOENavigationMenuManager f2096a;

        BOENavigationMenuManager(BOENavigationMenuManager bOENavigationMenuManager, ImageView imageView) {
            this.f2096a = bOENavigationMenuManager;
            super(imageView);
        }

        public void m3466a(Bitmap bitmap, boolean z) {
            this.f2096a.f2109i = new BitmapDrawable(this.f2096a.b.getResources(), bitmap);
            if (this.f2096a.b != null && this.f2096a.c != null && this.f2096a.c.getMenu() != null) {
                MenuItem findItem = this.f2096a.c.getMenu().findItem(2131756561);
                if (findItem != null) {
                    findItem.setIcon(this.f2096a.f2109i);
                }
            }
        }
    }

    /* renamed from: com.etsy.android.ui.a.2 */
    class BOENavigationMenuManager extends BroadcastReceiver {
        final /* synthetic */ BOENavigationMenuManager f2097a;

        BOENavigationMenuManager(BOENavigationMenuManager bOENavigationMenuManager) {
            this.f2097a = bOENavigationMenuManager;
        }

        public void onReceive(Context context, Intent intent) {
            this.f2097a.m3495a(context, aj.m1101a().m1118d());
        }
    }

    static {
        f2106g = new SparseArray();
        Map arrayMap = new ArrayMap();
        arrayMap.put(HomescreenTabsActivity.class.getName(), Integer.valueOf(2131756557));
        arrayMap.put(ConvoBaseActivity.class.getName(), Integer.valueOf(2131756559));
        arrayMap.put(FavoritesActivity.class.getName(), Integer.valueOf(2131756558));
        arrayMap.put(UserActivity.class.getName(), Integer.valueOf(2131756561));
        arrayMap.put(PurchasesActivity.class.getName(), Integer.valueOf(2131756560));
        f2107h = Collections.unmodifiableMap(arrayMap);
    }

    public BOENavigationMenuManager(Context context, EtsyNavigationView etsyNavigationView, DrawerLayout drawerLayout) {
        super(context, etsyNavigationView, drawerLayout);
        this.f2108a = new BOENavigationMenuManager(this);
    }

    public static void m3487a(Context context) {
        Resources resources = context.getResources();
        int color = ContextCompat.getColor(context, R.navigation_view_item_color);
        int i = (int) (((double) (resources.getDisplayMetrics().density * 18.0f)) + 0.5d);
        SparseArray sparseArray = f2106g;
        sparseArray.put(2131756557, BaseNavigationMenuManager.m3468a(EtsyFontIcons.HOME, i, color));
        sparseArray.put(2131756558, BaseNavigationMenuManager.m3468a(EtsyFontIcons.HEART, i, color));
        sparseArray.put(2131756559, BaseNavigationMenuManager.m3468a(EtsyFontIcons.CONVERSATIONS, i, color));
        sparseArray.put(2131756560, BaseNavigationMenuManager.m3468a(EtsyFontIcons.ITEMS, i, color));
        sparseArray.put(2131756561, BaseNavigationMenuManager.m3468a(EtsyFontIcons.USER_PROFILE, i, color));
        sparseArray.put(2131756563, BaseNavigationMenuManager.m3468a(EtsyFontIcons.SETTINGS, i, color));
        sparseArray.put(2131756568, BaseNavigationMenuManager.m3468a(EtsyFontIcons.USER_PROFILE, i, color));
    }

    protected Collection<Integer> m3493a() {
        return f2107h.values();
    }

    public void m3498b() {
        super.m3480b();
        aj.m1101a().m1112a((al) this);
        m3495a(this.b, aj.m1101a().m1118d());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(EtsyApplication.ACTION_INSTALL_STATE_DISCOVERED);
        intentFilter.addAction("com.etsy.android.badge.count.UPDATE");
        LocalBroadcastManager.getInstance(this.b).registerReceiver(this.f2108a, intentFilter);
    }

    public void m3499c() {
        super.m3483c();
        Integer num = (Integer) f2107h.get(this.b.getClass().getName());
        if (num != null) {
            this.f = num.intValue();
            MenuItem findItem = this.c.getMenu().findItem(this.f);
            if (findItem != null) {
                findItem.setChecked(true);
            }
        }
    }

    public void m3500d() {
        super.m3484d();
        aj.m1101a().m1115b((al) this);
        LocalBroadcastManager.getInstance(this.b).unregisterReceiver(this.f2108a);
    }

    protected void m3494a(int i) {
        FragmentActivity fragmentActivity = (FragmentActivity) this.b;
        EtsyActivityNavigator f = Nav.m4682a(fragmentActivity).m4683a().m4446a(AnimationMode.NONE).m4525f();
        switch (i) {
            case 2131756557:
                f.m4543q();
            case 2131756558:
                f.m4520e().m4468a(SharedPreferencesUtility.m3134c(fragmentActivity), 0);
            case 2131756559:
                f.m4546t();
            case 2131756560:
                f.m4548v();
            case 2131756561:
                f.m4535i();
            case 2131756563:
                Nav.m4682a(fragmentActivity).m4683a().m4536j();
            case 2131756564:
                Nav.m4682a(fragmentActivity).m4683a().m4446a(AnimationMode.NONE).m4544r();
            case 2131756565:
                Nav.m4682a(fragmentActivity).m4683a().m4539m();
            case 2131756566:
                BugHuntNav.m5421a(fragmentActivity).m5423a();
            case 2131756567:
                EtsyApplication etsyApplication = (EtsyApplication) fragmentActivity.getApplicationContext();
                if (etsyApplication.isSOEInstalled()) {
                    fragmentActivity.startActivity(etsyApplication.getSOELaunchIntent());
                } else {
                    fragmentActivity.startActivity(EtsyApplication.getSOEDownloadIntent());
                }
            case 2131756568:
                f.m4495a(false);
            default:
        }
    }

    public void onSignedInChanged(Context context, boolean z) {
        if (!z) {
            this.f2109i = null;
        }
        m3495a(context, z);
    }

    protected void m3495a(Context context, boolean z) {
        this.f2110j = z;
        m3474a(context, this.f2110j ? 2131820554 : 2131820555, f2106g);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.design_navigation_icon_size);
        if (z && this.f2109i == null) {
            this.e.m1572a(SharedPreferencesUtility.m3144g(this.b), new BOENavigationMenuManager(this, null), dimensionPixelSize);
        }
    }

    protected void m3496a(Menu menu) {
        if (!EtsyBuildHelper.m5709d()) {
            menu.removeItem(2131756564);
        }
        if (!(EtsyBuildHelper.m5706a() || EtsyBuildHelper.m5709d())) {
            menu.removeItem(2131756565);
        }
        if (!EtsyConfig.m837a().m869d().m884b() && !EtsyBuildHelper.m5709d()) {
            menu.removeItem(2131756566);
        }
    }

    protected void m3497a(MenuItem menuItem, Context context) {
        int itemId = menuItem.getItemId();
        if (itemId == 2131756561) {
            if (this.f2110j) {
                menuItem.setTitle(SharedPreferencesUtility.m3140e(context));
            }
            if (this.f2109i != null) {
                menuItem.setIcon(this.f2109i);
                itemId = 0;
            }
            itemId = 0;
        } else if (itemId == 2131756560) {
            if (this.f2110j) {
                itemId = UserBadgeCountManager.m5063a();
            }
            itemId = 0;
        } else if (itemId == 2131756559) {
            if (this.f2110j) {
                itemId = UserBadgeCountManager.m5066b();
            }
            itemId = 0;
        } else {
            if (itemId == 2131756567) {
                if (((EtsyApplication) context.getApplicationContext()).isSOEInstalled()) {
                    menuItem.setTitle(R.nav_switch_to_sell_on_etsy);
                    itemId = 0;
                } else {
                    menuItem.setTitle(R.nav_get_sell_on_etsy);
                }
            }
            itemId = 0;
        }
        if (itemId > 0) {
            m3477a(menuItem, menuItem.getTitle().toString(), itemId);
        }
        if (menuItem.getItemId() == this.f) {
            menuItem.setChecked(true);
        }
    }
}
