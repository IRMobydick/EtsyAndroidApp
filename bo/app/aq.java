package bo.app;

import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;

public final class aq implements IEventSubscriber<bm> {
    final /* synthetic */ ao f122a;

    public aq(ao aoVar) {
        this.f122a = aoVar;
    }

    public final /* synthetic */ void trigger(Object obj) {
        bm bmVar = (bm) obj;
        try {
            bv k = this.f122a.f114f;
            try {
                if (k.m64b(bmVar)) {
                    AppboyLogger.m670w(bv.f179a, "Not logging duplicate database exception.");
                } else {
                    k.m62a(da.m149a(bmVar, k.f180b.m80c()));
                }
            } catch (Throwable e) {
                AppboyLogger.m665e(bv.f179a, String.format("Failed to create database exception event from %s.", new Object[]{bmVar}), e);
            } catch (Throwable e2) {
                AppboyLogger.m665e(bv.f179a, "Failed to log error.", e2);
            }
        } catch (Throwable e22) {
            AppboyLogger.m665e(ao.f109a, "Failed to log the database exception.", e22);
        }
    }
}
