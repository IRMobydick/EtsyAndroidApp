package bo.app;

import android.os.AsyncTask;
import com.appboy.support.AppboyLogger;

final class dq extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ dp f342a;

    private dq(dp dpVar) {
        this.f342a = dpVar;
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        if (!this.f342a.f340b.getBoolean("piqqueue", false) || dp.f339c) {
            AppboyLogger.m666i(dp.f338a, "No placeIQ requests queued.");
        } else if (this.f342a.f341d != null) {
            bv b = this.f342a.f341d;
            if (b.f182d.m95f() != null) {
                AppboyLogger.m666i(bv.f179a, "Advertising Id present. Will request PlaceIQ id.");
                b.f184f.m240a(new dr("https://appboy.data.placeiq.com/dataex/id/", b.f182d.m95f()));
            } else {
                AppboyLogger.m666i(bv.f179a, "Advertising Id was null. Not requesting PlaceIQ id.");
            }
            dp.f339c = true;
        } else {
            AppboyLogger.m666i(dp.f338a, "Not calling placeIQ because Appboy manager is null.");
        }
        return null;
    }
}
