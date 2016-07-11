package com.etsy.android.uikit.nav;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import com.etsy.android.iconsy.AbstractFontIcon;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.uikit.navigationview.EtsyNavigationView;
import java.util.ArrayList;
import java.util.Collection;

/* renamed from: com.etsy.android.uikit.nav.a */
public abstract class BaseNavigationMenuManager implements EtsyNavigationView {
    private static final SparseArray<String> f2098a;
    private static final ArrayList<Integer> f2099g;
    protected Context f2100b;
    protected EtsyNavigationView f2101c;
    protected DrawerLayout f2102d;
    protected ImageBatch f2103e;
    protected int f2104f;
    private final Handler f2105h;

    /* renamed from: com.etsy.android.uikit.nav.a.1 */
    class BaseNavigationMenuManager implements Runnable {
        final /* synthetic */ int f3995a;
        final /* synthetic */ BaseNavigationMenuManager f3996b;

        BaseNavigationMenuManager(BaseNavigationMenuManager baseNavigationMenuManager, int i) {
            this.f3996b = baseNavigationMenuManager;
            this.f3995a = i;
        }

        public void run() {
            this.f3996b.m3471d(this.f3995a);
        }
    }

    protected abstract Collection<Integer> m3472a();

    protected abstract void m3473a(int i);

    protected abstract void m3475a(Menu menu);

    protected abstract void m3476a(MenuItem menuItem, Context context);

    static {
        f2098a = new SparseArray(0);
        f2099g = new ArrayList();
    }

    public BaseNavigationMenuManager(Context context, EtsyNavigationView etsyNavigationView, DrawerLayout drawerLayout) {
        this.f2105h = new Handler();
        this.f2100b = context;
        this.f2101c = etsyNavigationView;
        this.f2102d = drawerLayout;
        this.f2103e = new ImageBatch();
    }

    protected static Drawable m3468a(AbstractFontIcon abstractFontIcon, int i, int i2) {
        return IconDrawable.m775a(Resources.getSystem()).m780a(abstractFontIcon).m779a(i2).m778a((float) i).m777a();
    }

    protected void m3474a(Context context, int i, SparseArray<Drawable> sparseArray) {
        Menu menu = this.f2101c.getMenu();
        menu.clear();
        this.f2101c.inflateMenu(i);
        m3475a(menu);
        int size = menu.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = menu.getItem(i2);
            Drawable drawable = (Drawable) sparseArray.get(item.getItemId());
            if (drawable != null) {
                item.setIcon(drawable);
            }
            m3476a(item, context);
            m3482b(item);
        }
    }

    public boolean m3479a(MenuItem menuItem) {
        menuItem.setChecked(true);
        int itemId = menuItem.getItemId();
        if (m3472a().contains(Integer.valueOf(itemId))) {
            this.f2102d.closeDrawer(this.f2101c);
            if (itemId != this.f2104f) {
                this.f2105h.postDelayed(new BaseNavigationMenuManager(this, itemId), 300);
            }
            this.f2104f = itemId;
        } else {
            m3471d(itemId);
        }
        return true;
    }

    protected void m3477a(MenuItem menuItem, String str, int i) {
        menuItem.setTitle(str + "#" + i + "#");
    }

    protected void m3478a(MenuItem menuItem, String str, String str2) {
        menuItem.setTitle(str + "#" + str2 + "#");
    }

    protected void m3482b(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        String str = (String) f2098a.get(itemId);
        if (str != null) {
            if (SharedPreferencesUtility.m3133b(this.f2100b, str, false)) {
                f2098a.remove(itemId);
                f2099g.add(Integer.valueOf(itemId));
                return;
            }
            m3478a(menuItem, menuItem.getTitle().toString(), this.f2100b.getResources().getString(R.new_nav_badge));
        }
    }

    private void m3470c(int i) {
        String str = (String) f2098a.get(i);
        if (str != null) {
            SharedPreferencesUtility.m3137c(this.f2100b, str, true);
            f2098a.remove(i);
            f2099g.add(Integer.valueOf(i));
        }
    }

    private void m3471d(int i) {
        m3473a(i);
        m3481b(i);
    }

    protected void m3481b(int i) {
        m3470c(i);
    }

    public void m3480b() {
        if (aj.m1101a().m1128o()) {
            int size = f2098a.size();
            for (int i = 0; i < size; i++) {
                m3470c(f2098a.keyAt(i));
            }
        }
    }

    public void m3484d() {
    }

    public void m3483c() {
    }
}
