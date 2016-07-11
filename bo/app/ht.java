package bo.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import com.etsy.android.uikit.view.FullImageView;
import java.io.IOException;
import java.io.InputStream;

public final class ht implements hw {
    protected final boolean f738a;

    public ht(boolean z) {
        this.f738a = z;
    }

    public final Bitmap m520a(hx hxVar) {
        InputStream b = m519b(hxVar);
        if (b == null) {
            ip.m569d("No stream for image [%s]", hxVar.f743a);
            return null;
        }
        try {
            hu a;
            hv hvVar;
            hh hhVar;
            int i;
            Bitmap decodeStream;
            int i2;
            boolean z;
            Matrix matrix;
            int i3;
            float b2;
            Bitmap createBitmap;
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(b, null, options);
            String str = hxVar.f744b;
            if (hxVar.f750h) {
                boolean z2 = "image/jpeg".equalsIgnoreCase(options.outMimeType) && id.m526a(str) == id.FILE;
                if (z2) {
                    a = m517a(str);
                    hvVar = new hv(new hh(options.outWidth, options.outHeight, a.f739a), a);
                    b = m518a(b, hxVar);
                    hhVar = hvVar.f741a;
                    i = hxVar.f746d;
                    if (i == hg.f703a) {
                        i = 1;
                    } else if (i != hg.f704b) {
                        i = il.m556a(hhVar);
                    } else {
                        i = il.m557a(hhVar, hxVar.f745c, hxVar.f747e, i != hg.f705c);
                    }
                    if (i > 1 && this.f738a) {
                        ip.m564a("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", hhVar, new hh(hhVar.f710a / i, hhVar.f711b / i), Integer.valueOf(i), hxVar.f743a);
                    }
                    options = hxVar.f751i;
                    options.inSampleSize = i;
                    decodeStream = BitmapFactory.decodeStream(b, null, options);
                    in.m560a(b);
                    if (decodeStream != null) {
                        ip.m569d("Image can't be decoded [%s]", hxVar.f743a);
                        return decodeStream;
                    }
                    i2 = hvVar.f742b.f739a;
                    z = hvVar.f742b.f740b;
                    matrix = new Matrix();
                    i3 = hxVar.f746d;
                    if (i3 == hg.f707e || i3 == hg.f708f) {
                        b2 = il.m559b(new hh(decodeStream.getWidth(), decodeStream.getHeight(), i2), hxVar.f745c, hxVar.f747e, i3 != hg.f708f);
                        if (Float.compare(b2, FullImageView.ASPECT_RATIO_SQUARE) != 0) {
                            matrix.setScale(b2, b2);
                            if (this.f738a) {
                                ip.m564a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", r7, new hh((int) (((float) r7.f710a) * b2), (int) (((float) r7.f711b) * b2)), Float.valueOf(b2), hxVar.f743a);
                            }
                        }
                    }
                    if (z) {
                        matrix.postScale(-1.0f, FullImageView.ASPECT_RATIO_SQUARE);
                        if (this.f738a) {
                            ip.m564a("Flip image horizontally [%s]", hxVar.f743a);
                        }
                    }
                    if (i2 != 0) {
                        matrix.postRotate((float) i2);
                        if (this.f738a) {
                            ip.m564a("Rotate image on %1$d\u00b0 [%2$s]", Integer.valueOf(i2), hxVar.f743a);
                        }
                    }
                    createBitmap = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), matrix, true);
                    if (createBitmap != decodeStream) {
                        decodeStream.recycle();
                    }
                    return createBitmap;
                }
            }
            a = new hu();
            hvVar = new hv(new hh(options.outWidth, options.outHeight, a.f739a), a);
            b = m518a(b, hxVar);
            hhVar = hvVar.f741a;
            i = hxVar.f746d;
            if (i == hg.f703a) {
                i = 1;
            } else if (i != hg.f704b) {
                if (i != hg.f705c) {
                }
                i = il.m557a(hhVar, hxVar.f745c, hxVar.f747e, i != hg.f705c);
            } else {
                i = il.m556a(hhVar);
            }
            ip.m564a("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", hhVar, new hh(hhVar.f710a / i, hhVar.f711b / i), Integer.valueOf(i), hxVar.f743a);
            options = hxVar.f751i;
            options.inSampleSize = i;
            decodeStream = BitmapFactory.decodeStream(b, null, options);
            in.m560a(b);
            if (decodeStream != null) {
                i2 = hvVar.f742b.f739a;
                z = hvVar.f742b.f740b;
                matrix = new Matrix();
                i3 = hxVar.f746d;
                if (i3 != hg.f708f) {
                }
                b2 = il.m559b(new hh(decodeStream.getWidth(), decodeStream.getHeight(), i2), hxVar.f745c, hxVar.f747e, i3 != hg.f708f);
                if (Float.compare(b2, FullImageView.ASPECT_RATIO_SQUARE) != 0) {
                    matrix.setScale(b2, b2);
                    if (this.f738a) {
                        ip.m564a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", r7, new hh((int) (((float) r7.f710a) * b2), (int) (((float) r7.f711b) * b2)), Float.valueOf(b2), hxVar.f743a);
                    }
                }
                if (z) {
                    matrix.postScale(-1.0f, FullImageView.ASPECT_RATIO_SQUARE);
                    if (this.f738a) {
                        ip.m564a("Flip image horizontally [%s]", hxVar.f743a);
                    }
                }
                if (i2 != 0) {
                    matrix.postRotate((float) i2);
                    if (this.f738a) {
                        ip.m564a("Rotate image on %1$d\u00b0 [%2$s]", Integer.valueOf(i2), hxVar.f743a);
                    }
                }
                createBitmap = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), matrix, true);
                if (createBitmap != decodeStream) {
                    decodeStream.recycle();
                }
                return createBitmap;
            }
            ip.m569d("Image can't be decoded [%s]", hxVar.f743a);
            return decodeStream;
        } catch (Throwable th) {
            in.m560a(b);
        }
    }

    private static InputStream m519b(hx hxVar) {
        return hxVar.f748f.m464a(hxVar.f744b, hxVar.f749g);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static bo.app.hu m517a(java.lang.String r5) {
        /*
        r1 = 0;
        r0 = 1;
        r2 = new android.media.ExifInterface;	 Catch:{ IOException -> 0x002a }
        r3 = bo.app.id.FILE;	 Catch:{ IOException -> 0x002a }
        r3 = r3.m529c(r5);	 Catch:{ IOException -> 0x002a }
        r2.<init>(r3);	 Catch:{ IOException -> 0x002a }
        r3 = "Orientation";
        r4 = 1;
        r2 = r2.getAttributeInt(r3, r4);	 Catch:{ IOException -> 0x002a }
        switch(r2) {
            case 1: goto L_0x0017;
            case 2: goto L_0x0018;
            case 3: goto L_0x0022;
            case 4: goto L_0x0023;
            case 5: goto L_0x0027;
            case 6: goto L_0x001e;
            case 7: goto L_0x001f;
            case 8: goto L_0x0026;
            default: goto L_0x0017;
        };
    L_0x0017:
        r0 = r1;
    L_0x0018:
        r2 = new bo.app.hu;
        r2.<init>(r1, r0);
        return r2;
    L_0x001e:
        r0 = r1;
    L_0x001f:
        r1 = 90;
        goto L_0x0018;
    L_0x0022:
        r0 = r1;
    L_0x0023:
        r1 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        goto L_0x0018;
    L_0x0026:
        r0 = r1;
    L_0x0027:
        r1 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        goto L_0x0018;
    L_0x002a:
        r2 = move-exception;
        r2 = "Can't read EXIF tags from file [%s]";
        r0 = new java.lang.Object[r0];
        r0[r1] = r5;
        bo.app.ip.m568c(r2, r0);
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.ht.a(java.lang.String):bo.app.hu");
    }

    private static InputStream m518a(InputStream inputStream, hx hxVar) {
        try {
            inputStream.reset();
            return inputStream;
        } catch (IOException e) {
            in.m560a(inputStream);
            return m519b(hxVar);
        }
    }
}
