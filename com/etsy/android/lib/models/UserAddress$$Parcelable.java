package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class UserAddress$$Parcelable implements android.os.Parcelable, ax<UserAddress> {
    public static final as CREATOR;
    private UserAddress userAddress$$26;

    static {
        CREATOR = new as();
    }

    public UserAddress$$Parcelable(Parcel parcel) {
        UserAddress userAddress;
        if (parcel.readInt() == -1) {
            userAddress = null;
        } else {
            userAddress = readcom_etsy_android_lib_models_UserAddress(parcel);
        }
        this.userAddress$$26 = userAddress;
    }

    public UserAddress$$Parcelable(UserAddress userAddress) {
        this.userAddress$$26 = userAddress;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.userAddress$$26 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_UserAddress(this.userAddress$$26, parcel, i);
    }

    private UserAddress readcom_etsy_android_lib_models_UserAddress(Parcel parcel) {
        Country country;
        EtsyId etsyId;
        boolean z = true;
        EtsyId etsyId2 = null;
        UserAddress userAddress = new UserAddress();
        if (parcel.readInt() != 1) {
            z = false;
        }
        userAddress.mIsDefaultShipping = z;
        userAddress.mFirstLine = parcel.readString();
        userAddress.mCity = parcel.readString();
        if (parcel.readInt() == -1) {
            country = null;
        } else {
            country = readcom_etsy_android_lib_models_Country(parcel);
        }
        userAddress.mCountry = country;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddress.mCountryId = etsyId;
        userAddress.mState = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddress.mUserAddressId = etsyId;
        userAddress.mSecondLine = parcel.readString();
        userAddress.mName = parcel.readString();
        userAddress.mZip = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddress.mUserId = etsyId2;
        return userAddress;
    }

    private Country readcom_etsy_android_lib_models_Country(Parcel parcel) {
        boolean z = true;
        Country country = new Country();
        country.mIsoCountryCode = parcel.readString();
        country.mCountryId = parcel.readInt();
        if (parcel.readInt() != 1) {
            z = false;
        }
        country.mIsPrimary = z;
        country.mLongitude = parcel.readFloat();
        country.mName = parcel.readString();
        country.mLatitude = parcel.readFloat();
        country.mWorldBankCountryCode = parcel.readString();
        return country;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_UserAddress(UserAddress userAddress, Parcel parcel, int i) {
        parcel.writeInt(userAddress.mIsDefaultShipping ? 1 : 0);
        parcel.writeString(userAddress.mFirstLine);
        parcel.writeString(userAddress.mCity);
        if (userAddress.mCountry == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_Country(userAddress.mCountry, parcel, i);
        }
        if (userAddress.mCountryId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(userAddress.mCountryId, parcel, i);
        }
        parcel.writeString(userAddress.mState);
        if (userAddress.mUserAddressId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(userAddress.mUserAddressId, parcel, i);
        }
        parcel.writeString(userAddress.mSecondLine);
        parcel.writeString(userAddress.mName);
        parcel.writeString(userAddress.mZip);
        if (userAddress.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(userAddress.mUserId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_Country(Country country, Parcel parcel, int i) {
        parcel.writeString(country.mIsoCountryCode);
        parcel.writeInt(country.mCountryId);
        parcel.writeInt(country.mIsPrimary ? 1 : 0);
        parcel.writeFloat(country.mLongitude);
        parcel.writeString(country.mName);
        parcel.writeFloat(country.mLatitude);
        parcel.writeString(country.mWorldBankCountryCode);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public UserAddress getParcel() {
        return this.userAddress$$26;
    }
}
