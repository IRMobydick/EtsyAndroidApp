package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lc;
import org.apache.commons.lang3.StringUtils;

@jw
/* renamed from: com.google.android.gms.ads.internal.b */
public class C1078b {
    private final Context f4382a;
    private final AutoClickProtectionConfigurationParcel f4383b;
    private boolean f4384c;

    public C1078b(Context context) {
        this(context, false);
    }

    public C1078b(Context context, lc lcVar) {
        this.f4382a = context;
        if (lcVar == null || lcVar.f5353b.zzMj == null) {
            this.f4383b = new AutoClickProtectionConfigurationParcel();
        } else {
            this.f4383b = lcVar.f5353b.zzMj;
        }
    }

    public C1078b(Context context, boolean z) {
        this.f4382a = context;
        this.f4383b = new AutoClickProtectionConfigurationParcel(z);
    }

    public void m5854a() {
        this.f4384c = true;
    }

    public void m5855a(String str) {
        if (str == null) {
            str = StringUtils.EMPTY;
        }
        C1129c.m6190c("Action was blocked because no touch was detected.");
        if (this.f4383b.zzMl && this.f4383b.zzMm != null) {
            for (String str2 : this.f4383b.zzMm) {
                if (!TextUtils.isEmpty(str2)) {
                    C1101o.m6041e().m7103a(this.f4382a, StringUtils.EMPTY, str2.replace("{NAVIGATION_URL}", Uri.encode(str)));
                }
            }
        }
    }

    public boolean m5856b() {
        return !this.f4383b.zzMl || this.f4384c;
    }
}
