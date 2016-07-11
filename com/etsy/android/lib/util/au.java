package com.etsy.android.lib.util;

import android.os.Parcel;
import java.util.Date;
import org.parceler.av;

/* compiled from: ParcelerConverters */
public final class au implements av<Date> {
    public /* synthetic */ Object fromParcel(Parcel parcel) {
        return m3269a(parcel);
    }

    public /* synthetic */ void toParcel(Object obj, Parcel parcel) {
        m3270a((Date) obj, parcel);
    }

    public void m3270a(Date date, Parcel parcel) {
        if (date == null) {
            parcel.writeLong(0);
        } else {
            parcel.writeLong(date.getTime());
        }
    }

    public Date m3269a(Parcel parcel) {
        return new Date(parcel.readLong());
    }
}
