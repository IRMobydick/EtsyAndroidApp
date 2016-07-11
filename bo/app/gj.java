package bo.app;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class gj implements ThreadFactory {
    private static final AtomicInteger f538a;
    private final ThreadGroup f539b;
    private final AtomicInteger f540c;
    private final String f541d;
    private final int f542e;

    static {
        f538a = new AtomicInteger(1);
    }

    gj(int i, String str) {
        this.f540c = new AtomicInteger(1);
        this.f542e = i;
        this.f539b = Thread.currentThread().getThreadGroup();
        this.f541d = str + f538a.getAndIncrement() + "-thread-";
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f539b, runnable, this.f541d + this.f540c.getAndIncrement(), 0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        thread.setPriority(this.f542e);
        return thread;
    }
}
