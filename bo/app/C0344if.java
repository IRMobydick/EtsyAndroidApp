package bo.app;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.foresee.sdk.configuration.MeasureConfiguration;
import java.lang.reflect.Field;

/* renamed from: bo.app.if */
public final class C0344if extends ig {
    public final /* bridge */ /* synthetic */ View m554d() {
        return (ImageView) super.m545d();
    }

    public C0344if(ImageView imageView) {
        super(imageView);
    }

    public final int m549a() {
        int a = super.m538a();
        if (a <= 0) {
            Object obj = (ImageView) this.a.get();
            if (obj != null) {
                return C0344if.m548a(obj, "mMaxWidth");
            }
        }
        return a;
    }

    public final int m552b() {
        int b = super.m543b();
        if (b <= 0) {
            Object obj = (ImageView) this.a.get();
            if (obj != null) {
                return C0344if.m548a(obj, "mMaxHeight");
            }
        }
        return b;
    }

    public final int m553c() {
        ImageView imageView = (ImageView) this.a.get();
        if (imageView != null) {
            return hk.m497a(imageView);
        }
        return super.m544c();
    }

    protected final void m551a(Drawable drawable, View view) {
        ((ImageView) view).setImageDrawable(drawable);
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    protected final void m550a(Bitmap bitmap, View view) {
        ((ImageView) view).setImageBitmap(bitmap);
    }

    private static int m548a(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(obj)).intValue();
            if (intValue > 0 && intValue < MeasureConfiguration.DISABLED) {
                return intValue;
            }
        } catch (Throwable e) {
            ip.m565a(e);
        }
        return 0;
    }
}
