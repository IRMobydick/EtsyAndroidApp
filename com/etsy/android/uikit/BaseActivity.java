package com.etsy.android.uikit;

import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.toolbar.TakeScreenshotTask;
import com.etsy.android.uikit.ui.bughunt.BugHuntNav;

/* renamed from: com.etsy.android.uikit.b */
class BaseActivity extends TakeScreenshotTask {
    final /* synthetic */ BaseActivity f3948a;

    protected /* synthetic */ void onPostExecute(Object obj) {
        m5327a((String) obj);
    }

    public BaseActivity(BaseActivity baseActivity, FragmentActivity fragmentActivity) {
        this.f3948a = baseActivity;
        super(fragmentActivity);
    }

    protected void m5327a(String str) {
        if (str == null || this.mContext == null) {
            EtsyDebug.m1916d(getClass().getName(), "we lost our activity during screenshot save :/");
        } else {
            BugHuntNav.m5421a(this.f3948a).m5424a(str);
        }
    }
}
