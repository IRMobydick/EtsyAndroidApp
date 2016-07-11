package com.etsy.android.lib.models.datatypes;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.datatypes.a */
final class EtsyDeepLinkId$$Parcelable implements Creator<EtsyDeepLinkId$$Parcelable> {
    private EtsyDeepLinkId$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2613a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2614a(i);
    }

    public EtsyDeepLinkId$$Parcelable m2613a(Parcel parcel) {
        return new EtsyDeepLinkId$$Parcelable(parcel);
    }

    public EtsyDeepLinkId$$Parcelable[] m2614a(int i) {
        return new EtsyDeepLinkId$$Parcelable[i];
    }
}
