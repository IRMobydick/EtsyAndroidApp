package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.ads.internal.InterstitialAdParameterParcel;
import com.google.android.gms.ads.internal.client.a;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.dynamic.zzd.zza;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.fg;
import com.google.android.gms.internal.fq;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.no;

@jw
public final class AdOverlayInfoParcel extends AbstractSafeParcelable {
    public static final zzf CREATOR;
    public final int orientation;
    public final String url;
    public final int versionCode;
    public final AdLauncherIntentInfoParcel zzHC;
    public final a zzHD;
    public final f zzHE;
    public final no zzHF;
    public final fg zzHG;
    public final String zzHH;
    public final boolean zzHI;
    public final String zzHJ;
    public final l zzHK;
    public final int zzHL;
    public final fq zzHM;
    public final String zzHN;
    public final InterstitialAdParameterParcel zzHO;
    public final VersionInfoParcel zzsx;

    static {
        CREATOR = new zzf();
    }

    AdOverlayInfoParcel(int i, AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i2, int i3, String str3, VersionInfoParcel versionInfoParcel, IBinder iBinder6, String str4, InterstitialAdParameterParcel interstitialAdParameterParcel) {
        this.versionCode = i;
        this.zzHC = adLauncherIntentInfoParcel;
        this.zzHD = (a) zze.zzx(zza.zzbz(iBinder));
        this.zzHE = (f) zze.zzx(zza.zzbz(iBinder2));
        this.zzHF = (no) zze.zzx(zza.zzbz(iBinder3));
        this.zzHG = (fg) zze.zzx(zza.zzbz(iBinder4));
        this.zzHH = str;
        this.zzHI = z;
        this.zzHJ = str2;
        this.zzHK = (l) zze.zzx(zza.zzbz(iBinder5));
        this.orientation = i2;
        this.zzHL = i3;
        this.url = str3;
        this.zzsx = versionInfoParcel;
        this.zzHM = (fq) zze.zzx(zza.zzbz(iBinder6));
        this.zzHN = str4;
        this.zzHO = interstitialAdParameterParcel;
    }

    public AdOverlayInfoParcel(a aVar, f fVar, l lVar, no noVar, int i, VersionInfoParcel versionInfoParcel, String str, InterstitialAdParameterParcel interstitialAdParameterParcel) {
        this.versionCode = 4;
        this.zzHC = null;
        this.zzHD = aVar;
        this.zzHE = fVar;
        this.zzHF = noVar;
        this.zzHG = null;
        this.zzHH = null;
        this.zzHI = false;
        this.zzHJ = null;
        this.zzHK = lVar;
        this.orientation = i;
        this.zzHL = 1;
        this.url = null;
        this.zzsx = versionInfoParcel;
        this.zzHM = null;
        this.zzHN = str;
        this.zzHO = interstitialAdParameterParcel;
    }

    public AdOverlayInfoParcel(a aVar, f fVar, l lVar, no noVar, boolean z, int i, VersionInfoParcel versionInfoParcel) {
        this.versionCode = 4;
        this.zzHC = null;
        this.zzHD = aVar;
        this.zzHE = fVar;
        this.zzHF = noVar;
        this.zzHG = null;
        this.zzHH = null;
        this.zzHI = z;
        this.zzHJ = null;
        this.zzHK = lVar;
        this.orientation = i;
        this.zzHL = 2;
        this.url = null;
        this.zzsx = versionInfoParcel;
        this.zzHM = null;
        this.zzHN = null;
        this.zzHO = null;
    }

    public AdOverlayInfoParcel(a aVar, f fVar, fg fgVar, l lVar, no noVar, boolean z, int i, String str, VersionInfoParcel versionInfoParcel, fq fqVar) {
        this.versionCode = 4;
        this.zzHC = null;
        this.zzHD = aVar;
        this.zzHE = fVar;
        this.zzHF = noVar;
        this.zzHG = fgVar;
        this.zzHH = null;
        this.zzHI = z;
        this.zzHJ = null;
        this.zzHK = lVar;
        this.orientation = i;
        this.zzHL = 3;
        this.url = str;
        this.zzsx = versionInfoParcel;
        this.zzHM = fqVar;
        this.zzHN = null;
        this.zzHO = null;
    }

    public AdOverlayInfoParcel(a aVar, f fVar, fg fgVar, l lVar, no noVar, boolean z, int i, String str, String str2, VersionInfoParcel versionInfoParcel, fq fqVar) {
        this.versionCode = 4;
        this.zzHC = null;
        this.zzHD = aVar;
        this.zzHE = fVar;
        this.zzHF = noVar;
        this.zzHG = fgVar;
        this.zzHH = str2;
        this.zzHI = z;
        this.zzHJ = str;
        this.zzHK = lVar;
        this.orientation = i;
        this.zzHL = 3;
        this.url = null;
        this.zzsx = versionInfoParcel;
        this.zzHM = fqVar;
        this.zzHN = null;
        this.zzHO = null;
    }

    public AdOverlayInfoParcel(AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, a aVar, f fVar, l lVar, VersionInfoParcel versionInfoParcel) {
        this.versionCode = 4;
        this.zzHC = adLauncherIntentInfoParcel;
        this.zzHD = aVar;
        this.zzHE = fVar;
        this.zzHF = null;
        this.zzHG = null;
        this.zzHH = null;
        this.zzHI = false;
        this.zzHJ = null;
        this.zzHK = lVar;
        this.orientation = -1;
        this.zzHL = 4;
        this.url = null;
        this.zzsx = versionInfoParcel;
        this.zzHM = null;
        this.zzHN = null;
        this.zzHO = null;
    }

    public static void zza(Intent intent, AdOverlayInfoParcel adOverlayInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static AdOverlayInfoParcel zzb(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    IBinder zzgA() {
        return zze.zzD(this.zzHM).asBinder();
    }

    IBinder zzgB() {
        return zze.zzD(this.zzHK).asBinder();
    }

    IBinder zzgw() {
        return zze.zzD(this.zzHD).asBinder();
    }

    IBinder zzgx() {
        return zze.zzD(this.zzHE).asBinder();
    }

    IBinder zzgy() {
        return zze.zzD(this.zzHF).asBinder();
    }

    IBinder zzgz() {
        return zze.zzD(this.zzHG).asBinder();
    }
}
