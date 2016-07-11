package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.BaseModelImage$$PackageHelper;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class ShopIcon$$Parcelable implements android.os.Parcelable, ax<ShopIcon> {
    public static final ay CREATOR;
    private ShopIcon shopIcon$$0;

    static {
        CREATOR = new ay();
    }

    public ShopIcon$$Parcelable(Parcel parcel) {
        ShopIcon shopIcon;
        if (parcel.readInt() == -1) {
            shopIcon = null;
        } else {
            shopIcon = readcom_etsy_android_lib_models_apiv3_ShopIcon(parcel);
        }
        this.shopIcon$$0 = shopIcon;
    }

    public ShopIcon$$Parcelable(ShopIcon shopIcon) {
        this.shopIcon$$0 = shopIcon;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopIcon$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopIcon(this.shopIcon$$0, parcel, i);
    }

    private ShopIcon readcom_etsy_android_lib_models_apiv3_ShopIcon(Parcel parcel) {
        EtsyId etsyId;
        BaseModelImage shopIcon = new ShopIcon();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopIcon.mId = etsyId;
        shopIcon.mUrl280x280 = parcel.readString();
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl570xN(shopIcon, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl224xN(shopIcon, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$PORTRAIT_HEIGHT_RATIO(shopIcon, parcel.readDouble());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl680x540(shopIcon, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl75x75(shopIcon, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl170x135(shopIcon, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrlFullxFull(shopIcon, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl300x300(shopIcon, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl340x270(shopIcon, parcel.readString());
        return shopIcon;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopIcon(ShopIcon shopIcon, Parcel parcel, int i) {
        if (shopIcon.mId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopIcon.mId, parcel, i);
        }
        parcel.writeString(shopIcon.mUrl280x280);
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl570xN(shopIcon));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl224xN(shopIcon));
        parcel.writeDouble(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$PORTRAIT_HEIGHT_RATIO(shopIcon));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl680x540(shopIcon));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl75x75(shopIcon));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl170x135(shopIcon));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrlFullxFull(shopIcon));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl300x300(shopIcon));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl340x270(shopIcon));
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public ShopIcon getParcel() {
        return this.shopIcon$$0;
    }
}
