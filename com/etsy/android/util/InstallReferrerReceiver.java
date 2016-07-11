package com.etsy.android.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.adjust.sdk.AdjustReferrerReceiver;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.ui.nav.NotificationActivity;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import com.google.ads.conversiontracking.InstallReceiver;
import java.net.URLDecoder;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class InstallReferrerReceiver extends BroadcastReceiver {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(InstallReferrerReceiver.class);
    }

    public void onReceive(Context context, Intent intent) {
        forwardToReceivers(context, intent);
        HashMap hashMap = new HashMap();
        try {
            if (intent.hasExtra(Constants.REFERRER)) {
                for (String split : intent.getStringExtra(Constants.REFERRER).split("&")) {
                    String[] split2 = split.split("=");
                    hashMap.put(URLDecoder.decode(split2[0], Constants.ENCODING), split2.length > 1 ? URLDecoder.decode(split2[1], Constants.ENCODING) : StringUtils.EMPTY);
                }
            }
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "URLDecoder could not decode referrer data", e);
        }
        new AdHocEventCompatBuilder("installed_app_from_play_store").m5515a("app").m5516a(hashMap).m5517a();
        if (hashMap.containsKey(com.appboy.Constants.APPBOY_PUSH_DEEP_LINK_KEY) && !hashMap.containsKey("__ysurl")) {
            Uri a = DeepLinkUtil.m5704a(hashMap);
            if (a != null && !a.getHost().equals("home")) {
                Intent intent2 = new Intent("android.intent.action.VIEW", a.buildUpon().appendQueryParameter(NotificationActivity.ETSY_DEFERRED_PARAM, "1").build());
                intent2.setClass(context, NotificationActivity.class);
                intent2.addFlags(268435456);
                context.startActivity(intent2);
            }
        }
    }

    private void forwardToReceivers(Context context, Intent intent) {
        new YozioInstallReferrerReceiver().onReceive(context, intent);
        new AdjustReferrerReceiver().onReceive(context, intent);
        new InstallReceiver().onReceive(context, intent);
    }
}
