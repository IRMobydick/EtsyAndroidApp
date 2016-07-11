package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.jw;
import java.util.ArrayList;
import java.util.List;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.m */
public final class C1085m {
    private long f4438a;
    private Bundle f4439b;
    private int f4440c;
    private List<String> f4441d;
    private boolean f4442e;
    private int f4443f;
    private boolean f4444g;
    private String f4445h;
    private SearchAdRequestParcel f4446i;
    private Location f4447j;
    private String f4448k;
    private Bundle f4449l;
    private Bundle f4450m;
    private List<String> f4451n;
    private String f4452o;
    private String f4453p;
    private boolean f4454q;

    public C1085m() {
        this.f4438a = -1;
        this.f4439b = new Bundle();
        this.f4440c = -1;
        this.f4441d = new ArrayList();
        this.f4442e = false;
        this.f4443f = -1;
        this.f4444g = false;
        this.f4445h = null;
        this.f4446i = null;
        this.f4447j = null;
        this.f4448k = null;
        this.f4449l = new Bundle();
        this.f4450m = new Bundle();
        this.f4451n = new ArrayList();
        this.f4452o = null;
        this.f4453p = null;
        this.f4454q = false;
    }

    public C1085m(AdRequestParcel adRequestParcel) {
        this.f4438a = adRequestParcel.zzuN;
        this.f4439b = adRequestParcel.extras;
        this.f4440c = adRequestParcel.zzuO;
        this.f4441d = adRequestParcel.zzuP;
        this.f4442e = adRequestParcel.zzuQ;
        this.f4443f = adRequestParcel.zzuR;
        this.f4444g = adRequestParcel.zzuS;
        this.f4445h = adRequestParcel.zzuT;
        this.f4446i = adRequestParcel.zzuU;
        this.f4447j = adRequestParcel.zzuV;
        this.f4448k = adRequestParcel.zzuW;
        this.f4449l = adRequestParcel.zzuX;
        this.f4450m = adRequestParcel.zzuY;
        this.f4451n = adRequestParcel.zzuZ;
        this.f4452o = adRequestParcel.zzva;
        this.f4453p = adRequestParcel.zzvb;
    }

    public AdRequestParcel m5924a() {
        return new AdRequestParcel(7, this.f4438a, this.f4439b, this.f4440c, this.f4441d, this.f4442e, this.f4443f, this.f4444g, this.f4445h, this.f4446i, this.f4447j, this.f4448k, this.f4449l, this.f4450m, this.f4451n, this.f4452o, this.f4453p, false);
    }

    public C1085m m5925a(@Nullable Location location) {
        this.f4447j = location;
        return this;
    }

    public C1085m m5926a(Bundle bundle) {
        this.f4449l = bundle;
        return this;
    }
}
