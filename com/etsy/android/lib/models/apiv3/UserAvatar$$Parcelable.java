package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.BaseModelImage$$PackageHelper;
import org.parceler.ax;

public class UserAvatar$$Parcelable implements android.os.Parcelable, ax<UserAvatar> {
    public static final bq CREATOR;
    private UserAvatar userAvatar$$0;

    static {
        CREATOR = new bq();
    }

    public UserAvatar$$Parcelable(Parcel parcel) {
        UserAvatar userAvatar;
        if (parcel.readInt() == -1) {
            userAvatar = null;
        } else {
            userAvatar = readcom_etsy_android_lib_models_apiv3_UserAvatar(parcel);
        }
        this.userAvatar$$0 = userAvatar;
    }

    public UserAvatar$$Parcelable(UserAvatar userAvatar) {
        this.userAvatar$$0 = userAvatar;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.userAvatar$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_UserAvatar(this.userAvatar$$0, parcel, i);
    }

    private UserAvatar readcom_etsy_android_lib_models_apiv3_UserAvatar(Parcel parcel) {
        BaseModelImage userAvatar = new UserAvatar();
        userAvatar.mUrl400x400 = parcel.readString();
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl570xN(userAvatar, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl224xN(userAvatar, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$PORTRAIT_HEIGHT_RATIO(userAvatar, parcel.readDouble());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl680x540(userAvatar, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl75x75(userAvatar, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl170x135(userAvatar, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrlFullxFull(userAvatar, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl300x300(userAvatar, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl340x270(userAvatar, parcel.readString());
        return userAvatar;
    }

    private void writecom_etsy_android_lib_models_apiv3_UserAvatar(UserAvatar userAvatar, Parcel parcel, int i) {
        parcel.writeString(userAvatar.mUrl400x400);
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl570xN(userAvatar));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl224xN(userAvatar));
        parcel.writeDouble(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$PORTRAIT_HEIGHT_RATIO(userAvatar));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl680x540(userAvatar));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl75x75(userAvatar));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl170x135(userAvatar));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrlFullxFull(userAvatar));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl300x300(userAvatar));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl340x270(userAvatar));
    }

    public int describeContents() {
        return 0;
    }

    public UserAvatar getParcel() {
        return this.userAvatar$$0;
    }
}
