package bo.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import com.appboy.Constants;
import com.appboy.configuration.XmlAppConfigurationProvider;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PermissionUtils;

public class cd {
    public static final String f233a;
    public final Context f234b;
    public final cj f235c;

    static {
        f233a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, cd.class.getName()});
    }

    public cd(Context context, cj cjVar) {
        this.f234b = context;
        this.f235c = cjVar;
    }

    public static boolean m108a(Context context, XmlAppConfigurationProvider xmlAppConfigurationProvider) {
        return m107a(context) && m109b(context, xmlAppConfigurationProvider);
    }

    private static boolean m107a(Context context) {
        if (VERSION.SDK_INT < 8) {
            AppboyLogger.m664e(f233a, "GCM requires devices running Android 2.2 or higher.");
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gsf", 0);
            return true;
        } catch (NameNotFoundException e) {
            AppboyLogger.m664e(f233a, "GCM requires the Google Play store installed.");
            return false;
        } catch (Exception e2) {
            AppboyLogger.m664e(f233a, String.format("Unexpected exception while checking for %s.", new Object[]{"com.google.android.gsf"}));
            return false;
        }
    }

    private static boolean m109b(Context context, XmlAppConfigurationProvider xmlAppConfigurationProvider) {
        StringBuilder stringBuilder = new StringBuilder();
        PackageManager packageManager = context.getPackageManager();
        String str = context.getPackageName() + ".permission.C2D_MESSAGE";
        try {
            packageManager.getPermissionInfo(str, ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT);
        } catch (NameNotFoundException e) {
            stringBuilder.append(String.format("The manifest does not define the %s permission.", new Object[]{str}));
        }
        if (!PermissionUtils.hasPermission(context, "android.permission.INTERNET")) {
            stringBuilder.append(String.format("Missing permission. The %s permission must be set so that the Android application can send the registration ID to the 3rd party server.", new Object[]{"android.permission.INTERNET"}));
        } else if (!PermissionUtils.hasPermission(context, "com.google.android.c2dm.permission.RECEIVE")) {
            stringBuilder.append(String.format("Missing permission. The %s permission must be set so that the Android application can register and receive messages.", new Object[]{"com.google.android.c2dm.permission.RECEIVE"}));
        } else if (!PermissionUtils.hasPermission(context, str)) {
            stringBuilder.append(String.format("Missing permission. The %s permission must be set so that ONLY this Android application can register and receive GCM messages.", new Object[]{str}));
        }
        if (!PermissionUtils.hasPermission(context, "android.permission.GET_ACCOUNTS")) {
            if (VERSION.SDK_INT >= 16) {
                AppboyLogger.m666i(f233a, String.format("Missing permission. The %s permission is recommended to be set so that pre-Jelly Bean Android devices can register with the GCM server.", new Object[]{"android.permission.GET_ACCOUNTS"}));
            } else {
                stringBuilder.append(String.format("Missing permission. The %s permission must be set so that this pre-Jelly Bean Android device can register with the GCM server.", new Object[]{"android.permission.GET_ACCOUNTS"}));
            }
        }
        if (!PermissionUtils.hasPermission(context, "android.permission.WAKE_LOCK")) {
            AppboyLogger.m666i(f233a, String.format("Missing permission. The %s permission is recommended be set so that the GCM receiver can notify users by waking the phone when a message is received.", new Object[]{"android.permission.WAKE_LOCK"}));
        }
        try {
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.appboy.AppboyGcmReceiver"), 2);
            if (receiverInfo == null || !receiverInfo.enabled) {
                stringBuilder.append(String.format("The %s broadcast receiver is either not found or is disabled", new Object[]{r4.getClassName()}));
            }
        } catch (NameNotFoundException e2) {
            stringBuilder.append(String.format("No %s broadcast receiver is registered in the manifest.", new Object[]{r4.getClassName()}));
        }
        if (xmlAppConfigurationProvider.getGcmSenderId() == null) {
            stringBuilder.append(String.format("Cannot find the Google Cloud Messaging sender ID attribute %s in res/values/appboy.xml.", new Object[]{"com.appboy.GCM_SENDER_ID"}));
        }
        if (stringBuilder.length() == 0) {
            return true;
        }
        AppboyLogger.m664e(f233a, stringBuilder.toString());
        return false;
    }
}
