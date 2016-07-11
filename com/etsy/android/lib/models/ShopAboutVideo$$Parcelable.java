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

public class ShopAboutVideo$$Parcelable implements android.os.Parcelable, ax<ShopAboutVideo> {
    public static final ai CREATOR;
    private ShopAboutVideo shopAboutVideo$$4;

    static {
        CREATOR = new ai();
    }

    public ShopAboutVideo$$Parcelable(Parcel parcel) {
        ShopAboutVideo shopAboutVideo;
        if (parcel.readInt() == -1) {
            shopAboutVideo = null;
        } else {
            shopAboutVideo = readcom_etsy_android_lib_models_ShopAboutVideo(parcel);
        }
        this.shopAboutVideo$$4 = shopAboutVideo;
    }

    public ShopAboutVideo$$Parcelable(ShopAboutVideo shopAboutVideo) {
        this.shopAboutVideo$$4 = shopAboutVideo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopAboutVideo$$4 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_ShopAboutVideo(this.shopAboutVideo$$4, parcel, i);
    }

    private ShopAboutVideo readcom_etsy_android_lib_models_ShopAboutVideo(Parcel parcel) {
        EtsyId etsyId;
        BaseModelImageWrapper baseModelImageWrapper;
        EtsyId etsyId2 = null;
        ShopAboutVideo shopAboutVideo = new ShopAboutVideo();
        shopAboutVideo.mUrl = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopAboutVideo.mShopId = etsyId;
        if (parcel.readInt() == -1) {
            baseModelImageWrapper = null;
        } else {
            baseModelImageWrapper = readcom_etsy_android_lib_models_BaseModelImageWrapper(parcel);
        }
        shopAboutVideo.mThumbnail = baseModelImageWrapper;
        shopAboutVideo.mProviderStatus = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopAboutVideo.mVideoId = etsyId2;
        return shopAboutVideo;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private BaseModelImageWrapper readcom_etsy_android_lib_models_BaseModelImageWrapper(Parcel parcel) {
        Image image;
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        BaseModelImageWrapper baseModelImageWrapper = new BaseModelImageWrapper(image);
        baseModelImageWrapper.mUrl570xN = parcel.readString();
        baseModelImageWrapper.mUrl224xN = parcel.readString();
        baseModelImageWrapper.PORTRAIT_HEIGHT_RATIO = parcel.readDouble();
        baseModelImageWrapper.mUrl680x540 = parcel.readString();
        baseModelImageWrapper.mUrl75x75 = parcel.readString();
        baseModelImageWrapper.mUrl170x135 = parcel.readString();
        baseModelImageWrapper.mUrlFullxFull = parcel.readString();
        baseModelImageWrapper.mUrl300x300 = parcel.readString();
        baseModelImageWrapper.mUrl340x270 = parcel.readString();
        return baseModelImageWrapper;
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

    private Source readcom_etsy_android_lib_models_apiv3_Image$Source(Parcel parcel) {
        Source source = new Source();
        Image$Source$$PackageHelper.accessImage$Source$FS$height(source, parcel.readInt());
        Image$Source$$PackageHelper.accessImage$Source$FS$mUrl(source, parcel.readString());
        Image$Source$$PackageHelper.accessImage$Source$FS$width(source, parcel.readInt());
        return source;
    }

    private void writecom_etsy_android_lib_models_ShopAboutVideo(ShopAboutVideo shopAboutVideo, Parcel parcel, int i) {
        parcel.writeString(shopAboutVideo.mUrl);
        if (shopAboutVideo.mShopId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopAboutVideo.mShopId, parcel, i);
        }
        if (shopAboutVideo.mThumbnail == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_BaseModelImageWrapper(shopAboutVideo.mThumbnail, parcel, i);
        }
        parcel.writeString(shopAboutVideo.mProviderStatus);
        if (shopAboutVideo.mVideoId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(shopAboutVideo.mVideoId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void writecom_etsy_android_lib_models_BaseModelImageWrapper(BaseModelImageWrapper baseModelImageWrapper, Parcel parcel, int i) {
        if (baseModelImageWrapper.mImage == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(baseModelImageWrapper.mImage, parcel, i);
        }
        parcel.writeString(baseModelImageWrapper.mUrl570xN);
        parcel.writeString(baseModelImageWrapper.mUrl224xN);
        parcel.writeDouble(baseModelImageWrapper.PORTRAIT_HEIGHT_RATIO);
        parcel.writeString(baseModelImageWrapper.mUrl680x540);
        parcel.writeString(baseModelImageWrapper.mUrl75x75);
        parcel.writeString(baseModelImageWrapper.mUrl170x135);
        parcel.writeString(baseModelImageWrapper.mUrlFullxFull);
        parcel.writeString(baseModelImageWrapper.mUrl300x300);
        parcel.writeString(baseModelImageWrapper.mUrl340x270);
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

    private void writecom_etsy_android_lib_models_apiv3_Image$Source(Source source, Parcel parcel, int i) {
        parcel.writeInt(Image$Source$$PackageHelper.accessImage$Source$FG$height(source));
        parcel.writeString(Image$Source$$PackageHelper.accessImage$Source$FG$mUrl(source));
        parcel.writeInt(Image$Source$$PackageHelper.accessImage$Source$FG$width(source));
    }

    public int describeContents() {
        return 0;
    }

    public ShopAboutVideo getParcel() {
        return this.shopAboutVideo$$4;
    }
}
