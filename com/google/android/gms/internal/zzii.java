package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.appboy.Constants;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.zzk.zza;
import com.google.android.gms.ads.internal.request.zzl;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@jw
public final class zzii extends zza {
    private static zzii zzMP;
    private static final Object zzrs;
    private final Context mContext;
    private final jx zzMQ;
    private final ds zzMR;
    private final gx zzMS;

    static {
        zzrs = new Object();
    }

    zzii(Context context, ds dsVar, jx jxVar) {
        this.mContext = context;
        this.zzMQ = jxVar;
        this.zzMR = dsVar;
        this.zzMS = new gx(context.getApplicationContext() != null ? context.getApplicationContext() : context, new VersionInfoParcel(9080278, 9080278, true), dsVar.m6422a(), new 4(this), new gz());
    }

    private static AdResponseParcel zza(Context context, gx gxVar, ds dsVar, jx jxVar, AdRequestInfoParcel adRequestInfoParcel) {
        Bundle bundle;
        Future future;
        Throwable e;
        C1129c.m6185a("Starting ad request from service using: AFMA_getAd");
        dz.m6444a(context);
        ei eiVar = new ei(((Boolean) dz.f4808H.m6433c()).booleanValue(), "load_ad", adRequestInfoParcel.zzsB.zzvs);
        if (adRequestInfoParcel.versionCode > 10 && adRequestInfoParcel.zzLA != -1) {
            eiVar.m6479a(eiVar.m6474a(adRequestInfoParcel.zzLA), "cts");
        }
        eg a = eiVar.m6473a();
        Bundle bundle2 = (adRequestInfoParcel.versionCode < 4 || adRequestInfoParcel.zzLp == null) ? null : adRequestInfoParcel.zzLp;
        if (!((Boolean) dz.f4817Q.m6433c()).booleanValue() || jxVar.f5219i == null) {
            bundle = bundle2;
            future = null;
        } else {
            if (bundle2 == null && ((Boolean) dz.f4818R.m6433c()).booleanValue()) {
                lo.m7056e("contentInfo is not present, but we'll still launch the app index task");
                bundle2 = new Bundle();
            }
            if (bundle2 != null) {
                bundle = bundle2;
                future = ls.m7071a(new 1(jxVar, context, adRequestInfoParcel, bundle2));
            } else {
                bundle = bundle2;
                future = null;
            }
        }
        mw mwVar = new mw(null);
        Bundle bundle3 = adRequestInfoParcel.zzLi.extras;
        Object obj = (bundle3 == null || bundle3.getString("_ad") == null) ? null : 1;
        mz a2 = (adRequestInfoParcel.zzLH && obj == null) ? jxVar.f5214d.a(adRequestInfoParcel.applicationInfo) : mwVar;
        kd a3 = C1101o.m6047k().m6958a(context);
        if (a3.f5285m == -1) {
            C1129c.m6185a("Device is offline.");
            return new AdResponseParcel(2);
        }
        String uuid = adRequestInfoParcel.versionCode >= 7 ? adRequestInfoParcel.zzLx : UUID.randomUUID().toString();
        jz jzVar = new jz(uuid, adRequestInfoParcel.applicationInfo.packageName);
        if (adRequestInfoParcel.zzLi.extras != null) {
            String string = adRequestInfoParcel.zzLi.extras.getString("_ad");
            if (string != null) {
                return jy.m6890a(context, adRequestInfoParcel, string);
            }
        }
        String a4 = jxVar.f5215e.m6797a(context, adRequestInfoParcel.zzsv, adRequestInfoParcel.zzLj.packageName);
        List a5 = jxVar.f5212b.a(adRequestInfoParcel);
        String a6 = jxVar.f5216f.a(adRequestInfoParcel);
        kk a7 = jxVar.f5217g.a(context);
        if (future != null) {
            try {
                lo.m7056e("Waiting for app index fetching task.");
                future.get(((Long) dz.f4819S.m6433c()).longValue(), TimeUnit.MILLISECONDS);
                lo.m7056e("App index fetching task completed.");
            } catch (ExecutionException e2) {
                e = e2;
                C1129c.m6193d("Failed to fetch app index signal", e);
            } catch (InterruptedException e3) {
                e = e3;
                C1129c.m6193d("Failed to fetch app index signal", e);
            } catch (TimeoutException e4) {
                C1129c.m6185a("Timed out waiting for app index fetching task");
            }
        }
        String a8 = jxVar.f5211a.a(adRequestInfoParcel.zzLj.packageName);
        JSONObject a9 = jy.m6896a(context, adRequestInfoParcel, a3, a7, zzb(a2), dsVar, a4, a6, a5, bundle, a8);
        if (adRequestInfoParcel.versionCode < 7) {
            try {
                a9.put("request_id", uuid);
            } catch (JSONException e5) {
            }
        }
        if (a9 != null) {
            try {
                a9.put("prefetch_mode", ResponseConstants.URL);
            } catch (Throwable e6) {
                C1129c.m6193d("Failed putting prefetch parameters to ad request.", e6);
            }
        }
        if (a9 == null) {
            return new AdResponseParcel(0);
        }
        String jSONObject = a9.toString();
        eiVar.m6479a(a, "arc");
        lt.f5423a.post(new 2(gxVar, jzVar, eiVar, eiVar.m6473a(), jSONObject));
        AdResponseParcel adResponseParcel;
        try {
            kc kcVar = (kc) jzVar.m6909b().get(10, TimeUnit.SECONDS);
            if (kcVar == null) {
                adResponseParcel = new AdResponseParcel(0);
                return adResponseParcel;
            } else if (kcVar.m6948a() != -2) {
                adResponseParcel = new AdResponseParcel(kcVar.m6948a());
                lt.f5423a.post(new 3(jxVar, context, jzVar, adRequestInfoParcel));
                return adResponseParcel;
            } else {
                if (eiVar.m6483e() != null) {
                    eiVar.m6479a(eiVar.m6483e(), "rur");
                }
                adResponseParcel = null;
                if (!TextUtils.isEmpty(kcVar.m6957i())) {
                    adResponseParcel = jy.m6890a(context, adRequestInfoParcel, kcVar.m6957i());
                }
                if (adResponseParcel == null && !TextUtils.isEmpty(kcVar.m6952d())) {
                    adResponseParcel = zza(adRequestInfoParcel, context, adRequestInfoParcel.zzsx.afmaVersion, kcVar.m6952d(), a8, kcVar.m6956h() ? a4 : null, kcVar, eiVar, jxVar);
                }
                if (adResponseParcel == null) {
                    adResponseParcel = new AdResponseParcel(0);
                }
                if (adResponseParcel.zzMb == 1) {
                    jxVar.f5215e.m6798a(context, adRequestInfoParcel.zzLj.packageName);
                }
                eiVar.m6479a(a, "tts");
                adResponseParcel.zzMd = eiVar.m6481c();
                lt.f5423a.post(new 3(jxVar, context, jzVar, adRequestInfoParcel));
                return adResponseParcel;
            }
        } catch (Exception e7) {
            adResponseParcel = new AdResponseParcel(0);
            return adResponseParcel;
        } finally {
            lt.f5423a.post(new 3(jxVar, context, jzVar, adRequestInfoParcel));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.ads.internal.request.AdResponseParcel zza(com.google.android.gms.ads.internal.request.AdRequestInfoParcel r13, android.content.Context r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, com.google.android.gms.internal.kc r19, com.google.android.gms.internal.ei r20, com.google.android.gms.internal.jx r21) {
        /*
        if (r20 == 0) goto L_0x0108;
    L_0x0002:
        r2 = r20.m6473a();
        r3 = r2;
    L_0x0007:
        r8 = new com.google.android.gms.internal.ka;	 Catch:{ IOException -> 0x0113 }
        r8.<init>(r13);	 Catch:{ IOException -> 0x0113 }
        r4 = "AdRequestServiceImpl: Sending request: ";
        r2 = java.lang.String.valueOf(r16);	 Catch:{ IOException -> 0x0113 }
        r5 = r2.length();	 Catch:{ IOException -> 0x0113 }
        if (r5 == 0) goto L_0x010c;
    L_0x0018:
        r2 = r4.concat(r2);	 Catch:{ IOException -> 0x0113 }
    L_0x001c:
        com.google.android.gms.ads.internal.util.client.C1129c.m6185a(r2);	 Catch:{ IOException -> 0x0113 }
        r4 = new java.net.URL;	 Catch:{ IOException -> 0x0113 }
        r0 = r16;
        r4.<init>(r0);	 Catch:{ IOException -> 0x0113 }
        r2 = 0;
        r5 = com.google.android.gms.ads.internal.C1101o.m6045i();	 Catch:{ IOException -> 0x0113 }
        r10 = r5.elapsedRealtime();	 Catch:{ IOException -> 0x0113 }
        r6 = r2;
        r7 = r4;
    L_0x0031:
        if (r21 == 0) goto L_0x003a;
    L_0x0033:
        r0 = r21;
        r2 = r0.f5218h;	 Catch:{ IOException -> 0x0113 }
        r2.a();	 Catch:{ IOException -> 0x0113 }
    L_0x003a:
        r2 = r7.openConnection();	 Catch:{ IOException -> 0x0113 }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x0113 }
        r4 = com.google.android.gms.ads.internal.C1101o.m6041e();	 Catch:{ all -> 0x0139 }
        r5 = 0;
        r4.m7106a(r14, r15, r5, r2);	 Catch:{ all -> 0x0139 }
        r4 = android.text.TextUtils.isEmpty(r17);	 Catch:{ all -> 0x0139 }
        if (r4 != 0) goto L_0x005b;
    L_0x004e:
        r4 = r19.m6954f();	 Catch:{ all -> 0x0139 }
        if (r4 == 0) goto L_0x005b;
    L_0x0054:
        r4 = "x-afma-drt-cookie";
        r0 = r17;
        r2.addRequestProperty(r4, r0);	 Catch:{ all -> 0x0139 }
    L_0x005b:
        r4 = android.text.TextUtils.isEmpty(r18);	 Catch:{ all -> 0x0139 }
        if (r4 != 0) goto L_0x007a;
    L_0x0061:
        r4 = "Bearer ";
        r5 = java.lang.String.valueOf(r4);	 Catch:{ all -> 0x0139 }
        r4 = java.lang.String.valueOf(r18);	 Catch:{ all -> 0x0139 }
        r9 = r4.length();	 Catch:{ all -> 0x0139 }
        if (r9 == 0) goto L_0x0132;
    L_0x0071:
        r4 = r5.concat(r4);	 Catch:{ all -> 0x0139 }
    L_0x0075:
        r5 = "Authorization";
        r2.addRequestProperty(r5, r4);	 Catch:{ all -> 0x0139 }
    L_0x007a:
        r4 = r13.zzLI;	 Catch:{ all -> 0x0139 }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0139 }
        if (r5 != 0) goto L_0x008c;
    L_0x0082:
        r5 = "Sending webview cookie in ad request header.";
        com.google.android.gms.ads.internal.util.client.C1129c.m6185a(r5);	 Catch:{ all -> 0x0139 }
        r5 = "Cookie";
        r2.addRequestProperty(r5, r4);	 Catch:{ all -> 0x0139 }
    L_0x008c:
        if (r19 == 0) goto L_0x00b8;
    L_0x008e:
        r4 = r19.m6951c();	 Catch:{ all -> 0x0139 }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0139 }
        if (r4 != 0) goto L_0x00b8;
    L_0x0098:
        r4 = 1;
        r2.setDoOutput(r4);	 Catch:{ all -> 0x0139 }
        r4 = r19.m6951c();	 Catch:{ all -> 0x0139 }
        r9 = r4.getBytes();	 Catch:{ all -> 0x0139 }
        r4 = r9.length;	 Catch:{ all -> 0x0139 }
        r2.setFixedLengthStreamingMode(r4);	 Catch:{ all -> 0x0139 }
        r5 = 0;
        r4 = new java.io.BufferedOutputStream;	 Catch:{ all -> 0x0147 }
        r12 = r2.getOutputStream();	 Catch:{ all -> 0x0147 }
        r4.<init>(r12);	 Catch:{ all -> 0x0147 }
        r4.write(r9);	 Catch:{ all -> 0x01f6 }
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x0139 }
    L_0x00b8:
        r9 = r2.getResponseCode();	 Catch:{ all -> 0x0139 }
        r12 = r2.getHeaderFields();	 Catch:{ all -> 0x0139 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r9 < r4) goto L_0x0153;
    L_0x00c4:
        r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 >= r4) goto L_0x0153;
    L_0x00c8:
        r6 = r7.toString();	 Catch:{ all -> 0x0139 }
        r5 = 0;
        r4 = new java.io.InputStreamReader;	 Catch:{ all -> 0x014d }
        r7 = r2.getInputStream();	 Catch:{ all -> 0x014d }
        r4.<init>(r7);	 Catch:{ all -> 0x014d }
        r5 = com.google.android.gms.ads.internal.C1101o.m6041e();	 Catch:{ all -> 0x01f3 }
        r5 = r5.m7094a(r4);	 Catch:{ all -> 0x01f3 }
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x0139 }
        zza(r6, r12, r5, r9);	 Catch:{ all -> 0x0139 }
        r8.m6944a(r6, r12, r5);	 Catch:{ all -> 0x0139 }
        if (r20 == 0) goto L_0x00f6;
    L_0x00e9:
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ all -> 0x0139 }
        r5 = 0;
        r6 = "ufe";
        r4[r5] = r6;	 Catch:{ all -> 0x0139 }
        r0 = r20;
        r0.m6479a(r3, r4);	 Catch:{ all -> 0x0139 }
    L_0x00f6:
        r3 = r8.m6943a(r10);	 Catch:{ all -> 0x0139 }
        r2.disconnect();	 Catch:{ IOException -> 0x0113 }
        if (r21 == 0) goto L_0x0106;
    L_0x00ff:
        r0 = r21;
        r2 = r0.f5218h;	 Catch:{ IOException -> 0x0113 }
        r2.b();	 Catch:{ IOException -> 0x0113 }
    L_0x0106:
        r2 = r3;
    L_0x0107:
        return r2;
    L_0x0108:
        r2 = 0;
        r3 = r2;
        goto L_0x0007;
    L_0x010c:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x0113 }
        r2.<init>(r4);	 Catch:{ IOException -> 0x0113 }
        goto L_0x001c;
    L_0x0113:
        r2 = move-exception;
        r3 = "Error while connecting to ad server: ";
        r2 = r2.getMessage();
        r2 = java.lang.String.valueOf(r2);
        r4 = r2.length();
        if (r4 == 0) goto L_0x01ec;
    L_0x0124:
        r2 = r3.concat(r2);
    L_0x0128:
        com.google.android.gms.ads.internal.util.client.C1129c.m6192d(r2);
        r2 = new com.google.android.gms.ads.internal.request.AdResponseParcel;
        r3 = 2;
        r2.<init>(r3);
        goto L_0x0107;
    L_0x0132:
        r4 = new java.lang.String;	 Catch:{ all -> 0x0139 }
        r4.<init>(r5);	 Catch:{ all -> 0x0139 }
        goto L_0x0075;
    L_0x0139:
        r3 = move-exception;
        r2.disconnect();	 Catch:{ IOException -> 0x0113 }
        if (r21 == 0) goto L_0x0146;
    L_0x013f:
        r0 = r21;
        r2 = r0.f5218h;	 Catch:{ IOException -> 0x0113 }
        r2.b();	 Catch:{ IOException -> 0x0113 }
    L_0x0146:
        throw r3;	 Catch:{ IOException -> 0x0113 }
    L_0x0147:
        r3 = move-exception;
        r4 = r5;
    L_0x0149:
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x0139 }
        throw r3;	 Catch:{ all -> 0x0139 }
    L_0x014d:
        r3 = move-exception;
        r4 = r5;
    L_0x014f:
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x0139 }
        throw r3;	 Catch:{ all -> 0x0139 }
    L_0x0153:
        r4 = r7.toString();	 Catch:{ all -> 0x0139 }
        r5 = 0;
        zza(r4, r12, r5, r9);	 Catch:{ all -> 0x0139 }
        r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 < r4) goto L_0x01ac;
    L_0x015f:
        r4 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r9 >= r4) goto L_0x01ac;
    L_0x0163:
        r4 = "Location";
        r4 = r2.getHeaderField(r4);	 Catch:{ all -> 0x0139 }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0139 }
        if (r5 == 0) goto L_0x0188;
    L_0x016f:
        r3 = "No location header to follow redirect.";
        com.google.android.gms.ads.internal.util.client.C1129c.m6192d(r3);	 Catch:{ all -> 0x0139 }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x0139 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0139 }
        r2.disconnect();	 Catch:{ IOException -> 0x0113 }
        if (r21 == 0) goto L_0x0186;
    L_0x017f:
        r0 = r21;
        r2 = r0.f5218h;	 Catch:{ IOException -> 0x0113 }
        r2.b();	 Catch:{ IOException -> 0x0113 }
    L_0x0186:
        r2 = r3;
        goto L_0x0107;
    L_0x0188:
        r5 = new java.net.URL;	 Catch:{ all -> 0x0139 }
        r5.<init>(r4);	 Catch:{ all -> 0x0139 }
        r4 = r6 + 1;
        r6 = 5;
        if (r4 <= r6) goto L_0x01d9;
    L_0x0192:
        r3 = "Too many redirects.";
        com.google.android.gms.ads.internal.util.client.C1129c.m6192d(r3);	 Catch:{ all -> 0x0139 }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x0139 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0139 }
        r2.disconnect();	 Catch:{ IOException -> 0x0113 }
        if (r21 == 0) goto L_0x01a9;
    L_0x01a2:
        r0 = r21;
        r2 = r0.f5218h;	 Catch:{ IOException -> 0x0113 }
        r2.b();	 Catch:{ IOException -> 0x0113 }
    L_0x01a9:
        r2 = r3;
        goto L_0x0107;
    L_0x01ac:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0139 }
        r4 = 46;
        r3.<init>(r4);	 Catch:{ all -> 0x0139 }
        r4 = "Received error HTTP response code: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0139 }
        r3 = r3.append(r9);	 Catch:{ all -> 0x0139 }
        r3 = r3.toString();	 Catch:{ all -> 0x0139 }
        com.google.android.gms.ads.internal.util.client.C1129c.m6192d(r3);	 Catch:{ all -> 0x0139 }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x0139 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0139 }
        r2.disconnect();	 Catch:{ IOException -> 0x0113 }
        if (r21 == 0) goto L_0x01d6;
    L_0x01cf:
        r0 = r21;
        r2 = r0.f5218h;	 Catch:{ IOException -> 0x0113 }
        r2.b();	 Catch:{ IOException -> 0x0113 }
    L_0x01d6:
        r2 = r3;
        goto L_0x0107;
    L_0x01d9:
        r8.m6945a(r12);	 Catch:{ all -> 0x0139 }
        r2.disconnect();	 Catch:{ IOException -> 0x0113 }
        if (r21 == 0) goto L_0x01e8;
    L_0x01e1:
        r0 = r21;
        r2 = r0.f5218h;	 Catch:{ IOException -> 0x0113 }
        r2.b();	 Catch:{ IOException -> 0x0113 }
    L_0x01e8:
        r6 = r4;
        r7 = r5;
        goto L_0x0031;
    L_0x01ec:
        r2 = new java.lang.String;
        r2.<init>(r3);
        goto L_0x0128;
    L_0x01f3:
        r3 = move-exception;
        goto L_0x014f;
    L_0x01f6:
        r3 = move-exception;
        goto L_0x0149;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzii.zza(com.google.android.gms.ads.internal.request.AdRequestInfoParcel, android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.kc, com.google.android.gms.internal.ei, com.google.android.gms.internal.jx):com.google.android.gms.ads.internal.request.AdResponseParcel");
    }

    public static zzii zza(Context context, ds dsVar, jx jxVar) {
        zzii com_google_android_gms_internal_zzii;
        synchronized (zzrs) {
            if (zzMP == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzMP = new zzii(context, dsVar, jxVar);
            }
            com_google_android_gms_internal_zzii = zzMP;
        }
        return com_google_android_gms_internal_zzii;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (C1129c.m6187a(2)) {
            lo.m7056e(new StringBuilder(String.valueOf(str).length() + 39).append("Http Response: {\n  URL:\n    ").append(str).append("\n  Headers:").toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    String str32;
                    lo.m7056e(new StringBuilder(String.valueOf(str32).length() + 5).append("    ").append(str32).append(Draft.IMAGE_DELIMITER).toString());
                    for (String str322 : (List) map.get(str322)) {
                        String str4 = "      ";
                        str322 = String.valueOf(str322);
                        lo.m7056e(str322.length() != 0 ? str4.concat(str322) : new String(str4));
                    }
                }
            }
            lo.m7056e("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS) {
                    lo.m7056e(str2.substring(i2, Math.min(str2.length(), i2 + Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS)));
                }
            } else {
                lo.m7056e("    null");
            }
            lo.m7056e("  Response Code:\n    " + i + "\n}");
        }
    }

    private static Location zzb(mz<Location> mzVar) {
        try {
            return (Location) mzVar.get(((Long) dz.bo.m6433c()).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            C1129c.m6193d("Exception caught while getting location", e);
            return null;
        }
    }

    public void zza(AdRequestInfoParcel adRequestInfoParcel, zzl com_google_android_gms_ads_internal_request_zzl) {
        C1101o.m6044h().m7015a(this.mContext, adRequestInfoParcel.zzsx);
        ls.m7070a(new 5(this, adRequestInfoParcel, com_google_android_gms_ads_internal_request_zzl));
    }

    public AdResponseParcel zzd(AdRequestInfoParcel adRequestInfoParcel) {
        return zza(this.mContext, this.zzMS, this.zzMR, this.zzMQ, adRequestInfoParcel);
    }
}
