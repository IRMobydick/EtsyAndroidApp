package bo.app;

final class gy implements Runnable {
    final /* synthetic */ int f682a;
    final /* synthetic */ Throwable f683b;
    final /* synthetic */ gw f684c;

    gy(gw gwVar, int i, Throwable th) {
        this.f684c = gwVar;
        this.f682a = i;
        this.f683b = th;
    }

    public final void run() {
        gl glVar = this.f684c.f665d;
        Object obj = (glVar.f556f == null && glVar.f553c == 0) ? null : 1;
        if (obj != null) {
            ie ieVar = this.f684c.f664c;
            glVar = this.f684c.f665d;
            ieVar.m532a(glVar.f553c != 0 ? this.f684c.f662a.f594a.getDrawable(glVar.f553c) : glVar.f556f);
        }
        ih ihVar = this.f684c.f666e;
        String str = this.f684c.f663b;
        this.f684c.f664c.m535d();
        hd hdVar = new hd(this.f682a, this.f683b);
    }
}
