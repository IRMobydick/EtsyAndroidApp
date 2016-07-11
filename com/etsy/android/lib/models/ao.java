package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: TaxonomyNode$$Parcelable */
final class ao implements Creator<TaxonomyNode$$Parcelable> {
    private ao() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2358a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2359a(i);
    }

    public TaxonomyNode$$Parcelable m2358a(Parcel parcel) {
        return new TaxonomyNode$$Parcelable(parcel);
    }

    public TaxonomyNode$$Parcelable[] m2359a(int i) {
        return new TaxonomyNode$$Parcelable[i];
    }
}
