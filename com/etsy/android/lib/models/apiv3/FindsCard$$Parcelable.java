package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.l */
final class FindsCard$$Parcelable implements Creator<FindsCard$$Parcelable> {
    private FindsCard$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2541a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2542a(i);
    }

    public FindsCard$$Parcelable m2541a(Parcel parcel) {
        return new FindsCard$$Parcelable(parcel);
    }

    public FindsCard$$Parcelable[] m2542a(int i) {
        return new FindsCard$$Parcelable[i];
    }
}
