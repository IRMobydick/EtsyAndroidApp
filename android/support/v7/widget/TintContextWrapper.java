package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

class TintContextWrapper extends ContextWrapper {
    private static final ArrayList<WeakReference<TintContextWrapper>> sCache;
    private Resources mResources;

    static {
        sCache = new ArrayList();
    }

    public static Context wrap(@NonNull Context context) {
        if (context instanceof TintContextWrapper) {
            return context;
        }
        Context context2;
        int size = sCache.size();
        for (int i = 0; i < size; i++) {
            WeakReference weakReference = (WeakReference) sCache.get(i);
            context2 = weakReference != null ? (TintContextWrapper) weakReference.get() : null;
            if (context2 != null && context2.getBaseContext() == context) {
                return context2;
            }
        }
        context2 = new TintContextWrapper(context);
        sCache.add(new WeakReference(context2));
        return context2;
    }

    private TintContextWrapper(Context context) {
        super(context);
    }

    public Resources getResources() {
        if (this.mResources == null) {
            this.mResources = new TintResources(this, super.getResources());
        }
        return this.mResources;
    }
}
