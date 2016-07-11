package com.etsy.android.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.appboy.Constants;
import com.appboy.push.AppboyNotificationUtils;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.ui.nav.NotificationActivity;
import com.etsy.android.util.AppboyUtil;
import org.apache.commons.lang3.StringUtils;

public class AppboyBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(AppboyBroadcastReceiver.class);
    }

    public void onReceive(Context context, Intent intent) {
        String packageName = context.getPackageName();
        String str = packageName + AppboyNotificationUtils.APPBOY_NOTIFICATION_RECEIVED_SUFFIX;
        packageName = packageName + AppboyNotificationUtils.APPBOY_NOTIFICATION_OPENED_SUFFIX;
        String action = intent.getAction();
        EtsyDebug.m1914c(TAG, "Received intent with action %s", action);
        Bundle b = AppboyUtil.m5691b(intent);
        if (str.equals(action)) {
            if (AppboyNotificationUtils.isUninstallTrackingPush(intent.getExtras())) {
                EtsyDebug.m1912c(TAG, "Got uninstall tracking push");
            }
        } else if (packageName.equals(action)) {
            AppboyUtil.m5688a(intent, b);
            AppboyUtil.m5686a(context, "android_opened_campaign_" + b.getString("utm_campaign", "default_campaign"));
            if (intent.getBooleanExtra(Constants.APPBOY_ACTION_IS_CUSTOM_ACTION_KEY, false)) {
                EtsyDebug.m1912c(TAG, "Clicked a custom action.");
                return;
            }
            Intent intent2 = new Intent(context, NotificationActivity.class);
            intent2.setAction("com.etsy.android.action.NOTIFICATION");
            intent2.setFlags(872415232);
            if (TextUtils.isEmpty(intent.getStringExtra(Constants.APPBOY_PUSH_DEEP_LINK_KEY))) {
                intent2.setData(getListingLandingPageUri(b));
            } else {
                intent2.setData(Uri.parse(intent.getStringExtra(Constants.APPBOY_PUSH_DEEP_LINK_KEY)));
            }
            intent2.putExtras(b);
            context.startActivity(intent2);
        } else {
            EtsyDebug.m1914c(TAG, "Ignoring intent with unsupported action %s", action);
        }
    }

    private Uri getListingLandingPageUri(Bundle bundle) {
        String string = bundle.getString(ResponseConstants.API_PATH, StringUtils.EMPTY);
        String string2 = bundle.getString(FindsModule.FIELD_TITLE, StringUtils.EMPTY);
        String string3 = bundle.getString(ResponseConstants.EVENT_NAME, StringUtils.EMPTY);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        if (!string.startsWith("/")) {
            string = "/" + string;
        }
        return new Builder().scheme("etsy").authority("listing-landing-page").appendQueryParameter(ResponseConstants.API_PATH, string).appendQueryParameter(FindsModule.FIELD_TITLE, string2).appendQueryParameter(ResponseConstants.EVENT_NAME, string3).build();
    }
}
