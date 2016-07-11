package bo.app;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import com.appboy.Appboy;
import com.etsy.android.ui.dialog.EtsyDialogFragment;

/* renamed from: bo.app.c */
public final class C0335c implements Runnable {
    final /* synthetic */ String f212a;
    final /* synthetic */ ImageView f213b;
    final /* synthetic */ boolean f214c;
    final /* synthetic */ Appboy f215d;

    public C0335c(Appboy appboy, String str, ImageView imageView, boolean z) {
        this.f215d = appboy;
        this.f212a = str;
        this.f213b = imageView;
        this.f214c = z;
    }

    public final void run() {
        int i = 0;
        String uri = Appboy.getAppboyResourceEndpoint(Uri.parse(this.f212a)).toString();
        gn c = this.f215d.f853j;
        ImageView imageView = this.f213b;
        ih c0336d = new C0336d(this);
        ie c0344if = new C0344if(imageView);
        if (c.f591b == null) {
            throw new IllegalStateException("ImageLoader must be init with configuration before using");
        }
        gl glVar = c.f591b.f611r;
        if (TextUtils.isEmpty(uri)) {
            c.f592c.m471b(c0344if);
            c0344if.m535d();
            if (!(glVar.f555e == null && glVar.f552b == 0)) {
                i = 1;
            }
            if (i != 0) {
                Drawable drawable;
                Resources resources = c.f591b.f594a;
                if (glVar.f552b != 0) {
                    drawable = resources.getDrawable(glVar.f552b);
                } else {
                    drawable = glVar.f555e;
                }
                c0344if.m532a(drawable);
            } else {
                c0344if.m532a(null);
            }
            c0336d.m146a(uri, c0344if.m535d(), null);
            return;
        }
        go goVar = c.f591b;
        DisplayMetrics displayMetrics = goVar.f594a.getDisplayMetrics();
        int i2 = goVar.f595b;
        if (i2 <= 0) {
            i2 = displayMetrics.widthPixels;
        }
        int i3 = goVar.f596c;
        if (i3 <= 0) {
            i3 = displayMetrics.heightPixels;
        }
        hh a = il.m558a(c0344if, new hh(i2, i3));
        String str = "_" + a.f710a + EtsyDialogFragment.OPT_X_BUTTON + a.f711b;
        c.f592c.f646e.put(Integer.valueOf(c0344if.m537f()), str);
        c0344if.m535d();
        Bitmap a2 = c.f591b.f607n.m423a(str);
        if (a2 == null || a2.isRecycled()) {
            i2 = (glVar.f554d == null && glVar.f551a == 0) ? 0 : 1;
            if (i2 != 0) {
                c0344if.m532a(glVar.f551a != 0 ? c.f591b.f594a.getDrawable(glVar.f551a) : glVar.f554d);
            } else if (glVar.f557g) {
                c0344if.m532a(null);
            }
            gw gwVar = new gw(c.f592c, new gv(uri, c0344if, a, str, glVar, c0336d, c.f592c.m469a(uri)), gn.m442a(glVar));
            if (glVar.f569s) {
                gwVar.run();
                return;
            }
            gt gtVar = c.f592c;
            gtVar.f645d.execute(new gu(gtVar, gwVar));
            return;
        }
        ip.m564a("Load image from memory cache [%s]", str);
        if (glVar.m440a()) {
            Runnable hbVar = new hb(c.f592c, a2, new gv(uri, c0344if, a, str, glVar, c0336d, c.f592c.m469a(uri)), gn.m442a(glVar));
            if (glVar.f569s) {
                hbVar.run();
                return;
            }
            gtVar = c.f592c;
            gtVar.m470a();
            gtVar.f644c.execute(hbVar);
            return;
        }
        hy hyVar = glVar.f567q;
        hi hiVar = hi.MEMORY_CACHE;
        hyVar.m521a(a2, c0344if);
        c0336d.m146a(uri, c0344if.m535d(), a2);
    }
}
