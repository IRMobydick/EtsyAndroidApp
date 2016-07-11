package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Deprecated
public final class Address extends AbstractSafeParcelable {
    public static final Creator<Address> CREATOR;
    private final int mVersionCode;
    String name;
    String phoneNumber;
    String zzNZ;
    String zzaTN;
    String zzaTO;
    String zzaTP;
    String zzaTU;
    boolean zzaTW;
    String zzaTX;
    String zzbyK;
    String zzbyL;

    static {
        CREATOR = new zza();
    }

    Address() {
        this.mVersionCode = 1;
    }

    Address(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, String str10) {
        this.mVersionCode = i;
        this.name = str;
        this.zzaTN = str2;
        this.zzaTO = str3;
        this.zzaTP = str4;
        this.zzNZ = str5;
        this.zzbyK = str6;
        this.zzbyL = str7;
        this.zzaTU = str8;
        this.phoneNumber = str9;
        this.zzaTW = z;
        this.zzaTX = str10;
    }

    public String getAddress1() {
        return this.zzaTN;
    }

    public String getAddress2() {
        return this.zzaTO;
    }

    public String getAddress3() {
        return this.zzaTP;
    }

    public String getCity() {
        return this.zzbyK;
    }

    public String getCompanyName() {
        return this.zzaTX;
    }

    public String getCountryCode() {
        return this.zzNZ;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPostalCode() {
        return this.zzaTU;
    }

    public String getState() {
        return this.zzbyL;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isPostBox() {
        return this.zzaTW;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }
}
