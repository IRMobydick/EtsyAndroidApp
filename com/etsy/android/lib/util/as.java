package com.etsy.android.lib.util;

import android.os.Parcel;
import android.text.TextUtils;
import org.parceler.av;

/* compiled from: ParcelerConverters */
public final class as implements av<CharSequence> {
    public /* synthetic */ Object fromParcel(Parcel parcel) {
        return m3265a(parcel);
    }

    public /* synthetic */ void toParcel(Object obj, Parcel parcel) {
        m3266a((CharSequence) obj, parcel);
    }

    public void m3266a(CharSequence charSequence, Parcel parcel) {
        TextUtils.writeToParcel(charSequence, parcel, 0);
    }

    public CharSequence m3265a(Parcel parcel) {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }
}
