package bo.app;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

final class ga extends ByteArrayOutputStream {
    final /* synthetic */ fz f530a;

    ga(fz fzVar, int i) {
        this.f530a = fzVar;
        super(i);
    }

    public final String toString() {
        int i = (this.count <= 0 || this.buf[this.count - 1] != 13) ? this.count : this.count - 1;
        try {
            return new String(this.buf, 0, i, this.f530a.f520a.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
