package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class Source$$Parcelable implements android.os.Parcelable, ax<Source> {
    public static final am CREATOR;
    private Source source$$129;

    static {
        CREATOR = new am();
    }

    public Source$$Parcelable(Parcel parcel) {
        Source source;
        if (parcel.readInt() == -1) {
            source = null;
        } else {
            source = readcom_etsy_android_lib_models_Source(parcel);
        }
        this.source$$129 = source;
    }

    public Source$$Parcelable(Source source) {
        this.source$$129 = source;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.source$$129 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_Source(this.source$$129, parcel, i);
    }

    private Source readcom_etsy_android_lib_models_Source(Parcel parcel) {
        EtsyId etsyId;
        Source source = new Source();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        source.mListingId = etsyId;
        source.mCount = parcel.readInt();
        source.mFormattedType = parcel.readString();
        source.mThumbnail = parcel.readString();
        source.mRawType = parcel.readString();
        return source;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_Source(Source source, Parcel parcel, int i) {
        if (source.mListingId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(source.mListingId, parcel, i);
        }
        parcel.writeInt(source.mCount);
        parcel.writeString(source.mFormattedType);
        parcel.writeString(source.mThumbnail);
        parcel.writeString(source.mRawType);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public Source getParcel() {
        return this.source$$129;
    }
}
