package bo.app;

import com.appboy.Appboy;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.support.AppboyLogger;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

/* renamed from: bo.app.b */
public final class C0334b implements Runnable {
    final /* synthetic */ Appboy f141a;

    public C0334b(Appboy appboy) {
        this.f141a = appboy;
    }

    public final void run() {
        try {
            az b = this.f141a.f854k;
            eu euVar = this.f141a.f849b;
            b.m34a(euVar.m276a(new JSONArray(euVar.f413b.getString("cards", "[]")), euVar.f413b.getString("uid", StringUtils.EMPTY), true, euVar.f413b.getLong("cards_timestamp", -1)), FeedUpdatedEvent.class);
        } catch (Throwable e) {
            AppboyLogger.m671w(Appboy.f840e, "Failed to retrieve and publish feed from offline cache.", e);
        }
    }
}
