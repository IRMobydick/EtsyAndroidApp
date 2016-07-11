package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.appboy.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.google.android.gms.ads.internal.formats.C1093a;
import com.google.android.gms.ads.internal.formats.C1095f;
import com.google.android.gms.ads.internal.formats.e;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.dynamic.zze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@jw
public class jp implements Callable<lb> {
    private static final long f5190a;
    private final Context f5191b;
    private final mk f5192c;
    private final zzq f5193d;
    private final bu f5194e;
    private final jn f5195f;
    private final Object f5196g;
    private final lc f5197h;
    private boolean f5198i;
    private int f5199j;
    private List<String> f5200k;
    private JSONObject f5201l;

    static {
        f5190a = TimeUnit.SECONDS.toMillis(60);
    }

    public jp(Context context, zzq com_google_android_gms_ads_internal_zzq, mk mkVar, bu buVar, lc lcVar) {
        this.f5196g = new Object();
        this.f5191b = context;
        this.f5193d = com_google_android_gms_ads_internal_zzq;
        this.f5192c = mkVar;
        this.f5197h = lcVar;
        this.f5194e = buVar;
        this.f5195f = m6861a(context, lcVar, com_google_android_gms_ads_internal_zzq, buVar);
        this.f5195f.m6841a();
        this.f5198i = false;
        this.f5199j = -2;
        this.f5200k = null;
    }

    private e m6848a(jq jqVar, JSONObject jSONObject, String str) {
        if (m6870b()) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("tracking_urls_and_actions");
        String[] b = m6860b(jSONObject2, "impression_tracking_urls");
        this.f5200k = b == null ? null : Arrays.asList(b);
        this.f5201l = jSONObject2.optJSONObject("active_view");
        e a = jqVar.a(this, jSONObject);
        if (a == null) {
            C1129c.m6188b("Failed to retrieve ad assets.");
            return null;
        }
        a.zzb(new C1095f(this.f5191b, this.f5193d, this.f5195f, this.f5194e, jSONObject, a, this.f5197h.f5352a.zzsx, str));
        return a;
    }

    private mz<zzc> m6850a(JSONObject jSONObject, boolean z, boolean z2) {
        String string = z ? jSONObject.getString(ResponseConstants.URL) : jSONObject.optString(ResponseConstants.URL);
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        if (!TextUtils.isEmpty(string)) {
            return z2 ? new mw(new zzc(null, Uri.parse(string), optDouble)) : this.f5192c.m7179a(string, new 6(this, z, optDouble, string));
        } else {
            m6868a(0, z);
            return new mw(null);
        }
    }

