package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public final class SignInConfiguration extends AbstractSafeParcelable {
    public static final Creator<SignInConfiguration> CREATOR;
    final int versionCode;
    private final String zzacT;
    private GoogleSignInOptions zzacU;

    static {
        CREATOR = new zzj();
    }

    SignInConfiguration(int i, String str, GoogleSignInOptions googleSignInOptions) {
        this.versionCode = i;
        this.zzacT = zzaa.zzdl(str);
        this.zzacU = googleSignInOptions;
    }

    public SignInConfiguration(String str, GoogleSignInOptions googleSignInOptions) {
        this(3, str, googleSignInOptions);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
            if (!this.zzacT.equals(signInConfiguration.zzpz())) {
                return false;
            }
            if (this.zzacU == null) {
                if (signInConfiguration.zzpA() != null) {
                    return false;
                }
            } else if (!this.zzacU.equals(signInConfiguration.zzpA())) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        return new e().a(this.zzacT).a(this.zzacU).a();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzj.zza(this, parcel, i);
    }

    public GoogleSignInOptions zzpA() {
        return this.zzacU;
    }

    public String zzpz() {
        return this.zzacT;
    }
}
