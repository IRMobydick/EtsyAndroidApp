package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.internal.zzo;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.gcm.Task;

public class zzc {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final zzc zzakn;

    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        zzakn = new zzc();
    }

    zzc() {
    }

    private String zzl(@Nullable Context context, @Nullable String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gcore_");
        stringBuilder.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        stringBuilder.append("-");
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        stringBuilder.append("-");
        if (context != null) {
            stringBuilder.append(context.getPackageName());
        }
        stringBuilder.append("-");
        if (context != null) {
            try {
                stringBuilder.append(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (NameNotFoundException e) {
            }
        }
        return stringBuilder.toString();
    }

    public static zzc zzqV() {
        return zzakn;
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return zza(context, i, i2, null);
    }

    public String getErrorString(int i) {
        return zze.getErrorString(i);
    }

    @Nullable
    public String getOpenSourceSoftwareLicenseInfo(Context context) {
        return zze.getOpenSourceSoftwareLicenseInfo(context);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        int isGooglePlayServicesAvailable = zze.isGooglePlayServicesAvailable(context);
        return zze.zzc(context, isGooglePlayServicesAvailable) ? 18 : isGooglePlayServicesAvailable;
    }

    public boolean isUserResolvableError(int i) {
        return zze.isUserRecoverableError(i);
    }

    @Nullable
    public PendingIntent zza(Context context, int i, int i2, @Nullable String str) {
        if (zzi.zzaB(context) && i == 2) {
            i = 42;
        }
        Intent zza = zza(context, i, str);
        return zza == null ? null : PendingIntent.getActivity(context, i2, zza, 268435456);
    }

    @Nullable
    public Intent zza(Context context, int i, @Nullable String str) {
        switch (i) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
                return zzo.zzB(GOOGLE_PLAY_SERVICES_PACKAGE, zzl(context, str));
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return zzo.zzdi(GOOGLE_PLAY_SERVICES_PACKAGE);
            case R.AppCompatTheme_dialogTheme /*42*/:
                return zzo.zztM();
            default:
                return null;
        }
    }

    public int zzae(Context context) {
        return zze.zzae(context);
    }

    public void zzaf(Context context) {
        zze.zzaa(context);
    }

    public void zzag(Context context) {
        zze.zzag(context);
    }

    @Nullable
    @Deprecated
    public Intent zzbB(int i) {
        return zza(null, i, null);
    }

    public boolean zzc(Context context, int i) {
        return zze.zzc(context, i);
    }

    public boolean zzk(Context context, String str) {
        return zze.zzk(context, str);
    }
}
