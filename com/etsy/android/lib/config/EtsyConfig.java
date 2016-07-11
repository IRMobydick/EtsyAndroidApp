package com.etsy.android.lib.config;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKey.Environment;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.EtsyRequestQueue;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.core.http.request.BaseHttpRequestJob;
import com.etsy.android.lib.core.http.request.EtsyApiV2Request;
import com.etsy.android.lib.core.http.request.EtsyApiV2RequestJob;
import com.etsy.android.lib.core.http.url.p009a.EtsyV2Urls;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.JsonNodeResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.toolbar.AdminToolbar;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.af;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

/* renamed from: com.etsy.android.lib.config.a */
public class EtsyConfig {
    public static final List<String> f1158a;
    private static final String f1159b;
    private static EtsyConfig f1160c;
    private String f1161d;
    private boolean f1162e;
    private final String f1163f;
    private final String f1164g;
    private final String f1165h;
    private TrackingEtsyConfigMap f1166i;
    private boolean f1167j;
    private SharedPreferences f1168k;
    private String f1169l;
    private String f1170m;
    private Map<String, EtsyConfig> f1171n;
    private final Set<EtsyConfig> f1172o;
    private BaseHttpRequestJob f1173p;
    private final Object f1174q;
    private EtsyRequestQueue f1175r;
    private final String f1176s;
    private final String f1177t;

    /* renamed from: com.etsy.android.lib.config.a.1 */
    final class EtsyConfig extends EtsyApiV2RequestJob<JsonNodeResult> {
        final /* synthetic */ EtsyConfig f1156a;
        final /* synthetic */ Context f1157b;

        EtsyConfig(EtsyConfig etsyConfig, Context context) {
            this.f1156a = etsyConfig;
            this.f1157b = context;
        }

        public void m836a(@NonNull List<JsonNodeResult> list, int i, @NonNull EtsyResult<JsonNodeResult> etsyResult) {
            synchronized (this.f1156a.f1174q) {
                this.f1156a.f1173p = null;
            }
            if (!list.isEmpty()) {
                JsonNode data = ((JsonNodeResult) list.get(0)).getData();
                this.f1156a.m844a(this.f1157b, this.f1156a.f1161d, data);
                EtsyDebug.m1904b("  CONFIG DATA: " + data);
                this.f1156a.m862a(this.f1157b, data);
                this.f1156a.m859l();
                boolean z = !this.f1156a.f1167j;
                this.f1156a.f1167j = true;
                Intent intent = new Intent("com.etsy.etsyconfig.updated");
                intent.putExtra("is_first_config_update", z);
                LocalBroadcastManager.getInstance(this.f1157b).sendBroadcast(intent);
            }
        }

        public void m835a(int i, @Nullable String str, @NonNull EtsyResult<JsonNodeResult> etsyResult) {
            EtsyDebug.m1919e(EtsyConfig.f1159b, "Problem Fetching config: " + etsyResult.m1052c());
        }
    }

    static {
        f1159b = EtsyDebug.m1891a(EtsyConfig.class);
        f1158a = Arrays.asList(new String[]{"test_name", ResponseConstants.ENABLED, "selector"});
    }

    public static EtsyConfig m838a(Context context, String str, String str2) {
        if (f1160c == null) {
            f1160c = new EtsyConfig(context, str, str2);
            f1160c.m848b(context, null);
        }
        return f1160c;
    }

    public static EtsyConfig m837a() {
        if (f1160c != null) {
            return f1160c;
        }
        throw new IllegalStateException("EtsyConfig must be created via createInstance before getInstance can be called");
    }

    EtsyConfig(Context context, String str, String str2) {
        this.f1166i = null;
        this.f1167j = false;
        this.f1172o = Collections.synchronizedSet(new HashSet());
        this.f1174q = new Object();
        Resources resources = context.getResources();
        this.f1171n = Collections.synchronizedMap(new HashMap());
        this.f1163f = resources.getString(R.config_prefs_prod_value);
        this.f1164g = resources.getString(R.config_prefs_princess_value);
        this.f1165h = resources.getString(R.config_prefs_dev_value);
        this.f1169l = resources.getString(R.config_prefs_last_requested);
        this.f1170m = resources.getString(R.config_prefs_last_updated);
        this.f1168k = context.getSharedPreferences(resources.getString(R.config_prefs_key), 0);
        m866b(context);
        this.f1176s = str;
        this.f1177t = str2;
        m855g(context);
    }

    public void m861a(@NonNull Context context) {
        m862a(context, null);
    }

    public void m862a(@NonNull Context context, @Nullable JsonNode jsonNode) {
        m855g(context);
        m848b(context, jsonNode);
    }

