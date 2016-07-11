package bo.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.Constants;
import com.appboy.configuration.XmlAppConfigurationProvider;
import com.appboy.support.AppboyLogger;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public class bv implements ce {
    public static final String f179a;
    public final by f180b;
    public final C0349z f181c;
    public final cg f182d;
    public final XmlAppConfigurationProvider f183e;
    public final ed f184f;
    public Class<? extends Activity> f185g;
    private AtomicInteger f186h;
    private AtomicInteger f187i;
    private volatile String f188j;
    private final SharedPreferences f189k;

    static {
        f179a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, bv.class.getName()});
    }

    public bv(by byVar, C0349z c0349z, cg cgVar, XmlAppConfigurationProvider xmlAppConfigurationProvider, Context context, ed edVar) {
        this.f186h = new AtomicInteger(0);
        this.f187i = new AtomicInteger(0);
        this.f188j = StringUtils.EMPTY;
        this.f185g = null;
        this.f180b = byVar;
        this.f181c = c0349z;
        this.f182d = cgVar;
        this.f183e = xmlAppConfigurationProvider;
        this.f184f = edVar;
        this.f189k = context.getSharedPreferences("com.appboy.stored.push.clicks", 0);
    }

    public final ct m59a() {
        ct a = this.f180b.m77a();
        this.f181c.m614a(a);
        AppboyLogger.m666i(f179a, "Completed the openSession call. Starting or continuing session " + a.f257d);
        return a;
    }

    public final boolean m62a(cp cpVar) {
        boolean z = false;
        boolean z2 = cpVar.m126b().equals(ae.PUSH_NOTIFICATION_TRACKING) || cpVar.m126b().equals(ae.PUSH_NOTIFICATION_ACTION_TRACKING);
        if (z2 && (this.f180b.m80c() == null || this.f180b.m81d())) {
            if (cpVar.m126b().equals(ae.PUSH_NOTIFICATION_TRACKING) || cpVar.m126b().equals(ae.PUSH_NOTIFICATION_ACTION_TRACKING)) {
                z = true;
            }
            if (z) {
                Editor edit = this.f189k.edit();
                edit.putString(Double.toString(cpVar.m125a()), cpVar.m128d());
                edit.apply();
                return true;
            }
            String str = f179a;
            return true;
        } else if (cpVar == null) {
            throw new NullPointerException();
        } else {
            ct a = this.f180b.m78a(cpVar);
            if (a == null) {
                return false;
            }
            this.f181c.m614a(a);
            if (!a.m138c()) {
                return true;
            }
            m60a(ac.f18d);
            return true;
        }
    }

    public final void m61a(Throwable th) {
        try {
            if (m64b(th)) {
                AppboyLogger.m670w(f179a, "Not logging duplicate error.");
            } else {
                m62a(da.m160a(th, this.f180b.m80c()));
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f179a, String.format("Failed to create error event from %s.", new Object[]{th}), e);
        } catch (Throwable e2) {
            AppboyLogger.m665e(f179a, "Failed to log error.", e2);
        }
    }

    public final void m60a(int i) {
        this.f181c.m615a(new dy(this.f183e.getBaseUrlForRequests(), i));
    }

    public final boolean m64b(Throwable th) {
        this.f186h.getAndIncrement();
        if (this.f188j.equals(th.getMessage()) && this.f187i.get() > 3 && this.f186h.get() < 100) {
            return true;
        }
        if (this.f188j.equals(th.getMessage())) {
            this.f187i.getAndIncrement();
        } else {
            this.f187i.set(0);
        }
        if (this.f186h.get() >= 100) {
            this.f186h.set(0);
        }
        this.f188j = th.getMessage();
        return false;
    }

    public final void m63b() {
        if (this.f180b.m80c() != null && !this.f180b.m81d()) {
            for (String string : this.f189k.getAll().keySet()) {
                String string2 = this.f189k.getString(string, null);
                if (!fj.m353b(string2)) {
                    try {
                        m62a(da.m161a(new JSONObject(string2)));
                    } catch (Throwable e) {
                        AppboyLogger.m671w(f179a, "Could not log pending AppboyEvent from shared preferences storage. Serialized string is: " + string2, e);
                    }
                }
            }
            Editor edit = this.f189k.edit();
            edit.clear();
            edit.apply();
        }
    }
}
