package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class UserProfileV3$$Parcelable implements android.os.Parcelable, ax<UserProfileV3> {
    public static final bt CREATOR;
    private UserProfileV3 userProfileV3$$3;

    static {
        CREATOR = new bt();
    }

    public UserProfileV3$$Parcelable(Parcel parcel) {
        UserProfileV3 userProfileV3;
        if (parcel.readInt() == -1) {
            userProfileV3 = null;
        } else {
            userProfileV3 = readcom_etsy_android_lib_models_apiv3_UserProfileV3(parcel);
        }
        this.userProfileV3$$3 = userProfileV3;
    }

    public UserProfileV3$$Parcelable(UserProfileV3 userProfileV3) {
        this.userProfileV3$$3 = userProfileV3;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.userProfileV3$$3 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_UserProfileV3(this.userProfileV3$$3, parcel, i);
    }

    private UserProfileV3 readcom_etsy_android_lib_models_apiv3_UserProfileV3(Parcel parcel) {
        boolean z;
        EtsyId etsyId;
        boolean z2 = true;
        UserProfileV3 userProfileV3 = new UserProfileV3();
        userProfileV3.mAppreciationPhotoCount = parcel.readInt();
        userProfileV3.mFavoriteListingsCount = parcel.readInt();
        userProfileV3.mLoginName = parcel.readString();
        userProfileV3.mLocation = parcel.readString();
        userProfileV3.mFollowingCount = parcel.readInt();
        userProfileV3.mAreFavoriteShopsPublic = parcel.readInt() == 1;
        userProfileV3.mFavoriteTreasuriesCount = parcel.readInt();
        userProfileV3.mBio = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        userProfileV3.mAreFavoriteListingsPublic = z;
        userProfileV3.mAvatarUrl = parcel.readString();
        userProfileV3.mFirstName = parcel.readString();
        userProfileV3.mDisplayName = parcel.readString();
        userProfileV3.mFavoriteShopsCount = parcel.readInt();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        userProfileV3.mIsFollowing = z;
        userProfileV3.mTransactionsSoldCount = parcel.readInt();
        userProfileV3.mFollowerCount = parcel.readInt();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        userProfileV3.mIsAdmin = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        userProfileV3.mIsSeller = z;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        userProfileV3.mAreFavoriteTreasuriesPublic = z2;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userProfileV3.mUserId = etsyId;
        return userProfileV3;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_apiv3_UserProfileV3(UserProfileV3 userProfileV3, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        parcel.writeInt(userProfileV3.mAppreciationPhotoCount);
        parcel.writeInt(userProfileV3.mFavoriteListingsCount);
        parcel.writeString(userProfileV3.mLoginName);
        parcel.writeString(userProfileV3.mLocation);
        parcel.writeInt(userProfileV3.mFollowingCount);
        parcel.writeInt(userProfileV3.mAreFavoriteShopsPublic ? 1 : 0);
        parcel.writeInt(userProfileV3.mFavoriteTreasuriesCount);
        parcel.writeString(userProfileV3.mBio);
        if (userProfileV3.mAreFavoriteListingsPublic) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(userProfileV3.mAvatarUrl);
        parcel.writeString(userProfileV3.mFirstName);
        parcel.writeString(userProfileV3.mDisplayName);
        parcel.writeInt(userProfileV3.mFavoriteShopsCount);
        if (userProfileV3.mIsFollowing) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeInt(userProfileV3.mTransactionsSoldCount);
        parcel.writeInt(userProfileV3.mFollowerCount);
        if (userProfileV3.mIsAdmin) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (userProfileV3.mIsSeller) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (userProfileV3.mAreFavoriteTreasuriesPublic) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        if (userProfileV3.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(userProfileV3.mUserId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public UserProfileV3 getParcel() {
        return this.userProfileV3$$3;
    }
}
