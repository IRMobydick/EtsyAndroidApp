package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.jw;
import java.util.Collections;
import java.util.List;

@jw
public final class AdResponseParcel extends AbstractSafeParcelable {
    public static final zzh CREATOR;
    public String body;
    public final int errorCode;
    public final int orientation;
    public final int versionCode;
    public final List<String> zzEF;
    public final List<String> zzEG;
    public final List<String> zzEI;
    @Nullable
    public final boolean zzEJ;
    public final long zzEL;
    private AdRequestInfoParcel zzFm;
    public final String zzHH;
    public final boolean zzLH;
    @Nullable
    public String zzLI;
    public final long zzLO;
    public final boolean zzLP;
    public final long zzLQ;
    public final List<String> zzLR;
    public final String zzLS;
    public final long zzLT;
    public final String zzLU;
    public final boolean zzLV;
    public final String zzLW;
    public final String zzLX;
    public final boolean zzLY;
    public final boolean zzLZ;
    public final boolean zzLq;
    public final boolean zzMa;
    public final int zzMb;
    public LargeParcelTeleporter zzMc;
    public String zzMd;
    public final String zzMe;
    @Nullable
    public final RewardItemParcel zzMf;
    @Nullable
    public final List<String> zzMg;
    @Nullable
    public final List<String> zzMh;
    @Nullable
    public final boolean zzMi;
    public final AutoClickProtectionConfigurationParcel zzMj;
    @Nullable
    public final String zzMk;
    public final boolean zzvv;
    public final boolean zzvw;
    public final boolean zzvx;

    static {
        CREATOR = new zzh();
    }

    public AdResponseParcel(int i) {
        this(18, null, null, null, i, null, -1, false, -1, null, -1, -1, null, -1, null, false, null, null, false, false, false, true, false, 0, null, null, null, false, false, null, null, null, false, null, false, null, null, null, false);
    }

    public AdResponseParcel(int i, long j) {
        this(18, null, null, null, i, null, -1, false, -1, null, j, -1, null, -1, null, false, null, null, false, false, false, true, false, 0, null, null, null, false, false, null, null, null, false, null, false, null, null, null, false);
    }

    AdResponseParcel(int i, String str, String str2, List<String> list, int i2, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i3, String str3, long j4, String str4, boolean z2, String str5, String str6, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i4, LargeParcelTeleporter largeParcelTeleporter, String str7, String str8, boolean z8, boolean z9, RewardItemParcel rewardItemParcel, List<String> list4, List<String> list5, boolean z10, AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, boolean z11, String str9, List<String> list6, String str10, boolean z12) {
        this.versionCode = i;
        this.zzHH = str;
        this.body = str2;
        this.zzEF = list != null ? Collections.unmodifiableList(list) : null;
        this.errorCode = i2;
        this.zzEG = list2 != null ? Collections.unmodifiableList(list2) : null;
        this.zzLO = j;
        this.zzLP = z;
        this.zzLQ = j2;
        this.zzLR = list3 != null ? Collections.unmodifiableList(list3) : null;
        this.zzEL = j3;
        this.orientation = i3;
        this.zzLS = str3;
        this.zzLT = j4;
        this.zzLU = str4;
        this.zzLV = z2;
        this.zzLW = str5;
        this.zzLX = str6;
        this.zzLY = z3;
        this.zzvv = z4;
        this.zzLq = z5;
        this.zzLZ = z6;
        this.zzMa = z7;
        this.zzMb = i4;
        this.zzMc = largeParcelTeleporter;
        this.zzMd = str7;
        this.zzMe = str8;
        if (this.body == null && this.zzMc != null) {
            StringParcel stringParcel = (StringParcel) this.zzMc.zza(StringParcel.CREATOR);
            if (!(stringParcel == null || TextUtils.isEmpty(stringParcel.zzhY()))) {
                this.body = stringParcel.zzhY();
            }
        }
        this.zzvw = z8;
        this.zzvx = z9;
        this.zzMf = rewardItemParcel;
        this.zzMg = list4;
        this.zzMh = list5;
        this.zzMi = z10;
        this.zzMj = autoClickProtectionConfigurationParcel;
        this.zzLH = z11;
        this.zzLI = str9;
        this.zzEI = list6;
        this.zzMk = str10;
        this.zzEJ = z12;
    }

    public AdResponseParcel(AdRequestInfoParcel adRequestInfoParcel, String str, String str2, List<String> list, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i, String str3, long j4, String str4, String str5, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i2, String str6, boolean z7, boolean z8, RewardItemParcel rewardItemParcel, List<String> list4, List<String> list5, boolean z9, AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, boolean z10, String str7, List<String> list6, String str8, boolean z11) {
        this(18, str, str2, list, -2, list2, j, z, j2, list3, j3, i, str3, j4, str4, false, null, str5, z2, z3, z4, z5, z6, i2, null, null, str6, z7, z8, rewardItemParcel, list4, list5, z9, autoClickProtectionConfigurationParcel, z10, str7, list6, str8, z11);
        this.zzFm = adRequestInfoParcel;
    }

    public AdResponseParcel(AdRequestInfoParcel adRequestInfoParcel, String str, String str2, List<String> list, List<String> list2, long j, boolean z, long j2, List<String> list3, long j3, int i, String str3, long j4, String str4, boolean z2, String str5, String str6, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i2, String str7, boolean z8, boolean z9, RewardItemParcel rewardItemParcel, List<String> list4, List<String> list5, boolean z10, AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, boolean z11, String str8, List<String> list6, String str9, boolean z12) {
        this(18, str, str2, list, -2, list2, j, z, j2, list3, j3, i, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6, z7, i2, null, null, str7, z8, z9, rewardItemParcel, list4, list5, z10, autoClickProtectionConfigurationParcel, z11, str8, list6, str9, z12);
        this.zzFm = adRequestInfoParcel;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (!(this.zzFm == null || this.zzFm.versionCode < 9 || TextUtils.isEmpty(this.body))) {
            this.zzMc = new LargeParcelTeleporter(new StringParcel(this.body));
            this.body = null;
        }
        zzh.zza(this, parcel, i);
    }
}
