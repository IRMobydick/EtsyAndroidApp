package com.etsy.android.lib.util;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.etsy.android.lib.util.b */
public class ArrayUtil {
    public static <V, T> ArrayList<T> m3308a(List<V> list, ArrayUtil<V, T> arrayUtil) {
        ArrayList<T> arrayList = new ArrayList();
        for (V a : list) {
            Object a2 = arrayUtil.m1921a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }
}
