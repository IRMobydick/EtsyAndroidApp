package com.etsy.android.lib.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings.Secure;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.ag;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.config.q */
public class InstallInfo {
    private static InstallInfo f1285o;
    private String f1286a;
    private String f1287b;
    private String f1288c;
    private String f1289d;
    private String f1290e;
    private String f1291f;
    private String f1292g;
    private int f1293h;
    private int f1294i;
    private long f1295j;
    private long f1296k;
    private boolean f1297l;
    private EtsyBuild f1298m;
    private boolean f1299n;

    public static InstallInfo m919a() {
        if (f1285o != null) {
            return f1285o;
        }
        throw new IllegalStateException("InstallInfo must be created via createInstance before getInstance can be called");
    }

    public static void m920a(Context context, String str, int i, EtsyBuild etsyBuild, boolean z) {
        if (f1285o == null) {
            f1285o = new InstallInfo(context, str, i, etsyBuild, z);
        }
    }

    private InstallInfo(Context context, String str, int i, EtsyBuild etsyBuild, boolean z) {
        this.f1292g = StringUtils.EMPTY;
        this.f1297l = true;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f1289d = packageInfo.versionName;
            this.f1290e = packageInfo.packageName;
            this.f1294i = packageInfo.versionCode;
            this.f1295j = packageInfo.firstInstallTime;
            this.f1296k = packageInfo.lastUpdateTime;
            this.f1297l = context.getResources().getBoolean(R.is_phone);
            this.f1286a = str;
            this.f1293h = i;
            this.f1298m = etsyBuild;
            this.f1299n = z;
        } catch (NameNotFoundException e) {
            EtsyDebug.m1911c("Error fetching version: " + e.getLocalizedMessage());
        }
        this.f1288c = Secure.getString(context.getContentResolver(), "android_id");
        this.f1287b = ag.m3214a(context);
        this.f1291f = ag.m3218c(context);
        Thread thread = new Thread(new InstallInfo(context, this));
        thread.setPriority(10);
        thread.start();
    }

    public String m925b() {
        return this.f1287b;
    }

    public EtsyBuild m926c() {
        return this.f1298m;
    }

    public void m924a(Context context) {
        ag.m3216b(context);
        this.f1287b = ag.m3214a(context);
    }

    public String m927d() {
        return this.f1288c;
    }

    public String m928e() {
        return this.f1291f;
    }

    public String m929f() {
        return this.f1289d;
    }

    public String m930g() {
        return this.f1290e;
    }

    public long m931h() {
        return this.f1295j;
    }

    public long m932i() {
        return this.f1296k;
    }

    public String m933j() {
        return this.f1286a;
    }

    public int m934k() {
        return this.f1293h;
    }

    public String m935l() {
        return m923a(System.getProperty("http.agent"));
    }

    public String m923a(String str) {
        String str2 = " " + this.f1286a + "/" + m929f() + " Android/1";
        if (this.f1297l) {
            str2 = " Mobile/1" + str2;
        }
        return str + str2;
    }

    public int m936m() {
        return this.f1294i;
    }

    public boolean m937n() {
        return this.f1299n;
    }

    public String m938o() {
        return this.f1292g;
    }

    private void m922b(String str) {
        this.f1292g = str;
    }
}
