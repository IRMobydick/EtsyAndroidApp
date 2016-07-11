package com.etsy.android.lib.messaging;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

/* renamed from: com.etsy.android.lib.messaging.a */
public class EtsyGooglePlayServicesUtil {

    /* renamed from: com.etsy.android.lib.messaging.a.1 */
    final class EtsyGooglePlayServicesUtil implements OnClickListener {
        final /* synthetic */ Activity f1865a;

        EtsyGooglePlayServicesUtil(Activity activity) {
            this.f1865a = activity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            EtsyGooglePlayServicesUtil.m2261c(this.f1865a);
        }
    }

    /* renamed from: com.etsy.android.lib.messaging.a.2 */
    final class EtsyGooglePlayServicesUtil implements OnClickListener {
        final /* synthetic */ Context f1866a;

        EtsyGooglePlayServicesUtil(Context context) {
            this.f1866a = context;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, null));
            this.f1866a.startActivity(intent);
        }
    }

    public static void m2256a(@NonNull Activity activity) {
        Object obj = (aj.m1101a().m1118d() && SharedPreferencesUtility.m3157o(activity) && EtsyGooglePlayServicesUtil.m2258a((Context) activity)) ? 1 : null;
        if (obj != null) {
            EtsyGooglePlayServicesUtil.m2260b(activity);
            SharedPreferencesUtility.m3118a((Context) activity);
        }
    }

    public static boolean m2258a(Context context) {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        return (isGooglePlayServicesAvailable == 0 || isGooglePlayServicesAvailable == 9 || !GooglePlayServicesUtil.isUserRecoverableError(isGooglePlayServicesAvailable)) ? false : true;
    }

    public static void m2260b(Activity activity) {
        if (EtsyGooglePlayServicesUtil.m2258a((Context) activity)) {
            EtsyGooglePlayServicesUtil.m2257a(activity, GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity));
        }
    }

    private static void m2257a(Activity activity, int i) {
        int i2;
        int i3;
        int i4;
        OnClickListener d;
        Builder builder = new Builder(activity);
        switch (i) {
            case Task.NETWORK_STATE_ANY /*2*/:
                i2 = R.update_google_play_services;
                i3 = R.service_update_message;
                i4 = R.update;
                d = EtsyGooglePlayServicesUtil.m2262d(activity);
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                i2 = R.enable_google_play_services;
                i3 = R.service_enable_message;
                i4 = R.enable_google_play_services;
                d = EtsyGooglePlayServicesUtil.m2259b((Context) activity);
                break;
            default:
                i2 = R.get_google_play_services;
                i3 = R.service_missing_message;
                i4 = R.get_google_play_services;
                d = EtsyGooglePlayServicesUtil.m2262d(activity);
                break;
        }
        builder.setTitle(i2);
        builder.setMessage(i3);
        builder.setPositiveButton(i4, d);
        builder.create().show();
    }

    private static OnClickListener m2262d(Activity activity) {
        return new EtsyGooglePlayServicesUtil(activity);
    }

    public static void m2261c(Activity activity) {
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.android.gms")));
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gms")));
        }
    }

    private static OnClickListener m2259b(Context context) {
        return new EtsyGooglePlayServicesUtil(context);
    }
}
