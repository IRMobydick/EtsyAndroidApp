package bo.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;

final class bu extends BroadcastReceiver {
    final /* synthetic */ bt f178a;

    bu(bt btVar) {
        this.f178a = btVar;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            AppboyLogger.m664e(bt.f167a, "Location broadcast receiver received null intent.");
            return;
        }
        String action = intent.getAction();
        if (action.endsWith(Constants.APPBOY_SINGLE_LOCATION_UPDATE_INTENT_SUFFIX)) {
            bt.m48a(this.f178a, intent);
        } else if (action.endsWith(Constants.APPBOY_REQUEST_INIT_LOCATION_SERVICE_INTENT_SUFFIX)) {
            this.f178a.m55b();
        }
    }
}
