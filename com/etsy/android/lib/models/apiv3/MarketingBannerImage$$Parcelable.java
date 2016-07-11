package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class MarketingBannerImage$$Parcelable implements android.os.Parcelable, ax<MarketingBannerImage> {
    public static final ab CREATOR;
    private MarketingBannerImage marketingBannerImage$$12;

    static {
        CREATOR = new ab();
    }

    public MarketingBannerImage$$Parcelable(Parcel parcel) {
        MarketingBannerImage marketingBannerImage;
        if (parcel.readInt() == -1) {
            marketingBannerImage = null;
        } else {
            marketingBannerImage = readcom_etsy_android_lib_models_apiv3_MarketingBannerImage(parcel);
        }
        this.marketingBannerImage$$12 = marketingBannerImage;
    }

    public MarketingBannerImage$$Parcelable(MarketingBannerImage marketingBannerImage) {
        this.marketingBannerImage$$12 = marketingBannerImage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.marketingBannerImage$$12 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_MarketingBannerImage(this.marketingBannerImage$$12, parcel, i);
    }

    private MarketingBannerImage readcom_etsy_android_lib_models_apiv3_MarketingBannerImage(Parcel parcel) {
        MarketingBannerImage marketingBannerImage = new MarketingBannerImage();
        marketingBannerImage.mHeight = parcel.readInt();
        marketingBannerImage.mImageColor = parcel.readInt();
        marketingBannerImage.mUrl = parcel.readString();
        marketingBannerImage.mWidth = parcel.readInt();
        return marketingBannerImage;
    }

    private void writecom_etsy_android_lib_models_apiv3_MarketingBannerImage(MarketingBannerImage marketingBannerImage, Parcel parcel, int i) {
        parcel.writeInt(marketingBannerImage.mHeight);
        parcel.writeInt(marketingBannerImage.mImageColor);
        parcel.writeString(marketingBannerImage.mUrl);
        parcel.writeInt(marketingBannerImage.mWidth);
    }

    public int describeContents() {
        return 0;
    }

    public MarketingBannerImage getParcel() {
        return this.marketingBannerImage$$12;
    }
}
