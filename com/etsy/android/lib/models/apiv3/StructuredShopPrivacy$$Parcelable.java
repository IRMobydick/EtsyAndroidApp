package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.util.MachineTranslationViewState$$PackageHelper;
import org.parceler.ax;

public class StructuredShopPrivacy$$Parcelable implements android.os.Parcelable, ax<StructuredShopPrivacy> {
    public static final bi CREATOR;
    private StructuredShopPrivacy structuredShopPrivacy$$3;

    static {
        CREATOR = new bi();
    }

    public StructuredShopPrivacy$$Parcelable(Parcel parcel) {
        StructuredShopPrivacy structuredShopPrivacy;
        if (parcel.readInt() == -1) {
            structuredShopPrivacy = null;
        } else {
            structuredShopPrivacy = readcom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(parcel);
        }
        this.structuredShopPrivacy$$3 = structuredShopPrivacy;
    }

    public StructuredShopPrivacy$$Parcelable(StructuredShopPrivacy structuredShopPrivacy) {
        this.structuredShopPrivacy$$3 = structuredShopPrivacy;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.structuredShopPrivacy$$3 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(this.structuredShopPrivacy$$3, parcel, i);
    }

    private StructuredShopPrivacy readcom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(Parcel parcel) {
        MachineTranslationViewState machineTranslationViewState;
        StructuredShopPrivacy structuredShopPrivacy = new StructuredShopPrivacy();
        structuredShopPrivacy.mTranslatedOtherText = parcel.readString();
        structuredShopPrivacy.mHeader = parcel.readString();
        if (parcel.readInt() == -1) {
            machineTranslationViewState = null;
        } else {
            machineTranslationViewState = readcom_etsy_android_uikit_util_MachineTranslationViewState(parcel);
        }
        structuredShopPrivacy.mOtherTranslationState = machineTranslationViewState;
        structuredShopPrivacy.mFlags = (PrivacyFlags) parcel.readSerializable();
        return structuredShopPrivacy;
    }

    private MachineTranslationViewState readcom_etsy_android_uikit_util_MachineTranslationViewState(Parcel parcel) {
        boolean z = true;
        MachineTranslationViewState machineTranslationViewState = new MachineTranslationViewState();
        MachineTranslationViewState$$PackageHelper.m5555a(machineTranslationViewState, parcel.readInt());
        if (parcel.readInt() != 1) {
            z = false;
        }
        MachineTranslationViewState$$PackageHelper.m5556a(machineTranslationViewState, z);
        return machineTranslationViewState;
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(StructuredShopPrivacy structuredShopPrivacy, Parcel parcel, int i) {
        parcel.writeString(structuredShopPrivacy.mTranslatedOtherText);
        parcel.writeString(structuredShopPrivacy.mHeader);
        if (structuredShopPrivacy.mOtherTranslationState == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_uikit_util_MachineTranslationViewState(structuredShopPrivacy.mOtherTranslationState, parcel, i);
        }
        parcel.writeSerializable(structuredShopPrivacy.mFlags);
    }

    private void writecom_etsy_android_uikit_util_MachineTranslationViewState(MachineTranslationViewState machineTranslationViewState, Parcel parcel, int i) {
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5558b(machineTranslationViewState));
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5557a(machineTranslationViewState) ? 1 : 0);
    }

    public int describeContents() {
        return 0;
    }

    public StructuredShopPrivacy getParcel() {
        return this.structuredShopPrivacy$$3;
    }
}
