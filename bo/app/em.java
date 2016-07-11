package bo.app;

import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class em implements ew {
    final ew f395a;
    private final bq f396b;

    public em(ew ewVar, bq bqVar) {
        this.f395a = ewVar;
        this.f396b = bqVar;
    }

    public final synchronized void m258a(ct ctVar) {
        this.f396b.execute(new en(this, ctVar));
    }

    public final synchronized ct m257a() {
        try {
        } catch (Throwable e) {
            throw new RuntimeException("Error while trying to asynchronously get stored open sessions.", e);
        }
        return (ct) this.f396b.submit(new eo(this)).get(5000, TimeUnit.MILLISECONDS);
    }

    public final synchronized void m261b(ct ctVar) {
        m262c(ctVar);
    }

    public final synchronized Future<?> m262c(ct ctVar) {
        return this.f396b.submit(new ep(this, ctVar));
    }

    public final synchronized Collection<ct> m260b() {
        try {
        } catch (Throwable e) {
            throw new RuntimeException("Error while trying to asynchronously get sealed sessions.", e);
        }
        return (Collection) this.f396b.submit(new eq(this)).get(5000, TimeUnit.MILLISECONDS);
    }

    public final void m259a(ct ctVar, cp cpVar) {
        this.f396b.execute(new er(this, ctVar, cpVar));
    }
}
