package bo.app;

import android.os.AsyncTask;

final class ez extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ ey f434a;

    private ez(ey eyVar) {
        this.f434a = eyVar;
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return m304a();
    }

    private Void m304a() {
        cx cxVar = new cx();
        cxVar.f272c = this.f434a.m302h();
        cxVar.f271b = this.f434a.m301g();
        cxVar.f273d = this.f434a.m303i();
        cxVar.f270a = this.f434a.m300f();
        cxVar.f278i = this.f434a.m295a();
        cxVar.f275f = this.f434a.m297c();
        cxVar.f274e = this.f434a.m296b();
        cxVar.f276g = this.f434a.m298d();
        cxVar.f277h = this.f434a.m299e();
        synchronized (this.f434a.f430d) {
            this.f434a.f432f = cxVar;
        }
        return null;
    }
}
