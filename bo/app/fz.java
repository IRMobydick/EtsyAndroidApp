package bo.app;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

final class fz implements Closeable {
    final Charset f520a;
    private final InputStream f521b;
    private byte[] f522c;
    private int f523d;
    private int f524e;

    public fz(InputStream inputStream, Charset charset) {
        this(inputStream, charset, (byte) 0);
    }

    private fz(InputStream inputStream, Charset charset, byte b) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (charset.equals(gb.f531a)) {
            this.f521b = inputStream;
            this.f520a = charset;
            this.f522c = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public final void close() {
        synchronized (this.f521b) {
            if (this.f522c != null) {
                this.f522c = null;
                this.f521b.close();
            }
        }
    }

    public final String m413a() {
        String str;
        synchronized (this.f521b) {
            if (this.f522c == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f523d >= this.f524e) {
                m412b();
            }
            int i2 = this.f523d;
            while (i2 != this.f524e) {
                if (this.f522c[i2] == (byte) 10) {
                    int i3 = (i2 == this.f523d || this.f522c[i2 - 1] != 13) ? i2 : i2 - 1;
                    str = new String(this.f522c, this.f523d, i3 - this.f523d, this.f520a.name());
                    this.f523d = i2 + 1;
                } else {
                    i2++;
                }
            }
            ByteArrayOutputStream gaVar = new ga(this, (this.f524e - this.f523d) + 80);
            loop1:
            while (true) {
                gaVar.write(this.f522c, this.f523d, this.f524e - this.f523d);
                this.f524e = -1;
                m412b();
                i = this.f523d;
                while (i != this.f524e) {
                    if (this.f522c[i] == (byte) 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f523d) {
                gaVar.write(this.f522c, this.f523d, i - this.f523d);
            }
            this.f523d = i + 1;
            str = gaVar.toString();
        }
        return str;
    }

    private void m412b() {
        int read = this.f521b.read(this.f522c, 0, this.f522c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f523d = 0;
        this.f524e = read;
    }
}
