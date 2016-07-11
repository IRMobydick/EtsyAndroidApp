package com.etsy.android.lib.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import java.util.ArrayList;

/* compiled from: PermissionUtil */
public abstract class av {
    public static boolean m3272a(int[] iArr) {
        if (iArr.length < 1) {
            return false;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static void m3271a(@NonNull Activity activity, @NonNull String[] strArr, int i) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (aw.m3273a((Context) activity, str) != 0) {
                arrayList.add(str);
            }
        }
        if (!arrayList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), i);
        }
    }
}
