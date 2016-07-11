package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.appboy.Constants;
import com.appboy.configuration.XmlAppConfigurationProvider;
import com.appboy.models.outgoing.Environment;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.gcm.Task;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;

public final class cb implements cg {
    private static final String f220b;
    private static boolean f221l;
    final SharedPreferences f222a;
    private final Context f223c;
    private final ch f224d;
    private final cj f225e;
    private final Environment f226f;
    private final es f227g;
    private final ey f228h;
    private final cf<dj> f229i;
    private final bc f230j;
    private String f231k;

    static /* synthetic */ void m98a(cb cbVar, String str) {
        if (fj.m354c(str)) {
            AppboyLogger.m664e(f220b, "Received null ad id, doing nothing.");
            return;
        }
        cbVar.f231k = fj.m356e(str);
        String string = cbVar.f222a.getString(Constants.APPBOY_PUSH_CONTENT_KEY, StringUtils.EMPTY);
        if (string.equals(cbVar.f231k)) {
            AppboyLogger.m666i(f220b, "Google Play Services Advertising Id matched stored Advertising Id.");
            return;
        }
        AppboyLogger.m666i(f220b, "Advertising Id did not match stored Advertising Id.  Replacing stored Advertising Id and requesting new PlaceIQ Id.");
        cbVar.f230j.m31a(du.f349a, du.class);
        Editor edit = cbVar.f222a.edit();
        edit.putString(Constants.APPBOY_PUSH_CONTENT_KEY, cbVar.f231k);
        if (!fj.m354c(string)) {
            f221l = true;
            edit.putBoolean(Constants.APPBOY_PUSH_ACCENT_KEY, true);
        }
        edit.apply();
    }

    static {
        f220b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, cb.class.getName()});
        f221l = false;
    }

    public static boolean m99a() {
        return f221l;
    }

    public cb(Context context, XmlAppConfigurationProvider xmlAppConfigurationProvider, String str, ch chVar, cj cjVar, es esVar, ey eyVar, cf<dj> cfVar, bc bcVar) {
        if (context == null) {
            throw new NullPointerException();
        }
        this.f223c = context;
        this.f224d = chVar;
        this.f225e = cjVar;
        this.f227g = esVar;
        this.f228h = eyVar;
        String packageName = this.f223c.getPackageName();
        PackageInfo a = m97a(packageName);
        this.f226f = new Environment(Constants.APPBOY_SDK_VERSION, a.versionCode, a.versionName, packageName, eyVar);
        this.f229i = cfVar;
        this.f230j = bcVar;
        this.f222a = context.getSharedPreferences("com.appboy.storage.device_ad_info" + fj.m352b(str, xmlAppConfigurationProvider.getAppboyApiKey().toString()), 0);
        if (this.f228h != null && this.f228h.m295a()) {
            new Thread(new cc(this)).start();
        }
    }

    public final db m102b() {
        String str;
        Locale locale = Locale.getDefault();
        String str2 = null;
        if (this.f225e != null) {
            str2 = this.f225e.m110a();
        }
        Integer valueOf = Integer.valueOf(VERSION.SDK_INT);
        String str3 = Build.CPU_ABI;
        TelephonyManager telephonyManager = (TelephonyManager) this.f223c.getSystemService(ResponseConstants.PHONE);
        switch (telephonyManager.getPhoneType()) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                str = null;
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
                str = telephonyManager.getNetworkOperatorName();
                break;
            default:
                AppboyLogger.m670w(f220b, "Unknown phone type");
                str = null;
                break;
        }
        String str4 = Build.MODEL;
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String id = TimeZone.getDefault().getID();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.f223c.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        df dfVar = new df(displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.xdpi, displayMetrics.ydpi, displayMetrics.densityDpi);
        return new db(valueOf, str3, str, str4, language, country, id, dfVar, this.f224d.m40a(), str2, this.f229i.m83a());
    }

    public final db m103c() {
        this.f227g.f408b = m102b();
        return (db) this.f227g.m249b();
    }

    public final Environment m104d() {
        return this.f226f;
    }

    public final String m105e() {
        String b = this.f224d.m41b();
        if (b == null) {
            AppboyLogger.m664e(f220b, "Error reading deviceId, received a null value.");
        }
        return b;
    }

    public final String m106f() {
        if (this.f231k == null) {
            this.f231k = this.f222a.getString(Constants.APPBOY_PUSH_CONTENT_KEY, null);
        }
        return this.f231k;
    }

    private PackageInfo m97a(String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = this.f223c.getPackageManager().getPackageInfo(str, 0);
        } catch (Throwable e) {
            AppboyLogger.m665e(f220b, String.format("Unable to inspect package [%s]", new Object[]{str}), e);
        }
        ApplicationInfo applicationInfo = this.f223c.getApplicationInfo();
        if (packageInfo == null) {
            return this.f223c.getPackageManager().getPackageArchiveInfo(applicationInfo.sourceDir, 0);
        }
        return packageInfo;
    }
}
