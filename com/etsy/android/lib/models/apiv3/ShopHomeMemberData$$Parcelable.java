package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class ShopHomeMemberData$$Parcelable implements android.os.Parcelable, ax<ShopHomeMemberData> {
    public static final aw CREATOR;
    private ShopHomeMemberData shopHomeMemberData$$0;

    static {
        CREATOR = new aw();
    }

    public ShopHomeMemberData$$Parcelable(Parcel parcel) {
        ShopHomeMemberData shopHomeMemberData;
        if (parcel.readInt() == -1) {
            shopHomeMemberData = null;
        } else {
            shopHomeMemberData = readcom_etsy_android_lib_models_apiv3_ShopHomeMemberData(parcel);
        }
        this.shopHomeMemberData$$0 = shopHomeMemberData;
    }

    public ShopHomeMemberData$$Parcelable(ShopHomeMemberData shopHomeMemberData) {
        this.shopHomeMemberData$$0 = shopHomeMemberData;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopHomeMemberData$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopHomeMemberData(this.shopHomeMemberData$$0, parcel, i);
    }

    private ShopHomeMemberData readcom_etsy_android_lib_models_apiv3_ShopHomeMemberData(Parcel parcel) {
        boolean z;
        List list = null;
        boolean z2 = true;
        ShopHomeMemberData shopHomeMemberData = new ShopHomeMemberData();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_ListingMemberData(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        shopHomeMemberData.mListingsInfo = list;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopHomeMemberData.mIsSubscribedToVacationNotification = z;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        shopHomeMemberData.mIsFavorer = z2;
        return shopHomeMemberData;
    }

    private ListingMemberData readcom_etsy_android_lib_models_apiv3_ListingMemberData(Parcel parcel) {
        EtsyId etsyId;
        boolean z;
        boolean z2 = true;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        return new ListingMemberData(etsyId, z, z2);
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopHomeMemberData(ShopHomeMemberData shopHomeMemberData, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        if (shopHomeMemberData.mListingsInfo == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopHomeMemberData.mListingsInfo.size());
            for (ListingMemberData listingMemberData : shopHomeMemberData.mListingsInfo) {
                if (listingMemberData == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingMemberData(listingMemberData, parcel, i);
                }
            }
        }
        if (shopHomeMemberData.mIsSubscribedToVacationNotification) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!shopHomeMemberData.mIsFavorer) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_apiv3_ListingMemberData(ListingMemberData listingMemberData, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        if (listingMemberData.mListingId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(listingMemberData.mListingId, parcel, i);
        }
        if (listingMemberData.mIsFavorite) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!listingMemberData.mHasCollections) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public ShopHomeMemberData getParcel() {
        return this.shopHomeMemberData$$0;
    }
}
