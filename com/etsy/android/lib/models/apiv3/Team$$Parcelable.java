package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import org.parceler.ax;

public class Team$$Parcelable implements android.os.Parcelable, ax<Team> {
    public static final bn CREATOR;
    private Team team$$9;

    static {
        CREATOR = new bn();
    }

    public Team$$Parcelable(Parcel parcel) {
        Team team;
        if (parcel.readInt() == -1) {
            team = null;
        } else {
            team = readcom_etsy_android_lib_models_apiv3_Team(parcel);
        }
        this.team$$9 = team;
    }

    public Team$$Parcelable(Team team) {
        this.team$$9 = team;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.team$$9 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_Team(this.team$$9, parcel, i);
    }

    private Team readcom_etsy_android_lib_models_apiv3_Team(Parcel parcel) {
        EtsyId etsyId;
        Team team = new Team();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        team.mId = etsyId;
        team.mShortDescription = parcel.readString();
        team.mAvatarUrl = parcel.readString();
        team.mName = parcel.readString();
        return team;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void writecom_etsy_android_lib_models_apiv3_Team(Team team, Parcel parcel, int i) {
        if (team.mId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(team.mId, parcel, i);
        }
        parcel.writeString(team.mShortDescription);
        parcel.writeString(team.mAvatarUrl);
        parcel.writeString(team.mName);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public Team getParcel() {
        return this.team$$9;
    }
}
