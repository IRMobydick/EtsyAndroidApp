package com.etsy.android.ui.local;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LocalSearchQuery implements Parcelable {
    public static final Creator<LocalSearchQuery> CREATOR;
    private static final int RADIUS_DEFAULT = 50000;
    private final double mLatitude;
    private final double mLongitude;
    private final int mRadius;

    /* renamed from: com.etsy.android.ui.local.LocalSearchQuery.1 */
    final class C07571 implements Creator<LocalSearchQuery> {
        C07571() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m4308a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m4309a(i);
        }

        public LocalSearchQuery m4308a(Parcel parcel) {
            return new LocalSearchQuery(parcel);
        }

        public LocalSearchQuery[] m4309a(int i) {
            return new LocalSearchQuery[i];
        }
    }

    public LocalSearchQuery() {
        this.mLatitude = 0.0d;
        this.mLongitude = 0.0d;
        this.mRadius = 0;
    }

    public LocalSearchQuery(double d, double d2) {
        this.mLatitude = d;
        this.mLongitude = d2;
        this.mRadius = 0;
    }

    public LocalSearchQuery(double d, double d2, int i) {
        this.mLatitude = d;
        this.mLongitude = d2;
        this.mRadius = i;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public int getOrDefaultRadius() {
        return this.mRadius > 0 ? this.mRadius : RADIUS_DEFAULT;
    }

    public LocalSearchQuery(Parcel parcel) {
        this.mLatitude = parcel.readDouble();
        this.mLongitude = parcel.readDouble();
        this.mRadius = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mLatitude);
        parcel.writeDouble(this.mLongitude);
        parcel.writeInt(this.mRadius);
    }

    static {
        CREATOR = new C07571();
    }
}
