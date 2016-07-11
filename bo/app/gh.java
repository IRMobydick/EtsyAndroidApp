package bo.app;

import android.graphics.Bitmap;
import com.etsy.android.uikit.view.ListingFullImageView;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;

public final class gh implements gf {
    private final LinkedHashMap<String, Bitmap> f535a;
    private final int f536b;
    private int f537c;

    public gh(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f536b = i;
        this.f535a = new LinkedHashMap(0, ListingFullImageView.ASPECT_RATIO_STANDARD, true);
    }

    public final Bitmap m433a(String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        Bitmap bitmap;
        synchronized (this) {
            bitmap = (Bitmap) this.f535a.get(str);
        }
        return bitmap;
    }

    public final boolean m435a(String str, Bitmap bitmap) {
        if (str == null || bitmap == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f537c += m431a(bitmap);
            Bitmap bitmap2 = (Bitmap) this.f535a.put(str, bitmap);
            if (bitmap2 != null) {
                this.f537c -= m431a(bitmap2);
            }
        }
        m432a(this.f536b);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m432a(int r4) {
        /*
        r3 = this;
    L_0x0000:
        monitor-enter(r3);
        r0 = r3.f537c;	 Catch:{ all -> 0x0032 }
        if (r0 < 0) goto L_0x0011;
    L_0x0005:
        r0 = r3.f535a;	 Catch:{ all -> 0x0032 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0035;
    L_0x000d:
        r0 = r3.f537c;	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0035;
    L_0x0011:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0032 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0032 }
        r1.<init>();	 Catch:{ all -> 0x0032 }
        r2 = r3.getClass();	 Catch:{ all -> 0x0032 }
        r2 = r2.getName();	 Catch:{ all -> 0x0032 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0032 }
        r2 = ".sizeOf() is reporting inconsistent results!";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0032 }
        r1 = r1.toString();	 Catch:{ all -> 0x0032 }
        r0.<init>(r1);	 Catch:{ all -> 0x0032 }
        throw r0;	 Catch:{ all -> 0x0032 }
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x0035:
        r0 = r3.f537c;	 Catch:{ all -> 0x0032 }
        if (r0 <= r4) goto L_0x0041;
    L_0x0039:
        r0 = r3.f535a;	 Catch:{ all -> 0x0032 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0043;
    L_0x0041:
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
    L_0x0042:
        return;
    L_0x0043:
        r0 = r3.f535a;	 Catch:{ all -> 0x0032 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0032 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0032 }
        r0 = r0.next();	 Catch:{ all -> 0x0032 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0032 }
        if (r0 != 0) goto L_0x0057;
    L_0x0055:
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
        goto L_0x0042;
    L_0x0057:
        r1 = r0.getKey();	 Catch:{ all -> 0x0032 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0032 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0032 }
        r0 = (android.graphics.Bitmap) r0;	 Catch:{ all -> 0x0032 }
        r2 = r3.f535a;	 Catch:{ all -> 0x0032 }
        r2.remove(r1);	 Catch:{ all -> 0x0032 }
        r1 = r3.f537c;	 Catch:{ all -> 0x0032 }
        r0 = m431a(r0);	 Catch:{ all -> 0x0032 }
        r0 = r1 - r0;
        r3.f537c = r0;	 Catch:{ all -> 0x0032 }
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.gh.a(int):void");
    }

    public final Bitmap m436b(String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        Bitmap bitmap;
        synchronized (this) {
            bitmap = (Bitmap) this.f535a.remove(str);
            if (bitmap != null) {
                this.f537c -= m431a(bitmap);
            }
        }
        return bitmap;
    }

    public final Collection<String> m434a() {
        Collection hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.f535a.keySet());
        }
        return hashSet;
    }

    private static int m431a(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public final synchronized String toString() {
        return String.format("LruCache[maxSize=%d]", new Object[]{Integer.valueOf(this.f536b)});
    }
}
