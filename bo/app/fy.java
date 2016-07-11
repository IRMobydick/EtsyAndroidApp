package bo.app;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.foresee.sdk.configuration.MeasureConfiguration;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public final class fy implements fo {
    public static final CompressFormat f513a;
    protected fr f514b;
    protected final gc f515c;
    protected int f516d;
    protected CompressFormat f517e;
    protected int f518f;
    private File f519g;

    static {
        f513a = CompressFormat.PNG;
    }

    public fy(File file, File file2, gc gcVar, long j, int i) {
        this.f516d = AccessibilityNodeInfoCompat.ACTION_PASTE;
        this.f517e = f513a;
        this.f518f = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (j < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        } else if (i < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        } else if (gcVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            long j2;
            int i2;
            if (j == 0) {
                j2 = Long.MAX_VALUE;
            } else {
                j2 = j;
            }
            if (i == 0) {
                i2 = MeasureConfiguration.DISABLED;
            } else {
                i2 = i;
            }
            this.f519g = file2;
            this.f515c = gcVar;
            m407a(file, file2, j2, i2);
        }
    }

    private void m407a(File file, File file2, long j, int i) {
        try {
            this.f514b = fr.m373a(file, j, i);
        } catch (Throwable e) {
            ip.m565a(e);
            if (file2 != null) {
                m407a(file2, null, j, i);
            }
            if (this.f514b == null) {
                throw e;
            }
        }
    }

    public final File m409a(String str) {
        Throwable e;
        Throwable th;
        File file = null;
        fx a;
        try {
            a = this.f514b.m395a(m408b(str));
            if (a != null) {
                try {
                    file = a.f507a[0];
                } catch (IOException e2) {
                    e = e2;
                    try {
                        ip.m565a(e);
                        if (a != null) {
                            a.close();
                        }
                        return file;
                    } catch (Throwable th2) {
                        th = th2;
                        if (a != null) {
                            a.close();
                        }
                        throw th;
                    }
                }
            }
            if (a != null) {
                a.close();
            }
        } catch (IOException e3) {
            e = e3;
            a = file;
            ip.m565a(e);
            if (a != null) {
                a.close();
            }
            return file;
        } catch (Throwable e4) {
            a = file;
            th = e4;
            if (a != null) {
                a.close();
            }
            throw th;
        }
        return file;
    }

    public final boolean m411a(String str, InputStream inputStream, io ioVar) {
        fu b = this.f514b.m396b(m408b(str));
        if (b == null) {
            return false;
        }
        Closeable bufferedOutputStream = new BufferedOutputStream(b.m399a(), this.f516d);
        try {
            boolean a = in.m562a(inputStream, bufferedOutputStream, ioVar, this.f516d);
            in.m560a(bufferedOutputStream);
            if (a) {
                b.m400b();
                return a;
            }
            b.m401c();
            return a;
        } catch (Throwable th) {
            in.m560a(bufferedOutputStream);
            b.m401c();
        }
    }

    public final boolean m410a(String str, Bitmap bitmap) {
        fu b = this.f514b.m396b(m408b(str));
        if (b == null) {
            return false;
        }
        Closeable bufferedOutputStream = new BufferedOutputStream(b.m399a(), this.f516d);
        try {
            boolean compress = bitmap.compress(this.f517e, this.f518f, bufferedOutputStream);
            if (compress) {
                b.m400b();
                return compress;
            }
            b.m401c();
            return compress;
        } finally {
            in.m560a(bufferedOutputStream);
        }
    }

    private String m408b(String str) {
        return this.f515c.m419a(str);
    }
}
