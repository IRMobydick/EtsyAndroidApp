package com.etsy.android.lib.core;

import android.app.NotificationManager;
import android.content.Context;
import android.text.TextUtils;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.convos.ConvoHelper;
import com.etsy.android.lib.convos.contentprovider.ConvoDatabaseUtil;
import com.etsy.android.lib.core.posts.EtsyPostManager;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.apiv3.Pseudonym;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ak;
import com.etsy.android.lib.util.az;
import com.foresee.sdk.configuration.Configuration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* compiled from: Session */
public class aj {
    private static final String f1427a;
    private static aj f1428b;
    private static final Object f1429c;
    private Context f1430d;
    private an f1431e;
    private boolean f1432f;
    private boolean f1433g;
    private EtsyAuthManager f1434h;
    private final Object f1435i;
    private EtsyId f1436j;
    private String f1437k;
    private EtsyRequestQueue f1438l;
    private EtsyPostManager f1439m;
    private ak f1440n;
    private final List<al> f1441o;
    private EtsyNetworkJob<?> f1442p;

    /* renamed from: com.etsy.android.lib.core.aj.1 */
    class Session implements EtsyJobResponse {
        final /* synthetic */ aj f1424a;

        Session(aj ajVar) {
            this.f1424a = ajVar;
        }

        public void m1097a(int i, String str, EtsyResult etsyResult) {
            EtsyDebug.m1919e(aj.f1427a, "Error fetching user pseudonym");
        }
    }

    /* renamed from: com.etsy.android.lib.core.aj.2 */
    class Session implements EtsyJobResponse {
        final /* synthetic */ aj f1425a;

        Session(aj ajVar) {
            this.f1425a = ajVar;
        }

        public void m1098a(EtsyResult etsyResult) {
            EtsyDebug.m1906b(aj.f1427a, "Empty result fetching user pseudonym");
        }
    }

    /* renamed from: com.etsy.android.lib.core.aj.3 */
    class Session implements EtsyJobResponse<Pseudonym> {
        final /* synthetic */ aj f1426a;

        Session(aj ajVar) {
            this.f1426a = ajVar;
        }

        public void m1099a(List<Pseudonym> list, int i, EtsyResult<Pseudonym> etsyResult) {
            EtsyDebug.m1906b(aj.f1427a, "Success fetching user pseudonym");
            if (aj.m1101a().m1118d()) {
                Pseudonym pseudonym = (Pseudonym) list.get(0);
                aj.m1101a().m1109a(pseudonym.getPseudonym());
                SharedPreferencesUtility.m3153k(this.f1426a.f1430d, pseudonym.getPseudonym());
            }
        }
    }

    public aj() {
        this.f1435i = new Object();
        this.f1436j = new EtsyId();
        this.f1437k = StringUtils.EMPTY;
        this.f1440n = null;
        this.f1441o = Collections.synchronizedList(new ArrayList());
    }

    static {
        f1427a = EtsyDebug.m1891a(aj.class);
        f1429c = new Object();
    }

    public static aj m1101a() {
        aj ajVar;
        synchronized (f1429c) {
            if (f1428b == null) {
                throw new IllegalStateException("Session must be created via createInstance before getInstance can be called");
            }
            ajVar = f1428b;
        }
        return ajVar;
    }

    public void m1107a(EtsyNetworkJob<?> etsyNetworkJob) {
        this.f1442p = etsyNetworkJob;
    }

    public static void m1102a(Context context, an anVar, boolean z) {
        synchronized (f1429c) {
            if (f1428b == null) {
                f1428b = new aj();
            }
            if (f1428b.f1430d == null) {
                f1428b.f1430d = context;
            }
            f1428b.f1431e = anVar;
            f1428b.f1432f = z;
            f1428b.m1116c();
        }
    }

    public void m1113b() {
        if (this.f1440n == null) {
            this.f1440n = new ak();
            NetworkUtils.m3107a().m3113a(this.f1440n);
        }
    }

    public void m1116c() {
        this.f1438l = new EtsyRequestQueue(this.f1430d);
        this.f1439m = new EtsyPostManager(this.f1430d);
        synchronized (this.f1435i) {
            this.f1434h = null;
        }
    }

