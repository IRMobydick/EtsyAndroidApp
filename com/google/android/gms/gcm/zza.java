package com.google.android.gms.gcm;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;
import com.foresee.sdk.configuration.Configuration;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

class zza {
    static zza f4708a;
    private final Context f4709b;

    private zza(Context context) {
        this.f4709b = context.getApplicationContext();
    }

    private int m6281a() {
        return (int) SystemClock.uptimeMillis();
    }

    static synchronized zza m6282a(Context context) {
        zza com_google_android_gms_gcm_zza;
        synchronized (zza.class) {
            if (f4708a == null) {
                f4708a = new zza(context);
            }
            com_google_android_gms_gcm_zza = f4708a;
        }
        return com_google_android_gms_gcm_zza;
    }

    static String m6283a(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    private String m6284a(String str) {
        return str.substring("gcm.n.".length());
    }

    private void m6285a(String str, Notification notification) {
        if (Log.isLoggable("GcmNotification", 3)) {
            Log.d("GcmNotification", "Showing notification");
        }
        NotificationManager notificationManager = (NotificationManager) this.f4709b.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME);
        if (TextUtils.isEmpty(str)) {
            str = "GCM-Notification:" + SystemClock.uptimeMillis();
        }
        notificationManager.notify(str, 0, notification);
    }

    static boolean m6286a(Bundle bundle) {
        return "1".equals(m6283a(bundle, "gcm.n.e")) || m6283a(bundle, "gcm.n.icon") != null;
    }

    private int m6287b(String str) {
        int identifier;
        if (!TextUtils.isEmpty(str)) {
            Resources resources = this.f4709b.getResources();
            identifier = resources.getIdentifier(str, "drawable", this.f4709b.getPackageName());
            if (identifier != 0) {
                return identifier;
            }
            identifier = resources.getIdentifier(str, "mipmap", this.f4709b.getPackageName());
            if (identifier != 0) {
                return identifier;
            }
            Log.w("GcmNotification", new StringBuilder(String.valueOf(str).length() + 57).append("Icon resource ").append(str).append(" not found. Notification will use app icon.").toString());
        }
        identifier = this.f4709b.getApplicationInfo().icon;
        return identifier == 0 ? 17301651 : identifier;
    }

