package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public final class CredentialRequest extends AbstractSafeParcelable {
    public static final Creator<CredentialRequest> CREATOR;
    final int mVersionCode;
    private final boolean zzabD;
    private final String[] zzabE;
    private final CredentialPickerConfig zzabF;
    private final CredentialPickerConfig zzabG;

    static {
        CREATOR = new zzc();
    }

    CredentialRequest(int i, boolean z, String[] strArr, CredentialPickerConfig credentialPickerConfig, CredentialPickerConfig credentialPickerConfig2) {
        this.mVersionCode = i;
        this.zzabD = z;
        this.zzabE = (String[]) zzaa.zzz(strArr);
        if (credentialPickerConfig == null) {
            credentialPickerConfig = new a().a();
        }
        this.zzabF = credentialPickerConfig;
        if (credentialPickerConfig2 == null) {
            credentialPickerConfig2 = new a().a();
        }
        this.zzabG = credentialPickerConfig2;
    }

    private CredentialRequest(b bVar) {
        this(2, b.a(bVar), b.b(bVar), b.c(bVar), b.d(bVar));
    }

    @NonNull
    public String[] getAccountTypes() {
        return this.zzabE;
    }

    @NonNull
    public CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.zzabG;
    }

    @NonNull
    public CredentialPickerConfig getCredentialPickerConfig() {
        return this.zzabF;
    }

    @Deprecated
    public boolean getSupportsPasswordLogin() {
        return isPasswordLoginSupported();
    }

    public boolean isPasswordLoginSupported() {
        return this.zzabD;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }
}
