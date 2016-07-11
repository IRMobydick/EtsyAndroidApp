package com.etsy.android.lib.util;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.List;

/* renamed from: com.etsy.android.lib.util.j */
public class CameraUtils {
    public static boolean m3393a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && (packageManager.hasSystemFeature("android.hardware.camera") || packageManager.hasSystemFeature("android.hardware.camera.front"));
    }

    public static void m3392a(List<File> list) {
        if (list != null) {
            for (File delete : list) {
                delete.delete();
            }
            list.clear();
        }
    }
}
