package com.google.android.gms.internal;

import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@jw
class go {
    private final Object[] f4967a;

    go(AdRequestParcel adRequestParcel, String str, int i) {
        this.f4967a = m6606a(adRequestParcel, str, i);
    }

    private static String m6605a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(new ArrayList(bundle.keySet()));
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            String str2 = obj == null ? "null" : obj instanceof Bundle ? m6605a((Bundle) obj) : obj.toString();
            stringBuilder.append(str2);
        }
        return stringBuilder.toString();
    }

    private static Object[] m6606a(AdRequestParcel adRequestParcel, String str, int i) {
        Set hashSet = new HashSet(Arrays.asList(((String) dz.aj.m6433c()).split(",")));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        if (hashSet.contains("networkType")) {
            arrayList.add(Integer.valueOf(i));
        }
        if (hashSet.contains("birthday")) {
            arrayList.add(Long.valueOf(adRequestParcel.zzuN));
        }
        if (hashSet.contains("extras")) {
            arrayList.add(m6605a(adRequestParcel.extras));
        }
        if (hashSet.contains("gender")) {
            arrayList.add(Integer.valueOf(adRequestParcel.zzuO));
        }
        if (hashSet.contains("keywords")) {
            if (adRequestParcel.zzuP != null) {
                arrayList.add(adRequestParcel.zzuP.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("isTestDevice")) {
            arrayList.add(Boolean.valueOf(adRequestParcel.zzuQ));
        }
        if (hashSet.contains("tagForChildDirectedTreatment")) {
            arrayList.add(Integer.valueOf(adRequestParcel.zzuR));
        }
        if (hashSet.contains("manualImpressionsEnabled")) {
            arrayList.add(Boolean.valueOf(adRequestParcel.zzuS));
        }
        if (hashSet.contains("publisherProvidedId")) {
            arrayList.add(adRequestParcel.zzuT);
        }
        if (hashSet.contains(ResponseConstants.LOCATION)) {
            if (adRequestParcel.zzuV != null) {
                arrayList.add(adRequestParcel.zzuV.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("contentUrl")) {
            arrayList.add(adRequestParcel.zzuW);
        }
        if (hashSet.contains("networkExtras")) {
            arrayList.add(m6605a(adRequestParcel.zzuX));
        }
        if (hashSet.contains("customTargeting")) {
            arrayList.add(m6605a(adRequestParcel.zzuY));
        }
        if (hashSet.contains("categoryExclusions")) {
            if (adRequestParcel.zzuZ != null) {
                arrayList.add(adRequestParcel.zzuZ.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("requestAgent")) {
            arrayList.add(adRequestParcel.zzva);
        }
        if (hashSet.contains("requestPackage")) {
            arrayList.add(adRequestParcel.zzvb);
        }
        return arrayList.toArray();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof go)) {
            return false;
        }
        return Arrays.equals(this.f4967a, ((go) obj).f4967a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f4967a);
    }

    public String toString() {
        String valueOf = String.valueOf(Arrays.toString(this.f4967a));
        return new StringBuilder(String.valueOf(valueOf).length() + 24).append("[InterstitialAdPoolKey ").append(valueOf).append("]").toString();
    }
}
