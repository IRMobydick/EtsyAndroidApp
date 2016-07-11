package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.internal.jw;
import java.util.List;

@jw
public final class AdRequestParcel extends AbstractSafeParcelable {
    public static final zzg CREATOR;
    public final Bundle extras;
    public final int versionCode;
    public final long zzuN;
    public final int zzuO;
    public final List<String> zzuP;
    public final boolean zzuQ;
    public final int zzuR;
    public final boolean zzuS;
    public final String zzuT;
    public final SearchAdRequestParcel zzuU;
    public final Location zzuV;
    public final String zzuW;
    public final Bundle zzuX;
    public final Bundle zzuY;
    public final List<String> zzuZ;
    public final String zzva;
    public final String zzvb;
    public final boolean zzvc;

    static {
        CREATOR = new zzg();
    }

    public AdRequestParcel(int i, long j, Bundle bundle, int i2, List<String> list, boolean z, int i3, boolean z2, String str, SearchAdRequestParcel searchAdRequestParcel, Location location, String str2, Bundle bundle2, Bundle bundle3, List<String> list2, String str3, String str4, boolean z3) {
        this.versionCode = i;
        this.zzuN = j;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.extras = bundle;
        this.zzuO = i2;
        this.zzuP = list;
        this.zzuQ = z;
        this.zzuR = i3;
        this.zzuS = z2;
        this.zzuT = str;
        this.zzuU = searchAdRequestParcel;
        this.zzuV = location;
        this.zzuW = str2;
        this.zzuX = bundle2;
        this.zzuY = bundle3;
        this.zzuZ = list2;
        this.zzva = str3;
        this.zzvb = str4;
        this.zzvc = z3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AdRequestParcel)) {
            return false;
        }
        AdRequestParcel adRequestParcel = (AdRequestParcel) obj;
        return this.versionCode == adRequestParcel.versionCode && this.zzuN == adRequestParcel.zzuN && zzz.equal(this.extras, adRequestParcel.extras) && this.zzuO == adRequestParcel.zzuO && zzz.equal(this.zzuP, adRequestParcel.zzuP) && this.zzuQ == adRequestParcel.zzuQ && this.zzuR == adRequestParcel.zzuR && this.zzuS == adRequestParcel.zzuS && zzz.equal(this.zzuT, adRequestParcel.zzuT) && zzz.equal(this.zzuU, adRequestParcel.zzuU) && zzz.equal(this.zzuV, adRequestParcel.zzuV) && zzz.equal(this.zzuW, adRequestParcel.zzuW) && zzz.equal(this.zzuX, adRequestParcel.zzuX) && zzz.equal(this.zzuY, adRequestParcel.zzuY) && zzz.equal(this.zzuZ, adRequestParcel.zzuZ) && zzz.equal(this.zzva, adRequestParcel.zzva) && zzz.equal(this.zzvb, adRequestParcel.zzvb) && this.zzvc == adRequestParcel.zzvc;
    }

    public int hashCode() {
        return zzz.hashCode(new Object[]{Integer.valueOf(this.versionCode), Long.valueOf(this.zzuN), this.extras, Integer.valueOf(this.zzuO), this.zzuP, Boolean.valueOf(this.zzuQ), Integer.valueOf(this.zzuR), Boolean.valueOf(this.zzuS), this.zzuT, this.zzuU, this.zzuV, this.zzuW, this.zzuX, this.zzuY, this.zzuZ, this.zzva, this.zzvb, Boolean.valueOf(this.zzvc)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }
}
