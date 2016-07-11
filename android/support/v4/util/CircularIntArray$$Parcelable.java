package android.support.v4.util;

import android.os.Parcel;
import com.etsy.android.lib.util.at;
import org.parceler.ax;

public class CircularIntArray$$Parcelable implements android.os.Parcelable, ax<CircularIntArray> {
    public static final CircularIntArray$$Parcelable$Creator$$0 CREATOR;
    private CircularIntArray circularIntArray$$0;

    static {
        CREATOR = new CircularIntArray$$Parcelable$Creator$$0();
    }

    public CircularIntArray$$Parcelable(Parcel parcel) {
        this.circularIntArray$$0 = new at().m3267a(parcel);
    }

    public CircularIntArray$$Parcelable(CircularIntArray circularIntArray) {
        this.circularIntArray$$0 = circularIntArray;
    }

    public void writeToParcel(Parcel parcel, int i) {
        new at().m3268a(this.circularIntArray$$0, parcel);
    }

    public int describeContents() {
        return 0;
    }

    public CircularIntArray getParcel() {
        return this.circularIntArray$$0;
    }
}
