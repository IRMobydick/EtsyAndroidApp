package com.etsy.android.iconsy;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map.Entry;

/* renamed from: com.etsy.android.iconsy.h */
public class TypefaceCache {
    private static TypefaceCache f1118b;
    private final HashMap<Class, Typeface> f1119a;

    private TypefaceCache() {
        this.f1119a = new HashMap();
    }

    static {
        f1118b = new TypefaceCache();
    }

    public static TypefaceCache m772a() {
        return f1118b;
    }

    public Typeface m773a(AbstractFontIcon abstractFontIcon) {
        return (Typeface) this.f1119a.get(abstractFontIcon.getClass());
    }

    protected void m774a(Context context) {
        for (Entry entry : FontSets.m768a().entrySet()) {
            this.f1119a.put(entry.getKey(), Typeface.createFromAsset(context.getAssets(), (String) entry.getValue()));
        }
    }
}
