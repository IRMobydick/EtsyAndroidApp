package bo.app;

import android.content.Context;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class gi {
    public static Executor m438a(int i, int i2, int i3) {
        return new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, (i3 == hj.f717b ? 1 : null) != null ? new ho() : new LinkedBlockingQueue(), m439a(i2, "uil-pool-"));
    }

    public static fo m437a(Context context, gc gcVar, long j, int i) {
        File a = is.m571a(context, false);
        File file = new File(a, "uil-images");
        if (file.exists() || file.mkdir()) {
            a = file;
        }
        if (j > 0 || i > 0) {
            File a2 = is.m571a(context, true);
            file = new File(a2, "uil-images");
            if (file.exists() || file.mkdir()) {
                a2 = file;
            }
            try {
                return new fy(a2, a, gcVar, j, i);
            } catch (Throwable e) {
                ip.m565a(e);
            }
        }
        return new fq(is.m571a(context, true), a, gcVar);
    }

    static ThreadFactory m439a(int i, String str) {
        return new gj(i, str);
    }
}
