package bo.app;

import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;

public final class hx {
    final String f743a;
    final String f744b;
    final hh f745c;
    final int f746d;
    final int f747e;
    final ic f748f;
    final Object f749g;
    final boolean f750h;
    final Options f751i;
    private final String f752j;

    public hx(String str, String str2, String str3, hh hhVar, int i, ic icVar, gl glVar) {
        this.f743a = str;
        this.f744b = str2;
        this.f752j = str3;
        this.f745c = hhVar;
        this.f746d = glVar.f560j;
        this.f747e = i;
        this.f748f = icVar;
        this.f749g = glVar.f564n;
        this.f750h = glVar.f563m;
        this.f751i = new Options();
        Options options = glVar.f561k;
        Options options2 = this.f751i;
        options2.inDensity = options.inDensity;
        options2.inDither = options.inDither;
        options2.inInputShareable = options.inInputShareable;
        options2.inJustDecodeBounds = options.inJustDecodeBounds;
        options2.inPreferredConfig = options.inPreferredConfig;
        options2.inPurgeable = options.inPurgeable;
        options2.inSampleSize = options.inSampleSize;
        options2.inScaled = options.inScaled;
        options2.inScreenDensity = options.inScreenDensity;
        options2.inTargetDensity = options.inTargetDensity;
        options2.inTempStorage = options.inTempStorage;
        if (VERSION.SDK_INT >= 10) {
            options2.inPreferQualityOverSpeed = options.inPreferQualityOverSpeed;
        }
        if (VERSION.SDK_INT >= 11) {
            options2.inBitmap = options.inBitmap;
            options2.inMutable = options.inMutable;
        }
    }
}