    public void m1110a(boolean z) {
        m1103d(z);
    }

    private EtsyAuthManager m1106q() {
        if (this.f1434h == null) {
            synchronized (this.f1435i) {
                this.f1434h = m1121g();
            }
        }
        return this.f1434h;
    }

    public boolean m1118d() {
        return m1106q().m1183b();
    }

    public void m1119e() {
        m1114b(false);
    }

    public void m1114b(boolean z) {
        m1111a(z, true);
    }

    public void m1111a(boolean z, boolean z2) {
        m1106q().m1180a(z);
        m1116c();
        ((NotificationManager) this.f1430d.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME)).cancelAll();
        ConvoDatabaseUtil.m979c(this.f1430d);
        ConvoHelper.m957a(this.f1430d);
        EtsyConfig.m837a().m870d(this.f1430d);
        SharedPreferencesUtility.m3128b(this.f1430d);
        ak.m3247c(this.f1430d);
        if (z2) {
            m1110a(false);
        }
        m1123i().m1698a();
        if (this.f1431e != null && z2) {
            this.f1431e.m1141a(this.f1430d, z);
        }
        this.f1436j.setId(StringUtils.EMPTY);
        this.f1437k = StringUtils.EMPTY;
    }

    private void m1103d(boolean z) {
        for (al onSignedInChanged : this.f1441o) {
            onSignedInChanged.onSignedInChanged(this.f1430d, z);
        }
    }

    public boolean m1112a(al alVar) {
        return this.f1441o.add(alVar);
    }

    public boolean m1115b(al alVar) {
        return this.f1441o.remove(alVar);
    }

    public EtsyAuthManager m1120f() {
        return m1106q();
    }

    public EtsyAuthManager m1121g() {
        return new EtsyAuthManager(this.f1430d);
    }

    public EtsyAuthManager m1122h() {
        if (az.m3293b(this.f1430d)) {
            return m1101a().m1120f();
        }
        return m1101a().m1121g();
    }

    public EtsyRequestQueue m1123i() {
        return this.f1438l;
    }

    public EtsyPostManager m1124j() {
        return this.f1439m;
    }

    public EtsyId m1125k() {
        return this.f1436j;
    }

    public static boolean m1104l() {
        if (f1428b == null) {
            return false;
        }
        return m1101a().m1125k().hasId();
    }

    public EtsyId m1126m() {
        if (m1118d() && !this.f1436j.hasId()) {
            m1123i().m1700a((Object) "request_tag_user_id");
            if (this.f1442p != null) {
                m1123i().m1697a((Object) "request_tag_user_id", this.f1442p);
            }
            EtsyId c = SharedPreferencesUtility.m3134c(this.f1430d);
            if (c.hasId()) {
                this.f1436j = c;
            }
        } else if (!m1118d()) {
            this.f1436j.setId(StringUtils.EMPTY);
        }
        return this.f1436j;
    }

    public void m1108a(EtsyId etsyId) {
        this.f1436j = etsyId;
    }

    public String m1127n() {
        if (m1118d() && TextUtils.isEmpty(this.f1437k)) {
            m1123i().m1700a((Object) "request_tag_pseudonym");
            HttpRequestJobBuilder a = HttpRequestJobBuilder.m1712a(Pseudonym.class, "/etsyapps/v3/member/generate-pseudonym");
            a.m1743a(new Session(this)).m1741a(new Session(this)).m1742a(new Session(this));
            m1123i().m1697a((Object) "request_tag_pseudonym", a.m1737a());
            Object q = SharedPreferencesUtility.m3159q(this.f1430d);
            if (!TextUtils.isEmpty(q)) {
                this.f1437k = q;
            }
        } else if (!m1118d()) {
            this.f1437k = StringUtils.EMPTY;
        }
        return this.f1437k;
    }

    public void m1109a(String str) {
        this.f1437k = str;
    }

    public boolean m1128o() {
        return this.f1433g;
    }

    public void m1117c(boolean z) {
        this.f1433g = z;
    }
}
