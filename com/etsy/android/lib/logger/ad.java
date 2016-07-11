package com.etsy.android.lib.logger;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigMap;
import com.etsy.android.lib.config.TrackingEtsyConfigMap;
import com.etsy.android.lib.logger.ViewClickAnalyticsLog.ViewAction;
import com.etsy.android.lib.util.CrashUtil;
import java.util.HashMap;
import java.util.Map.Entry;
import org.parceler.Parcels;
import org.parceler.ax;

/* compiled from: ViewAnalyticsTracker */
public class ad extends AnalyticsTracker {
    private static final String f1754d;
    private static ae f1755e;
    private String f1756f;
    private boolean f1757g;
    private boolean f1758h;
    private boolean f1759i;
    private boolean f1760j;
    private boolean f1761k;
    private boolean f1762l;
    private HashMap<AnalyticsLogAttribute, Object> f1763m;
    private TrackingEtsyConfigMap f1764n;

    public /* synthetic */ EtsyConfigMap m1861c() {
        return m1864f();
    }

    static {
        f1754d = EtsyDebug.m1891a(ad.class);
        f1755e = new ae();
    }

    public static ad m1848a(@NonNull ITrackingView iTrackingView, boolean z, @Nullable Bundle bundle) {
        return f1755e.m1872a(iTrackingView, z, bundle);
    }

    private ad(@NonNull ITrackingView iTrackingView, boolean z, @Nullable Bundle bundle) {
        super(iTrackingView.getTrackingName());
        this.f1762l = true;
        m1855f(iTrackingView);
        m1852b(iTrackingView, z, bundle);
    }

    protected void m1857a(@NonNull ITrackingView iTrackingView, boolean z) {
        this.f1763m = new HashMap();
        this.f1761k = z;
        CrashUtil.m3037a().m3044a(this.b, "initializing ViewAnalyticsTracker");
        ITrackingView trackingParent = iTrackingView.getTrackingParent();
        if (!(trackingParent == null || trackingParent.getAnalyticsContext() == null)) {
            this.f1756f = trackingParent.getAnalyticsContext().m1843a();
        }
        this.f1764n = new TrackingEtsyConfigMap(this, EtsyConfig.m837a().m869d());
    }

    private void m1852b(@NonNull ITrackingView iTrackingView, boolean z, @Nullable Bundle bundle) {
        m1857a(iTrackingView, z);
        m1849a(bundle);
    }

    private void m1849a(Bundle bundle) {
        if (bundle != null && this.f1762l) {
            for (String str : bundle.keySet()) {
                aa a = ac.m1832a(str);
                Object a2;
                if (a != null) {
                    a2 = a.m1826a(bundle);
                    if (a2 != null) {
                        m1850a(a.m1825a(), a2);
                    }
                } else {
                    a2 = bundle.get(str);
                    if (a2 instanceof Bundle) {
                        m1849a((Bundle) a2);
                    } else if (a2 instanceof ITrackedObject) {
                        m1851a(((ITrackedObject) a2).getTrackingParameters());
                    } else if (a2 instanceof ax) {
                        a2 = Parcels.m7495a((Parcelable) a2);
                        if (a2 instanceof ITrackedObject) {
                            m1851a(((ITrackedObject) a2).getTrackingParameters());
                        }
                    }
                }
            }
        }
    }

    private void m1851a(@Nullable HashMap<AnalyticsLogAttribute, Object> hashMap) {
        if (hashMap != null) {
            for (Entry entry : hashMap.entrySet()) {
                m1850a((AnalyticsLogAttribute) entry.getKey(), entry.getValue());
            }
        }
    }

    private void m1850a(@NonNull AnalyticsLogAttribute analyticsLogAttribute, @Nullable Object obj) {
        if (analyticsLogAttribute.isPrivate()) {
            throw new IllegalArgumentException("Tried tracking with private attribute: " + analyticsLogAttribute.toString());
        }
        this.f1763m.put(analyticsLogAttribute, obj);
    }

    @Nullable
    public String m1863e() {
        return this.f1756f;
    }

    @NonNull
    public TrackingEtsyConfigMap m1864f() {
        return this.f1764n;
    }

    @NonNull
    HashMap<AnalyticsLogAttribute, Object> m1865g() {
        return this.f1763m;
    }

    public void m1856a(@NonNull ITrackingView iTrackingView) {
        m1862c(iTrackingView);
    }

    public void m1859b(@NonNull ITrackingView iTrackingView) {
        m1862c(iTrackingView);
    }

    public void m1866h() {
        m1871m();
        this.f1761k = false;
    }

    public void m1867i() {
        if (!this.f1759i) {
            this.f1761k = true;
        }
    }

    public void m1860b(@NonNull ITrackingView iTrackingView, boolean z) {
        this.f1761k = z;
        this.f1759i = true;
        if (this.f1761k) {
            m1862c(iTrackingView);
        } else {
            m1871m();
        }
    }

    public void m1868j() {
        m1871m();
    }

    public void m1869k() {
        m1871m();
    }

    public void m1870l() {
        m1871m();
    }

    protected void m1871m() {
        this.f1758h = false;
    }

    protected final void m1862c(@NonNull ITrackingView iTrackingView) {
        if (this.f1762l && !this.f1758h) {
            if (this.f1757g) {
                m1854e(iTrackingView);
                return;
            }
            m1853d(iTrackingView);
            this.f1757g = true;
        }
    }

    private void m1853d(@NonNull ITrackingView iTrackingView) {
        if (this.f1761k) {
            this.f1758h = true;
            this.f1760j = true;
        }
        AnalyticsLog viewAnalyticsLog = new ViewAnalyticsLog(iTrackingView, this.f1761k);
        AnalyticsTracker.m1838a(viewAnalyticsLog);
        if (AnalyticsTracker.m1841d()) {
            EtsyGoogleAnalyticsLogger.m1943a(c, this, viewAnalyticsLog);
        }
    }

    private void m1854e(@NonNull ITrackingView iTrackingView) {
        if (this.f1761k) {
            this.f1758h = true;
            AnalyticsLog viewAnalyticsLog = new ViewAnalyticsLog(iTrackingView, this.f1761k, this.f1760j);
            AnalyticsTracker.m1838a(viewAnalyticsLog);
            if (AnalyticsTracker.m1841d()) {
                EtsyGoogleAnalyticsLogger.m1943a(c, this, viewAnalyticsLog);
            }
            this.f1760j = true;
        }
    }

    public void m1858a(@NonNull String str, @NonNull ViewAction viewAction, @Nullable HashMap<AnalyticsLogAttribute, Object> hashMap) {
        ViewClickAnalyticsLog viewClickAnalyticsLog = new ViewClickAnalyticsLog(str, viewAction, hashMap, this);
        AnalyticsTracker.m1838a((AnalyticsLog) viewClickAnalyticsLog);
        if (AnalyticsTracker.m1841d()) {
            EtsyGoogleAnalyticsLogger.m1944a(c, this, viewClickAnalyticsLog);
        }
    }

    private void m1855f(@NonNull ITrackingView iTrackingView) {
        if (iTrackingView.getDefaultName().equals(iTrackingView.getTrackingName())) {
            this.f1762l = false;
        }
    }
}
