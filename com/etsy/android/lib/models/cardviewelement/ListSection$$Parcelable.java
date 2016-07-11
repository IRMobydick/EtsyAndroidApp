package com.etsy.android.lib.models.cardviewelement;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.cardviewelement.d */
final class ListSection$$Parcelable implements Creator<ListSection$$Parcelable> {
    private ListSection$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2601a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2602a(i);
    }

    public ListSection$$Parcelable m2601a(Parcel parcel) {
        return new ListSection$$Parcelable(parcel);
    }

    public ListSection$$Parcelable[] m2602a(int i) {
        return new ListSection$$Parcelable[i];
    }
}
