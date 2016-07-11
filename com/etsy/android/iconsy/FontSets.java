package com.etsy.android.iconsy;

import android.util.Pair;
import android.util.SparseArray;
import com.etsy.android.iconsy.fonts.DemoFontIcon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.etsy.android.iconsy.c */
public final class FontSets {
    private static Map<Class<? extends AbstractFontIcon>, String> f1115a;
    private static SparseArray<AbstractFontIcon> f1116b;

    static {
        f1115a = new HashMap();
        f1116b = new SparseArray();
        f1116b.put(R.ic_demo_example, DemoFontIcon.EXAMPLE);
    }

    public static Map<Class<? extends AbstractFontIcon>, String> m768a() {
        return f1115a;
    }

    public static AbstractFontIcon m767a(int i) {
        return (AbstractFontIcon) f1116b.get(i);
    }

    public static void m770a(Map<Class<? extends AbstractFontIcon>, String> map) {
        f1115a.putAll(map);
    }

    public static void m769a(List<Pair<Integer, AbstractFontIcon>> list) {
        if (list != null) {
            for (Pair pair : list) {
                f1116b.put(((Integer) pair.first).intValue(), pair.second);
            }
        }
    }
}
