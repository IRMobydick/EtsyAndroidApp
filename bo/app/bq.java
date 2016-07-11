package bo.app;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class bq extends ThreadPoolExecutor {
    private static final TimeUnit f162a;

    static {
        f162a = TimeUnit.MILLISECONDS;
    }

    public bq(ThreadFactory threadFactory) {
        super(1, 1, 0, f162a, new LinkedBlockingQueue(), threadFactory);
    }
}
