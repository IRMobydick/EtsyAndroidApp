package com.appboy.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Style;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.appboy.AppboyImageUtils;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;

public class AppboyNotificationStyleFactory {
    public static final int BIG_PICTURE_STYLE_IMAGE_HEIGHT = 192;
    private static final String TAG;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyNotificationStyleFactory.class.getName()});
    }

    @TargetApi(16)
    public static Style getBigNotificationStyle(Context context, Bundle bundle, Bundle bundle2) {
        Style style = null;
        if (bundle2 != null && bundle2.containsKey(Constants.APPBOY_PUSH_BIG_IMAGE_URL_KEY)) {
            style = getBigPictureNotificationStyle(context, bundle, bundle2);
        }
        if (style != null) {
            return style;
        }
        AppboyLogger.m662d(TAG, "Rendering push notification with BigTextStyle");
        return getBigTextNotificationStyle(bundle);
    }

    public static BigTextStyle getBigTextNotificationStyle(Bundle bundle) {
        CharSequence charSequence = null;
        if (bundle == null) {
            return null;
        }
        CharSequence string;
        BigTextStyle bigTextStyle = new BigTextStyle();
        bigTextStyle.bigText(bundle.getString(Constants.APPBOY_PUSH_CONTENT_KEY));
        if (bundle.containsKey(Constants.APPBOY_PUSH_BIG_SUMMARY_TEXT_KEY)) {
            string = bundle.getString(Constants.APPBOY_PUSH_BIG_SUMMARY_TEXT_KEY);
        } else {
            string = null;
        }
        if (bundle.containsKey(Constants.APPBOY_PUSH_BIG_TITLE_TEXT_KEY)) {
            charSequence = bundle.getString(Constants.APPBOY_PUSH_BIG_TITLE_TEXT_KEY);
        }
        if (string != null) {
            bigTextStyle.setSummaryText(string);
        }
        if (charSequence != null) {
            bigTextStyle.setBigContentTitle(charSequence);
        }
        return bigTextStyle;
    }

    @TargetApi(16)
    public static BigPictureStyle getBigPictureNotificationStyle(Context context, Bundle bundle, Bundle bundle2) {
        if (bundle2 == null || !bundle2.containsKey(Constants.APPBOY_PUSH_BIG_IMAGE_URL_KEY)) {
            return null;
        }
        String string = bundle2.getString(Constants.APPBOY_PUSH_BIG_IMAGE_URL_KEY);
        if (string == null) {
            return null;
        }
        Bitmap downloadImageBitmap = AppboyImageUtils.downloadImageBitmap(string);
        if (downloadImageBitmap == null) {
            return null;
        }
        try {
            if (downloadImageBitmap.getWidth() > downloadImageBitmap.getHeight()) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                int pixelsFromDensityAndDp = AppboyImageUtils.getPixelsFromDensityAndDp(displayMetrics.densityDpi, BIG_PICTURE_STYLE_IMAGE_HEIGHT);
                int i = pixelsFromDensityAndDp * 2;
                if (i > displayMetrics.widthPixels) {
                    i = displayMetrics.widthPixels;
                }
                try {
                    downloadImageBitmap = Bitmap.createScaledBitmap(downloadImageBitmap, i, pixelsFromDensityAndDp, true);
                } catch (Throwable e) {
                    AppboyLogger.m665e(TAG, "Failed to scale image bitmap, using original.", e);
                }
            }
            if (downloadImageBitmap == null) {
                AppboyLogger.m666i(TAG, "Bitmap download failed for push notification. No image will be included with the notification.");
                return null;
            }
            BigPictureStyle bigPictureStyle = new BigPictureStyle();
            bigPictureStyle.bigPicture(downloadImageBitmap);
            setBigPictureSummaryAndTitle(bigPictureStyle, bundle);
            return bigPictureStyle;
        } catch (Throwable e2) {
            AppboyLogger.m665e(TAG, "Failed to create Big Picture Style.", e2);
            return null;
        }
    }

    static void setBigPictureSummaryAndTitle(BigPictureStyle bigPictureStyle, Bundle bundle) {
        CharSequence string;
        CharSequence charSequence = null;
        if (bundle.containsKey(Constants.APPBOY_PUSH_BIG_SUMMARY_TEXT_KEY)) {
            string = bundle.getString(Constants.APPBOY_PUSH_BIG_SUMMARY_TEXT_KEY);
        } else {
            string = null;
        }
        if (bundle.containsKey(Constants.APPBOY_PUSH_BIG_TITLE_TEXT_KEY)) {
            charSequence = bundle.getString(Constants.APPBOY_PUSH_BIG_TITLE_TEXT_KEY);
        }
        if (string != null) {
            bigPictureStyle.setSummaryText(string);
        }
        if (charSequence != null) {
            bigPictureStyle.setBigContentTitle(charSequence);
        }
        if (bundle.getString(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY) == null && string == null) {
            bigPictureStyle.setSummaryText(bundle.getString(Constants.APPBOY_PUSH_CONTENT_KEY));
        }
    }
}
