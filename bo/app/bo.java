package bo.app;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class bo implements ThreadFactory {
    public UncaughtExceptionHandler f158a;
    private final AtomicInteger f159b;

    public bo() {
        this.f159b = new AtomicInteger(1);
    }

    public Thread newThread(Runnable runnable) {
        if (this.f158a == null) {
            throw new IllegalStateException("No UncaughtExceptionHandler. You must call setUncaughtExceptionHandler before creating a new thread");
        }
        Thread thread = new Thread(runnable, String.format("%s #%d", new Object[]{bo.class.getSimpleName(), Integer.valueOf(this.f159b.getAndIncrement())}));
        thread.setUncaughtExceptionHandler(this.f158a);
        return thread;
    }
}
