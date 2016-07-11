package com.google.android.gms.location.places;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.internal.zzz.zza;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlaceFilter extends l implements SafeParcelable {
    public static final zzg CREATOR;
    private static final PlaceFilter zzaWN;
    final int mVersionCode;
    private final Set<String> zzaWC;
    private final Set<Integer> zzaWD;
    private final Set<UserDataType> zzaWE;
    final boolean zzaWO;
    final List<String> zzaWx;
    final List<Integer> zzaWy;
    final List<UserDataType> zzaWz;

    static {
        CREATOR = new zzg();
        zzaWN = new PlaceFilter();
    }

    public PlaceFilter() {
        this(false, null);
    }

    PlaceFilter(int i, @Nullable List<Integer> list, boolean z, @Nullable List<String> list2, @Nullable List<UserDataType> list3) {
        this.mVersionCode = i;
        this.zzaWy = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.zzaWO = z;
        this.zzaWz = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.zzaWx = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzaWD = zzz(this.zzaWy);
        this.zzaWE = zzz(this.zzaWz);
        this.zzaWC = zzz(this.zzaWx);
    }

    public PlaceFilter(@Nullable Collection<Integer> collection, boolean z, @Nullable Collection<String> collection2, @Nullable Collection<UserDataType> collection3) {
        this(0, zzf(collection), z, zzf(collection2), zzf(collection3));
    }

    public PlaceFilter(boolean z, @Nullable Collection<String> collection) {
        this(null, z, collection, null);
    }

    @Deprecated
    public static PlaceFilter zzCM() {
        return new C1160e().m7453a();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceFilter)) {
            return false;
        }
        PlaceFilter placeFilter = (PlaceFilter) obj;
        return this.zzaWD.equals(placeFilter.zzaWD) && this.zzaWO == placeFilter.zzaWO && this.zzaWE.equals(placeFilter.zzaWE) && this.zzaWC.equals(placeFilter.zzaWC);
    }

    public Set<String> getPlaceIds() {
        return this.zzaWC;
    }

    public int hashCode() {
        return zzz.hashCode(new Object[]{this.zzaWD, Boolean.valueOf(this.zzaWO), this.zzaWE, this.zzaWC});
    }

    public boolean isRestrictedToPlacesOpenNow() {
        return this.zzaWO;
    }

    public String toString() {
        zza zzy = zzz.zzy(this);
        if (!this.zzaWD.isEmpty()) {
            zzy.zzg("types", this.zzaWD);
        }
        zzy.zzg("requireOpenNow", Boolean.valueOf(this.zzaWO));
        if (!this.zzaWC.isEmpty()) {
            zzy.zzg("placeIds", this.zzaWC);
        }
        if (!this.zzaWE.isEmpty()) {
            zzy.zzg("requestedUserDataTypes", this.zzaWE);
        }
        return zzy.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public Set<Integer> zzCL() {
        return this.zzaWD;
    }
}
