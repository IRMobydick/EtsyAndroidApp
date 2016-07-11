package bo.app;

import com.appboy.events.IEventSubscriber;
import com.appboy.support.AppboyLogger;
import java.util.concurrent.Semaphore;

public final class ay implements IEventSubscriber<Throwable> {
    final /* synthetic */ Semaphore f131a;
    final /* synthetic */ ao f132b;

    public ay(ao aoVar) {
        this.f132b = aoVar;
        this.f131a = null;
    }

    public final /* synthetic */ void trigger(Object obj) {
        try {
            this.f132b.f114f.m61a((Throwable) obj);
            if (this.f131a != null) {
                this.f131a.release();
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(ao.f109a, "Failed to log error.", e);
            if (this.f131a != null) {
                this.f131a.release();
            }
        } catch (Throwable th) {
            if (this.f131a != null) {
                this.f131a.release();
            }
        }
    }
}
