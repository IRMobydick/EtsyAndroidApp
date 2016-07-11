package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public class SignInAccount extends AbstractSafeParcelable {
    public static final Creator<SignInAccount> CREATOR;
    final int versionCode;
    @Deprecated
    String zzVA;
    private GoogleSignInAccount zzacH;
    @Deprecated
    String zzacn;

    static {
        CREATOR = new zzc();
    }

    SignInAccount(int i, String str, GoogleSignInAccount googleSignInAccount, String str2) {
        this.versionCode = i;
        this.zzacH = googleSignInAccount;
        this.zzacn = zzaa.zzh(str, "8.3 and 8.4 SDKs require non-null email");
        this.zzVA = zzaa.zzh(str2, "8.3 and 8.4 SDKs require non-null userId");
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }

    public GoogleSignInAccount zzpp() {
        return this.zzacH;
    }
}
