package com.etsy.android.lib.config;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.etsy.android.lib.config.EtsyConfigKey.Environment;
import java.util.Locale;

/* renamed from: com.etsy.android.lib.config.n */
public class EtsyConfigOption implements IEtsyConfigValue {
    @NonNull
    private String f1268a;
    @Nullable
    private String f1269b;
    private boolean f1270c;
    private int f1271d;
    private long f1272e;
    private double f1273f;
    private int[] f1274g;
    private String[] f1275h;
    private boolean f1276i;
    private boolean f1277j;
    private boolean f1278k;
    private boolean f1279l;
    private boolean f1280m;
    private boolean f1281n;
    private boolean f1282o;
    private String f1283p;
    private String f1284q;

    public EtsyConfigOption(@NonNull String str, @Nullable String str2) {
        this.f1268a = str;
        m903a(str2);
    }

    public EtsyConfigOption(@NonNull String str, @Nullable String str2, String str3, String str4) {
        this(str, str2);
        this.f1282o = true;
        this.f1283p = str3;
        this.f1284q = str4;
    }

    private void m894a(boolean z) {
        this.f1281n = z;
        this.f1276i = z;
        this.f1277j = z;
        this.f1278k = z;
        this.f1279l = z;
        this.f1280m = z;
    }

    @VisibleForTesting
    public void m903a(@Nullable String str) {
        this.f1269b = str;
        if (TextUtils.isEmpty(str)) {
            this.f1270c = false;
            this.f1271d = 0;
            this.f1273f = 0.0d;
            this.f1272e = 0;
            this.f1275h = new String[0];
            this.f1274g = new int[0];
            m894a(true);
            return;
        }
        m894a(false);
    }

    @NonNull
    public EtsyConfigOption m901a(Environment environment, boolean z) {
        return this;
    }

    @NonNull
    public String m902a() {
        return this.f1268a;
    }

    @Nullable
    public String m904b() {
        return this.f1269b;
    }

    public boolean m905c() {
        if (!this.f1281n) {
            synchronized (this) {
                if (!this.f1281n) {
                    m895b(this.f1269b);
                    this.f1281n = true;
                }
            }
        }
        return this.f1270c;
    }

    public int m906d() {
        if (!this.f1276i) {
            synchronized (this) {
                if (!this.f1276i) {
                    m898e(this.f1269b);
                    this.f1276i = true;
                }
            }
        }
        return this.f1271d;
    }

    public double m907e() {
        if (!this.f1277j) {
            synchronized (this) {
                if (!this.f1277j) {
                    m899f(this.f1269b);
                    this.f1277j = true;
                }
            }
        }
        return this.f1273f;
    }

    public long m908f() {
        if (!this.f1278k) {
            synchronized (this) {
                if (!this.f1278k) {
                    m900g(this.f1269b);
                    this.f1278k = true;
                }
            }
        }
        return this.f1272e;
    }

    @NonNull
    public String[] m909g() {
        if (!this.f1280m) {
            synchronized (this) {
                if (!this.f1280m) {
                    m896c(this.f1269b);
                    this.f1280m = true;
                }
            }
        }
        return this.f1275h;
    }

    @NonNull
    public int[] m910h() {
        if (!this.f1279l) {
            synchronized (this) {
                if (!this.f1279l) {
                    m897d(this.f1269b);
                    this.f1279l = true;
                }
            }
        }
        return this.f1274g;
    }

    public boolean m911i() {
        return this.f1282o;
    }

    @Nullable
    public String m912j() {
        return this.f1283p;
    }

    @Nullable
    public String m913k() {
        return this.f1284q;
    }

    private void m895b(@NonNull String str) {
        String toLowerCase = str.toLowerCase(Locale.US);
        if (TextUtils.isEmpty(toLowerCase) || toLowerCase.equals("off") || toLowerCase.equals("0") || toLowerCase.equals("false")) {
            this.f1270c = false;
        } else {
            this.f1270c = true;
        }
    }

    private void m896c(@NonNull String str) {
        this.f1275h = str.split(",");
    }

    private void m897d(@NonNull String str) {
        String[] split = str.split(",");
        int[] iArr = new int[split.length];
        int i = 0;
        while (i < split.length) {
            try {
                iArr[i] = Integer.parseInt(split[i]);
                i++;
            } catch (NumberFormatException e) {
                this.f1274g = new int[0];
                return;
            }
        }
        this.f1274g = iArr;
    }

    private void m898e(String str) {
        try {
            this.f1271d = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            this.f1271d = 0;
        }
    }

    private void m899f(String str) {
        try {
            this.f1273f = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            this.f1273f = 0.0d;
        }
    }

    private void m900g(String str) {
        try {
            this.f1272e = Long.parseLong(str);
        } catch (NumberFormatException e) {
            this.f1272e = 0;
        }
    }
}
