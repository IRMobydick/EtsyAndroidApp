package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.appboy.Constants;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.f;
import com.google.android.gms.internal.qw;
import com.google.android.gms.internal.rc;
import com.google.android.gms.internal.zzol;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.tasks.a;
import com.google.android.gms.tasks.c;
import org.apache.commons.lang3.StringUtils;

public class GoogleApiAvailability extends zzc {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailability zzakm;

    static {
        zzakm = new GoogleApiAvailability();
        GOOGLE_PLAY_SERVICES_VERSION_CODE = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    GoogleApiAvailability() {
    }

    public static GoogleApiAvailability getInstance() {
        return zzakm;
    }

    public Dialog getErrorDialog(Activity activity, int i, int i2) {
        return GooglePlayServicesUtil.getErrorDialog(i, activity, i2);
    }

    public Dialog getErrorDialog(Activity activity, int i, int i2, OnCancelListener onCancelListener) {
        return GooglePlayServicesUtil.getErrorDialog(i, activity, i2, onCancelListener);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return super.getErrorResolutionPendingIntent(context, i, i2);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            return connectionResult.getResolution();
        }
        int errorCode = connectionResult.getErrorCode();
        if (zzi.zzaB(context) && errorCode == 2) {
            errorCode = 42;
        }
        return getErrorResolutionPendingIntent(context, errorCode, 0);
    }

    public final String getErrorString(int i) {
        return super.getErrorString(i);
    }

    @Nullable
    public String getOpenSourceSoftwareLicenseInfo(Context context) {
        return super.getOpenSourceSoftwareLicenseInfo(context);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }

    public final boolean isUserResolvableError(int i) {
        return super.isUserResolvableError(i);
    }

    @MainThread
    public a<Void> makeGooglePlayServicesAvailable(Activity activity) {
        zzaa.zzdc("makeGooglePlayServicesAvailable must be called from the main thread");
        int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable(activity);
        if (isGooglePlayServicesAvailable == 0) {
            return c.a(null);
        }
        zzow zzu = zzow.zzu(activity);
        zzu.zzk(new ConnectionResult(isGooglePlayServicesAvailable, null));
        return zzu.getTask();
    }

    public boolean showErrorDialogFragment(Activity activity, int i, int i2) {
        return GooglePlayServicesUtil.showErrorDialogFragment(i, activity, i2);
    }

    public boolean showErrorDialogFragment(Activity activity, int i, int i2, OnCancelListener onCancelListener) {
        return GooglePlayServicesUtil.showErrorDialogFragment(i, activity, i2, onCancelListener);
    }

    public void showErrorNotification(Context context, int i) {
        if (i == 6) {
            Log.e("GoogleApiAvailability", "showErrorNotification(context, errorCode) is called for RESOLUTION_REQUIRED when showErrorNotification(context, result) should be called");
        }
        if (isUserResolvableError(i)) {
            GooglePlayServicesUtil.showErrorNotification(i, context);
        }
    }

    public void showErrorNotification(Context context, ConnectionResult connectionResult) {
        PendingIntent errorResolutionPendingIntent = getErrorResolutionPendingIntent(context, connectionResult);
        if (errorResolutionPendingIntent != null) {
            GooglePlayServicesUtil.zza(connectionResult.getErrorCode(), context, errorResolutionPendingIntent);
        }
    }

    public Dialog zza(Activity activity, OnCancelListener onCancelListener) {
        View progressBar = new ProgressBar(activity, null, 16842874);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        Builder builder = new Builder(activity);
        builder.setView(progressBar);
        String zzam = zze.zzam(activity);
        builder.setMessage(activity.getResources().getString(f.common_google_play_services_updating_text, new Object[]{zzam}));
        builder.setTitle(f.common_google_play_services_updating_title);
        builder.setPositiveButton(StringUtils.EMPTY, null);
        Dialog create = builder.create();
        GooglePlayServicesUtil.zza(activity, onCancelListener, "GooglePlayServicesUpdatingDialog", create);
        return create;
    }

    @Nullable
    public PendingIntent zza(Context context, int i, int i2, @Nullable String str) {
        return super.zza(context, i, i2, str);
    }

    @Nullable
    public Intent zza(Context context, int i, @Nullable String str) {
        return super.zza(context, i, str);
    }

    @Nullable
    public zzol zza(Context context, qw qwVar) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        zzol com_google_android_gms_internal_zzol = new zzol(qwVar);
        context.registerReceiver(com_google_android_gms_internal_zzol, intentFilter);
        com_google_android_gms_internal_zzol.setContext(context);
        if (zzk(context, GOOGLE_PLAY_SERVICES_PACKAGE)) {
            return com_google_android_gms_internal_zzol;
        }
        qwVar.a();
        com_google_android_gms_internal_zzol.unregister();
        return null;
    }

    public void zza(Context context, ConnectionResult connectionResult, int i) {
        PendingIntent errorResolutionPendingIntent = getErrorResolutionPendingIntent(context, connectionResult);
        if (errorResolutionPendingIntent != null) {
            GooglePlayServicesUtil.zza(connectionResult.getErrorCode(), context, GoogleApiActivity.zza(context, errorResolutionPendingIntent, i));
        }
    }

    public boolean zza(Activity activity, @NonNull rc rcVar, int i, int i2, OnCancelListener onCancelListener) {
        Dialog zza = GooglePlayServicesUtil.zza(i, activity, com.google.android.gms.common.internal.zzi.zza(rcVar, zza((Context) activity, i, Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE), i2), onCancelListener);
        if (zza == null) {
            return false;
        }
        GooglePlayServicesUtil.zza(activity, onCancelListener, GooglePlayServicesUtil.GMS_ERROR_DIALOG, zza);
        return true;
    }

    public int zzae(Context context) {
        return super.zzae(context);
    }

    @Nullable
    @Deprecated
    public Intent zzbB(int i) {
        return super.zzbB(i);
    }

    public boolean zzc(Context context, int i) {
        return super.zzc(context, i);
    }
}
