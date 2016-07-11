package bo.app;

import com.google.android.gms.gcm.Task;
import java.io.InputStream;

final class gs implements ic {
    private final ic f641a;

    public gs(ic icVar) {
        this.f641a = icVar;
    }

    public final InputStream m466a(String str, Object obj) {
        InputStream a = this.f641a.m464a(str, obj);
        switch (gp.f614a[id.m526a(str).ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
                return new hf(a);
            default:
                return a;
        }
    }
}
