package com.etsy.android.ui.user;

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
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;

public class CirclesActivity extends TabsActivity {
    private static final int TAB_COUNT = 2;
    public static final int TAB_FOLLOWERS = 1;
    public static final int TAB_FOLLOWING = 0;
    private EtsyId mUserId;
    private String mUserLoginName;

    /* renamed from: com.etsy.android.ui.user.CirclesActivity.1 */
    class C08191 extends FragmentPagerAdapter {
        final /* synthetic */ CirclesActivity f3443a;

        C08191(CirclesActivity circlesActivity, FragmentManager fragmentManager) {
            this.f3443a = circlesActivity;
            super(fragmentManager);
        }

        public int getCount() {
            return CirclesActivity.TAB_COUNT;
        }

        public Fragment getItem(int i) {
            Fragment circlesFragment = new CirclesFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(ResponseConstants.USER_ID, this.f3443a.mUserId);
            bundle.putString("username", this.f3443a.mUserLoginName);
            boolean z = !this.f3443a.mUserId.hasId() || aj.m1101a().m1126m().equals(this.f3443a.mUserId);
            switch (i) {
                case CirclesActivity.TAB_FOLLOWERS /*1*/:
                    if (z) {
                        bundle.putString("TRACKING_NAME", "your_circles_followers");
                    } else {
                        bundle.putString("TRACKING_NAME", "people_circles_followers");
                    }
                    bundle.putBoolean("CIRCLE_FOLLOWING", false);
                    break;
                default:
                    if (z) {
                        bundle.putString("TRACKING_NAME", "your_circles_following");
                    } else {
                        bundle.putString("TRACKING_NAME", "people_circles_following");
                    }
                    bundle.putBoolean("CIRCLE_FOLLOWING", true);
                    break;
            }
            circlesFragment.setArguments(bundle);
            return circlesFragment;
        }

        public CharSequence getPageTitle(int i) {
            CharSequence charSequence = StringUtils.EMPTY;
            switch (i) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                    return this.f3443a.getString(R.following);
                case CirclesActivity.TAB_FOLLOWERS /*1*/:
                    return this.f3443a.getString(R.followers);
                default:
                    return charSequence;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUserId = (EtsyId) getIntent().getSerializableExtra(ResponseConstants.USER_ID);
        this.mUserLoginName = getIntent().getStringExtra("username");
        if (this.mUserId == null) {
            this.mUserId = new EtsyId();
        }
        setupTabs();
        if (bundle == null) {
            selectTab(getIntent().getIntExtra(FindsModule.FIELD_TYPE, 0));
        }
    }

    protected PagerAdapter getViewPagerAdapter() {
        return new C08191(this, getSupportFragmentManager());
    }

    protected void logPageAtPosition(int i) {
        switch (i) {
            case TAB_FOLLOWERS /*1*/:
                this.mNavTracker.m4697a(false, this.mUserId);
            default:
                this.mNavTracker.m4697a(true, this.mUserId);
        }
    }

    protected void onResume() {
        super.onResume();
        if (bh.m3340a(this.mUserLoginName)) {
            setTitle(this.mUserLoginName);
        } else {
            setTitle(StringUtils.EMPTY);
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(2131820551, menu);
        return true;
    }
}
