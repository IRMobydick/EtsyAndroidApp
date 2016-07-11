package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class NotificationOptOutBannerSetting$$Parcelable implements android.os.Parcelable, ax<NotificationOptOutBannerSetting> {
    public static final ae CREATOR;
    private NotificationOptOutBannerSetting notificationOptOutBannerSetting$$0;

    static {
        CREATOR = new ae();
    }

    public NotificationOptOutBannerSetting$$Parcelable(Parcel parcel) {
        NotificationOptOutBannerSetting notificationOptOutBannerSetting;
        if (parcel.readInt() == -1) {
            notificationOptOutBannerSetting = null;
        } else {
            notificationOptOutBannerSetting = m2370x42821241(parcel);
        }
        this.notificationOptOutBannerSetting$$0 = notificationOptOutBannerSetting;
    }

    public NotificationOptOutBannerSetting$$Parcelable(NotificationOptOutBannerSetting notificationOptOutBannerSetting) {
        this.notificationOptOutBannerSetting$$0 = notificationOptOutBannerSetting;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.notificationOptOutBannerSetting$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        m2371xde12a518(this.notificationOptOutBannerSetting$$0, parcel, i);
    }

    private NotificationOptOutBannerSetting m2370x42821241(Parcel parcel) {
        boolean z = true;
        NotificationOptOutBannerSetting notificationOptOutBannerSetting = new NotificationOptOutBannerSetting();
        notificationOptOutBannerSetting.mBannerDisableLink = parcel.readString();
        if (parcel.readInt() != 1) {
            z = false;
        }
        notificationOptOutBannerSetting.mBannerShouldShow = z;
        notificationOptOutBannerSetting.mBannerText = parcel.readString();
        return notificationOptOutBannerSetting;
    }

    private void m2371xde12a518(NotificationOptOutBannerSetting notificationOptOutBannerSetting, Parcel parcel, int i) {
        parcel.writeString(notificationOptOutBannerSetting.mBannerDisableLink);
        parcel.writeInt(notificationOptOutBannerSetting.mBannerShouldShow ? 1 : 0);
        parcel.writeString(notificationOptOutBannerSetting.mBannerText);
    }

    public int describeContents() {
        return 0;
    }

    public NotificationOptOutBannerSetting getParcel() {
        return this.notificationOptOutBannerSetting$$0;
    }
}