    private Integer m6851a(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException e) {
            return null;
        }
    }

    private JSONObject m6853a(String str) {
        if (m6870b()) {
            return null;
        }
        mv mvVar = new mv();
        this.f5195f.m6842a(new 1(this, new jr(this), mvVar, str));
        return (JSONObject) mvVar.get(f5190a, TimeUnit.MILLISECONDS);
    }

    private void m6854a(e eVar) {
        if (eVar instanceof zzf) {
            zzf com_google_android_gms_ads_internal_formats_zzf = (zzf) eVar;
            jr jrVar = new jr(this);
            3 3 = new 3(this, com_google_android_gms_ads_internal_formats_zzf);
            jrVar.a = 3;
            this.f5195f.m6842a(new 4(this, 3));
        }
    }

    private void m6856a(zzdr com_google_android_gms_internal_zzdr, String str) {
        try {
            zzdv zzv = this.f5193d.zzv(com_google_android_gms_internal_zzdr.getCustomTemplateId());
            if (zzv != null) {
                zzv.zza(com_google_android_gms_internal_zzdr, str);
            }
        } catch (Throwable e) {
            C1129c.m6193d(new StringBuilder(String.valueOf(str).length() + 40).append("Failed to call onCustomClick for asset ").append(str).append(".").toString(), e);
        }
    }

    private lb m6858b(e eVar) {
        int i;
        synchronized (this.f5196g) {
            i = this.f5199j;
            if (eVar == null && this.f5199j == -2) {
                i = 0;
            }
        }
        return new lb(this.f5197h.f5352a.zzLi, null, this.f5197h.f5353b.zzEF, i, this.f5197h.f5353b.zzEG, this.f5200k, this.f5197h.f5353b.orientation, this.f5197h.f5353b.zzEL, this.f5197h.f5352a.zzLl, false, null, null, null, null, null, 0, this.f5197h.f5355d, this.f5197h.f5353b.zzLO, this.f5197h.f5357f, this.f5197h.f5358g, this.f5197h.f5353b.zzLU, this.f5201l, i != -2 ? null : eVar, null, null, null, this.f5197h.f5353b.zzMi, this.f5197h.f5353b.zzMj, null, this.f5197h.f5353b.zzEI);
    }

    private static List<Drawable> m6859b(List<zzc> list) {
        List<Drawable> arrayList = new ArrayList();
        for (zzc zzeM : list) {
            arrayList.add((Drawable) zze.zzx(zzeM.zzeM()));
        }
        return arrayList;
    }

    private String[] m6860b(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.getString(i);
        }
        return strArr;
    }

    jn m6861a(Context context, lc lcVar, zzq com_google_android_gms_ads_internal_zzq, bu buVar) {
        return new jn(context, lcVar, com_google_android_gms_ads_internal_zzq, buVar);
    }

    protected jq m6862a(JSONObject jSONObject) {
        if (m6870b()) {
            return null;
        }
        String string = jSONObject.getString("template_id");
        boolean z = this.f5197h.f5352a.zzsP != null ? this.f5197h.f5352a.zzsP.zzBl : false;
        boolean z2 = this.f5197h.f5352a.zzsP != null ? this.f5197h.f5352a.zzsP.zzBn : false;
        if ("2".equals(string)) {
            return new js(z, z2);
        }
        if ("1".equals(string)) {
            return new jt(z, z2);
        }
        if ("3".equals(string)) {
            String string2 = jSONObject.getString("custom_template_id");
            mv mvVar = new mv();
            lt.f5423a.post(new 2(this, mvVar, string2));
            if (mvVar.get(f5190a, TimeUnit.MILLISECONDS) != null) {
                return new ju(z);
            }
            string2 = "No handler for custom template: ";
            String valueOf = String.valueOf(jSONObject.getString("custom_template_id"));
            C1129c.m6188b(valueOf.length() != 0 ? string2.concat(valueOf) : new String(string2));
        } else {
            m6867a(0);
        }
        return null;
    }

    public lb m6863a() {
        try {
            this.f5195f.m6843b();
            String uuid = UUID.randomUUID().toString();
            JSONObject a = m6853a(uuid);
            e a2 = m6848a(m6862a(a), a, uuid);
            m6854a(a2);
            return m6858b(a2);
        } catch (CancellationException e) {
            if (!this.f5198i) {
                m6867a(0);
            }
            return m6858b(null);
        } catch (ExecutionException e2) {
            if (this.f5198i) {
                m6867a(0);
            }
            return m6858b(null);
        } catch (InterruptedException e3) {
            if (this.f5198i) {
                m6867a(0);
            }
            return m6858b(null);
        } catch (Throwable e4) {
            C1129c.m6193d("Malformed native JSON response.", e4);
            if (this.f5198i) {
                m6867a(0);
            }
            return m6858b(null);
        } catch (Throwable e42) {
            C1129c.m6193d("Timeout when loading native ad.", e42);
            if (this.f5198i) {
                m6867a(0);
            }
            return m6858b(null);
        }
    }

    public mz<zzc> m6864a(JSONObject jSONObject, String str, boolean z, boolean z2) {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return m6850a(jSONObject2, z, z2);
    }

    public List<mz<zzc>> m6865a(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) {
        JSONArray jSONArray = z ? jSONObject.getJSONArray(str) : jSONObject.optJSONArray(str);
        List<mz<zzc>> arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            m6868a(0, z);
            return arrayList;
        }
        int length = z3 ? jSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(m6850a(jSONObject2, z, z2));
        }
        return arrayList;
    }

    public Future<zzc> m6866a(JSONObject jSONObject, String str, boolean z) {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return m6850a(jSONObject2, optBoolean, z);
    }

    public void m6867a(int i) {
        synchronized (this.f5196g) {
            this.f5198i = true;
            this.f5199j = i;
        }
    }

    public void m6868a(int i, boolean z) {
        if (z) {
            m6867a(i);
        }
    }

    public mz<C1093a> m6869b(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return new mw(null);
        }
        String optString = optJSONObject.optString(FindsModule.FIELD_TEXT);
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer a = m6851a(optJSONObject, "text_color");
        Integer a2 = m6851a(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS);
        int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        List arrayList = new ArrayList();
        if (optJSONObject.optJSONArray(FindsModule.FIELD_IMAGES) != null) {
            arrayList = m6865a(optJSONObject, FindsModule.FIELD_IMAGES, false, false, true);
        } else {
            arrayList.add(m6864a(optJSONObject, ResponseConstants.IMAGE, false, false));
        }
        return mx.m7203a(mx.m7204a(arrayList), new 5(this, optString, a2, a, optInt, optInt3, optInt2));
    }

    public boolean m6870b() {
        boolean z;
        synchronized (this.f5196g) {
            z = this.f5198i;
        }
        return z;
    }

    public /* synthetic */ Object call() {
        return m6863a();
    }
}
