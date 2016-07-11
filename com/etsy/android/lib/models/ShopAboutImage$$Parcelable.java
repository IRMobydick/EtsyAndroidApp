package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image$Source$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class ShopAboutImage$$Parcelable implements android.os.Parcelable, ax<ShopAboutImage> {
    public static final ag CREATOR;
    private ShopAboutImage shopAboutImage$$11;

    static {
        CREATOR = new ag();
    }

    public ShopAboutImage$$Parcelable(Parcel parcel) {
        ShopAboutImage shopAboutImage;
        if (parcel.readInt() == -1) {
            shopAboutImage = null;
        } else {
            shopAboutImage = readcom_etsy_android_lib_models_ShopAboutImage(parcel);
        }
        this.shopAboutImage$$11 = shopAboutImage;
    }

    public ShopAboutImage$$Parcelable(ShopAboutImage shopAboutImage) {
        this.shopAboutImage$$11 = shopAboutImage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopAboutImage$$11 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_ShopAboutImage(this.shopAboutImage$$11, parcel, i);
    }

    private ShopAboutImage readcom_etsy_android_lib_models_ShopAboutImage(Parcel parcel) {
        List list;
        EtsyId etsyId;
        Image image = null;
        ShopAboutImage shopAboutImage = new ShopAboutImage();
        shopAboutImage.mKey = parcel.readString();
        shopAboutImage.mImageUrl545xN = parcel.readString();
        shopAboutImage.mRank = parcel.readInt();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            Object obj;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_Image$Source(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        shopAboutImage.mSources = list;
        shopAboutImage.mUrl = parcel.readString();
        shopAboutImage.mImageUrl178x178 = parcel.readString();
        shopAboutImage.mImageUrl760xN = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopAboutImage.mImageId = etsyId;
        if (parcel.readInt() != -1) {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        shopAboutImage.mImage = image;
        shopAboutImage.mCaption = parcel.readString();
        shopAboutImage.mUrl570xN = parcel.readString();
        shopAboutImage.mUrl224xN = parcel.readString();
        shopAboutImage.PORTRAIT_HEIGHT_RATIO = parcel.readDouble();
        shopAboutImage.mUrl680x540 = parcel.readString();
        shopAboutImage.mUrl75x75 = parcel.readString();
        shopAboutImage.mUrl170x135 = parcel.readString();
        shopAboutImage.mUrlFullxFull = parcel.readString();
        shopAboutImage.mUrl300x300 = parcel.readString();
        shopAboutImage.mUrl340x270 = parcel.readString();
        return shopAboutImage;
    }

    private Source readcom_etsy_android_lib_models_apiv3_Image$Source(Parcel parcel) {
        Source source = new Source();
        Image$Source$$PackageHelper.accessImage$Source$FS$height(source, parcel.readInt());
        Image$Source$$PackageHelper.accessImage$Source$FS$mUrl(source, parcel.readString());
        Image$Source$$PackageHelper.accessImage$Source$FS$width(source, parcel.readInt());
        return source;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private Image readcom_etsy_android_lib_models_apiv3_Image(Parcel parcel) {
        List list = null;
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_Image$Source(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        return new Image(readString, readString2, list);
    }

    private void writecom_etsy_android_lib_models_ShopAboutImage(ShopAboutImage shopAboutImage, Parcel parcel, int i) {
        parcel.writeString(shopAboutImage.mKey);
        parcel.writeString(shopAboutImage.mImageUrl545xN);
        parcel.writeInt(shopAboutImage.mRank);
        if (shopAboutImage.mSources == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopAboutImage.mSources.size());
            for (Source source : shopAboutImage.mSources) {
                if (source == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_Image$Source(source, parcel, i);
                }
            }
        }
        parcel.writeString(shopAboutImage.mUrl);
        parcel.writeString(shopAboutImage.mImageUrl178x178);
        parcel.writeString(shopAboutImage.mImageUrl760xN);
        if (shopAboutImage.mImageId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopAboutImage.mImageId, parcel, i);
        }
        if (shopAboutImage.mImage == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(shopAboutImage.mImage, parcel, i);
        }
        parcel.writeString(shopAboutImage.mCaption);
        parcel.writeString(shopAboutImage.mUrl570xN);
        parcel.writeString(shopAboutImage.mUrl224xN);
        parcel.writeDouble(shopAboutImage.PORTRAIT_HEIGHT_RATIO);
        parcel.writeString(shopAboutImage.mUrl680x540);
        parcel.writeString(shopAboutImage.mUrl75x75);
        parcel.writeString(shopAboutImage.mUrl170x135);
        parcel.writeString(shopAboutImage.mUrlFullxFull);
        parcel.writeString(shopAboutImage.mUrl300x300);
        parcel.writeString(shopAboutImage.mUrl340x270);
    }

    private void writecom_etsy_android_lib_models_apiv3_Image$Source(Source source, Parcel parcel, int i) {
        parcel.writeInt(Image$Source$$PackageHelper.accessImage$Source$FG$height(source));
        parcel.writeString(Image$Source$$PackageHelper.accessImage$Source$FG$mUrl(source));
        parcel.writeInt(Image$Source$$PackageHelper.accessImage$Source$FG$width(source));
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void writecom_etsy_android_lib_models_apiv3_Image(Image image, Parcel parcel, int i) {
        parcel.writeString(Image$$PackageHelper.accessImage$FG$mKey(image));
        parcel.writeString(Image$$PackageHelper.accessImage$FG$mUrl(image));
        if (Image$$PackageHelper.accessImage$FG$mSources(image) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(Image$$PackageHelper.accessImage$FG$mSources(image).size());
        for (Source source : Image$$PackageHelper.accessImage$FG$mSources(image)) {
            if (source == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_apiv3_Image$Source(source, parcel, i);
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public ShopAboutImage getParcel() {
        return this.shopAboutImage$$11;
    }
}
