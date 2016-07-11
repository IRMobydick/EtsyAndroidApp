package com.etsy.android.lib.util;

import android.os.Parcel;
import android.support.v4.util.CircularIntArray;
import org.parceler.av;

/* compiled from: ParcelerConverters */
public class at implements av<CircularIntArray> {
    public /* synthetic */ Object fromParcel(Parcel parcel) {
        return m3267a(parcel);
    }

    public /* synthetic */ void toParcel(Object obj, Parcel parcel) {
        m3268a((CircularIntArray) obj, parcel);
    }

    public void m3268a(CircularIntArray circularIntArray, Parcel parcel) {
        if (circularIntArray == null) {
            parcel.writeInt(-1);
            return;
        }
        int size = circularIntArray.size();
        parcel.writeInt(size);
        for (int i = 0; i < size; i++) {
            parcel.writeInt(circularIntArray.get(i));
        }
    }

    public CircularIntArray m3267a(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        CircularIntArray circularIntArray = new CircularIntArray(readInt);
        for (int i = 0; i < readInt; i++) {
            circularIntArray.addLast(parcel.readInt());
        }
        return circularIntArray;
    }
}