    private void m848b(Context context, @Nullable JsonNode jsonNode) {
        EtsyConfigMap etsyConfigMap;
        Environment environment = Environment.PRODUCTION;
        String f;
        if (this.f1165h.equals(this.f1161d)) {
            EtsyConfigMap etsyConfigMap2;
            f = m854f(context);
            Environment environment2 = Environment.DEVELOPMENT;
            if (this.f1162e) {
                etsyConfigMap2 = new EtsyConfigMap(f, this.f1176s, this.f1177t);
            } else {
                etsyConfigMap2 = new EtsyConfigMap(f, "0l491asoic3251rbzpu6hk7b", "twc1vnfwcq");
            }
            Environment environment3 = environment2;
            etsyConfigMap = etsyConfigMap2;
            environment = environment3;
        } else {
            f = m854f(context);
            if (this.f1164g.equals(this.f1161d)) {
                environment = Environment.PRINCESS;
            }
            etsyConfigMap = new EtsyConfigMap(f, this.f1176s, this.f1177t);
        }
        if (jsonNode == null) {
            jsonNode = m841a(context, this.f1161d);
        }
        TrackingEtsyConfigMap trackingEtsyConfigMap = new TrackingEtsyConfigMap(EtsyApplication.get().getAnalyticsTracker(), environment, etsyConfigMap);
        try {
            trackingEtsyConfigMap.m882a(jsonNode);
            if (EtsyDebug.m1903a()) {
                EtsyDebug.m1893a(trackingEtsyConfigMap.toString());
            }
        } catch (JSONException e) {
            EtsyDebug.m1911c("!!!!Problem build config map. " + e.getLocalizedMessage());
            m849b(context, this.f1161d);
            try {
                trackingEtsyConfigMap.m882a(null);
            } catch (Throwable e2) {
                CrashUtil.m3037a().m3045a(e2);
            }
        }
        this.f1166i = trackingEtsyConfigMap;
    }

    private String m854f(Context context) {
        return this.f1168k.getString(context.getResources().getString(R.config_prefs_vm), EtsyConfig.m856i());
    }

    private String m843a(@NonNull String str) {
        if (this.f1163f.equals(str)) {
            return this.f1163f;
        }
        if (this.f1165h.equals(str)) {
            return this.f1165h;
        }
        if (this.f1164g.equals(str)) {
            return this.f1164g;
        }
        throw new IllegalArgumentException("Attempted to read configs with unknown environment");
    }

    @Nullable
    private synchronized JsonNode m841a(@NonNull Context context, @NonNull String str) {
        JsonNode jsonNode;
        String a = m843a(str);
        jsonNode = null;
        try {
            jsonNode = ad.m1081a().m1083b().readTree(new File(context.getDir("configs", 0), a));
        } catch (IOException e) {
            EtsyDebug.m1919e(f1159b, "Error reading saved config from file");
        }
        return jsonNode;
    }

    private synchronized void m850b(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        af.m3201a(new File(context.getDir("configs", 0), m843a(str)), str2, false);
    }

    private synchronized void m844a(@NonNull Context context, @NonNull String str, @NonNull JsonNode jsonNode) {
        try {
            ad.m1081a().m1083b().writeValue(new File(context.getDir("configs", 0), m843a(str)), (Object) jsonNode);
        } catch (IOException e) {
            EtsyDebug.m1919e(f1159b, "Error writing config to file");
        }
    }

    private synchronized void m849b(@NonNull Context context, @NonNull String str) {
        File file = new File(context.getDir("configs", 0), m843a(str));
        if (file.exists() && !file.delete()) {
            EtsyDebug.m1920e(f1159b, "Couldn't remove config file: %s", r1);
        }
    }

    public synchronized void m866b(@NonNull Context context) {
        synchronized (this) {
            for (String str : new String[]{this.f1163f, this.f1164g, this.f1165h}) {
                String string = this.f1168k.getString(str, null);
                if (string != null) {
                    m850b(context, str, string);
                    this.f1168k.edit().remove(str).apply();
                }
            }
        }
    }

    private void m855g(@NonNull Context context) {
        Resources resources = context.getResources();
        this.f1161d = this.f1168k.getString(resources.getString(R.config_prefs_environment), this.f1163f);
        this.f1162e = this.f1168k.getBoolean(resources.getString(R.config_prefs_dev_proxy), false);
        this.f1171n.clear();
    }

    @Nullable
    protected EtsyConfig m860a(@NonNull EtsyConfigOption etsyConfigOption) {
        if (!etsyConfigOption.m911i()) {
            return null;
        }
        EtsyConfig etsyConfig = new EtsyConfig(etsyConfigOption.m912j(), etsyConfigOption.m902a(), etsyConfigOption.m904b(), etsyConfigOption.m913k());
        this.f1171n.put(etsyConfigOption.m902a(), etsyConfig);
        return etsyConfig;
    }

