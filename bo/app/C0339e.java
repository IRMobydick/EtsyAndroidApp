package bo.app;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout.LayoutParams;
import com.appboy.Appboy;

/* renamed from: bo.app.e */
final class C0339e implements OnGlobalLayoutListener {
    final /* synthetic */ float f366a;
    final /* synthetic */ C0336d f367b;

    C0339e(C0336d c0336d, float f) {
        this.f367b = c0336d;
        this.f366a = f;
    }

    public final void onGlobalLayout() {
        int width = this.f367b.f281a.f213b.getWidth();
        this.f367b.f281a.f213b.setLayoutParams(new LayoutParams(width, (int) (((float) width) / this.f366a)));
        Appboy appboy = this.f367b.f281a.f215d;
        Appboy.m647a(this.f367b.f281a.f213b.getViewTreeObserver(), this);
    }
}
