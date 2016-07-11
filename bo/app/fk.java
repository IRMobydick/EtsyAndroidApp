package bo.app;

import android.app.AlarmManager;
import android.content.Context;
import com.appboy.Constants;
import com.appboy.configuration.XmlAppConfigurationProvider;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

public final class fk {
    private static final String f448m;
    public final fa f449a;
    public final es f450b;
    public final az f451c;
    public final C0350o f452d;
    public final bv f453e;
    public final eu f454f;
    public final ao f455g;
    public final ThreadPoolExecutor f456h;
    public final ew f457i;
    public final C0341g f458j;
    public final ci f459k;
    public final ey f460l;
    private final C0360x f461n;
    private final ex f462o;
    private final C0355s f463p;
    private final by f464q;
    private final cf<dj> f465r;

    static {
        f448m = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, fk.class.getName()});
    }

    public fk(Context context, C0347m c0347m, XmlAppConfigurationProvider xmlAppConfigurationProvider, bc bcVar, bs bsVar, cj cjVar, boolean z) {
        el a;
        String a2 = c0347m.m613a();
        ThreadFactory boVar = new bo();
        this.f456h = new ThreadPoolExecutor(2, 16, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(40), boVar);
        this.f451c = new az(this.f456h);
        this.f460l = new ey(context, xmlAppConfigurationProvider.getAppboyApiKey().toString(), this.f451c);
        if (a2.equals(StringUtils.EMPTY)) {
            this.f449a = new fa(context, this.f460l);
            this.f450b = new es(context);
            a = el.m251a(context, null);
        } else {
            this.f449a = new fa(context, a2, xmlAppConfigurationProvider.getAppboyApiKey().toString(), this.f460l);
            this.f450b = new es(context, a2, xmlAppConfigurationProvider.getAppboyApiKey().toString());
            a = el.m251a(context, a2);
        }
        this.f465r = new ca(context);
        this.f465r.m85b();
        Context context2 = context;
        XmlAppConfigurationProvider xmlAppConfigurationProvider2 = xmlAppConfigurationProvider;
        bs bsVar2 = bsVar;
        cj cjVar2 = cjVar;
        cg cbVar = new cb(context2, xmlAppConfigurationProvider2, a2, bsVar2, cjVar2, this.f450b, this.f460l, this.f465r, this.f451c);
        this.f458j = new C0341g(xmlAppConfigurationProvider, cbVar);
        this.f458j.f525a = a2;
        this.f461n = new C0360x(this.f449a, cbVar);
        bq bqVar = new bq(boVar);
        boVar.f158a = new bp(this.f451c);
        this.f462o = new ex(a);
        em emVar = new em(this.f462o, bqVar);
        this.f457i = new et(emVar, this.f451c);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompatApi21.CATEGORY_ALARM);
        this.f464q = new by(this.f457i, this.f451c, context, alarmManager, xmlAppConfigurationProvider.getSessionTimeoutSeconds(), this.f460l);
        this.f454f = new eu(context, a2);
        bc bcVar2 = bcVar;
        ei eiVar = new ei(this.f458j, new C0345k(new C0346l(new C0343h())), this.f451c, bcVar2, this.f456h, this.f454f, this.f460l);
        this.f463p = new C0355s(context, this.f451c, new C0353q(), alarmManager, new C0354r(context));
        C0355s c0355s = this.f463p;
        az azVar = this.f451c;
        azVar.m35a(new C0357u(c0355s), bk.class);
        azVar.m35a(new C0358v(c0355s), bl.class);
        this.f463p.m634a();
        this.f452d = new C0350o(xmlAppConfigurationProvider, this.f451c, eiVar, this.f461n, boVar, z);
        this.f453e = new bv(this.f464q, this.f452d, cbVar, xmlAppConfigurationProvider, context, eiVar);
        if (!z) {
            eiVar.f379a = this.f453e;
        }
        this.f454f.f416e = this.f453e;
        this.f461n.f834b = this.f453e;
        this.f459k = new bt(context, this.f453e, xmlAppConfigurationProvider, this.f460l);
        em emVar2 = emVar;
        Context context3 = context;
        this.f455g = new ao(this.f459k, emVar2, this.f452d, this.f464q, this.f453e, context3, this.f449a, this.f450b, this.f460l, new dp(context, xmlAppConfigurationProvider.getAppboyApiKey().toString(), this.f453e));
    }
}
