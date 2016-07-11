package com.etsy.android.uikit.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.uikit.IEtsyFragment;
import com.etsy.android.uikit.nav.NavBase;
import java.util.List;

/* renamed from: com.etsy.android.uikit.util.k */
public class FragmentBackstackUtil {
    private static final String f4159a;

    static {
        f4159a = EtsyDebug.m1891a(FragmentBackstackUtil.class);
    }

    public static void m5537a(FragmentManager fragmentManager) {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 1) {
            try {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                for (backStackEntryCount -= 2; backStackEntryCount >= 0; backStackEntryCount--) {
                    Fragment findFragmentByTag = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(backStackEntryCount).getName());
                    if (findFragmentByTag != null) {
                        beginTransaction.hide(findFragmentByTag);
                    }
                }
                beginTransaction.commit();
            } catch (Throwable e) {
                EtsyDebug.m1917d(f4159a, "cleanUpFragmentBackStack error", e);
            }
        }
    }

    public static void m5541b(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            try {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                for (Fragment fragment : fragments) {
                    if (fragment != null) {
                        beginTransaction.detach(fragment);
                    }
                }
                beginTransaction.commitAllowingStateLoss();
            } catch (Throwable e) {
                EtsyDebug.m1917d(f4159a, "cleanUpNonRetainedFragments error", e);
            }
        }
    }

    public static void m5538a(FragmentManager fragmentManager, NavBase navBase) {
        IEtsyFragment c = FragmentBackstackUtil.m5543c(fragmentManager);
        if (c == null || !c.handleBackPressed()) {
            FragmentBackstackUtil.m5542b(fragmentManager, navBase);
        }
    }

    public static IEtsyFragment m5543c(FragmentManager fragmentManager) {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            return (IEtsyFragment) fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName());
        }
        if (fragmentManager.getFragments() == null || fragmentManager.getFragments().size() <= 0) {
            return null;
        }
        return (IEtsyFragment) fragmentManager.getFragments().get(0);
    }

    public static boolean m5542b(FragmentManager fragmentManager, NavBase navBase) {
        if (fragmentManager.getBackStackEntryCount() <= 0) {
            navBase.m4679f();
        } else {
            fragmentManager.popBackStack();
        }
        return true;
    }

    public static boolean m5539a(Activity activity, FragmentManager fragmentManager, NavBase navBase) {
        Intent parentActivityIntent = NavUtils.getParentActivityIntent(activity);
        if (fragmentManager.getBackStackEntryCount() > 1 || parentActivityIntent == null) {
            return FragmentBackstackUtil.m5542b(fragmentManager, navBase);
        }
        navBase.m4678e();
        return true;
    }

    public static boolean m5540a(FragmentManager fragmentManager, Intent intent, NavBase navBase) {
        if (fragmentManager.getBackStackEntryCount() > 1 || intent == null || !intent.getBooleanExtra("NAV_UP_TO_PARENT", false)) {
            return FragmentBackstackUtil.m5542b(fragmentManager, navBase);
        }
        navBase.m4677a(true);
        return true;
    }

    public static int m5536a(Activity activity, FragmentManager fragmentManager, int i) {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount < i) {
            if (fragmentManager.getBackStackEntryCount() > 0) {
                IEtsyFragment iEtsyFragment = (IEtsyFragment) fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName());
                if (iEtsyFragment != null) {
                    iEtsyFragment.onFragmentResume();
                }
            }
        } else if (backStackEntryCount == i) {
            EtsyLogger.m1966a().m1991a(activity.getClass().getSimpleName(), new Exception("UserActivity: current backstack count equals previous count"), false);
        }
        return backStackEntryCount;
    }
}
