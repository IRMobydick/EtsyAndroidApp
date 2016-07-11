package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;

@Deprecated
public class PlaceAlias extends AbstractSafeParcelable {
    public static final zzd CREATOR;
    public static final PlaceAlias zzaYu;
    public static final PlaceAlias zzaYv;
    final int mVersionCode;
    private final String zzaYw;

    static {
        CREATOR = new zzd();
        zzaYu = new PlaceAlias(0, "Home");
        zzaYv = new PlaceAlias(0, "Work");
    }

    PlaceAlias(int i, String str) {
        this.mVersionCode = i;
        this.zzaYw = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceAlias)) {
            return false;
        }
        return zzz.equal(this.zzaYw, ((PlaceAlias) obj).zzaYw);
    }

    public int hashCode() {
        return zzz.hashCode(new Object[]{this.zzaYw});
    }

    public String toString() {
        return zzz.zzy(this).zzg("alias", this.zzaYw).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }

    public String zzDm() {
        return this.zzaYw;
    }
}
