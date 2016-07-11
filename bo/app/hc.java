package bo.app;

import java.io.InputStream;

public final class hc extends InputStream {
    private final InputStream f693a;
    private final int f694b;

    public hc(InputStream inputStream, int i) {
        this.f693a = inputStream;
        this.f694b = i;
    }

    public final int available() {
        return this.f694b;
    }

    public final void close() {
        this.f693a.close();
    }

    public final void mark(int i) {
        this.f693a.mark(i);
    }

    public final int read() {
        return this.f693a.read();
    }

    public final int read(byte[] bArr) {
        return this.f693a.read(bArr);
    }

    public final int read(byte[] bArr, int i, int i2) {
        return this.f693a.read(bArr, i, i2);
    }

    public final void reset() {
        this.f693a.reset();
    }

    public final long skip(long j) {
        return this.f693a.skip(j);
    }

    public final boolean markSupported() {
        return this.f693a.markSupported();
    }
}
