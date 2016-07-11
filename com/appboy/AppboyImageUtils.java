package com.appboy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PermissionUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class AppboyImageUtils {
    public static final int BASELINE_SCREEN_DPI = 160;
    private static final String TAG;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyImageUtils.class.getName()});
    }

    public static Bitmap downloadImageBitmap(String str) {
        Bitmap bitmap = null;
        if (str == null || str.length() == 0) {
            AppboyLogger.m666i(TAG, "Null or empty Url string passed to image bitmap download. Not attempting download.");
        } else {
            try {
                InputStream openStream = new URL(str).openStream();
                bitmap = BitmapFactory.decodeStream(openStream);
                if (openStream != null) {
                    openStream.close();
                }
            } catch (Throwable e) {
                AppboyLogger.m665e(TAG, String.format("Out of Memory Error in image bitmap download for Url: %s.", new Object[]{str}), e);
            } catch (Throwable e2) {
                AppboyLogger.m665e(TAG, String.format("Unknown Host Exception in image bitmap download for Url: %s. Device may be offline.", new Object[]{str}), e2);
            } catch (Throwable e22) {
                AppboyLogger.m665e(TAG, String.format("Malformed URL Exception in image bitmap download for Url: %s. Image Url may be corrupted.", new Object[]{str}), e22);
            } catch (Throwable e222) {
                AppboyLogger.m665e(TAG, String.format("Exception in image bitmap download for Url: %s", new Object[]{str}), e222);
            }
        }
        return bitmap;
    }

    public static int getPixelsFromDensityAndDp(int i, int i2) {
        return (i * i2) / BASELINE_SCREEN_DPI;
    }

    public static Uri storeBitmapLocally(Context context, Bitmap bitmap, String str, String str2) {
        Uri uri = null;
        if (context == null) {
            AppboyLogger.m670w(TAG, "Received null context. Doing nothing.");
        } else if (bitmap == null) {
            AppboyLogger.m670w(TAG, "Received null bitmap. Doing nothing.");
        } else if (str == null) {
            AppboyLogger.m670w(TAG, "Received null image filename base. Doing nothing.");
        } else if (str2 == null) {
            AppboyLogger.m670w(TAG, "Received null image folder name. Doing nothing.");
        } else {
            try {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + str2);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, str + ".png");
                AppboyLogger.m662d(TAG, "Storing image locally at " + file2.getAbsolutePath());
                OutputStream fileOutputStream = new FileOutputStream(file2);
                bitmap.compress(CompressFormat.PNG, 0, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                MediaScannerConnection.scanFile(context, new String[]{file2.getAbsolutePath()}, null, null);
                uri = Uri.fromFile(file2);
            } catch (Throwable e) {
                AppboyLogger.m665e(TAG, "Exception occurred when attempting to store image locally.", e);
            }
        }
        return uri;
    }

    public static boolean isWriteExternalPermissionGranted(Context context) {
        return context != null && PermissionUtils.hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }
}
