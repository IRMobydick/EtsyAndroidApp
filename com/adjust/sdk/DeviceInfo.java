package com.adjust.sdk;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.DisplayMetrics;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

class DeviceInfo {
    String androidId;
    String appVersion;
    String clientSdk;
    String country;
    String deviceManufacturer;
    String deviceName;
    String deviceType;
    String displayHeight;
    String displayWidth;
    String fbAttributionId;
    String language;
    String macSha1;
    String macShortMd5;
    String osName;
    String osVersion;
    String packageName;
    Map<String, String> pluginKeys;
    String screenDensity;
    String screenFormat;
    String screenSize;

    DeviceInfo(Context context, String str) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = configuration.locale;
        int i = configuration.screenLayout;
        boolean z = Reflection.getPlayAdId(context) != null;
        String macAddress = getMacAddress(context, z);
        this.packageName = getPackageName(context);
        this.appVersion = getAppVersion(context);
        this.deviceType = getDeviceType(i);
        this.deviceName = getDeviceName();
        this.deviceManufacturer = getDeviceManufacturer();
        this.osName = getOsName();
        this.osVersion = getOsVersion();
        this.language = getLanguage(locale);
        this.country = getCountry(locale);
        this.screenSize = getScreenSize(i);
        this.screenFormat = getScreenFormat(i);
        this.screenDensity = getScreenDensity(displayMetrics);
        this.displayWidth = getDisplayWidth(displayMetrics);
        this.displayHeight = getDisplayHeight(displayMetrics);
        this.clientSdk = getClientSdk(str);
        this.androidId = getAndroidId(context, z);
        this.fbAttributionId = getFacebookAttributionId(context);
        this.pluginKeys = Reflection.getPluginKeys(context);
        this.macSha1 = getMacSha1(macAddress);
        this.macShortMd5 = getMacShortMd5(macAddress);
    }

    private String getMacAddress(Context context, boolean z) {
        if (z) {
            return null;
        }
        if (!Util.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            AdjustFactory.getLogger().warn("Missing permission: ACCESS_WIFI_STATE", new Object[0]);
        }
        return Reflection.getMacAddress(context);
    }

    private String getPackageName(Context context) {
        return context.getPackageName();
    }

    private String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    private String getDeviceType(int i) {
        switch (i & 15) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
                return ResponseConstants.PHONE;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return "tablet";
            default:
                return null;
        }
    }

    private String getDeviceName() {
        return Build.MODEL;
    }

    private String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    private String getOsName() {
        return AppBuild.ANDROID_PLATFORM;
    }

    private String getOsVersion() {
        String str = StringUtils.EMPTY + VERSION.SDK_INT;
        this.osVersion = str;
        return str;
    }

    private String getLanguage(Locale locale) {
        return locale.getLanguage();
    }

    private String getCountry(Locale locale) {
        return locale.getCountry();
    }

    private String getScreenSize(int i) {
        switch (i & 15) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return Constants.SMALL;
            case Task.NETWORK_STATE_ANY /*2*/:
                return Constants.NORMAL;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return Constants.LARGE;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return Constants.XLARGE;
            default:
                return null;
        }
    }

    private String getScreenFormat(int i) {
        switch (i & 48) {
            case CommonStatusCodes.CANCELED /*16*/:
                return Constants.NORMAL;
            case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_PRIVACY /*32*/:
                return Constants.LONG;
            default:
                return null;
        }
    }

    private String getScreenDensity(DisplayMetrics displayMetrics) {
        int i = displayMetrics.densityDpi;
        if (i == 0) {
            return null;
        }
        if (i < 140) {
            return Constants.LOW;
        }
        if (i > Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
            return Constants.HIGH;
        }
        return Constants.MEDIUM;
    }

    private String getDisplayWidth(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.widthPixels);
    }

    private String getDisplayHeight(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.heightPixels);
    }

    private String getClientSdk(String str) {
        if (str == null) {
            return Constants.CLIENT_SDK;
        }
        return String.format(Locale.US, "%s@%s", new Object[]{str, Constants.CLIENT_SDK});
    }

    private String getMacSha1(String str) {
        if (str == null) {
            return null;
        }
        return Util.sha1(str);
    }

    private String getMacShortMd5(String str) {
        if (str == null) {
            return null;
        }
        return Util.md5(str.replaceAll(Draft.IMAGE_DELIMITER, StringUtils.EMPTY));
    }

    private String getAndroidId(Context context, boolean z) {
        if (z) {
            return null;
        }
        return Reflection.getAndroidId(context);
    }

    private String getFacebookAttributionId(Context context) {
        try {
            String str = "aid";
            Cursor query = context.getContentResolver().query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), new String[]{"aid"}, null, null, null);
            if (query == null) {
                return null;
            }
            if (query.moveToFirst()) {
                String string = query.getString(query.getColumnIndex("aid"));
                query.close();
                return string;
            }
            query.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
