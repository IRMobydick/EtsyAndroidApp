package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class Snippet$$Parcelable implements android.os.Parcelable, ax<Snippet> {
    public static final al CREATOR;
    private Snippet snippet$$0;

    static {
        CREATOR = new al();
    }

    public Snippet$$Parcelable(Parcel parcel) {
        Snippet snippet;
        if (parcel.readInt() == -1) {
            snippet = null;
        } else {
            snippet = readcom_etsy_android_lib_models_Snippet(parcel);
        }
        this.snippet$$0 = snippet;
    }

    public Snippet$$Parcelable(Snippet snippet) {
        this.snippet$$0 = snippet;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.snippet$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_Snippet(this.snippet$$0, parcel, i);
    }

    private Snippet readcom_etsy_android_lib_models_Snippet(Parcel parcel) {
        EtsyId etsyId;
        Snippet snippet = new Snippet();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        snippet.mId = etsyId;
        snippet.mTitle = parcel.readString();
        snippet.mContent = parcel.readString();
        return snippet;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_Snippet(Snippet snippet, Parcel parcel, int i) {
        if (snippet.mId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(snippet.mId, parcel, i);
        }
        parcel.writeString(snippet.mTitle);
        parcel.writeString(snippet.mContent);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public Snippet getParcel() {
        return this.snippet$$0;
    }
}
