package com.etsy.android.lib.models;

import android.os.Parcel;
import org.parceler.ax;

public class Segment$$Parcelable implements android.os.Parcelable, ax<Segment> {
    public static final ad CREATOR;
    private Segment segment$$0;

    static {
        CREATOR = new ad();
    }

    public Segment$$Parcelable(Parcel parcel) {
        Segment segment;
        if (parcel.readInt() == -1) {
            segment = null;
        } else {
            segment = readcom_etsy_android_lib_models_Segment(parcel);
        }
        this.segment$$0 = segment;
    }

    public Segment$$Parcelable(Segment segment) {
        this.segment$$0 = segment;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.segment$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_Segment(this.segment$$0, parcel, i);
    }

    private Segment readcom_etsy_android_lib_models_Segment(Parcel parcel) {
        boolean z = true;
        Segment segment = new Segment();
        segment.mImageUrl = parcel.readString();
        segment.mListingId = parcel.readInt();
        segment.mShopId = parcel.readInt();
        segment.mWeight = parcel.readInt();
        if (parcel.readInt() != 1) {
            z = false;
        }
        segment.mHasChildren = z;
        segment.mStandaloneText = parcel.readString();
        segment.mName = parcel.readString();
        segment.mPath = parcel.readString();
        segment.mShopName = parcel.readString();
        return segment;
    }

    private void writecom_etsy_android_lib_models_Segment(Segment segment, Parcel parcel, int i) {
        parcel.writeString(segment.mImageUrl);
        parcel.writeInt(segment.mListingId);
        parcel.writeInt(segment.mShopId);
        parcel.writeInt(segment.mWeight);
        parcel.writeInt(segment.mHasChildren ? 1 : 0);
        parcel.writeString(segment.mStandaloneText);
        parcel.writeString(segment.mName);
        parcel.writeString(segment.mPath);
        parcel.writeString(segment.mShopName);
    }

    public int describeContents() {
        return 0;
    }

    public Segment getParcel() {
        return this.segment$$0;
    }
}
