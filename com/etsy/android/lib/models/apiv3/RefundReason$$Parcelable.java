package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import org.parceler.ax;

public class RefundReason$$Parcelable implements android.os.Parcelable, ax<RefundReason> {
    public static final aq CREATOR;
    private RefundReason refundReason$$0;

    static {
        CREATOR = new aq();
    }

    public RefundReason$$Parcelable(Parcel parcel) {
        RefundReason refundReason;
        if (parcel.readInt() == -1) {
            refundReason = null;
        } else {
            refundReason = readcom_etsy_android_lib_models_apiv3_RefundReason(parcel);
        }
        this.refundReason$$0 = refundReason;
    }

    public RefundReason$$Parcelable(RefundReason refundReason) {
        this.refundReason$$0 = refundReason;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.refundReason$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_RefundReason(this.refundReason$$0, parcel, i);
    }

    private RefundReason readcom_etsy_android_lib_models_apiv3_RefundReason(Parcel parcel) {
        RefundReason refundReason = new RefundReason();
        refundReason.mName = parcel.readString();
        refundReason.mFormatted = parcel.readString();
        return refundReason;
    }

    private void writecom_etsy_android_lib_models_apiv3_RefundReason(RefundReason refundReason, Parcel parcel, int i) {
        parcel.writeString(refundReason.mName);
        parcel.writeString(refundReason.mFormatted);
    }

    public int describeContents() {
        return 0;
    }

    public RefundReason getParcel() {
        return this.refundReason$$0;
    }
}
