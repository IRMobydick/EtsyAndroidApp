package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzqi.zza;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Future;
import org.apache.commons.lang3.StringUtils;

@jw
public class li implements lr {
    private long f5380A;
    private final Object f5381a;
    private final String f5382b;
    private final lj f5383c;
    private dc f5384d;
    private BigInteger f5385e;
    private final HashSet<ld> f5386f;
    private final HashMap<String, ll> f5387g;
    private boolean f5388h;
    private boolean f5389i;
    private int f5390j;
    private boolean f5391k;
    private Context f5392l;
    private VersionInfoParcel f5393m;
    private eb f5394n;
    private boolean f5395o;
    private zzcf f5396p;
    private di f5397q;
    private dh f5398r;
    private String f5399s;
    private Boolean f5400t;
    private String f5401u;
    private boolean f5402v;
    private boolean f5403w;
    private boolean f5404x;
    private boolean f5405y;
    private String f5406z;

    public li(lt ltVar) {
        this.f5381a = new Object();
        this.f5385e = BigInteger.ONE;
        this.f5386f = new HashSet();
        this.f5387g = new HashMap();
        this.f5388h = false;
        this.f5389i = true;
        this.f5390j = 0;
        this.f5391k = false;
        this.f5394n = null;
        this.f5395o = true;
        this.f5396p = null;
        this.f5397q = null;
        this.f5398r = null;
        this.f5400t = null;
        this.f5402v = false;
        this.f5403w = false;
        this.f5404x = false;
        this.f5405y = false;
        this.f5406z = StringUtils.EMPTY;
        this.f5380A = 0;
        this.f5382b = ltVar.m7130d();
        this.f5383c = new lj(this.f5382b);
    }

