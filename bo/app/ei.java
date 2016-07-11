package bo.app;

import java.util.concurrent.Executor;

public final class ei implements ed {
    public ce f379a;
    private final C0341g f380b;
    private final C0342i f381c;
    private final bc f382d;
    private final bc f383e;
    private final Executor f384f;
    private final eu f385g;
    private final ey f386h;

    public ei(C0341g c0341g, C0342i c0342i, bc bcVar, bc bcVar2, Executor executor, eu euVar, ey eyVar) {
        this.f380b = c0341g;
        this.f381c = c0342i;
        this.f382d = bcVar;
        this.f383e = bcVar2;
        this.f384f = executor;
        this.f385g = euVar;
        this.f386h = eyVar;
    }

    public final void m245a(ec ecVar) {
        if (ecVar instanceof eb) {
            this.f384f.execute(new dw((eb) ecVar, this.f380b, this.f381c, this.f382d, this.f383e, this.f385g, this.f379a, this.f386h));
        } else if (ecVar instanceof dr) {
            this.f384f.execute(new ef((dr) ecVar, new ds(), this.f381c, this.f382d, this.f383e));
        }
    }

    public final void m246b(ec ecVar) {
        if (ecVar instanceof eb) {
            new dw((eb) ecVar, this.f380b, this.f381c, this.f382d, this.f383e, this.f385g, this.f379a, this.f386h).run();
        } else if (ecVar instanceof dr) {
            new ef((dr) ecVar, new ds(), this.f381c, this.f382d, this.f383e).run();
        }
    }
}
