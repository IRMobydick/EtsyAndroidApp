package com.etsy.android.lib.config;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.config.EtsyConfigKey.Environment;
import com.etsy.android.lib.logger.AnalyticsTracker;
import java.util.HashSet;

/* renamed from: com.etsy.android.lib.config.s */
public class TrackingEtsyConfigMap extends EtsyConfigMap {
    private HashSet<String> f1302d;
    private AnalyticsTracker f1303e;
    private long f1304f;

    public TrackingEtsyConfigMap(@NonNull AnalyticsTracker analyticsTracker, @NonNull Environment environment, @Nullable EtsyConfigMap etsyConfigMap) {
        super(environment, etsyConfigMap);
        this.f1303e = analyticsTracker;
        m940c();
    }

    public TrackingEtsyConfigMap(AnalyticsTracker analyticsTracker, EtsyConfigMap etsyConfigMap) {
        super(etsyConfigMap);
        this.f1303e = analyticsTracker;
        m940c();
    }

    private void m940c() {
        this.f1302d = new HashSet();
        this.f1304f = System.currentTimeMillis();
    }

    private void m941d() {
        if (this.f1304f + 1800000 < System.currentTimeMillis()) {
            this.f1302d.clear();
            this.f1304f = System.currentTimeMillis();
        }
    }

    @NonNull
    public EtsyConfigOption m942a(@NonNull IEtsyConfigValue iEtsyConfigValue) {
        EtsyConfigOption a = super.m880a(iEtsyConfigValue);
        m941d();
        if (a.m911i() && !this.f1302d.contains(iEtsyConfigValue.m822a())) {
            m939a(a);
            EtsyConfig.m837a().m863a(iEtsyConfigValue, a);
            this.f1302d.add(iEtsyConfigValue.m822a());
        }
        return a;
    }

    private void m939a(@NonNull EtsyConfigOption etsyConfigOption) {
        if (this.f1303e != null) {
            this.f1303e.m1844a(etsyConfigOption);
        }
    }
}
