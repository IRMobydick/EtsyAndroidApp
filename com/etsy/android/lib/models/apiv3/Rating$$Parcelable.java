package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class Rating$$Parcelable implements android.os.Parcelable, ax<Rating> {
    public static final ao CREATOR;
    private Rating rating$$9;

    static {
        CREATOR = new ao();
    }

    public Rating$$Parcelable(Parcel parcel) {
        Rating rating;
        if (parcel.readInt() == -1) {
            rating = null;
        } else {
            rating = readcom_etsy_android_lib_models_apiv3_Rating(parcel);
        }
        this.rating$$9 = rating;
    }

    public Rating$$Parcelable(Rating rating) {
        this.rating$$9 = rating;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.rating$$9 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_Rating(this.rating$$9, parcel, i);
    }

    private Rating readcom_etsy_android_lib_models_apiv3_Rating(Parcel parcel) {
        Rating rating = new Rating();
        rating.mStars = parcel.readDouble();
        rating.mSellerFeedbackCount = parcel.readInt();
        rating.mCount = parcel.readInt();
        rating.mRating = parcel.readDouble();
        return rating;
    }

    private void writecom_etsy_android_lib_models_apiv3_Rating(Rating rating, Parcel parcel, int i) {
        parcel.writeDouble(rating.mStars);
        parcel.writeInt(rating.mSellerFeedbackCount);
        parcel.writeInt(rating.mCount);
        parcel.writeDouble(rating.mRating);
    }

    public int describeContents() {
        return 0;
    }

    public Rating getParcel() {
        return this.rating$$9;
    }
}
