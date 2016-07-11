package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class UserAddressV3$$Parcelable implements android.os.Parcelable, ax<UserAddressV3> {
    public static final bp CREATOR;
    private UserAddressV3 userAddressV3$$4;

    static {
        CREATOR = new bp();
    }

    public UserAddressV3$$Parcelable(Parcel parcel) {
        UserAddressV3 userAddressV3;
        if (parcel.readInt() == -1) {
            userAddressV3 = null;
        } else {
            userAddressV3 = readcom_etsy_android_lib_models_apiv3_UserAddressV3(parcel);
        }
        this.userAddressV3$$4 = userAddressV3;
    }

    public UserAddressV3$$Parcelable(UserAddressV3 userAddressV3) {
        this.userAddressV3$$4 = userAddressV3;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.userAddressV3$$4 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_UserAddressV3(this.userAddressV3$$4, parcel, i);
    }

    private UserAddressV3 readcom_etsy_android_lib_models_apiv3_UserAddressV3(Parcel parcel) {
        EtsyId etsyId;
        boolean z = true;
        EtsyId etsyId2 = null;
        UserAddressV3 userAddressV3 = new UserAddressV3();
        if (parcel.readInt() != 1) {
            z = false;
        }
        userAddressV3.mIsAvailableForMarket = z;
        userAddressV3.mPhone = parcel.readString();
        userAddressV3.mFirstLine = parcel.readString();
        userAddressV3.mCity = parcel.readString();
        userAddressV3.mCountryName = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddressV3.mCountryId = etsyId;
        userAddressV3.mState = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddressV3.mUserAddressId = etsyId;
        userAddressV3.mSecondLine = parcel.readString();
        userAddressV3.mName = parcel.readString();
        userAddressV3.mZip = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddressV3.mUserId = etsyId2;
        return userAddressV3;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_apiv3_UserAddressV3(UserAddressV3 userAddressV3, Parcel parcel, int i) {
        parcel.writeInt(userAddressV3.mIsAvailableForMarket ? 1 : 0);
        parcel.writeString(userAddressV3.mPhone);
        parcel.writeString(userAddressV3.mFirstLine);
        parcel.writeString(userAddressV3.mCity);
        parcel.writeString(userAddressV3.mCountryName);
        if (userAddressV3.mCountryId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(userAddressV3.mCountryId, parcel, i);
        }
        parcel.writeString(userAddressV3.mState);
        if (userAddressV3.mUserAddressId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(userAddressV3.mUserAddressId, parcel, i);
        }
        parcel.writeString(userAddressV3.mSecondLine);
        parcel.writeString(userAddressV3.mName);
        parcel.writeString(userAddressV3.mZip);
        if (userAddressV3.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(userAddressV3.mUserId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public UserAddressV3 getParcel() {
        return this.userAddressV3$$4;
    }
}
