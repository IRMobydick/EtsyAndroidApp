package bo.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.appboy.Constants;
import com.appboy.services.AppboyDataSyncService;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

/* renamed from: bo.app.s */
public final class C0355s {
    private static final String f817a;
    private final Context f818b;
    private final C0352y f819c;
    private final AlarmManager f820d;
    private final C0354r f821e;
    private final BroadcastReceiver f822f;
    private final PendingIntent f823g;
    private int f824h;
    private long f825i;
    private volatile boolean f826j;

    static /* synthetic */ void m631a(bc bcVar, Throwable th) {
        try {
            bcVar.m31a(th, Throwable.class);
        } catch (Throwable e) {
            AppboyLogger.m665e(f817a, "Failed to log throwable.", e);
        }
    }

    static {
        f817a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, C0355s.class.getName()});
    }

    public C0355s(Context context, bc bcVar, C0352y c0352y, AlarmManager alarmManager, C0354r c0354r) {
        this.f826j = false;
        this.f818b = context;
        this.f819c = c0352y;
        this.f820d = alarmManager;
        this.f821e = c0354r;
        this.f824h = ak.f73b;
        this.f825i = -1;
        if (!fi.m347a(this.f818b, AppboyDataSyncService.class)) {
            AppboyLogger.m664e(f817a, String.format("Appboy periodic data flushing is not available. Declare <service android:name=\"com.appboy.services.AppboyDataSyncService\"/> in your AndroidManifest.xml to enable Appboy periodic data flushing.", new Object[0]));
        }
        this.f823g = PendingIntent.getService(this.f818b, 0, new Intent(context.getApplicationContext().getPackageName() + Constants.APPBOY_REQUEST_SYNC_INTENT_SUFFIX).setClass(context, AppboyDataSyncService.class), 134217728);
        this.f822f = new C0356t(this, bcVar);
        String str = f817a;
    }

    public final synchronized boolean m634a() {
        boolean z = true;
        synchronized (this) {
            if (this.f826j) {
                String str = f817a;
                z = false;
            } else {
                this.f818b.registerReceiver(this.f822f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                m630a(3000);
                this.f826j = true;
            }
        }
        return z;
    }

    public final synchronized boolean m635b() {
        boolean z = false;
        synchronized (this) {
            if (this.f826j) {
                m633e();
                this.f818b.unregisterReceiver(this.f822f);
                this.f826j = false;
                z = true;
            } else {
                String str = f817a;
            }
        }
        return z;
    }

    final void m636c() {
        long j = this.f825i;
        switch (C0359w.f831a[this.f819c.m624a().ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.f825i = -1;
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.f825i = (long) (this.f821e.getIntValue("com_appboy_data_flush_interval_bad_network", 60) * Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS);
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                this.f825i = (long) (this.f821e.getIntValue("com_appboy_data_flush_interval_great_network", 10) * Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS);
                break;
            default:
                this.f825i = (long) (this.f821e.getIntValue("com_appboy_data_flush_interval_good_network", 30) * Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS);
                break;
        }
        if (this.f824h == ak.f73b) {
            this.f825i = -1;
        }
        if (j != this.f825i) {
            m630a(this.f825i);
            String str = f817a;
            String.format("Dispatch state has changed from %d to %d.", new Object[]{Long.valueOf(j), Long.valueOf(this.f825i)});
        }
    }

    private void m630a(long j) {
        String str;
        if (this.f820d == null) {
            str = f817a;
        } else if (this.f825i <= 0) {
            str = f817a;
            m633e();
        } else {
            this.f820d.setInexactRepeating(1, fd.m335c() + j, this.f825i, this.f823g);
        }
    }

    private void m633e() {
        this.f820d.cancel(this.f823g);
    }
}
