package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class UserNote$$Parcelable implements android.os.Parcelable, ax<UserNote> {
    public static final at CREATOR;
    private UserNote userNote$$4;

    static {
        CREATOR = new at();
    }

    public UserNote$$Parcelable(Parcel parcel) {
        UserNote userNote;
        if (parcel.readInt() == -1) {
            userNote = null;
        } else {
            userNote = readcom_etsy_android_lib_models_UserNote(parcel);
        }
        this.userNote$$4 = userNote;
    }

    public UserNote$$Parcelable(UserNote userNote) {
        this.userNote$$4 = userNote;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.userNote$$4 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_UserNote(this.userNote$$4, parcel, i);
    }

    private UserNote readcom_etsy_android_lib_models_UserNote(Parcel parcel) {
        EtsyId etsyId;
        UserNote userNote = new UserNote();
        userNote.mSubjectType = parcel.readString();
        userNote.mNote = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userNote.mUserNoteId = etsyId;
        return userNote;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_UserNote(UserNote userNote, Parcel parcel, int i) {
        parcel.writeString(userNote.mSubjectType);
        parcel.writeString(userNote.mNote);
        if (userNote.mUserNoteId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(userNote.mUserNoteId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public UserNote getParcel() {
        return this.userNote$$4;
    }
}
