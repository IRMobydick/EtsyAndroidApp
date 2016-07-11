package com.etsy.android.lib.models.cardviewelement;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.cardviewelement.h */
final class SearchPageLink$$Parcelable implements Creator<SearchPageLink$$Parcelable> {
    private SearchPageLink$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2609a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2610a(i);
    }

    public SearchPageLink$$Parcelable m2609a(Parcel parcel) {
        return new SearchPageLink$$Parcelable(parcel);
    }

    public SearchPageLink$$Parcelable[] m2610a(int i) {
        return new SearchPageLink$$Parcelable[i];
    }
}
