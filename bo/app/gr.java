package bo.app;

import com.google.android.gms.gcm.Task;
import java.io.InputStream;

final class gr implements ic {
    private final ic f640a;

    public gr(ic icVar) {
        this.f640a = icVar;
    }

    public final InputStream m465a(String str, Object obj) {
        switch (gp.f614a[id.m526a(str).ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
                throw new IllegalStateException();
            default:
                return this.f640a.m464a(str, obj);
        }
    }
}
