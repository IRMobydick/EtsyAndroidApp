package com.etsy.android.lib.toolbar;

import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.core.ap;
import com.etsy.android.lib.util.SharedPreferencesUtility;

public class AdminToolbarListener extends AdminToolbarReceiver {
    private TakeScreenshotTask mCreator;

    public AdminToolbarListener(FragmentActivity fragmentActivity, TakeScreenshotTask takeScreenshotTask) {
        super(fragmentActivity);
        this.mCreator = takeScreenshotTask;
    }

    protected void onReceivedScreenshotAction() {
        ap.m1142a(this.mCreator.create(this.mContext), new Void[0]);
    }

    protected void onDeviceShakenDetected() {
        if (SharedPreferencesUtility.m3155m(this.mContext)) {
            ap.m1142a(this.mCreator.create(this.mContext), new Void[0]);
        }
    }
}
