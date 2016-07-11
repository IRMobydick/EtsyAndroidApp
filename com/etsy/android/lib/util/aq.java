package com.etsy.android.lib.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import org.parceler.Parcels;
import org.parceler.av;

/* compiled from: ParcelerConverters */
public final class aq<K, V> implements av<ArrayMap<K, V>> {
    public /* synthetic */ Object fromParcel(Parcel parcel) {
        return m3261a(parcel);
    }

    public /* synthetic */ void toParcel(Object obj, Parcel parcel) {
        m3262a((ArrayMap) obj, parcel);
    }

    public void m3262a(ArrayMap<K, V> arrayMap, Parcel parcel) {
        if (arrayMap == null) {
            parcel.writeInt(-1);
            return;
        }
        int size = arrayMap.size();
        parcel.writeInt(size);
        for (int i = 0; i < size; i++) {
            parcel.writeParcelable(Parcels.m7494a(arrayMap.keyAt(i)), 0);
            parcel.writeParcelable(Parcels.m7494a(arrayMap.valueAt(i)), 0);
        }
    }

    public ArrayMap<K, V> m3261a(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        ArrayMap<K, V> arrayMap = new ArrayMap(readInt);
        ClassLoader classLoader = getClass().getClassLoader();
        for (int i = 0; i < readInt; i++) {
            Object a;
            Object a2 = Parcels.m7495a(parcel.readParcelable(classLoader));
            Parcelable readParcelable = parcel.readParcelable(classLoader);
            if (readParcelable != null) {
                a = Parcels.m7495a(readParcelable);
            } else {
                a = null;
            }
            arrayMap.put(a2, a);
        }
        return arrayMap;
    }
}
