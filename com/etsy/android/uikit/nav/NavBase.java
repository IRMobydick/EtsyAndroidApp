package com.etsy.android.uikit.nav;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;

/* renamed from: com.etsy.android.uikit.nav.b */
public class NavBase {
    protected FragmentActivity f3206a;

    protected NavBase(FragmentActivity fragmentActivity) {
        this.f3206a = fragmentActivity;
    }

    public static NavBase m4675b(FragmentActivity fragmentActivity) {
        return new NavBase(fragmentActivity);
    }

    public void m4678e() {
        m4677a(false);
    }

    public void m4677a(boolean z) {
        Intent parentActivityIntent = NavUtils.getParentActivityIntent(this.f3206a);
        if (z || NavUtils.shouldUpRecreateTask(this.f3206a, parentActivityIntent)) {
            TaskStackBuilder.create(this.f3206a).addNextIntentWithParentStack(parentActivityIntent).startActivities();
        } else {
            NavUtils.navigateUpFromSameTask(this.f3206a);
        }
        m4680g();
    }

    public void m4676a(Intent intent) {
        intent.addFlags(67108864);
        this.f3206a.startActivity(intent);
        this.f3206a.finish();
        m4680g();
    }

    public void m4679f() {
        this.f3206a.finish();
        m4680g();
    }

    public void m4680g() {
        Intent intent = this.f3206a.getIntent();
        if (intent != null) {
            this.f3206a.overridePendingTransition(intent.getIntExtra("NAV_ANIM_BOTTOM_ENTER", 0), intent.getIntExtra("NAV_ANIM_TOP_EXIT", 0));
        }
    }
}
