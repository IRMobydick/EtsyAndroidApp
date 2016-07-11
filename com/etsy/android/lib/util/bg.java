package com.etsy.android.lib.util;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.os.StrictMode.VmPolicy;

/* compiled from: StrictModeUtil */
public class bg {
    public static void m3328a(boolean z) {
        if (z) {
            ThreadPolicy build = new Builder().detectDiskReads().detectDiskWrites().detectAll().penaltyLog().build();
            if (build != null) {
                StrictMode.setThreadPolicy(build);
            }
            VmPolicy build2 = new VmPolicy.Builder().detectAll().penaltyLog().build();
            if (build2 != null) {
                StrictMode.setVmPolicy(build2);
                return;
            }
            return;
        }
        StrictMode.setThreadPolicy(new Builder().permitAll().build());
    }

    public static void m3329b(boolean z) {
        if (z) {
            StrictMode.setThreadPolicy(new Builder().permitAll().build());
        }
    }
}
