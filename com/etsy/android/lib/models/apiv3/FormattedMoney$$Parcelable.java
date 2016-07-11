package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.m */
final class FormattedMoney$$Parcelable implements Creator<FormattedMoney$$Parcelable> {
    private FormattedMoney$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2543a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2544a(i);
    }

    public FormattedMoney$$Parcelable m2543a(Parcel parcel) {
        return new FormattedMoney$$Parcelable(parcel);
    }

    public FormattedMoney$$Parcelable[] m2544a(int i) {
        return new FormattedMoney$$Parcelable[i];
    }
}
