package bo.app;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class fv extends FilterOutputStream {
    final /* synthetic */ fu f500a;

    private fv(fu fuVar, OutputStream outputStream) {
        this.f500a = fuVar;
        super(outputStream);
    }

    public final void write(int i) {
        try {
            this.out.write(i);
        } catch (IOException e) {
            this.f500a.f497c = true;
        }
    }

    public final void write(byte[] bArr, int i, int i2) {
        try {
            this.out.write(bArr, i, i2);
        } catch (IOException e) {
            this.f500a.f497c = true;
        }
    }

    public final void close() {
        try {
            this.out.close();
        } catch (IOException e) {
            this.f500a.f497c = true;
        }
    }

    public final void flush() {
        try {
            this.out.flush();
        } catch (IOException e) {
            this.f500a.f497c = true;
        }
    }
}
