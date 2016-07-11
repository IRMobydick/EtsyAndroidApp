package com.etsy.android.lib.models.shopedit.simplerow;

import android.os.Parcel;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.BaseModelImage$$PackageHelper;
import com.etsy.android.lib.models.BaseModelImageWrapper;
import com.etsy.android.lib.models.BaseModelImageWrapper$$PackageHelper;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.ShopAboutVideo$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image$Source$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import com.etsy.android.lib.models.shopedit.ShopVideoShareData;
import com.etsy.android.lib.models.shopedit.ShopVideoShareData$$PackageHelper;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class ShopEditVideoBannerRow$$Parcelable implements android.os.Parcelable, ax<ShopEditVideoBannerRow> {
    public static final ShopEditVideoBannerRow$$Parcelable CREATOR;
    private ShopEditVideoBannerRow shopEditVideoBannerRow$$0;

    static {
        CREATOR = new ShopEditVideoBannerRow$$Parcelable();
    }

    public ShopEditVideoBannerRow$$Parcelable(Parcel parcel) {
        ShopEditVideoBannerRow shopEditVideoBannerRow;
        if (parcel.readInt() == -1) {
            shopEditVideoBannerRow = null;
        } else {
            shopEditVideoBannerRow = m2944x3d688114(parcel);
        }
        this.shopEditVideoBannerRow$$0 = shopEditVideoBannerRow;
    }

    public ShopEditVideoBannerRow$$Parcelable(ShopEditVideoBannerRow shopEditVideoBannerRow) {
        this.shopEditVideoBannerRow$$0 = shopEditVideoBannerRow;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopEditVideoBannerRow$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        m2945xa635d06b(this.shopEditVideoBannerRow$$0, parcel, i);
    }

    private ShopEditVideoBannerRow m2944x3d688114(Parcel parcel) {
        ShopVideoShareData shopVideoShareData;
        ShopAboutVideo shopAboutVideo = null;
        ShopEditVideoBannerRow shopEditVideoBannerRow = new ShopEditVideoBannerRow();
        if (parcel.readInt() == -1) {
            shopVideoShareData = null;
        } else {
            shopVideoShareData = readcom_etsy_android_lib_models_shopedit_ShopVideoShareData(parcel);
        }
        shopEditVideoBannerRow.mShareData = shopVideoShareData;
        if (parcel.readInt() != -1) {
            shopAboutVideo = readcom_etsy_android_lib_models_ShopAboutVideo(parcel);
        }
        shopEditVideoBannerRow.mVideo = shopAboutVideo;
        shopEditVideoBannerRow.mViewType = parcel.readInt();
        return shopEditVideoBannerRow;
    }

    private ShopVideoShareData readcom_etsy_android_lib_models_shopedit_ShopVideoShareData(Parcel parcel) {
        ShopVideoShareData accessShopVideoShareData$INIT = ShopVideoShareData$$PackageHelper.accessShopVideoShareData$INIT();
        ShopVideoShareData$$PackageHelper.accessShopVideoShareData$FS$mShareUrl(accessShopVideoShareData$INIT, parcel.readString());
        return accessShopVideoShareData$INIT;
    }

    private ShopAboutVideo readcom_etsy_android_lib_models_ShopAboutVideo(Parcel parcel) {
        EtsyId etsyId;
        BaseModelImageWrapper baseModelImageWrapper;
        EtsyId etsyId2 = null;
        ShopAboutVideo shopAboutVideo = new ShopAboutVideo();
        ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FS$mUrl(shopAboutVideo, parcel.readString());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FS$mShopId(shopAboutVideo, etsyId);
        if (parcel.readInt() == -1) {
            baseModelImageWrapper = null;
        } else {
            baseModelImageWrapper = readcom_etsy_android_lib_models_BaseModelImageWrapper(parcel);
        }
        ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FS$mThumbnail(shopAboutVideo, baseModelImageWrapper);
        ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FS$mProviderStatus(shopAboutVideo, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FS$mVideoId(shopAboutVideo, etsyId2);
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
        BaseModelImage baseModelImageWrapper = new BaseModelImageWrapper(image);
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl570xN(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl224xN(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$PORTRAIT_HEIGHT_RATIO(baseModelImageWrapper, parcel.readDouble());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl680x540(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl75x75(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl170x135(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrlFullxFull(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl300x300(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl340x270(baseModelImageWrapper, parcel.readString());
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

    private void m2945xa635d06b(ShopEditVideoBannerRow shopEditVideoBannerRow, Parcel parcel, int i) {
        if (shopEditVideoBannerRow.mShareData == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_shopedit_ShopVideoShareData(shopEditVideoBannerRow.mShareData, parcel, i);
        }
        if (shopEditVideoBannerRow.mVideo == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_ShopAboutVideo(shopEditVideoBannerRow.mVideo, parcel, i);
        }
        parcel.writeInt(shopEditVideoBannerRow.mViewType);
    }

    private void writecom_etsy_android_lib_models_shopedit_ShopVideoShareData(ShopVideoShareData shopVideoShareData, Parcel parcel, int i) {
        parcel.writeString(ShopVideoShareData$$PackageHelper.accessShopVideoShareData$FG$mShareUrl(shopVideoShareData));
    }

    private void writecom_etsy_android_lib_models_ShopAboutVideo(ShopAboutVideo shopAboutVideo, Parcel parcel, int i) {
        parcel.writeString(ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mUrl(shopAboutVideo));
        if (ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mShopId(shopAboutVideo) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mShopId(shopAboutVideo), parcel, i);
        }
        if (ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mThumbnail(shopAboutVideo) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_BaseModelImageWrapper(ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mThumbnail(shopAboutVideo), parcel, i);
        }
        parcel.writeString(ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mProviderStatus(shopAboutVideo));
        if (ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mVideoId(shopAboutVideo) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mVideoId(shopAboutVideo), parcel, i);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void writecom_etsy_android_lib_models_BaseModelImageWrapper(BaseModelImageWrapper baseModelImageWrapper, Parcel parcel, int i) {
        if (BaseModelImageWrapper$$PackageHelper.accessBaseModelImageWrapper$FG$mImage(baseModelImageWrapper) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(BaseModelImageWrapper$$PackageHelper.accessBaseModelImageWrapper$FG$mImage(baseModelImageWrapper), parcel, i);
        }
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl570xN(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl224xN(baseModelImageWrapper));
        parcel.writeDouble(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$PORTRAIT_HEIGHT_RATIO(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl680x540(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl75x75(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl170x135(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrlFullxFull(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl300x300(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl340x270(baseModelImageWrapper));
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

    public ShopEditVideoBannerRow getParcel() {
        return this.shopEditVideoBannerRow$$0;
    }
}
