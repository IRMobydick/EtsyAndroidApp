package bo.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;

public final class by {
    private static final String f200a;
    private final Object f201b;
    private final ew f202c;
    private final bc f203d;
    private final Context f204e;
    private final AlarmManager f205f;
    private final int f206g;
    private final String f207h;
    private final ey f208i;
    private volatile ct f209j;
    private volatile boolean f210k;

    static {
        f200a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, by.class.getName()});
    }

    public by(ew ewVar, bc bcVar, Context context, AlarmManager alarmManager, int i, ey eyVar) {
        this.f201b = new Object();
        this.f210k = false;
        this.f202c = ewVar;
        this.f203d = bcVar;
        this.f204e = context;
        this.f205f = alarmManager;
        this.f206g = i;
        this.f208i = eyVar;
        BroadcastReceiver bzVar = new bz(this);
        this.f207h = context.getPackageName() + ".intent.APPBOY_SESSION_SHOULD_SEAL";
        context.registerReceiver(bzVar, new IntentFilter(this.f207h));
    }

    public final ct m77a() {
        ct ctVar;
        synchronized (this.f201b) {
            if (m75g()) {
                this.f202c.m253a(this.f209j);
            }
            Intent intent = new Intent(this.f207h);
            intent.putExtra("session_id", this.f209j.toString());
            this.f205f.cancel(PendingIntent.getBroadcast(this.f204e, 0, intent, 1073741824));
            this.f203d.m31a(bk.f156a, bk.class);
            ctVar = this.f209j;
        }
        return ctVar;
    }

    public final ct m79b() {
        ct ctVar;
        synchronized (this.f201b) {
            m75g();
            this.f209j.m135a(Double.valueOf(fd.m334b()));
            this.f202c.m253a(this.f209j);
            Intent intent = new Intent(this.f207h);
            intent.putExtra("session_id", this.f209j.toString());
            this.f205f.set(2, SystemClock.elapsedRealtime() + ((long) (this.f206g * Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS)), PendingIntent.getBroadcast(this.f204e, 0, intent, 1073741824));
            this.f203d.m31a(bl.f157a, bl.class);
            ctVar = this.f209j;
        }
        return ctVar;
    }

    public final ct m78a(cp cpVar) {
        synchronized (this.f201b) {
            m76h();
            if (this.f209j != null) {
                this.f209j.m136a(cpVar);
                this.f202c.m254a(this.f209j, cpVar);
                ct ctVar = this.f209j;
                return ctVar;
            }
            synchronized (bw.m65a().f195e) {
                if (bw.m65a().f192b) {
                    ctVar = bw.m65a().f193c;
                    if (ctVar != null) {
                        ctVar.m136a(cpVar);
                        this.f202c.m254a(ctVar, cpVar);
                        return ctVar;
                    }
                    AppboyLogger.m670w(f200a, "Could not access a stored session.  Dropping event");
                    return null;
                }
                bw a = bw.m65a();
                synchronized (a.f195e) {
                    if (cpVar != null) {
                        AppboyLogger.m666i(bw.f190a, "Queued event because no session exists.");
                        a.f194d.add(cpVar);
                    }
                }
                return null;
            }
        }
    }

    public final cy m80c() {
        cy cyVar;
        synchronized (this.f201b) {
            m76h();
            if (this.f209j == null) {
                cyVar = null;
            } else {
                cyVar = this.f209j.f257d;
            }
        }
        return cyVar;
    }

    public final boolean m81d() {
        boolean z;
        synchronized (this.f201b) {
            if (this.f209j == null) {
                z = false;
            } else {
                z = this.f209j.m138c();
            }
        }
        return z;
    }

    public final void m82e() {
        synchronized (this.f201b) {
            if (this.f209j != null) {
                ct ctVar = this.f209j;
                synchronized (ctVar.f262i) {
                    ctVar.f261h = true;
                    ctVar.m135a(Double.valueOf(fd.m334b()));
                }
                this.f202c.m253a(this.f209j);
                this.f203d.m31a(new bj(this.f209j), bj.class);
            }
        }
    }

    private boolean m75g() {
        synchronized (this.f201b) {
            m76h();
            if (this.f209j == null || this.f209j.m138c()) {
                ct ctVar = this.f209j;
                ct ctVar2 = new ct(cy.m144a(), fd.m334b());
                this.f208i.m294a(true);
                this.f203d.m31a(bi.f154a, bi.class);
                AppboyLogger.m666i(f200a, "New session created with ID: " + ctVar2.f257d);
                this.f209j = ctVar2;
                if (ctVar != null && ctVar.m142g()) {
                    String str = f200a;
                    String.format("Clearing completely dispatched sealed session %s", new Object[]{ctVar.f257d});
                    this.f202c.m256b(ctVar);
                }
                return true;
            } else if (this.f209j.m134a() != null) {
                this.f209j.m135a(null);
                return true;
            } else {
                return false;
            }
        }
    }

    private void m76h() {
        synchronized (this.f201b) {
            if (this.f209j == null && !this.f210k) {
                this.f209j = this.f202c.m252a();
                if (this.f209j != null) {
                    String str = f200a;
                    String.format("Restored session from offline storage: %s", new Object[]{this.f209j.f257d.toString()});
                }
            }
            this.f210k = true;
            if (!(this.f209j == null || this.f209j.m134a() == null || this.f209j.m138c() || (this.f209j.m134a().doubleValue() + ((double) ((long) this.f206g))) * 1000.0d > ((double) fd.m335c()))) {
                AppboyLogger.m666i(f200a, String.format("Session [%s] being sealed because its end time is over the grace period.", new Object[]{this.f209j.f257d}));
                m82e();
            }
        }
    }
}
