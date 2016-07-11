package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.etsy.android.lib.models.AppBuild;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;

@jw
public class lj {
    long f5407a;
    long f5408b;
    int f5409c;
    final String f5410d;
    int f5411e;
    int f5412f;
    private final Object f5413g;

    public lj(String str) {
        this.f5407a = -1;
        this.f5408b = -1;
        this.f5409c = -1;
        this.f5413g = new Object();
        this.f5411e = 0;
        this.f5412f = 0;
        this.f5410d = str;
    }

    public static boolean m7043a(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", AppBuild.ANDROID_PLATFORM);
        if (identifier == 0) {
            C1129c.m6190c("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            C1129c.m6190c("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (NameNotFoundException e) {
            C1129c.m6192d("Fail to fetch AdActivity theme");
            C1129c.m6190c("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }

    public long m7044a() {
        return this.f5408b;
    }

    public Bundle m7045a(Context context, String str) {
        Bundle bundle;
        synchronized (this.f5413g) {
            bundle = new Bundle();
            bundle.putString("session_id", this.f5410d);
            bundle.putLong("basets", this.f5408b);
            bundle.putLong("currts", this.f5407a);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.f5409c);
            bundle.putInt("pclick", this.f5411e);
            bundle.putInt("pimp", this.f5412f);
            bundle.putBoolean("support_transparent_background", m7043a(context));
        }
        return bundle;
    }

    public void m7046a(AdRequestParcel adRequestParcel, long j) {
        synchronized (this.f5413g) {
            if (this.f5408b == -1) {
                this.f5408b = j;
                this.f5407a = this.f5408b;
            } else {
                this.f5407a = j;
            }
            if (adRequestParcel.extras == null || adRequestParcel.extras.getInt("gw", 2) != 1) {
                this.f5409c++;
                return;
            }
        }
    }

    public void m7047b() {
        synchronized (this.f5413g) {
            this.f5411e++;
        }
    }

    public void m7048c() {
        synchronized (this.f5413g) {
            this.f5412f++;
        }
    }
}
