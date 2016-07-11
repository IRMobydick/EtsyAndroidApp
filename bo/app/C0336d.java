package bo.app;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: bo.app.d */
final class C0336d extends ij {
    final /* synthetic */ C0335c f281a;

    C0336d(C0335c c0335c) {
        this.f281a = c0335c;
    }

    public final void m148a(String str, View view, Bitmap bitmap) {
        super.m147a(str, view, bitmap);
        float height = (float) bitmap.getHeight();
        if (height != 0.0f && !this.f281a.f214c) {
            height = ((float) bitmap.getWidth()) / height;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new C0339e(this, height));
            }
        }
    }
}
