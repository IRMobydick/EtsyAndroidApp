package com.etsy.android.lib.models.shopshare;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.shopshare.a */
final class ShareItem$$Parcelable implements Creator<ShareItem$$Parcelable> {
    private ShareItem$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2956a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2957a(i);
    }

    public ShareItem$$Parcelable m2956a(Parcel parcel) {
        return new ShareItem$$Parcelable(parcel);
    }

    public ShareItem$$Parcelable[] m2957a(int i) {
        return new ShareItem$$Parcelable[i];
    }
}
