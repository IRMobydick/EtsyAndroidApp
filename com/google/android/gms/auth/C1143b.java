package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.auth.b.1;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.zza;
import com.google.android.gms.common.zze;
import java.io.IOException;

/* renamed from: com.google.android.gms.auth.b */
public class C1143b {
    private static final ComponentName f4693a;
    private static final ComponentName f4694b;
    public static final String f4695c;
    public static final String f4696d;

    static {
        f4695c = VERSION.SDK_INT >= 11 ? "callerUid" : "callerUid";
        f4696d = VERSION.SDK_INT >= 14 ? "androidPackageName" : "androidPackageName";
        f4693a = new ComponentName(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, "com.google.android.gms.auth.GetToken");
        f4694b = new ComponentName(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, "com.google.android.gms.recovery.RecoveryService");
    }

    private static <T> T m6258a(Context context, ComponentName componentName, c<T> cVar) {
        Throwable e;
        zza com_google_android_gms_common_zza = new zza();
        zzm zzav = zzm.zzav(context);
        if (zzav.zza(componentName, com_google_android_gms_common_zza, "GoogleAuthUtil")) {
            try {
                T b = cVar.b(com_google_android_gms_common_zza.zzqU());
                zzav.zzb(componentName, com_google_android_gms_common_zza, "GoogleAuthUtil");
                return b;
            } catch (RemoteException e2) {
                e = e2;
                try {
                    Log.i("GoogleAuthUtil", "Error on service connection.", e);
                    throw new IOException("Error on service connection.", e);
                } catch (Throwable th) {
                    zzav.zzb(componentName, com_google_android_gms_common_zza, "GoogleAuthUtil");
                }
            } catch (InterruptedException e3) {
                e = e3;
                Log.i("GoogleAuthUtil", "Error on service connection.", e);
                throw new IOException("Error on service connection.", e);
            }
        }
        throw new IOException("Could not bind to service.");
    }

    public static String m6260a(Context context, Account account, String str, Bundle bundle) {
        return C1143b.m6262b(context, account, str, bundle).getToken();
    }

    private static void m6261a(Context context) {
        try {
            zze.zzaa(context.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            throw new GooglePlayServicesAvailabilityException(e.getConnectionStatusCode(), e.getMessage(), e.getIntent());
        } catch (GooglePlayServicesNotAvailableException e2) {
            throw new GoogleAuthException(e2.getMessage());
        }
    }

    public static TokenData m6262b(Context context, Account account, String str, Bundle bundle) {
        zzaa.zzdd("Calling this from your main thread can lead to deadlock");
        zzaa.zzh(str, "Scope cannot be empty or null.");
        zzaa.zzb((Object) account, (Object) "Account cannot be null.");
        C1143b.m6261a(context);
        Bundle bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
        String str2 = context.getApplicationInfo().packageName;
        bundle2.putString("clientPackageName", str2);
        if (TextUtils.isEmpty(bundle2.getString(f4696d))) {
            bundle2.putString(f4696d, str2);
        }
        bundle2.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
        return (TokenData) C1143b.m6258a(context, f4693a, new 1(account, str, bundle2));
    }

    private static <T> T m6263b(T t) {
        if (t != null) {
            return t;
        }
        Log.w("GoogleAuthUtil", "Binder call returned null.");
        throw new IOException("Service unavailable.");
    }

    @Deprecated
    public static String m6264b(Context context, String str, String str2, Bundle bundle) {
        return C1143b.m6260a(context, new Account(str, "com.google"), str2, bundle);
    }
}
