package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class MarketingBanner$$Parcelable implements android.os.Parcelable, ax<MarketingBanner> {
    public static final aa CREATOR;
    private MarketingBanner marketingBanner$$0;

    static {
        CREATOR = new aa();
    }

    public MarketingBanner$$Parcelable(Parcel parcel) {
        MarketingBanner marketingBanner;
        if (parcel.readInt() == -1) {
            marketingBanner = null;
        } else {
            marketingBanner = readcom_etsy_android_lib_models_apiv3_MarketingBanner(parcel);
        }
        this.marketingBanner$$0 = marketingBanner;
    }

    public MarketingBanner$$Parcelable(MarketingBanner marketingBanner) {
        this.marketingBanner$$0 = marketingBanner;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.marketingBanner$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_MarketingBanner(this.marketingBanner$$0, parcel, i);
    }

    private MarketingBanner readcom_etsy_android_lib_models_apiv3_MarketingBanner(Parcel parcel) {
        MarketingBannerImages marketingBannerImages;
        MarketingBanner marketingBanner = new MarketingBanner();
        marketingBanner.mPlaceholderColor = parcel.readInt();
        marketingBanner.mSubtitle = parcel.readString();
        marketingBanner.mButtonText = parcel.readString();
        marketingBanner.mTitle = parcel.readString();
        marketingBanner.mUrl = parcel.readString();
        if (parcel.readInt() == -1) {
            marketingBannerImages = null;
        } else {
            marketingBannerImages = readcom_etsy_android_lib_models_apiv3_MarketingBannerImages(parcel);
        }
        marketingBanner.mMarketingBannerImages = marketingBannerImages;
        return marketingBanner;
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

    private void writecom_etsy_android_lib_models_apiv3_MarketingBanner(MarketingBanner marketingBanner, Parcel parcel, int i) {
        parcel.writeInt(marketingBanner.mPlaceholderColor);
        parcel.writeString(marketingBanner.mSubtitle);
        parcel.writeString(marketingBanner.mButtonText);
        parcel.writeString(marketingBanner.mTitle);
        parcel.writeString(marketingBanner.mUrl);
        if (marketingBanner.mMarketingBannerImages == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_MarketingBannerImages(marketingBanner.mMarketingBannerImages, parcel, i);
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

    public MarketingBanner getParcel() {
        return this.marketingBanner$$0;
    }
}
