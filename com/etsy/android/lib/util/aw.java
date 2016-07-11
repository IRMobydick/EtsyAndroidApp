package com.etsy.android.lib.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* compiled from: PermissionsHelper */
public final class aw {
    private static HashMap<Integer, WeakReference<OnRequestPermissionsResultCallback>> f2008a;

    static {
        f2008a = new HashMap();
    }

    @MainThread
    public static void m3276a(@NonNull Activity activity, @NonNull String[] strArr, int i, @Nullable OnRequestPermissionsResultCallback onRequestPermissionsResultCallback) {
        av.m3271a(activity, strArr, i);
        if (onRequestPermissionsResultCallback != null) {
            f2008a.put(Integer.valueOf(i), new WeakReference(onRequestPermissionsResultCallback));
        }
    }

    @MainThread
    public static void m3275a(@NonNull Activity activity, int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        Editor edit = activity.getSharedPreferences("permissions_state", 0).edit();
        for (String str : strArr) {
            boolean z;
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
                z = false;
            } else {
                z = true;
            }
            edit.putBoolean(str, z);
        }
        edit.apply();
        WeakReference weakReference = (WeakReference) f2008a.get(Integer.valueOf(i));
        if (weakReference != null) {
            OnRequestPermissionsResultCallback onRequestPermissionsResultCallback = (OnRequestPermissionsResultCallback) weakReference.get();
            if (onRequestPermissionsResultCallback != null) {
                onRequestPermissionsResultCallback.onRequestPermissionsResult(i, strArr, iArr);
            }
        }
        f2008a.remove(Integer.valueOf(i));
    }

    @MainThread
    public static void m3274a(int i, OnRequestPermissionsResultCallback onRequestPermissionsResultCallback) {
        if (f2008a.get(Integer.valueOf(i)) != null) {
            f2008a.put(Integer.valueOf(i), new WeakReference(onRequestPermissionsResultCallback));
        }
    }

    public static int m3273a(@NonNull Context context, @NonNull String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("permissions_state", 0);
        boolean z = sharedPreferences.getBoolean(str, false);
        int checkSelfPermission = ContextCompat.checkSelfPermission(context, str);
        if (z && checkSelfPermission == 0) {
            sharedPreferences.edit().putBoolean(str, false).apply();
        }
        return checkSelfPermission;
    }

    public static boolean m3277b(@NonNull Context context, @NonNull String str) {
        return context.getSharedPreferences("permissions_state", 0).getBoolean(str, false);
    }
}
