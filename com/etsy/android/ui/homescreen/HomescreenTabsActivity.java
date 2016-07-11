package com.etsy.android.ui.homescreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import com.etsy.android.lib.R;
import com.etsy.android.ui.local.LocalBrowseHomescreenFragment;
import com.etsy.android.uikit.ui.core.BaseFragment;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.homescreen.b */
class HomescreenTabsActivity extends FragmentPagerAdapter {
    final /* synthetic */ HomescreenTabsActivity f3008a;
    private int f3009b;
    private ArrayList<BaseFragment> f3010c;

    public HomescreenTabsActivity(HomescreenTabsActivity homescreenTabsActivity) {
        this.f3008a = homescreenTabsActivity;
        super(homescreenTabsActivity.getSupportFragmentManager());
        this.f3009b = m4275a();
        this.f3010c = new ArrayList();
    }

    private int m4275a() {
        return 4;
    }

    private Bundle m4276a(int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt(HomescreenFragment.NUM_TABS, i);
        bundle.putInt(HomescreenFragment.KEY_PAGE_ID, i2);
        bundle.putString("TRACKING_NAME", m4277a(i3));
        return bundle;
    }

    public Fragment getItem(int i) {
        Fragment homescreenFragment;
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                if (this.f3010c.size() > 0) {
                    return (BaseFragment) this.f3010c.get(0);
                }
                homescreenFragment = new HomescreenFragment();
                homescreenFragment.setArguments(m4276a(this.f3009b, 1, i));
                this.f3010c.add(0, homescreenFragment);
                return homescreenFragment;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                if (this.f3010c.size() > 1) {
                    return (BaseFragment) this.f3010c.get(1);
                }
                homescreenFragment = new HomescreenFragment();
                homescreenFragment.setArguments(m4276a(this.f3009b, 2, i));
                this.f3010c.add(1, homescreenFragment);
                return homescreenFragment;
            case Task.NETWORK_STATE_ANY /*2*/:
                if (this.f3010c.size() > 2) {
                    return (BaseFragment) this.f3010c.get(2);
                }
                homescreenFragment = new HomescreenFragment();
                homescreenFragment.setArguments(m4276a(this.f3009b, 0, i));
                this.f3010c.add(2, homescreenFragment);
                return homescreenFragment;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return new LocalBrowseHomescreenFragment();
            default:
                homescreenFragment = new HomescreenFragment();
                homescreenFragment.setArguments(m4276a(this.f3009b, 1, i));
                return homescreenFragment;
        }
    }

    public int getCount() {
        return this.f3009b;
    }

    public CharSequence getPageTitle(int i) {
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return this.f3008a.getString(R.tab_title_recommended);
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return this.f3008a.getString(R.tab_title_etsy_picks);
            case Task.NETWORK_STATE_ANY /*2*/:
                return this.f3008a.getString(R.tab_title_your_activity);
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return this.f3008a.getString(R.tab_title_local);
            default:
                return null;
        }
    }

    public String m4277a(int i) {
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return "homescreen_recommended";
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return "homescreen_etsy_picks";
            case Task.NETWORK_STATE_ANY /*2*/:
                return "homescreen_your_activity";
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return "local_browse";
            default:
                return StringUtils.EMPTY;
        }
    }
}
