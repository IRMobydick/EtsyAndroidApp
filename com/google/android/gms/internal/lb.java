package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.e;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@jw
public class lb {
    public final long f5319A;
    public final long f5320B;
    public final String f5321C;
    @Nullable
    public final e f5322D;
    public boolean f5323E;
    public boolean f5324F;
    @Nullable
    public final List<String> f5325G;
    public final AdRequestParcel f5326a;
    @Nullable
    public final no f5327b;
    public final List<String> f5328c;
    public final int f5329d;
    public final List<String> f5330e;
    public final List<String> f5331f;
    public final int f5332g;
    public final long f5333h;
    public final String f5334i;
    public final JSONObject f5335j;
    public final boolean f5336k;
    public final AutoClickProtectionConfigurationParcel f5337l;
    public boolean f5338m;
    public final boolean f5339n;
    @Nullable
    public final hj f5340o;
    @Nullable
    public final zzgb f5341p;
    @Nullable
    public final String f5342q;
    public final hk f5343r;
    @Nullable
    public final zzft f5344s;
    public final long f5345t;
    @Nullable
    public final String f5346u;
    public final AdSizeParcel f5347v;
    public final long f5348w;
    @Nullable
    public final RewardItemParcel f5349x;
    @Nullable
    public final List<String> f5350y;
    @Nullable
    public final List<String> f5351z;

    public lb(AdRequestParcel adRequestParcel, @Nullable no noVar, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, @Nullable hj hjVar, @Nullable zzgb com_google_android_gms_internal_zzgb, @Nullable String str2, hk hkVar, @Nullable zzft com_google_android_gms_internal_zzft, long j2, AdSizeParcel adSizeParcel, long j3, long j4, long j5, String str3, JSONObject jSONObject, @Nullable e eVar, RewardItemParcel rewardItemParcel, List<String> list4, List<String> list5, boolean z2, AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, @Nullable String str4, List<String> list6) {
        this.f5323E = false;
        this.f5324F = false;
        this.f5326a = adRequestParcel;
        this.f5327b = noVar;
        this.f5328c = m6988a(list);
        this.f5329d = i;
        this.f5330e = m6988a(list2);
        this.f5331f = m6988a(list3);
        this.f5332g = i2;
        this.f5333h = j;
        this.f5334i = str;
        this.f5339n = z;
        this.f5340o = hjVar;
        this.f5341p = com_google_android_gms_internal_zzgb;
        this.f5342q = str2;
        this.f5343r = hkVar;
        this.f5344s = com_google_android_gms_internal_zzft;
        this.f5345t = j2;
        this.f5347v = adSizeParcel;
        this.f5348w = j3;
        this.f5319A = j4;
        this.f5320B = j5;
        this.f5321C = str3;
        this.f5335j = jSONObject;
        this.f5322D = eVar;
        this.f5349x = rewardItemParcel;
        this.f5350y = m6988a(list4);
        this.f5351z = m6988a(list5);
        this.f5336k = z2;
        this.f5337l = autoClickProtectionConfigurationParcel;
        this.f5346u = str4;
        this.f5325G = m6988a(list6);
    }

    public lb(lc lcVar, @Nullable no noVar, @Nullable hj hjVar, @Nullable zzgb com_google_android_gms_internal_zzgb, @Nullable String str, @Nullable zzft com_google_android_gms_internal_zzft, @Nullable e eVar, @Nullable String str2) {
        no noVar2 = noVar;
        hj hjVar2 = hjVar;
        zzgb com_google_android_gms_internal_zzgb2 = com_google_android_gms_internal_zzgb;
        String str3 = str;
        zzft com_google_android_gms_internal_zzft2 = com_google_android_gms_internal_zzft;
        e eVar2 = eVar;
        String str4 = str2;
        this(lcVar.f5352a.zzLi, noVar2, lcVar.f5353b.zzEF, lcVar.f5356e, lcVar.f5353b.zzEG, lcVar.f5353b.zzLR, lcVar.f5353b.orientation, lcVar.f5353b.zzEL, lcVar.f5352a.zzLl, lcVar.f5353b.zzLP, hjVar2, com_google_android_gms_internal_zzgb2, str3, lcVar.f5354c, com_google_android_gms_internal_zzft2, lcVar.f5353b.zzLQ, lcVar.f5355d, lcVar.f5353b.zzLO, lcVar.f5357f, lcVar.f5358g, lcVar.f5353b.zzLU, lcVar.f5359h, eVar2, lcVar.f5353b.zzMf, lcVar.f5353b.zzMg, lcVar.f5353b.zzMg, lcVar.f5353b.zzMi, lcVar.f5353b.zzMj, str4, lcVar.f5353b.zzEI);
    }

    @Nullable
    private static <T> List<T> m6988a(@Nullable List<T> list) {
        return list == null ? null : Collections.unmodifiableList(list);
    }

    public boolean m6989a() {
        return (this.f5327b == null || this.f5327b.m7262l() == null) ? false : this.f5327b.m7262l().zzdi();
    }
}