    public HashMap<String, String> m865b() {
        HashMap<String, String> hashMap = new HashMap();
        if (this.f1172o.size() > 0) {
            Set<EtsyConfig> hashSet;
            synchronized (this.f1172o) {
                hashSet = new HashSet(this.f1172o);
                this.f1172o.clear();
            }
            Iterable arrayList = new ArrayList(hashSet.size());
            Iterable arrayList2 = new ArrayList(hashSet.size());
            Iterable arrayList3 = new ArrayList(hashSet.size());
            for (EtsyConfig etsyConfig : hashSet) {
                arrayList.add(etsyConfig.f1178a);
                arrayList2.add(etsyConfig.f1180c);
                arrayList3.add(etsyConfig.f1181d);
            }
            hashMap.put("php_ab_test_names", StringUtils.join(arrayList, ";"));
            hashMap.put("php_ab_var_names", StringUtils.join(arrayList2, ";"));
            hashMap.put("php_ab_selector_names", StringUtils.join(arrayList3, ";"));
        }
        return hashMap;
    }

    public boolean m868c() {
        return this.f1165h.equals(this.f1161d);
    }

    protected void m863a(@NonNull IEtsyConfigValue iEtsyConfigValue, @NonNull EtsyConfigOption etsyConfigOption) {
        if (iEtsyConfigValue != EtsyConfigKeys.cu) {
            m864a(iEtsyConfigValue.m822a(), etsyConfigOption);
        }
    }

    protected void m864a(@NonNull String str, @NonNull EtsyConfigOption etsyConfigOption) {
        EtsyConfig etsyConfig = (EtsyConfig) this.f1171n.get(str);
        if (etsyConfig == null) {
            etsyConfig = m860a(etsyConfigOption);
        }
        if (etsyConfig != null) {
            AdminToolbar.m2988a(etsyConfig);
            this.f1172o.add(etsyConfig);
        }
    }

    public EtsyConfigMap m869d() {
        return this.f1166i;
    }

    public boolean m871e() {
        return this.f1167j;
    }

    public void m867c(Context context) {
        EtsyDebug.m1912c(f1159b, "refreshServerConfigIfStale");
        if (!m858k() && m872f()) {
            EtsyDebug.m1912c(f1159b, "refreshServerConfigIfStale - it's stale!");
            m870d(context);
        }
    }

    public boolean m872f() {
        long currentTimeMillis = System.currentTimeMillis();
        long h = m874h();
        boolean z = currentTimeMillis > h && currentTimeMillis - h >= 10800000;
        EtsyDebug.m1912c(f1159b, "requiresConfigUpdate " + z);
        return z;
    }

    public long m873g() {
        long j = this.f1168k.getLong(this.f1169l, 0);
        EtsyDebug.m1912c(f1159b, "getConfigRequestedTime " + j);
        return j;
    }

    public long m874h() {
        long j = this.f1168k.getLong(this.f1170m, 0);
        EtsyDebug.m1912c(f1159b, "getConfigFetchedTime " + j);
        return j;
    }

    private boolean m858k() {
        return this.f1173p != null;
    }

    public void m870d(Context context) {
        EtsyDebug.m1912c(f1159b, "refreshServerConfig");
        synchronized (this.f1174q) {
            if (m858k()) {
                this.f1175r.m1700a((Object) this);
            }
            this.f1173p = EtsyConfig.m839a(this, context);
            if (this.f1175r == null) {
                this.f1175r = new EtsyRequestQueue(context);
            }
            this.f1175r.m1696a((Object) this, this.f1173p);
        }
    }

    private void m859l() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f1168k.edit().putLong(this.f1170m, currentTimeMillis).apply();
        EtsyDebug.m1912c(f1159b, "saveConfigFetchedTime " + currentTimeMillis);
    }

    public static String m856i() {
        return !TextUtils.isEmpty(StringUtils.EMPTY) ? StringUtils.EMPTY : "auto.vms.etsy.com";
    }

    public static boolean m853e(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.config_prefs_key), 0);
        String string = context.getString(R.config_prefs_vm);
        Object string2 = sharedPreferences.getString(string, StringUtils.EMPTY);
        if (!TextUtils.isEmpty(string2) && !string2.equals("auto")) {
            return false;
        }
        sharedPreferences.edit().putString(string, EtsyConfig.m856i()).apply();
        return true;
    }

    private static BaseHttpRequestJob m839a(EtsyConfig etsyConfig, Context context) {
        return ((EtsyApiV2RequestJob) EtsyApiV2RequestJob.m1449a(((EtsyApiV2Request) ((EtsyApiV2Request) ((EtsyApiV2Request) ((EtsyApiV2Request) EtsyApiV2Request.m1435a(JsonNodeResult.class, EtsyV2Urls.m1495a()).m1385a("device_type", AppBuild.ANDROID_PLATFORM)).m1385a("app_identifier", InstallInfo.m919a().m930g())).m1385a("version", InstallInfo.m919a().m929f())).m1385a("device_udid", InstallInfo.m919a().m925b())).m1444h()).m1421a(new EtsyConfig(etsyConfig, context.getApplicationContext()))).m1426c();
    }
}