    public Bundle m7009a(Context context, lk lkVar, String str) {
        Bundle bundle;
        synchronized (this.f5381a) {
            bundle = new Bundle();
            bundle.putBundle("app", this.f5383c.m7045a(context, str));
            Bundle bundle2 = new Bundle();
            for (String str2 : this.f5387g.keySet()) {
                bundle2.putBundle(str2, ((ll) this.f5387g.get(str2)).m7049a());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.f5386f.iterator();
            while (it.hasNext()) {
                arrayList.add(((ld) it.next()).m6998d());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            lkVar.zza(this.f5386f);
            this.f5386f.clear();
        }
        return bundle;
    }

    public di m7010a(Context context) {
        if (!((Boolean) dz.f4811K.m6433c()).booleanValue() || !zzs.zzva() || m7026b()) {
            return null;
        }
        synchronized (this.f5381a) {
            if (Looper.getMainLooper() == null || context == null) {
                return null;
            }
            if (this.f5396p == null) {
                Application application = (Application) context.getApplicationContext();
                if (application == null) {
                    application = (Application) context;
                }
                this.f5396p = new zzcf(application, context);
            }
            if (this.f5398r == null) {
                this.f5398r = new dh();
            }
            if (this.f5397q == null) {
                this.f5397q = new di(this.f5396p, this.f5398r, new jv(this.f5392l, this.f5393m, null, null));
            }
            this.f5397q.m6381a();
            di diVar = this.f5397q;
            return diVar;
        }
    }

    public String m7011a() {
        return this.f5382b;
    }

    public Future m7012a(Context context, String str) {
        Future a;
        this.f5380A = C1101o.m6045i().currentTimeMillis();
        synchronized (this.f5381a) {
            if (str != null) {
                if (!str.equals(this.f5406z)) {
                    this.f5406z = str;
                    a = lp.m7060a(context, str, this.f5380A);
                }
            }
            a = null;
        }
        return a;
    }

    public Future m7013a(Context context, boolean z) {
        Future a;
        synchronized (this.f5381a) {
            if (z != this.f5389i) {
                this.f5389i = z;
                a = lp.m7061a(context, z);
            } else {
                a = null;
            }
        }
        return a;
    }

    public Future m7014a(String str) {
        Future a;
        synchronized (this.f5381a) {
            if (str != null) {
                if (!str.equals(this.f5399s)) {
                    this.f5399s = str;
                    a = lp.m7059a(this.f5392l, str);
                }
            }
            a = null;
        }
        return a;
    }

    @TargetApi(23)
    public void m7015a(Context context, VersionInfoParcel versionInfoParcel) {
        synchronized (this.f5381a) {
            if (!this.f5391k) {
                this.f5392l = context.getApplicationContext();
                this.f5393m = versionInfoParcel;
                lp.m7058a(context, (lr) this);
                lp.m7062b(context, (lr) this);
                lp.m7064c(context, (lr) this);
                lp.m7066d(context, this);
                lp.m7067e(context, this);
                lp.m7068f(context, this);
                m7020a(Thread.currentThread());
                this.f5401u = C1101o.m6041e().m7092a(context, versionInfoParcel.afmaVersion);
                if (zzs.zzvh() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                    this.f5403w = true;
                }
                this.f5384d = new dc(context.getApplicationContext(), this.f5393m, C1101o.m6041e().m7089a(context, versionInfoParcel));
                m7042q();
                C1101o.m6051o().m6099a(this.f5392l);
                this.f5391k = true;
            }
        }
    }

    public void m7016a(Bundle bundle) {
        synchronized (this.f5381a) {
            this.f5389i = bundle.containsKey("use_https") ? bundle.getBoolean("use_https") : this.f5389i;
            this.f5390j = bundle.containsKey("webview_cache_version") ? bundle.getInt("webview_cache_version") : this.f5390j;
            if (bundle.containsKey("content_url_opted_out")) {
                m7023a(bundle.getBoolean("content_url_opted_out"));
            }
            if (bundle.containsKey("content_url_hashes")) {
                this.f5399s = bundle.getString("content_url_hashes");
            }
            this.f5404x = bundle.containsKey("auto_collect_location") ? bundle.getBoolean("auto_collect_location") : this.f5404x;
            this.f5406z = bundle.containsKey("app_settings_json") ? bundle.getString("app_settings_json") : this.f5406z;
            this.f5380A = bundle.containsKey("app_settings_last_update_ms") ? bundle.getLong("app_settings_last_update_ms") : 0;
        }
    }

    public void m7017a(ld ldVar) {
        synchronized (this.f5381a) {
            this.f5386f.add(ldVar);
        }
    }

    public void m7018a(Boolean bool) {
        synchronized (this.f5381a) {
            this.f5400t = bool;
        }
    }

    public void m7019a(String str, ll llVar) {
        synchronized (this.f5381a) {
            this.f5387g.put(str, llVar);
        }
    }

    public void m7020a(Thread thread) {
        jv.m6880a(this.f5392l, thread, this.f5393m);
    }

    public void m7021a(Throwable th, boolean z) {
        new jv(this.f5392l, this.f5393m, null, null).m6884a(th, z);
    }

    public void m7022a(HashSet<ld> hashSet) {
        synchronized (this.f5381a) {
            this.f5386f.addAll(hashSet);
        }
    }

    public void m7023a(boolean z) {
        synchronized (this.f5381a) {
            if (this.f5395o != z) {
                lp.m7063b(this.f5392l, z);
            }
            this.f5395o = z;
            di a = m7010a(this.f5392l);
            if (!(a == null || a.isAlive())) {
                C1129c.m6190c("start fetching content...");
                a.m6381a();
            }
        }
    }

    public Future m7024b(Context context, boolean z) {
        Future c;
        synchronized (this.f5381a) {
            if (z != this.f5404x) {
                this.f5404x = z;
                c = lp.m7065c(context, z);
            } else {
                c = null;
            }
        }
        return c;
    }

    public void m7025b(boolean z) {
        this.f5405y = z;
    }

    public boolean m7026b() {
        boolean z;
        synchronized (this.f5381a) {
            z = this.f5395o;
        }
        return z;
    }

    public String m7027c() {
        String bigInteger;
        synchronized (this.f5381a) {
            bigInteger = this.f5385e.toString();
            this.f5385e = this.f5385e.add(BigInteger.ONE);
        }
        return bigInteger;
    }

    public void m7028c(boolean z) {
        synchronized (this.f5381a) {
            this.f5402v = z;
        }
    }

    public lj m7029d() {
        lj ljVar;
        synchronized (this.f5381a) {
            ljVar = this.f5383c;
        }
        return ljVar;
    }

    public eb m7030e() {
        eb ebVar;
        synchronized (this.f5381a) {
            ebVar = this.f5394n;
        }
        return ebVar;
    }

    public boolean m7031f() {
        boolean z;
        synchronized (this.f5381a) {
            z = this.f5388h;
            this.f5388h = true;
        }
        return z;
    }

    public boolean m7032g() {
        boolean z;
        synchronized (this.f5381a) {
            z = this.f5389i || this.f5403w;
        }
        return z;
    }

    public String m7033h() {
        String str;
        synchronized (this.f5381a) {
            str = this.f5401u;
        }
        return str;
    }

    public String m7034i() {
        String str;
        synchronized (this.f5381a) {
            str = this.f5399s;
        }
        return str;
    }

    public Boolean m7035j() {
        Boolean bool;
        synchronized (this.f5381a) {
            bool = this.f5400t;
        }
        return bool;
    }

    public boolean m7036k() {
        boolean z;
        synchronized (this.f5381a) {
            z = this.f5404x;
        }
        return z;
    }

    public boolean m7037l() {
        return this.f5405y;
    }

    public lf m7038m() {
        lf lfVar;
        synchronized (this.f5381a) {
            lfVar = new lf(this.f5406z, this.f5380A);
        }
        return lfVar;
    }

    public dc m7039n() {
        return this.f5384d;
    }

    public Resources m7040o() {
        if (this.f5393m.zzRE) {
            return this.f5392l.getResources();
        }
        try {
            zzqi a = zzqi.a(this.f5392l, zzqi.a, "com.google.android.gms.ads.dynamite");
            return a != null ? a.a().getResources() : null;
        } catch (zza e) {
            C1129c.m6193d("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public boolean m7041p() {
        boolean z;
        synchronized (this.f5381a) {
            z = this.f5402v;
        }
        return z;
    }

    void m7042q() {
        try {
            this.f5394n = C1101o.m6046j().m6460a(new ea(this.f5392l, this.f5393m.afmaVersion));
        } catch (Throwable e) {
            C1129c.m6193d("Cannot initialize CSI reporter.", e);
        }
    }
}
