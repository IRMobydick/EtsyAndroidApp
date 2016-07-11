package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.m.1;
import com.google.android.gms.ads.internal.request.m.2;
import com.google.android.gms.ads.internal.request.m.3;
import com.google.android.gms.ads.internal.util.client.C1128a;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.ds;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.fk;
import com.google.android.gms.internal.fl;
import com.google.android.gms.internal.ft;
import com.google.android.gms.internal.gs;
import com.google.android.gms.internal.gx;
import com.google.android.gms.internal.ha;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.ln;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@jw
/* renamed from: com.google.android.gms.ads.internal.request.m */
public class C1124m extends ln {
    static final long f4642a;
    static boolean f4643b;
    private static final Object f4644c;
    private static gx f4645d;
    private static fl f4646e;
    private static ft f4647f;
    private static fk f4648g;
    private final c f4649h;
    private final C1117a f4650i;
    private final Object f4651j;
    private final Context f4652k;
    private ha f4653l;

    static {
        f4642a = TimeUnit.SECONDS.toMillis(10);
        f4644c = new Object();
        f4643b = false;
        f4645d = null;
        f4646e = null;
        f4647f = null;
        f4648g = null;
    }

    public C1124m(Context context, C1117a c1117a, c cVar) {
        super(true);
        this.f4651j = new Object();
        this.f4649h = cVar;
        this.f4652k = context;
        this.f4650i = c1117a;
        synchronized (f4644c) {
            if (!f4643b) {
                f4647f = new ft();
                f4646e = new fl(context.getApplicationContext(), c1117a.f4613j);
                f4648g = new p();
                f4645d = new gx(this.f4652k.getApplicationContext(), this.f4650i.f4613j, (String) dz.f4828b.m6433c(), new o(), new n());
                f4643b = true;
            }
        }
    }

    private AdResponseParcel m6136a(AdRequestInfoParcel adRequestInfoParcel) {
        String c = C1101o.m6041e().m7125c();
        JSONObject a = m6140a(adRequestInfoParcel, c);
        if (a == null) {
            return new AdResponseParcel(0);
        }
        long elapsedRealtime = C1101o.m6045i().elapsedRealtime();
        Future a2 = f4647f.m6540a(c);
        C1128a.f4666a.post(new 2(this, a, c));
        try {
            JSONObject jSONObject = (JSONObject) a2.get(f4642a - (C1101o.m6045i().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new AdResponseParcel(-1);
            }
            AdResponseParcel a3 = jy.m6890a(this.f4652k, adRequestInfoParcel, jSONObject.toString());
            return (a3.errorCode == -3 || !TextUtils.isEmpty(a3.body)) ? a3 : new AdResponseParcel(3);
        } catch (CancellationException e) {
            return new AdResponseParcel(-1);
        } catch (InterruptedException e2) {
            return new AdResponseParcel(-1);
        } catch (TimeoutException e3) {
            return new AdResponseParcel(2);
        } catch (ExecutionException e4) {
            return new AdResponseParcel(0);
        }
    }

    private JSONObject m6140a(AdRequestInfoParcel adRequestInfoParcel, String str) {
        Info advertisingIdInfo;
        Throwable e;
        JSONObject jSONObject = null;
        Bundle bundle = adRequestInfoParcel.zzLi.extras.getBundle("sdk_less_server_data");
        String string = adRequestInfoParcel.zzLi.extras.getString("sdk_less_network_id");
        if (bundle != null) {
            JSONObject a = jy.m6896a(this.f4652k, adRequestInfoParcel, C1101o.m6047k().m6958a(this.f4652k), jSONObject, jSONObject, new ds((String) dz.f4828b.m6433c()), jSONObject, jSONObject, new ArrayList(), jSONObject, jSONObject);
            if (a != null) {
                Map hashMap;
                try {
                    advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.f4652k);
                } catch (IOException e2) {
                    e = e2;
                    C1129c.m6193d("Cannot get advertising id info", e);
                    advertisingIdInfo = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", a);
                    hashMap.put(ActivityFeedEntity.DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        hashMap.put(ResponseConstants.LAT, Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = C1101o.m6041e().m7098a(hashMap);
                    return jSONObject;
                } catch (IllegalStateException e3) {
                    e = e3;
                    C1129c.m6193d("Cannot get advertising id info", e);
                    advertisingIdInfo = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", a);
                    hashMap.put(ActivityFeedEntity.DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put(ResponseConstants.LAT, Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = C1101o.m6041e().m7098a(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesNotAvailableException e4) {
                    e = e4;
                    C1129c.m6193d("Cannot get advertising id info", e);
                    advertisingIdInfo = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", a);
                    hashMap.put(ActivityFeedEntity.DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put(ResponseConstants.LAT, Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = C1101o.m6041e().m7098a(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesRepairableException e5) {
                    e = e5;
                    C1129c.m6193d("Cannot get advertising id info", e);
                    advertisingIdInfo = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", a);
                    hashMap.put(ActivityFeedEntity.DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put(ResponseConstants.LAT, Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = C1101o.m6041e().m7098a(hashMap);
                    return jSONObject;
                }
                hashMap = new HashMap();
                hashMap.put("request_id", str);
                hashMap.put("network_id", string);
                hashMap.put("request_param", a);
                hashMap.put(ActivityFeedEntity.DATA, bundle);
                if (advertisingIdInfo != null) {
                    hashMap.put("adid", advertisingIdInfo.getId());
                    if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                    }
                    hashMap.put(ResponseConstants.LAT, Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                }
                try {
                    jSONObject = C1101o.m6041e().m7098a(hashMap);
                } catch (JSONException e6) {
                }
            }
        }
        return jSONObject;
    }

    protected static void m6141a(gs gsVar) {
        gsVar.a("/loadAd", f4647f);
        gsVar.a("/fetchHttpRequest", f4646e);
        gsVar.a("/invalidRequest", f4648g);
    }

    protected static void m6144b(gs gsVar) {
        gsVar.b("/loadAd", f4647f);
        gsVar.b("/fetchHttpRequest", f4646e);
        gsVar.b("/invalidRequest", f4648g);
    }

    public void onStop() {
        synchronized (this.f4651j) {
            C1128a.f4666a.post(new 3(this));
        }
    }

    public void zzbQ() {
        C1129c.m6185a("SdkLessAdLoaderBackgroundTask started.");
        AdRequestInfoParcel adRequestInfoParcel = new AdRequestInfoParcel(this.f4650i, null, -1);
        AdResponseParcel a = m6136a(adRequestInfoParcel);
        AdSizeParcel adSizeParcel = null;
        C1128a.f4666a.post(new 1(this, new lc(adRequestInfoParcel, a, null, adSizeParcel, a.errorCode, C1101o.m6045i().elapsedRealtime(), a.zzLT, null)));
    }
}
