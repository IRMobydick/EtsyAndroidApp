package com.etsy.android.uikit.ui.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.etsy.android.lib.core.EtsyRequestQueue;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.core.posts.EtsyPostManager;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.toolbar.AdminToolbar;
import com.etsy.android.uikit.IEtsyFragment;
import com.etsy.android.uikit.util.CleanupUtil;

/* renamed from: com.etsy.android.uikit.ui.core.a */
public class BaseFragmentDelegate implements IEtsyFragment {
    private static final String f4073b;
    protected ImageBatch f4074a;
    private final Fragment f4075c;

    static {
        f4073b = EtsyDebug.m1891a(BaseFragmentDelegate.class);
    }

    public BaseFragmentDelegate(Fragment fragment) {
        this.f4075c = fragment;
    }

    public void m5441a(Bundle bundle) {
        boolean z;
        this.f4074a = new ImageBatch();
        if (bundle != null) {
            z = bundle.getBoolean("HIDDEN");
        } else {
            z = false;
        }
        if (z) {
            EtsyDebug.m1920e(f4073b, "Had to manually hide %s (probably on orientation change) - consider replacing this instead of adding it", this.f4075c.getClass().getSimpleName());
            this.f4075c.getFragmentManager().beginTransaction().hide(this.f4075c).commit();
        }
    }

    public void m5440a() {
        AdminToolbar.m2990a(this.f4075c.getClass().getSimpleName());
    }

    public void m5442b() {
        m5445d().m1700a(this.f4075c);
        this.f4074a.m1564a();
    }

    public void m5444c() {
        if (!this.f4075c.getRetainInstance()) {
            m5445d().m1700a(this.f4075c);
        }
        this.f4074a.m1564a();
        CleanupUtil.m5532a(this.f4075c.getView());
    }

    public void m5443b(Bundle bundle) {
        bundle.putBoolean("HIDDEN", this.f4075c.isHidden());
    }

    public EtsyRequestQueue m5445d() {
        return aj.m1101a().m1123i();
    }

    public EtsyPostManager m5446e() {
        return aj.m1101a().m1124j();
    }

    public ImageBatch m5447f() {
        return this.f4074a;
    }

    public boolean handleBackPressed() {
        return false;
    }

    public void onFragmentResume() {
    }
}
