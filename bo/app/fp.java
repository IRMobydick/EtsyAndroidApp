package bo.app;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public abstract class fp implements fo {
    public static final CompressFormat f469a;
    protected final File f470b;
    protected final File f471c;
    protected final gc f472d;
    protected int f473e;
    protected CompressFormat f474f;
    protected int f475g;

    static {
        f469a = CompressFormat.PNG;
    }

    public fp(File file, File file2, gc gcVar) {
        this.f473e = AccessibilityNodeInfoCompat.ACTION_PASTE;
        this.f474f = f469a;
        this.f475g = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (gcVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            this.f470b = file;
            this.f471c = file2;
            this.f472d = gcVar;
        }
    }

    public final File m370a(String str) {
        return m369b(str);
    }

    public final boolean m372a(String str, InputStream inputStream, io ioVar) {
        Throwable th;
        File b = m369b(str);
        File file = new File(b.getAbsolutePath() + ".tmp");
        Closeable bufferedOutputStream;
        boolean a;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.f473e);
            a = in.m562a(inputStream, bufferedOutputStream, ioVar, this.f473e);
            try {
                in.m560a(bufferedOutputStream);
                if (a && !file.renameTo(b)) {
                    a = false;
                }
                if (!a) {
                    file.delete();
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                a = false;
                if (!a) {
                    file.delete();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            a = false;
            if (a && !file.renameTo(b)) {
                a = false;
            }
            if (a) {
                file.delete();
            }
            throw th;
        }
    }

    public final boolean m371a(String str, Bitmap bitmap) {
        File b = m369b(str);
        File file = new File(b.getAbsolutePath() + ".tmp");
        Closeable bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.f473e);
        try {
            boolean compress = bitmap.compress(this.f474f, this.f475g, bufferedOutputStream);
            in.m560a(bufferedOutputStream);
            if (compress && !file.renameTo(b)) {
                compress = false;
            }
            if (!compress) {
                file.delete();
            }
            bitmap.recycle();
            return compress;
        } catch (Throwable th) {
            in.m560a(bufferedOutputStream);
            file.delete();
        }
    }

    private File m369b(String str) {
        String a = this.f472d.m419a(str);
        File file = this.f470b;
        if (!(this.f470b.exists() || this.f470b.mkdirs() || this.f471c == null || (!this.f471c.exists() && !this.f471c.mkdirs()))) {
            file = this.f471c;
        }
        return new File(file, a);
    }
}
