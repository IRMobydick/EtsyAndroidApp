package bo.app;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

final class fw {
    final String f501a;
    final long[] f502b;
    boolean f503c;
    fu f504d;
    long f505e;
    final /* synthetic */ fr f506f;

    private fw(fr frVar, String str) {
        this.f506f = frVar;
        this.f501a = str;
        this.f502b = new long[frVar.f486j];
    }

    public final String m404a() {
        StringBuilder stringBuilder = new StringBuilder();
        for (long append : this.f502b) {
            stringBuilder.append(' ').append(append);
        }
        return stringBuilder.toString();
    }

    final void m405a(String[] strArr) {
        if (strArr.length != this.f506f.f486j) {
            throw m402b(strArr);
        }
        int i = 0;
        while (i < strArr.length) {
            try {
                this.f502b[i] = Long.parseLong(strArr[i]);
                i++;
            } catch (NumberFormatException e) {
                throw m402b(strArr);
            }
        }
    }

    private static IOException m402b(String[] strArr) {
        throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
    }

    public final File m403a(int i) {
        return new File(this.f498d.f479c, this.f501a + "." + i);
    }

    public final File m406b(int i) {
        return new File(this.f506f.f479c, this.f501a + "." + i + ".tmp");
    }
}
