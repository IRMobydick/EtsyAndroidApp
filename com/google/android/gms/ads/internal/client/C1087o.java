package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.gms.ads.f;
import com.google.android.gms.h;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.o */
public final class C1087o {
    private final f[] f4456a;
    private final String f4457b;

    public C1087o(Context context, AttributeSet attributeSet) {
        Object obj = 1;
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, h.AdsAttrs);
        String string = obtainAttributes.getString(h.AdsAttrs_adSize);
        String string2 = obtainAttributes.getString(h.AdsAttrs_adSizes);
        Object obj2 = !TextUtils.isEmpty(string) ? 1 : null;
        if (TextUtils.isEmpty(string2)) {
            obj = null;
        }
        if (obj2 != null && r1 == null) {
            this.f4456a = C1087o.m5929a(string);
        } else if (obj2 == null && r1 != null) {
            this.f4456a = C1087o.m5929a(string2);
        } else if (obj2 != null) {
            throw new IllegalArgumentException("Either XML attribute \"adSize\" or XML attribute \"supportedAdSizes\" should be specified, but not both.");
        } else {
            throw new IllegalArgumentException("Required XML attribute \"adSize\" was missing.");
        }
        this.f4457b = obtainAttributes.getString(h.AdsAttrs_adUnitId);
        if (TextUtils.isEmpty(this.f4457b)) {
            throw new IllegalArgumentException("Required XML attribute \"adUnitId\" was missing.");
        }
    }

    private static f[] m5929a(String str) {
        String str2;
        String valueOf;
        String[] split = str.split("\\s*,\\s*");
        f[] fVarArr = new f[split.length];
        for (int i = 0; i < split.length; i++) {
            String trim = split[i].trim();
            if (trim.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$")) {
                String[] split2 = trim.split("[xX]");
                split2[0] = split2[0].trim();
                split2[1] = split2[1].trim();
                try {
                    fVarArr[i] = new f("FULL_WIDTH".equals(split2[0]) ? -1 : Integer.parseInt(split2[0]), "AUTO_HEIGHT".equals(split2[1]) ? -2 : Integer.parseInt(split2[1]));
                } catch (NumberFormatException e) {
                    str2 = "Could not parse XML attribute \"adSize\": ";
                    valueOf = String.valueOf(trim);
                    throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            } else if ("BANNER".equals(trim)) {
                fVarArr[i] = f.a;
            } else if ("LARGE_BANNER".equals(trim)) {
                fVarArr[i] = f.c;
            } else if ("FULL_BANNER".equals(trim)) {
                fVarArr[i] = f.b;
            } else if ("LEADERBOARD".equals(trim)) {
                fVarArr[i] = f.d;
            } else if ("MEDIUM_RECTANGLE".equals(trim)) {
                fVarArr[i] = f.e;
            } else if ("SMART_BANNER".equals(trim)) {
                fVarArr[i] = f.g;
            } else if ("WIDE_SKYSCRAPER".equals(trim)) {
                fVarArr[i] = f.f;
            } else if ("FLUID".equals(trim)) {
                fVarArr[i] = f.h;
            } else {
                str2 = "Could not parse XML attribute \"adSize\": ";
                valueOf = String.valueOf(trim);
                throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
        }
        if (fVarArr.length != 0) {
            return fVarArr;
        }
        str2 = "Could not parse XML attribute \"adSize\": ";
        valueOf = String.valueOf(str);
        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    public String m5930a() {
        return this.f4457b;
    }

    public f[] m5931a(boolean z) {
        if (z || this.f4456a.length == 1) {
            return this.f4456a;
        }
        throw new IllegalArgumentException("The adSizes XML attribute is only allowed on PublisherAdViews.");
    }
}
