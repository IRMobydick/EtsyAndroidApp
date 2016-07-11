package bo.app;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class ig implements ie {
    protected Reference<View> f767a;
    protected boolean f768b;

    protected abstract void m539a(Bitmap bitmap, View view);

    protected abstract void m540a(Drawable drawable, View view);

    public ig(View view) {
        this(view, (byte) 0);
    }

    private ig(View view, byte b) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        this.f767a = new WeakReference(view);
        this.f768b = true;
    }

    public int m538a() {
        View view = (View) this.f767a.get();
        if (view == null) {
            return 0;
        }
        int i;
        LayoutParams layoutParams = view.getLayoutParams();
        if (!this.f768b || layoutParams == null || layoutParams.width == -2) {
            i = 0;
        } else {
            i = view.getWidth();
        }
        if (i > 0 || layoutParams == null) {
            return i;
        }
        return layoutParams.width;
    }

    public int m543b() {
        View view = (View) this.f767a.get();
        if (view == null) {
            return 0;
        }
        int i;
        LayoutParams layoutParams = view.getLayoutParams();
        if (!this.f768b || layoutParams == null || layoutParams.height == -2) {
            i = 0;
        } else {
            i = view.getHeight();
        }
        if (i > 0 || layoutParams == null) {
            return i;
        }
        return layoutParams.height;
    }

    public int m544c() {
        return hk.f720b;
    }

    public View m545d() {
        return (View) this.f767a.get();
    }

    public final boolean m546e() {
        return this.f767a.get() == null;
    }

    public final int m547f() {
        View view = (View) this.f767a.get();
        return view == null ? super.hashCode() : view.hashCode();
    }

    public final boolean m542a(Drawable drawable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = (View) this.f767a.get();
            if (view != null) {
                m540a(drawable, view);
                return true;
            }
        }
        ip.m568c("Can't set a drawable into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        return false;
    }

    public final boolean m541a(Bitmap bitmap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = (View) this.f767a.get();
            if (view != null) {
                m539a(bitmap, view);
                return true;
            }
        }
        ip.m568c("Can't set a bitmap into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        return false;
    }
}
