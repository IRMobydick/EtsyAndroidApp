package com.google.android.gms.ads.internal.request;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Messenger;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.jw;
import java.util.List;

@jw
/* renamed from: com.google.android.gms.ads.internal.request.a */
public final class C1117a {
    public final String f4592A;
    public final float f4593B;
    public final boolean f4594C;
    public final int f4595D;
    public final int f4596E;
    public final boolean f4597F;
    public final boolean f4598G;
    public final String f4599H;
    public final String f4600I;
    public final boolean f4601J;
    public final int f4602K;
    public final Bundle f4603L;
    @Nullable
    public final Bundle f4604a;
    public final AdRequestParcel f4605b;
    public final AdSizeParcel f4606c;
    public final String f4607d;
    public final ApplicationInfo f4608e;
    @Nullable
    public final PackageInfo f4609f;
    public final String f4610g;
    public final String f4611h;
    public final Bundle f4612i;
    public final VersionInfoParcel f4613j;
    public final int f4614k;
    public final List<String> f4615l;
    public final List<String> f4616m;
    public final Bundle f4617n;
    public final boolean f4618o;
    public final Messenger f4619p;
    public final int f4620q;
    public final int f4621r;
    public final float f4622s;
    public final String f4623t;
    public final long f4624u;
    public final String f4625v;
    public final List<String> f4626w;
    public final String f4627x;
    public final NativeAdOptionsParcel f4628y;
    public final CapabilityParcel f4629z;

    public C1117a(@Nullable Bundle bundle, AdRequestParcel adRequestParcel, AdSizeParcel adSizeParcel, String str, ApplicationInfo applicationInfo, @Nullable PackageInfo packageInfo, String str2, String str3, VersionInfoParcel versionInfoParcel, Bundle bundle2, List<String> list, List<String> list2, Bundle bundle3, boolean z, Messenger messenger, int i, int i2, float f, String str4, long j, String str5, List<String> list3, String str6, NativeAdOptionsParcel nativeAdOptionsParcel, CapabilityParcel capabilityParcel, String str7, float f2, boolean z2, int i3, int i4, boolean z3, boolean z4, String str8, String str9, boolean z5, int i5, Bundle bundle4) {
        this.f4604a = bundle;
        this.f4605b = adRequestParcel;
        this.f4606c = adSizeParcel;
        this.f4607d = str;
        this.f4608e = applicationInfo;
        this.f4609f = packageInfo;
        this.f4610g = str2;
        this.f4611h = str3;
        this.f4613j = versionInfoParcel;
        this.f4612i = bundle2;
        this.f4618o = z;
        this.f4619p = messenger;
        this.f4620q = i;
        this.f4621r = i2;
        this.f4622s = f;
        if (list == null || list.size() <= 0) {
            if (adSizeParcel.zzvx) {
                this.f4614k = 4;
            } else {
                this.f4614k = 0;
            }
            this.f4615l = null;
            this.f4616m = null;
        } else {
            this.f4614k = 3;
            this.f4615l = list;
            this.f4616m = list2;
        }
        this.f4617n = bundle3;
        this.f4623t = str4;
        this.f4624u = j;
        this.f4625v = str5;
        this.f4626w = list3;
        this.f4627x = str6;
        this.f4628y = nativeAdOptionsParcel;
        this.f4629z = capabilityParcel;
        this.f4592A = str7;
        this.f4593B = f2;
        this.f4594C = z2;
        this.f4595D = i3;
        this.f4596E = i4;
        this.f4597F = z3;
        this.f4598G = z4;
        this.f4599H = str8;
        this.f4600I = str9;
        this.f4601J = z5;
        this.f4602K = i5;
        this.f4603L = bundle4;
    }
}
