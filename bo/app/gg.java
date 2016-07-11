package bo.app;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.Comparator;

public final class gg implements gf {
    private final gf f533a;
    private final Comparator<String> f534b;

    public gg(gf gfVar, Comparator<String> comparator) {
        this.f533a = gfVar;
        this.f534b = comparator;
    }

    public final boolean m429a(String str, Bitmap bitmap) {
        synchronized (this.f533a) {
            for (String str2 : this.f533a.m424a()) {
                if (this.f534b.compare(str, str2) == 0) {
                    break;
                }
            }
            String str22 = null;
            if (str22 != null) {
                this.f533a.m426b(str22);
            }
        }
        return this.f533a.m425a(str, bitmap);
    }

    public final Bitmap m427a(String str) {
        return this.f533a.m423a(str);
    }

    public final Bitmap m430b(String str) {
        return this.f533a.m426b(str);
    }

    public final Collection<String> m428a() {
        return this.f533a.m424a();
    }
}
