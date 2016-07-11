package com.etsy.android.ui.cardview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.HashMap;

/* renamed from: com.etsy.android.ui.cardview.a */
public abstract class BaseViewHolderFactory<T extends IBaseRecyclerViewElement> {
    protected LayoutInflater f2253a;
    protected Context f2254b;
    protected ImageBatch f2255c;
    protected HashMap<Integer, b> f2256d;
    protected String f2257e;
    @NonNull
    protected AnalyticsTracker f2258f;

    public abstract int m3641a(int i, int i2, int i3);

    public abstract BaseViewHolder m3643a(ViewGroup viewGroup, int i);

    public BaseViewHolderFactory(Context context, LayoutInflater layoutInflater, ImageBatch imageBatch, String str, @NonNull AnalyticsTracker analyticsTracker) {
        this.f2254b = context;
        this.f2255c = imageBatch;
        this.f2253a = layoutInflater;
        this.f2256d = new HashMap();
        this.f2257e = str;
        this.f2258f = analyticsTracker;
    }

    public void m3645a(String str) {
        this.f2257e = str;
        for (Integer num : this.f2256d.keySet()) {
            ((b) this.f2256d.get(num)).b(str);
        }
    }

    public void m3644a(int i, b bVar) {
        this.f2256d.put(Integer.valueOf(i), bVar);
    }

    public int m3642a(T t) {
        int viewType = t.getViewType();
        return viewType != -1 ? viewType : viewType;
    }
}
