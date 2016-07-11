package com.etsy.android.lib.models.cardviewelement;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.cardviewelement.b */
final class BasicSectionHeader$$Parcelable implements Creator<BasicSectionHeader$$Parcelable> {
    private BasicSectionHeader$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2597a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2598a(i);
    }

    public BasicSectionHeader$$Parcelable m2597a(Parcel parcel) {
        return new BasicSectionHeader$$Parcelable(parcel);
    }

    public BasicSectionHeader$$Parcelable[] m2598a(int i) {
        return new BasicSectionHeader$$Parcelable[i];
    }
}
