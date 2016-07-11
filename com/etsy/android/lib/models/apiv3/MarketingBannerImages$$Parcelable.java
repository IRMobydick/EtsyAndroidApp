package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class MarketingBannerImages$$Parcelable implements android.os.Parcelable, ax<MarketingBannerImages> {
    public static final ac CREATOR;
    private MarketingBannerImages marketingBannerImages$$3;

    static {
        CREATOR = new ac();
    }

    public MarketingBannerImages$$Parcelable(Parcel parcel) {
        MarketingBannerImages marketingBannerImages;
        if (parcel.readInt() == -1) {
            marketingBannerImages = null;
        } else {
            marketingBannerImages = readcom_etsy_android_lib_models_apiv3_MarketingBannerImages(parcel);
        }
        this.marketingBannerImages$$3 = marketingBannerImages;
    }

    public MarketingBannerImages$$Parcelable(MarketingBannerImages marketingBannerImages) {
        this.marketingBannerImages$$3 = marketingBannerImages;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.marketingBannerImages$$3 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_MarketingBannerImages(this.marketingBannerImages$$3, parcel, i);
    }

    private MarketingBannerImages readcom_etsy_android_lib_models_apiv3_MarketingBannerImages(Parcel parcel) {
        List list;
        ArrayList arrayList;
        int i;
        Object obj;
        int i2 = 0;
        List list2 = null;
        MarketingBannerImages marketingBannerImages = new MarketingBannerImages();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_MarketingBannerImage(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        marketingBannerImages.mLandscapeImages = list;
        i = parcel.readInt();
        if (i >= 0) {
            arrayList = new ArrayList();
            while (i2 < i) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_MarketingBannerImage(parcel);
                }
                arrayList.add(obj);
                i2++;
            }
            Object obj2 = arrayList;
        }
        marketingBannerImages.mPortraitImages = list2;
        return marketingBannerImages;
    }

    private MarketingBannerImage readcom_etsy_android_lib_models_apiv3_MarketingBannerImage(Parcel parcel) {
        MarketingBannerImage marketingBannerImage = new MarketingBannerImage();
        marketingBannerImage.mHeight = parcel.readInt();
        marketingBannerImage.mImageColor = parcel.readInt();
        marketingBannerImage.mUrl = parcel.readString();
        marketingBannerImage.mWidth = parcel.readInt();
        return marketingBannerImage;
    }

    private void writecom_etsy_android_lib_models_apiv3_MarketingBannerImages(MarketingBannerImages marketingBannerImages, Parcel parcel, int i) {
        if (marketingBannerImages.mLandscapeImages == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(marketingBannerImages.mLandscapeImages.size());
            for (MarketingBannerImage marketingBannerImage : marketingBannerImages.mLandscapeImages) {
                if (marketingBannerImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_MarketingBannerImage(marketingBannerImage, parcel, i);
                }
            }
        }
        if (marketingBannerImages.mPortraitImages == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(marketingBannerImages.mPortraitImages.size());
        for (MarketingBannerImage marketingBannerImage2 : marketingBannerImages.mPortraitImages) {
            if (marketingBannerImage2 == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_apiv3_MarketingBannerImage(marketingBannerImage2, parcel, i);
            }
        }
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

    public MarketingBannerImages getParcel() {
        return this.marketingBannerImages$$3;
    }
}