    private String m6288b(Bundle bundle, String str) {
        Object a = m6283a(bundle, str);
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        valueOf = m6283a(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(valueOf)) {
            return null;
        }
        Resources resources = this.f4709b.getResources();
        int identifier = resources.getIdentifier(valueOf, "string", this.f4709b.getPackageName());
        if (identifier == 0) {
            String str2 = "GcmNotification";
            String valueOf3 = String.valueOf(str);
            valueOf2 = String.valueOf("_loc_key");
            valueOf2 = String.valueOf(m6284a(valueOf2.length() != 0 ? valueOf3.concat(valueOf2) : new String(valueOf3)));
            Log.w(str2, new StringBuilder((String.valueOf(valueOf2).length() + 49) + String.valueOf(valueOf).length()).append(valueOf2).append(" resource not found: ").append(valueOf).append(" Default value will be used.").toString());
            return null;
        }
        String valueOf4 = String.valueOf(str);
        valueOf2 = String.valueOf("_loc_args");
        valueOf4 = m6283a(bundle, valueOf2.length() != 0 ? valueOf4.concat(valueOf2) : new String(valueOf4));
        if (TextUtils.isEmpty(valueOf4)) {
            return resources.getString(identifier);
        }
        try {
            JSONArray jSONArray = new JSONArray(valueOf4);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.opt(i);
            }
            return resources.getString(identifier, strArr);
        } catch (JSONException e) {
            valueOf = "GcmNotification";
            str2 = String.valueOf(str);
            valueOf2 = String.valueOf("_loc_args");
            valueOf2 = String.valueOf(m6284a(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2)));
            Log.w(valueOf, new StringBuilder((String.valueOf(valueOf2).length() + 41) + String.valueOf(valueOf4).length()).append("Malformed ").append(valueOf2).append(": ").append(valueOf4).append("  Default value will be used.").toString());
            return null;
        } catch (Throwable e2) {
            Log.w("GcmNotification", new StringBuilder((String.valueOf(valueOf).length() + 58) + String.valueOf(valueOf4).length()).append("Missing format argument for ").append(valueOf).append(": ").append(valueOf4).append(" Default value will be used.").toString(), e2);
            return null;
        }
    }

    static void m6289b(Bundle bundle) {
        String str;
        Bundle bundle2 = new Bundle();
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            str = (String) it.next();
            String string = bundle.getString(str);
            if (str.startsWith("gcm.notification.")) {
                str = str.replace("gcm.notification.", "gcm.n.");
            }
            if (str.startsWith("gcm.n.")) {
                if (!"gcm.n.e".equals(str)) {
                    bundle2.putString(str.substring("gcm.n.".length()), string);
                }
                it.remove();
            }
        }
        str = bundle2.getString("sound2");
        if (str != null) {
            bundle2.remove("sound2");
            bundle2.putString("sound", str);
        }
        if (!bundle2.isEmpty()) {
            bundle.putBundle(Configuration.NOTIFICATION_LAYOUT_NAME, bundle2);
        }
    }

    static boolean m6290b(Context context) {
        if (((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return false;
        }
        int myPid = Process.myPid();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.importance == 100;
            }
        }
        return false;
    }

    private Uri m6291c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if ("default".equals(str) || this.f4709b.getResources().getIdentifier(str, "raw", this.f4709b.getPackageName()) == 0) {
            return RingtoneManager.getDefaultUri(2);
        }
        String valueOf = String.valueOf("android.resource://");
        String valueOf2 = String.valueOf(this.f4709b.getPackageName());
        return Uri.parse(new StringBuilder(((String.valueOf(valueOf).length() + 5) + String.valueOf(valueOf2).length()) + String.valueOf(str).length()).append(valueOf).append(valueOf2).append("/raw/").append(str).toString());
    }

    private Notification m6292d(Bundle bundle) {
        CharSequence b = m6288b(bundle, "gcm.n.title");
        CharSequence b2 = m6288b(bundle, "gcm.n.body");
        int b3 = m6287b(m6283a(bundle, "gcm.n.icon"));
        Object a = m6283a(bundle, "gcm.n.color");
        Uri c = m6291c(m6283a(bundle, "gcm.n.sound2"));
        PendingIntent e = m6293e(bundle);
        Builder smallIcon = new Builder(this.f4709b).setAutoCancel(true).setSmallIcon(b3);
        if (TextUtils.isEmpty(b)) {
            smallIcon.setContentTitle(this.f4709b.getApplicationInfo().loadLabel(this.f4709b.getPackageManager()));
        } else {
            smallIcon.setContentTitle(b);
        }
        if (!TextUtils.isEmpty(b2)) {
            smallIcon.setContentText(b2);
        }
        if (!TextUtils.isEmpty(a)) {
            smallIcon.setColor(Color.parseColor(a));
        }
        if (c != null) {
            smallIcon.setSound(c);
        }
        if (e != null) {
            smallIcon.setContentIntent(e);
        }
        return smallIcon.build();
    }

    private PendingIntent m6293e(Bundle bundle) {
        Intent intent;
        Object a = m6283a(bundle, "gcm.n.click_action");
        Intent launchIntentForPackage;
        if (TextUtils.isEmpty(a)) {
            launchIntentForPackage = this.f4709b.getPackageManager().getLaunchIntentForPackage(this.f4709b.getPackageName());
            if (launchIntentForPackage == null) {
                Log.w("GcmNotification", "No activity found to launch app");
                return null;
            }
            intent = launchIntentForPackage;
        } else {
            launchIntentForPackage = new Intent(a);
            launchIntentForPackage.setPackage(this.f4709b.getPackageName());
            launchIntentForPackage.setFlags(268435456);
            intent = launchIntentForPackage;
        }
        Bundle bundle2 = new Bundle(bundle);
        GcmListenerService.zzz(bundle2);
        intent.putExtras(bundle2);
        for (String str : bundle2.keySet()) {
            if (str.startsWith("gcm.n.") || str.startsWith("gcm.notification.")) {
                intent.removeExtra(str);
            }
        }
        return PendingIntent.getActivity(this.f4709b, m6281a(), intent, 1073741824);
    }

    boolean m6294c(Bundle bundle) {
        try {
            m6285a(m6283a(bundle, "gcm.n.tag"), m6292d(bundle));
            return true;
        } catch (zza e) {
            String str = "GcmNotification";
            String str2 = "Failed to show notification: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return false;
        }
    }
}