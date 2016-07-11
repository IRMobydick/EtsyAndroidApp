package com.etsy.android.uikit.util;

import android.content.Context;
import com.etsy.android.lib.R;

/* renamed from: com.etsy.android.uikit.util.t */
public class TabletSupportUtil {
    private Context f4190a;
    private boolean f4191b;
    private boolean f4192c;
    private boolean f4193d;

    public TabletSupportUtil(Context context) {
        this.f4190a = context.getApplicationContext();
        this.f4191b = !context.getResources().getBoolean(R.is_phone);
        this.f4192c = context.getResources().getBoolean(R.is_seven_inch);
        this.f4193d = context.getResources().getBoolean(R.is_ten_inch);
    }

    public boolean m5621a() {
        return this.f4191b;
    }

    public boolean m5622b() {
        return !this.f4191b;
    }

    public boolean m5623c() {
        return this.f4193d;
    }

    public boolean m5624d() {
        return this.f4190a.getResources().getConfiguration().orientation == 2;
    }

    public boolean m5625e() {
        return !m5624d();
    }

    public boolean m5626f() {
        return m5621a() && m5624d();
    }

    public boolean m5627g() {
        return m5622b() && m5624d();
    }
}
