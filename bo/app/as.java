package bo.app;

import android.content.SharedPreferences.Editor;
import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;

public final class as implements IEventSubscriber<C0337do> {
    final /* synthetic */ ao f125a;

    public as(ao aoVar) {
        this.f125a = aoVar;
    }

    public final /* synthetic */ void trigger(Object obj) {
        dt dtVar = ((C0337do) obj).f336a;
        if ((dtVar.f348a != null ? 1 : null) != null) {
            AppboyLogger.m666i(ao.f109a, "Received PlaceIQ id: " + dtVar.f348a);
            try {
                dp g = this.f125a.f120l;
                AppboyLogger.m666i(dp.f338a, "Clearing placeIQ request.");
                Editor edit = g.f340b.edit();
                edit.remove("piqqueue");
                edit.apply();
                this.f125a.f116h.m325h(dtVar.f348a);
                return;
            } catch (Throwable e) {
                AppboyLogger.m665e(ao.f109a, "Failed to log PlaceIQ id event", e);
                return;
            }
        }
        AppboyLogger.m670w(ao.f109a, "Received PlaceIQ response without PlaceIQ Id.");
    }
}
