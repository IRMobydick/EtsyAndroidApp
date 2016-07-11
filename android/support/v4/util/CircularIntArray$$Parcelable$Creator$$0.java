package android.support.v4.util;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: CircularIntArray$$Parcelable */
final class CircularIntArray$$Parcelable$Creator$$0 implements Creator<CircularIntArray$$Parcelable> {
    private CircularIntArray$$Parcelable$Creator$$0() {
    }

    public CircularIntArray$$Parcelable createFromParcel(Parcel parcel) {
        return new CircularIntArray$$Parcelable(parcel);
    }

    public CircularIntArray$$Parcelable[] newArray(int i) {
        return new CircularIntArray$$Parcelable[i];
    }
}
