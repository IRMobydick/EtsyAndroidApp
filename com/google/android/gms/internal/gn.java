package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

@jw
public class gn {
    private final Map<go, gp> f4964a;
    private final LinkedList<go> f4965b;
    private gh f4966c;

    public gn() {
        this.f4964a = new HashMap();
        this.f4965b = new LinkedList();
    }

    @Nullable
    static Bundle m6589a(AdRequestParcel adRequestParcel) {
        Bundle bundle = adRequestParcel.zzuX;
        return bundle == null ? null : bundle.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
    }

    private static void m6590a(Bundle bundle, String str) {
        String[] split = str.split("/", 2);
        if (split.length != 0) {
            String str2 = split[0];
            if (split.length == 1) {
                bundle.remove(str2);
                return;
            }
            Bundle bundle2 = bundle.getBundle(str2);
            if (bundle2 != null) {
                m6590a(bundle2, split[1]);
            }
        }
    }

    private static void m6591a(String str, go goVar) {
        if (C1129c.m6187a(2)) {
            lo.m7056e(String.format(str, new Object[]{goVar}));
        }
    }

    private String[] m6592a(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), Constants.ENCODING);
            }
            return split;
        } catch (UnsupportedEncodingException e) {
            return new String[0];
        }
    }

    static AdRequestParcel m6593b(AdRequestParcel adRequestParcel) {
        Parcel obtain = Parcel.obtain();
        adRequestParcel.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        AdRequestParcel adRequestParcel2 = (AdRequestParcel) AdRequestParcel.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        Bundle a = m6589a(adRequestParcel2);
        if (a == null) {
            a = new Bundle();
            adRequestParcel2.zzuX.putBundle("com.google.ads.mediation.admob.AdMobAdapter", a);
        }
        a.putBoolean("_skipMediation", true);
        return adRequestParcel2;
    }

    private boolean m6594b(String str) {
        try {
            return Pattern.matches((String) dz.ao.m6433c(), str);
        } catch (Throwable e) {
            C1101o.m6044h().m7021a(e, true);
            return false;
        }
    }

    static boolean m6595c(AdRequestParcel adRequestParcel) {
        Bundle bundle = adRequestParcel.zzuX;
        if (bundle == null) {
            return false;
        }
        bundle = bundle.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle != null && bundle.containsKey("_skipMediation");
    }

    private static AdRequestParcel m6596d(AdRequestParcel adRequestParcel) {
        Parcel obtain = Parcel.obtain();
        adRequestParcel.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        AdRequestParcel adRequestParcel2 = (AdRequestParcel) AdRequestParcel.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        for (String a : ((String) dz.ak.m6433c()).split(",")) {
            m6590a(adRequestParcel2.zzuX, a);
        }
        return adRequestParcel2;
    }

    private String m6597e() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = this.f4965b.iterator();
            while (it.hasNext()) {
                stringBuilder.append(Base64.encodeToString(((go) it.next()).toString().getBytes(Constants.ENCODING), 0));
                if (it.hasNext()) {
                    stringBuilder.append("\u0000");
                }
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }

    gq m6598a(AdRequestParcel adRequestParcel, String str) {
        if (m6594b(str)) {
            return null;
        }
        gp gpVar;
        int i = new ke(this.f4966c.m6581a()).a().f5285m;
        AdRequestParcel d = m6596d(adRequestParcel);
        go goVar = new go(d, str, i);
        gp gpVar2 = (gp) this.f4964a.get(goVar);
        if (gpVar2 == null) {
            m6591a("Interstitial pool created at %s.", goVar);
            gpVar2 = new gp(d, str, i);
            this.f4964a.put(goVar, gpVar2);
            gpVar = gpVar2;
        } else {
            gpVar = gpVar2;
        }
        this.f4965b.remove(goVar);
        this.f4965b.add(goVar);
        gpVar.m6618g();
        while (this.f4965b.size() > ((Integer) dz.al.m6433c()).intValue()) {
            go goVar2 = (go) this.f4965b.remove();
            gp gpVar3 = (gp) this.f4964a.get(goVar2);
            m6591a("Evicting interstitial queue for %s.", goVar2);
            while (gpVar3.m6615d() > 0) {
                gpVar3.m6610a(null).a.zzbO();
            }
            this.f4964a.remove(goVar2);
        }
        while (gpVar.m6615d() > 0) {
            gq a = gpVar.m6610a(d);
            if (!a.e || C1101o.m6045i().currentTimeMillis() - a.d <= 1000 * ((long) ((Integer) dz.an.m6433c()).intValue())) {
                String str2 = a.b != null ? " (inline) " : " ";
                m6591a(new StringBuilder(String.valueOf(str2).length() + 34).append("Pooled interstitial").append(str2).append("returned at %s.").toString(), goVar);
                return a;
            }
            m6591a("Expired interstitial at %s.", goVar);
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m6599a() {
        /*
        r9 = this;
        r8 = 2;
        r0 = r9.f4966c;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r0 = r9.f4964a;
        r0 = r0.entrySet();
        r3 = r0.iterator();
    L_0x0010:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x0076;
    L_0x0016:
        r0 = r3.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (com.google.android.gms.internal.go) r1;
        r0 = r0.getValue();
        r0 = (com.google.android.gms.internal.gp) r0;
        r2 = com.google.android.gms.ads.internal.util.client.C1129c.m6187a(r8);
        if (r2 == 0) goto L_0x0056;
    L_0x002e:
        r2 = r0.m6615d();
        r4 = r0.m6616e();
        if (r4 >= r2) goto L_0x0056;
    L_0x0038:
        r5 = "Loading %s/%s pooled interstitials for %s.";
        r6 = 3;
        r6 = new java.lang.Object[r6];
        r7 = 0;
        r4 = r2 - r4;
        r4 = java.lang.Integer.valueOf(r4);
        r6[r7] = r4;
        r4 = 1;
        r2 = java.lang.Integer.valueOf(r2);
        r6[r4] = r2;
        r6[r8] = r1;
        r2 = java.lang.String.format(r5, r6);
        com.google.android.gms.internal.lo.m7056e(r2);
    L_0x0056:
        r0.m6617f();
    L_0x0059:
        r4 = r0.m6615d();
        r2 = com.google.android.gms.internal.dz.am;
        r2 = r2.m6433c();
        r2 = (java.lang.Integer) r2;
        r2 = r2.intValue();
        if (r4 >= r2) goto L_0x0010;
    L_0x006b:
        r2 = "Pooling and loading one new interstitial for %s.";
        m6591a(r2, r1);
        r2 = r9.f4966c;
        r0.m6611a(r2);
        goto L_0x0059;
    L_0x0076:
        r9.m6601b();
        goto L_0x0005;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gn.a():void");
    }

    void m6600a(gh ghVar) {
        if (this.f4966c == null) {
            this.f4966c = ghVar.m6584b();
            m6603c();
        }
    }

    void m6601b() {
        if (this.f4966c != null) {
            Editor edit = this.f4966c.m6581a().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
            edit.clear();
            for (Entry entry : this.f4964a.entrySet()) {
                go goVar = (go) entry.getKey();
                gp gpVar = (gp) entry.getValue();
                if (gpVar.m6619h()) {
                    edit.putString(goVar.toString(), new gr(gpVar).m6620a());
                    m6591a("Saved interstitial queue for %s.", goVar);
                }
            }
            edit.putString("PoolKeys", m6597e());
            edit.apply();
        }
    }

    void m6602b(AdRequestParcel adRequestParcel, String str) {
        if (this.f4966c != null) {
            int i = new ke(this.f4966c.m6581a()).a().f5285m;
            AdRequestParcel d = m6596d(adRequestParcel);
            go goVar = new go(d, str, i);
            gp gpVar = (gp) this.f4964a.get(goVar);
            if (gpVar == null) {
                m6591a("Interstitial pool created at %s.", goVar);
                gpVar = new gp(d, str, i);
                this.f4964a.put(goVar, gpVar);
            }
            gpVar.m6612a(this.f4966c, adRequestParcel);
            gpVar.m6618g();
            m6591a("Inline entry added to the queue at %s.", goVar);
        }
    }

    void m6603c() {
        Throwable e;
        if (this.f4966c != null) {
            go goVar;
            SharedPreferences sharedPreferences = this.f4966c.m6581a().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
            m6604d();
            Map hashMap = new HashMap();
            for (Entry entry : sharedPreferences.getAll().entrySet()) {
                try {
                    if (!((String) entry.getKey()).equals("PoolKeys")) {
                        gr grVar = new gr((String) entry.getValue());
                        goVar = new go(grVar.f4973a, grVar.f4974b, grVar.f4975c);
                        if (!this.f4964a.containsKey(goVar)) {
                            this.f4964a.put(goVar, new gp(grVar.f4973a, grVar.f4974b, grVar.f4975c));
                            hashMap.put(goVar.toString(), goVar);
                            m6591a("Restored interstitial queue for %s.", goVar);
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    C1129c.m6193d("Malformed preferences value for InterstitialAdPool.", e);
                } catch (ClassCastException e3) {
                    e = e3;
                    C1129c.m6193d("Malformed preferences value for InterstitialAdPool.", e);
                }
            }
            for (Object obj : m6592a(sharedPreferences.getString("PoolKeys", StringUtils.EMPTY))) {
                goVar = (go) hashMap.get(obj);
                if (this.f4964a.containsKey(goVar)) {
                    this.f4965b.add(goVar);
                }
            }
        }
    }

    void m6604d() {
        while (this.f4965b.size() > 0) {
            go goVar = (go) this.f4965b.remove();
            gp gpVar = (gp) this.f4964a.get(goVar);
            m6591a("Flushing interstitial queue for %s.", goVar);
            while (gpVar.m6615d() > 0) {
                gpVar.m6610a(null).a.zzbO();
            }
            this.f4964a.remove(goVar);
        }
    }
}
