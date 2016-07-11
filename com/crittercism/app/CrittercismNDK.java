package com.crittercism.app;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import crittercism.android.dy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CrittercismNDK {
    private static final String LEGACY_SO_FILE_NAME = "libcrittercism-ndk.so";
    private static final String LIBRARY_NAME = "crittercism-v3";
    private static final String SO_FILE_NAME = "libcrittercism-v3.so";
    private static boolean isNdkInstalled;

    public static native boolean installNdk(String str);

    static {
        isNdkInstalled = false;
    }

    public static File getInstalledLibraryFile(Context context) {
        return new File((context.getFilesDir().getAbsolutePath() + "/com.crittercism/lib/") + SO_FILE_NAME);
    }

    public static void installNdkLib(Context context, String str) {
        boolean z = true;
        String str2 = context.getFilesDir().getAbsolutePath() + "/" + str;
        if (doNdkSharedLibrariesExist(context)) {
            z = loadLibraryFromAssets(context);
        } else {
            try {
                System.loadLibrary(LIBRARY_NAME);
            } catch (Throwable th) {
                z = false;
            }
        }
        if (z) {
            try {
                if (installNdk(str2)) {
                    new File(str2).mkdirs();
                    isNdkInstalled = true;
                    return;
                }
                dy.c("Unable to initialize NDK crash reporting.");
            } catch (Throwable th2) {
            }
        }
    }

    public static boolean loadLibraryFromAssets(Context context) {
        File file = new File(context.getFilesDir(), "/com.crittercism/lib/");
        File file2 = new File(file, SO_FILE_NAME);
        File file3 = new File(file, LEGACY_SO_FILE_NAME);
        if (!file2.exists()) {
            if (copyLibFromAssets(context, file2)) {
                file3.delete();
            } else {
                file2.delete();
                return false;
            }
        }
        try {
            System.load(file2.getAbsolutePath());
            return true;
        } catch (Throwable th) {
            dy.a("Unable to install NDK library", th);
            file2.delete();
            return false;
        }
    }

    public static boolean doNdkSharedLibrariesExist(Context context) {
        try {
            getJarredLibFileStream(context);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static InputStream getJarredLibFileStream(Context context) {
        String str = "armeabi";
        if (System.getProperty("os.arch").contains("v7")) {
            str = str + "-v7a";
        }
        return context.getAssets().open(str + "/libcrittercism-v3.so");
    }

    public static boolean copyLibFromAssets(Context context, File file) {
        dy.b();
        try {
            file.getParentFile().mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream jarredLibFileStream = getJarredLibFileStream(context);
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
            while (true) {
                int read = jarredLibFileStream.read(bArr);
                if (read >= 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    jarredLibFileStream.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            dy.b("Could not install breakpad library: " + e.toString());
            return false;
        }
    }

    public static boolean isNdkCrashReportingInstalled() {
        return isNdkInstalled;
    }
}
