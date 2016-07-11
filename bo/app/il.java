package bo.app;

import android.opengl.GLES10;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.gcm.Task;

public final class il {
    private static hh f769a;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], ItemAnimator.FLAG_MOVED);
        f769a = new hh(max, max);
    }

    public static hh m558a(ie ieVar, hh hhVar) {
        int a = ieVar.m530a();
        if (a <= 0) {
            a = hhVar.f710a;
        }
        int b = ieVar.m533b();
        if (b <= 0) {
            b = hhVar.f711b;
        }
        return new hh(a, b);
    }

    public static int m557a(hh hhVar, hh hhVar2, int i, boolean z) {
        int max;
        int i2 = 1;
        int i3 = hhVar.f710a;
        int i4 = hhVar.f711b;
        int i5 = hhVar2.f710a;
        int i6 = hhVar2.f711b;
        int i7;
        int i8;
        switch (im.f770a[i - 1]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                if (!z) {
                    max = Math.max(i3 / i5, i4 / i6);
                    break;
                }
                i7 = i3 / 2;
                i8 = i4 / 2;
                max = 1;
                while (true) {
                    if (i7 / max <= i5 && i8 / max <= i6) {
                        break;
                    }
                    max *= 2;
                }
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                if (!z) {
                    max = Math.min(i3 / i5, i4 / i6);
                    break;
                }
                i7 = i3 / 2;
                i8 = i4 / 2;
                max = 1;
                while (i7 / max > i5 && i8 / max > i6) {
                    max *= 2;
                }
                break;
            default:
                max = 1;
                break;
        }
        if (max > 0) {
            i2 = max;
        }
        max = f769a.f710a;
        i5 = f769a.f711b;
        while (true) {
            if (i3 / i2 <= max && i4 / i2 <= i5) {
                return i2;
            }
            if (z) {
                i2 *= 2;
            } else {
                i2++;
            }
        }
    }

    public static int m556a(hh hhVar) {
        int i = hhVar.f710a;
        int i2 = hhVar.f711b;
        return Math.max((int) Math.ceil((double) (((float) i) / ((float) f769a.f710a))), (int) Math.ceil((double) (((float) i2) / ((float) f769a.f711b))));
    }

    public static float m559b(hh hhVar, hh hhVar2, int i, boolean z) {
        int i2 = hhVar.f710a;
        int i3 = hhVar.f711b;
        int i4 = hhVar2.f710a;
        int i5 = hhVar2.f711b;
        float f = ((float) i2) / ((float) i4);
        float f2 = ((float) i3) / ((float) i5);
        int i6;
        if ((i != hk.f719a || f < f2) && (i != hk.f720b || f >= f2)) {
            i6 = i5;
            i5 = (int) (((float) i2) / f2);
        } else {
            i6 = (int) (((float) i3) / f);
            i5 = i4;
        }
        if ((z || i5 >= i2 || r2 >= i3) && (!z || i5 == i2 || r2 == i3)) {
            return FullImageView.ASPECT_RATIO_SQUARE;
        }
        return ((float) i5) / ((float) i2);
    }
}
