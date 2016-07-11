package com.etsy.android.lib.util;

import android.os.Parcel;
import java.util.Calendar;
import org.parceler.av;

/* compiled from: ParcelerConverters */
public final class ar implements av<Calendar> {
    public /* synthetic */ Object fromParcel(Parcel parcel) {
        return m3263a(parcel);
    }

    public /* synthetic */ void toParcel(Object obj, Parcel parcel) {
        m3264a((Calendar) obj, parcel);
    }

    public void m3264a(Calendar calendar, Parcel parcel) {
        if (calendar == null) {
            parcel.writeLong(0);
        } else {
            parcel.writeLong(calendar.getTimeInMillis());
        }
    }

    public Calendar m3263a(Parcel parcel) {
        long readLong = parcel.readLong();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(readLong);
        return instance;
    }
}
