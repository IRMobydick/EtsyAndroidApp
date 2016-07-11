package com.appboy.push;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Extender;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.app.NotificationCompat.WearableExtender;
import com.appboy.AppboyImageUtils;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;

public class AppboyWearableNotificationUtils {
    private static final String TAG;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyWearableNotificationUtils.class.getName()});
    }

    public static void setWearableNotificationFeaturesIfPresentAndSupported(Context context, Builder builder, Bundle bundle) {
        if (bundle != null) {
            Extender wearableExtender = new WearableExtender();
            if (bundle.containsKey(Constants.APPBOY_PUSH_WEAR_HIDE_APP_ICON_KEY)) {
                wearableExtender.setHintHideIcon(Boolean.valueOf(Boolean.parseBoolean(bundle.getString(Constants.APPBOY_PUSH_WEAR_HIDE_APP_ICON_KEY))).booleanValue());
            }
            if (bundle.containsKey(Constants.APPBOY_PUSH_WEAR_BACKGROUND_IMAGE_URL_KEY)) {
                Bitmap downloadImageBitmap = AppboyImageUtils.downloadImageBitmap(bundle.getString(Constants.APPBOY_PUSH_WEAR_BACKGROUND_IMAGE_URL_KEY));
                if (downloadImageBitmap != null) {
                    wearableExtender.setBackground(downloadImageBitmap);
                }
            }
            for (int i = 0; isWearExtraPagePresentInBundle(bundle, i); i++) {
                CharSequence string = bundle.getString(Constants.APPBOY_PUSH_WEAR_EXTRA_PAGE_TITLE_KEY_PREFIX + i);
                CharSequence string2 = bundle.getString(Constants.APPBOY_PUSH_WEAR_EXTRA_PAGE_CONTENT_KEY_PREFIX + i);
                if (string2 == null || string == null) {
                    AppboyLogger.m662d(TAG, String.format("The title or content of extra page %s was null. Adding no further extra pages.", new Object[]{Integer.valueOf(i)}));
                    break;
                }
                Style bigTextStyle = new BigTextStyle();
                bigTextStyle.bigText(string2);
                wearableExtender.addPage(new Builder(context).setContentTitle(string).setStyle(bigTextStyle).build());
            }
            builder.extend(wearableExtender);
        }
    }

    private static boolean isWearExtraPagePresentInBundle(Bundle bundle, int i) {
        return bundle.containsKey(new StringBuilder().append(Constants.APPBOY_PUSH_WEAR_EXTRA_PAGE_TITLE_KEY_PREFIX).append(i).toString()) && bundle.containsKey(Constants.APPBOY_PUSH_WEAR_EXTRA_PAGE_CONTENT_KEY_PREFIX + i);
    }
}
