package bo.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.appboy.support.AppboyLogger;

final class bz extends BroadcastReceiver {
    final /* synthetic */ by f211a;

    bz(by byVar) {
        this.f211a = byVar;
    }

    public final void onReceive(Context context, Intent intent) {
        synchronized (this.f211a.f201b) {
            try {
                this.f211a.m76h();
            } catch (Exception e) {
                try {
                    this.f211a.f203d.m31a(e, Throwable.class);
                } catch (Throwable e2) {
                    AppboyLogger.m665e(by.f200a, "Failed to log throwable.", e2);
                }
            }
        }
    }
}
