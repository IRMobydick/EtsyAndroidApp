package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.f.1;
import com.google.android.gms.ads.internal.formats.f.2;
import com.google.android.gms.ads.internal.formats.f.3;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.bu;
import com.google.android.gms.internal.jn;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.no;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

@jw
/* renamed from: com.google.android.gms.ads.internal.formats.f */
public class C1095f implements d {
    private final Object f4493a;
    private final zzq f4494b;
    private final Context f4495c;
    private final JSONObject f4496d;
    private final jn f4497e;
    private final e f4498f;
    private final bu f4499g;
    private final VersionInfoParcel f4500h;
    private boolean f4501i;
    private no f4502j;
    private String f4503k;
    private String f4504l;
    private WeakReference<View> f4505m;

    public C1095f(Context context, zzq com_google_android_gms_ads_internal_zzq, jn jnVar, bu buVar, JSONObject jSONObject, e eVar, VersionInfoParcel versionInfoParcel, String str) {
        this.f4493a = new Object();
        this.f4505m = null;
        this.f4495c = context;
        this.f4494b = com_google_android_gms_ads_internal_zzq;
        this.f4497e = jnVar;
        this.f4499g = buVar;
        this.f4496d = jSONObject;
        this.f4498f = eVar;
        this.f4500h = versionInfoParcel;
        this.f4504l = str;
    }

    public C1094b m5984a(OnClickListener onClickListener) {
        C1093a zzeQ = this.f4498f.zzeQ();
        if (zzeQ == null) {
            return null;
        }
        C1094b c1094b = new C1094b(this.f4495c, zzeQ);
        c1094b.setLayoutParams(new LayoutParams(-1, -1));
        c1094b.m5980a().setOnClickListener(onClickListener);
        c1094b.m5980a().setContentDescription("Ad attribution icon");
        return c1094b;
    }

    public void m5985a() {
        zzaa.zzdc("recordImpression must be called on the main UI thread.");
        m5990a(true);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad", this.f4496d);
            jSONObject.put("ads_id", this.f4504l);
            this.f4497e.m6842a(new 2(this, jSONObject));
        } catch (Throwable e) {
            C1129c.m6189b("Unable to create impression JSON.", e);
        }
        this.f4494b.zza((d) this);
    }

    public void m5986a(MotionEvent motionEvent) {
        this.f4499g.a(motionEvent);
    }

    public void m5987a(View view) {
    }

    public void m5988a(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        zzaa.zzdc("performClick must be called on the main UI thread.");
        for (Entry entry : map.entrySet()) {
            if (view.equals((View) ((WeakReference) entry.getValue()).get())) {
                m5989a((String) entry.getKey(), jSONObject, jSONObject2, jSONObject3);
                return;
            }
        }
        if ("2".equals(this.f4498f.zzeP())) {
            m5989a("2099", jSONObject, jSONObject2, jSONObject3);
        } else if ("1".equals(this.f4498f.zzeP())) {
            m5989a("1099", jSONObject, jSONObject2, jSONObject3);
        }
    }

    public void m5989a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        zzaa.zzdc("performClick must be called on the main UI thread.");
        try {
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("asset", str);
            jSONObject4.put("template", this.f4498f.zzeP());
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("ad", this.f4496d);
            jSONObject5.put("click", jSONObject4);
            jSONObject5.put("has_custom_click_handler", this.f4494b.zzv(this.f4498f.getCustomTemplateId()) != null);
            if (jSONObject != null) {
                jSONObject5.put("view_rectangles", jSONObject);
            }
            if (jSONObject2 != null) {
                jSONObject5.put("click_point", jSONObject2);
            }
            if (jSONObject3 != null) {
                jSONObject5.put("native_view_rectangle", jSONObject3);
            }
            jSONObject5.put("ads_id", this.f4504l);
            this.f4497e.m6842a(new 1(this, jSONObject5));
        } catch (Throwable e) {
            C1129c.m6189b("Unable to create click JSON.", e);
        }
    }

    protected void m5990a(boolean z) {
        this.f4501i = z;
    }

    public void m5991b(View view) {
        synchronized (this.f4493a) {
            if (this.f4501i) {
            } else if (!view.isShown()) {
            } else if (view.getGlobalVisibleRect(new Rect(), null)) {
                m5985a();
            }
        }
    }

    public void m5992c(View view) {
        this.f4505m = new WeakReference(view);
    }

    public no m5993d() {
        this.f4502j = m5996g();
        this.f4502j.m7247b().setVisibility(8);
        this.f4497e.m6842a(new 3(this));
        return this.f4502j;
    }

    public View m5994e() {
        return this.f4505m != null ? (View) this.f4505m.get() : null;
    }

    public Context m5995f() {
        return this.f4495c;
    }

    no m5996g() {
        return C1101o.m6042f().m7277a(this.f4495c, AdSizeParcel.zzk(this.f4495c), false, false, this.f4499g, this.f4500h);
    }
}
