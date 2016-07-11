package com.etsy.android.lib.util;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.etsy.android.lib.core.EtsyRequestQueue;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.ap;
import com.etsy.android.lib.logger.EtsyDebug;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.iid.C1153a;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/* compiled from: MessagingUtil */
public class ak {
    private static final String f2005a;

    /* renamed from: com.etsy.android.lib.util.ak.1 */
    final class MessagingUtil extends AsyncTask<Void, Void, String> {
        final /* synthetic */ Context f2004a;

        MessagingUtil(Context context) {
            this.f2004a = context;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m3241a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m3242a((String) obj);
        }

        protected String m3241a(Void... voidArr) {
            String a;
            Throwable e;
            String str = StringUtils.EMPTY;
            try {
                C1153a c = C1153a.m6302c(this.f2004a);
                if (TextUtils.isEmpty(ag.m3220e(this.f2004a))) {
                    ag.m3217b(this.f2004a, c.m6305b());
                }
                a = c.m6303a("296956783393", "GCM", null);
                try {
                    EtsyDebug.m1906b(ak.f2005a, "Device registered, token=" + a);
                    ag.m3215a(this.f2004a, a);
                } catch (IOException e2) {
                    e = e2;
                    EtsyDebug.m1917d(ak.f2005a, "Gcm registration error :" + e.getMessage(), e);
                    return a;
                }
            } catch (Throwable e3) {
                Throwable th = e3;
                a = str;
                e = th;
                EtsyDebug.m1917d(ak.f2005a, "Gcm registration error :" + e.getMessage(), e);
                return a;
            }
            return a;
        }

        protected void m3242a(String str) {
            ak.m3244a(this.f2004a, str);
            ak.m3251g(this.f2004a);
        }
    }

    static {
        f2005a = EtsyDebug.m1891a(ak.class);
    }

    public static boolean m3245a(Context context) {
        return m3249e(context);
    }

    public static void m3246b(Context context) {
        if (!aj.m1101a().m1118d()) {
            EtsyDebug.m1906b(f2005a, "Not registering for push: not signed in");
        } else if (m3249e(context)) {
            String d = ag.m3219d(context);
            if (d.isEmpty()) {
                m3250f(context);
                return;
            }
            EtsyDebug.m1906b(f2005a, "Already registered for GCM with regId " + d);
            m3244a(context, d);
            m3251g(context);
        } else {
            EtsyDebug.m1906b(f2005a, "No GCM or ADM available");
        }
    }

    public static void m3244a(Context context, String str) {
        EtsyRequestQueue etsyRequestQueue = new EtsyRequestQueue(context.getApplicationContext());
        if (bh.m3340a(str)) {
            etsyRequestQueue.m1699a(new al(str));
        } else {
            EtsyDebug.m1916d(f2005a, "Attempted to register for Etsy notifications with empty ID");
        }
    }

    public static void m3247c(Context context) {
        new EtsyRequestQueue(context.getApplicationContext()).m1699a(new am());
    }

    private static boolean m3249e(Context context) {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable == 0) {
            return true;
        }
        if (GooglePlayServicesUtil.isUserRecoverableError(isGooglePlayServicesAvailable)) {
            EtsyDebug.m1906b(f2005a, "GCM messaging may become available from recoverable error: " + isGooglePlayServicesAvailable);
        } else {
            EtsyDebug.m1906b(f2005a, "GCM messaging not available.");
        }
        return false;
    }

    private static void m3250f(Context context) {
        ap.m1142a(new MessagingUtil(context), new Void[0]);
    }

    private static void m3251g(Context context) {
        if (context != null) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.etsy.android.lib.util.GCM_REGISTERED"));
        }
    }
}
