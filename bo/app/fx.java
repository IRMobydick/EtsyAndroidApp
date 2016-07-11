package bo.app;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;

public final class fx implements Closeable {
    File[] f507a;
    final /* synthetic */ fr f508b;
    private final String f509c;
    private final long f510d;
    private final InputStream[] f511e;
    private final long[] f512f;

    private fx(fr frVar, String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr) {
        this.f508b = frVar;
        this.f509c = str;
        this.f510d = j;
        this.f507a = fileArr;
        this.f511e = inputStreamArr;
        this.f512f = jArr;
    }

    public final void close() {
        for (Closeable a : this.f511e) {
            gb.m417a(a);
        }
    }
}
