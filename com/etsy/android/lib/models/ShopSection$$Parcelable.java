package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import com.etsy.android.lib.util.as;
import org.parceler.ax;

public class ShopSection$$Parcelable implements android.os.Parcelable, ax<ShopSection> {
    public static final aj CREATOR;
    private ShopSection shopSection$$4;

    static {
        CREATOR = new aj();
    }

    public ShopSection$$Parcelable(Parcel parcel) {
        ShopSection shopSection;
        if (parcel.readInt() == -1) {
            shopSection = null;
        } else {
            shopSection = readcom_etsy_android_lib_models_ShopSection(parcel);
        }
        this.shopSection$$4 = shopSection;
    }

    public ShopSection$$Parcelable(ShopSection shopSection) {
        this.shopSection$$4 = shopSection;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopSection$$4 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_ShopSection(this.shopSection$$4, parcel, i);
    }

    private ShopSection readcom_etsy_android_lib_models_ShopSection(Parcel parcel) {
        EtsyId etsyId;
        ShopSection shopSection = new ShopSection();
        shopSection.mRank = parcel.readInt();
        shopSection.mListingActiveCount = parcel.readInt();
        shopSection.mTitle = parcel.readString();
        shopSection.mDisplayLabel = new as().m3265a(parcel);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopSection.mShopSectionId = etsyId;
        return shopSection;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_ShopSection(ShopSection shopSection, Parcel parcel, int i) {
        parcel.writeInt(shopSection.mRank);
        parcel.writeInt(shopSection.mListingActiveCount);
        parcel.writeString(shopSection.mTitle);
        new as().m3266a(shopSection.mDisplayLabel, parcel);
        if (shopSection.mShopSectionId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(shopSection.mShopSectionId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public ShopSection getParcel() {
        return this.shopSection$$4;
    }
}
