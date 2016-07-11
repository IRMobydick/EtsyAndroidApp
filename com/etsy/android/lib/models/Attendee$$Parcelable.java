package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.a */
final class Attendee$$Parcelable implements Creator<Attendee$$Parcelable> {
    private Attendee$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2328a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2329a(i);
    }

    public Attendee$$Parcelable m2328a(Parcel parcel) {
        return new Attendee$$Parcelable(parcel);
    }

    public Attendee$$Parcelable[] m2329a(int i) {
        return new Attendee$$Parcelable[i];
    }
}
