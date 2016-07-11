package com.etsy.android.lib.models;

import android.os.Parcel;
import java.util.Date;
import org.parceler.ax;

public class ReviewResponse$$Parcelable implements android.os.Parcelable, ax<ReviewResponse> {
    public static final ab CREATOR;
    private ReviewResponse reviewResponse$$18;

    static {
        CREATOR = new ab();
    }

    public ReviewResponse$$Parcelable(Parcel parcel) {
        ReviewResponse reviewResponse;
        if (parcel.readInt() == -1) {
            reviewResponse = null;
        } else {
            reviewResponse = readcom_etsy_android_lib_models_ReviewResponse(parcel);
        }
        this.reviewResponse$$18 = reviewResponse;
    }

    public ReviewResponse$$Parcelable(ReviewResponse reviewResponse) {
        this.reviewResponse$$18 = reviewResponse;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.reviewResponse$$18 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_ReviewResponse(this.reviewResponse$$18, parcel, i);
    }

    private ReviewResponse readcom_etsy_android_lib_models_ReviewResponse(Parcel parcel) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.mResponseMessage = parcel.readString();
        reviewResponse.mCreateDate = (Date) parcel.readSerializable();
        return reviewResponse;
    }

    private void writecom_etsy_android_lib_models_ReviewResponse(ReviewResponse reviewResponse, Parcel parcel, int i) {
        parcel.writeString(reviewResponse.mResponseMessage);
        parcel.writeSerializable(reviewResponse.mCreateDate);
    }

    public int describeContents() {
        return 0;
    }

    public ReviewResponse getParcel() {
        return this.reviewResponse$$18;
    }
}
