package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Deprecated
public class CountrySpecification extends AbstractSafeParcelable {
    public static final Creator<CountrySpecification> CREATOR;
    private final int mVersionCode;
    String zzNZ;

    static {
        CREATOR = new zzc();
    }

    CountrySpecification(int i, String str) {
        this.mVersionCode = i;
        this.zzNZ = str;
    }

    public CountrySpecification(String str) {
        this.mVersionCode = 1;
        this.zzNZ = str;
    }

    public String getCountryCode() {
        return this.zzNZ;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }
}
