package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class ShopIconV3$$Parcelable implements android.os.Parcelable, ax<ShopIconV3> {
    public static final az CREATOR;
    private ShopIconV3 shopIconV3$$3;

    static {
        CREATOR = new az();
    }

    public ShopIconV3$$Parcelable(Parcel parcel) {
        ShopIconV3 shopIconV3;
        if (parcel.readInt() == -1) {
            shopIconV3 = null;
        } else {
            shopIconV3 = readcom_etsy_android_lib_models_apiv3_ShopIconV3(parcel);
        }
        this.shopIconV3$$3 = shopIconV3;
    }

    public ShopIconV3$$Parcelable(ShopIconV3 shopIconV3) {
        this.shopIconV3$$3 = shopIconV3;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopIconV3$$3 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopIconV3(this.shopIconV3$$3, parcel, i);
    }

    private ShopIconV3 readcom_etsy_android_lib_models_apiv3_ShopIconV3(Parcel parcel) {
        EtsyId etsyId;
        List list = null;
        ShopIconV3 shopIconV3 = new ShopIconV3();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopIconV3.mImageId = etsyId;
        shopIconV3.mKey = parcel.readString();
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
        shopIconV3.mSources = list;
        shopIconV3.mUrl = parcel.readString();
        return shopIconV3;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private Source readcom_etsy_android_lib_models_apiv3_Image$Source(Parcel parcel) {
        Source source = new Source();
        source.height = parcel.readInt();
        source.mUrl = parcel.readString();
        source.width = parcel.readInt();
        return source;
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopIconV3(ShopIconV3 shopIconV3, Parcel parcel, int i) {
        if (shopIconV3.mImageId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopIconV3.mImageId, parcel, i);
        }
        parcel.writeString(shopIconV3.mKey);
        if (shopIconV3.mSources == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopIconV3.mSources.size());
            for (Source source : shopIconV3.mSources) {
                if (source == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_Image$Source(source, parcel, i);
                }
            }
        }
        parcel.writeString(shopIconV3.mUrl);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void writecom_etsy_android_lib_models_apiv3_Image$Source(Source source, Parcel parcel, int i) {
        parcel.writeInt(source.height);
        parcel.writeString(source.mUrl);
        parcel.writeInt(source.width);
    }

    public int describeContents() {
        return 0;
    }

    public ShopIconV3 getParcel() {
        return this.shopIconV3$$3;
    }
}
