package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class ShopTask$$Parcelable implements android.os.Parcelable, ax<ShopTask> {
    public static final ak CREATOR;
    private ShopTask shopTask$$0;

    static {
        CREATOR = new ak();
    }

    public ShopTask$$Parcelable(Parcel parcel) {
        ShopTask shopTask;
        if (parcel.readInt() == -1) {
            shopTask = null;
        } else {
            shopTask = readcom_etsy_android_lib_models_ShopTask(parcel);
        }
        this.shopTask$$0 = shopTask;
    }

    public ShopTask$$Parcelable(ShopTask shopTask) {
        this.shopTask$$0 = shopTask;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopTask$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_ShopTask(this.shopTask$$0, parcel, i);
    }

    private ShopTask readcom_etsy_android_lib_models_ShopTask(Parcel parcel) {
        EtsyId etsyId;
        boolean z = true;
        ShopTask shopTask = new ShopTask();
        shopTask.mTitleIncomplete = parcel.readString();
        shopTask.mSubTitleComplete = parcel.readString();
        shopTask.mPriority = parcel.readInt();
        if (parcel.readInt() != 1) {
            z = false;
        }
        shopTask.mNeedsAttention = z;
        shopTask.mTitleComplete = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopTask.mTaskId = etsyId;
        shopTask.mName = parcel.readString();
        shopTask.mSubTitleIncomplete = parcel.readString();
        return shopTask;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_ShopTask(ShopTask shopTask, Parcel parcel, int i) {
        parcel.writeString(shopTask.mTitleIncomplete);
        parcel.writeString(shopTask.mSubTitleComplete);
        parcel.writeInt(shopTask.mPriority);
        parcel.writeInt(shopTask.mNeedsAttention ? 1 : 0);
        parcel.writeString(shopTask.mTitleComplete);
        if (shopTask.mTaskId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopTask.mTaskId, parcel, i);
        }
        parcel.writeString(shopTask.mName);
        parcel.writeString(shopTask.mSubTitleIncomplete);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public ShopTask getParcel() {
        return this.shopTask$$0;
    }
}
