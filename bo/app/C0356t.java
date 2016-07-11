package bo.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.t */
final class C0356t extends BroadcastReceiver {
    final /* synthetic */ bc f827a;
    final /* synthetic */ C0355s f828b;

    C0356t(C0355s c0355s, bc bcVar) {
        this.f828b = c0355s;
        this.f827a = bcVar;
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            this.f828b.f819c.m625a(intent, (ConnectivityManager) context.getSystemService("connectivity"));
            this.f828b.m636c();
        } catch (Throwable e) {
            AppboyLogger.m665e(C0355s.f817a, "Failed to process connectivity event.", e);
            C0355s c0355s = this.f828b;
            C0355s.m631a(this.f827a, e);
        }
    }
}
