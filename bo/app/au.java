package bo.app;

import android.content.SharedPreferences.Editor;
import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;

public final class au implements IEventSubscriber<du> {
    final /* synthetic */ ao f127a;

    public au(ao aoVar) {
        this.f127a = aoVar;
    }

    public final /* synthetic */ void trigger(Object obj) {
        dp g = this.f127a.f120l;
        AppboyLogger.m666i(dp.f338a, "Queuing placeIQ request.");
        Editor edit = g.f340b.edit();
        edit.putBoolean("piqqueue", true);
        edit.apply();
    }
}
