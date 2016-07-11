package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.List;

@jw
public class em implements bq {
    private CustomTabsSession f4883a;
    private CustomTabsClient f4884b;
    private CustomTabsServiceConnection f4885c;
    private en f4886d;

    public static boolean m6484a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        List queryIntentActivities = packageManager.queryIntentActivities(intent, AccessibilityNodeInfoCompat.ACTION_CUT);
        if (queryIntentActivities == null || resolveActivity == null) {
            return false;
        }
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            if (resolveActivity.activityInfo.name.equals(((ResolveInfo) queryIntentActivities.get(i)).activityInfo.name)) {
                return resolveActivity.activityInfo.packageName.equals(bo.a(context));
            }
        }
        return false;
    }

    public CustomTabsSession m6485a() {
        if (this.f4884b == null) {
            this.f4883a = null;
        } else if (this.f4883a == null) {
            this.f4883a = this.f4884b.newSession(null);
        }
        return this.f4883a;
    }

    public void m6486a(Activity activity) {
        if (this.f4885c != null) {
            activity.unbindService(this.f4885c);
            this.f4884b = null;
            this.f4883a = null;
            this.f4885c = null;
        }
    }

    public void m6487a(en enVar) {
        this.f4886d = enVar;
    }

    public void m6488b(Activity activity) {
        if (this.f4884b == null) {
            String a = bo.a(activity);
            if (a != null) {
                this.f4885c = new bp(this);
                CustomTabsClient.bindCustomTabsService(activity, a, this.f4885c);
            }
        }
    }
}
