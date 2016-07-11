package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.appboy.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.overlay.zzk;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Map;
import java.util.WeakHashMap;
import org.json.JSONObject;

@jw
public final class fz implements fk {
    private final Map<no, Integer> f4940a;
    private boolean f4941b;

    public fz() {
        this.f4940a = new WeakHashMap();
    }

    private static int m6554a(Context context, Map<String, String> map, String str, int i) {
        String str2 = (String) map.get(str);
        if (str2 != null) {
            try {
                i = C1089r.m5951a().m6166a(context, Integer.parseInt(str2));
            } catch (NumberFormatException e) {
                C1129c.m6192d(new StringBuilder((String.valueOf(str).length() + 34) + String.valueOf(str2).length()).append("Could not parse ").append(str).append(" in a video GMSG: ").append(str2).toString());
            }
        }
        return i;
    }

    public void m6555a(no noVar, Map<String, String> map) {
        String str = (String) map.get("action");
        if (str == null) {
            C1129c.m6192d("Action missing from video GMSG.");
            return;
        }
        if (C1129c.m6187a(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String valueOf = String.valueOf(jSONObject.toString());
            C1129c.m6185a(new StringBuilder((String.valueOf(str).length() + 13) + String.valueOf(valueOf).length()).append("Video GMSG: ").append(str).append(" ").append(valueOf).toString());
        }
        if ("background".equals(str)) {
            valueOf = (String) map.get(ResponseConstants.COLOR);
            if (TextUtils.isEmpty(valueOf)) {
                C1129c.m6192d("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                int parseColor = Color.parseColor(valueOf);
                nl w = noVar.m7273w();
                if (w != null) {
                    zzk a = w.m7227a();
                    if (a != null) {
                        a.setBackgroundColor(parseColor);
                        return;
                    }
                }
                this.f4940a.put(noVar, Integer.valueOf(parseColor));
                return;
            } catch (IllegalArgumentException e) {
                C1129c.m6192d("Invalid color parameter in video GMSG.");
                return;
            }
        }
        nl w2 = noVar.m7273w();
        if (w2 == null) {
            C1129c.m6192d("Could not get underlay container for a video GMSG.");
            return;
        }
        boolean equals = "new".equals(str);
        boolean equals2 = "position".equals(str);
        int a2;
        int a3;
        if (equals || equals2) {
            int parseInt;
            Context context = noVar.getContext();
            int a4 = m6554a(context, map, EtsyDialogFragment.OPT_X_BUTTON, 0);
            a2 = m6554a(context, map, "y", 0);
            a3 = m6554a(context, map, "w", -1);
            int a5 = m6554a(context, map, "h", -1);
            try {
                parseInt = Integer.parseInt((String) map.get("player"));
            } catch (NumberFormatException e2) {
                parseInt = 0;
            }
            boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
            if (equals && w2.m7227a() == null) {
                w2.m7229a(a4, a2, a3, a5, parseInt, parseBoolean);
                if (this.f4940a.containsKey(noVar)) {
                    w2.m7227a().setBackgroundColor(((Integer) this.f4940a.get(noVar)).intValue());
                    return;
                }
                return;
            }
            w2.m7228a(a4, a2, a3, a5);
            return;
        }
        zzk a6 = w2.m7227a();
        if (a6 == null) {
            zzk.zzh(noVar);
        } else if ("click".equals(str)) {
            r0 = noVar.getContext();
            a2 = m6554a(r0, map, EtsyDialogFragment.OPT_X_BUTTON, 0);
            a3 = m6554a(r0, map, "y", 0);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) a2, (float) a3, 0);
            a6.zzd(obtain);
            obtain.recycle();
        } else if ("currentTime".equals(str)) {
            valueOf = (String) map.get(Constants.APPBOY_LOCATION_TIME_INTERVAL_KEY);
            if (valueOf == null) {
                C1129c.m6192d("Time parameter missing from currentTime video GMSG.");
                return;
            }
            try {
                a6.seekTo((int) (Float.parseFloat(valueOf) * 1000.0f));
            } catch (NumberFormatException e3) {
                str = "Could not parse time parameter from currentTime video GMSG: ";
                valueOf = String.valueOf(valueOf);
                C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if ("hide".equals(str)) {
            a6.setVisibility(4);
        } else if ("load".equals(str)) {
            a6.zzfp();
        } else if ("mimetype".equals(str)) {
            a6.setMimeType((String) map.get("mimetype"));
        } else if ("muted".equals(str)) {
            if (Boolean.parseBoolean((String) map.get("muted"))) {
                a6.zzgi();
            } else {
                a6.zzgj();
            }
        } else if ("pause".equals(str)) {
            a6.pause();
        } else if ("play".equals(str)) {
            a6.play();
        } else if ("show".equals(str)) {
            a6.setVisibility(0);
        } else if ("src".equals(str)) {
            a6.zzav((String) map.get("src"));
        } else if ("touchMove".equals(str)) {
            r0 = noVar.getContext();
            a6.zza((float) m6554a(r0, map, "dx", 0), (float) m6554a(r0, map, "dy", 0));
            if (!this.f4941b) {
                noVar.m7259i().zzgv();
                this.f4941b = true;
            }
        } else if ("volume".equals(str)) {
            valueOf = (String) map.get("volume");
            if (valueOf == null) {
                C1129c.m6192d("Level parameter missing from volume video GMSG.");
                return;
            }
            try {
                a6.zza(Float.parseFloat(valueOf));
            } catch (NumberFormatException e4) {
                str = "Could not parse volume parameter from volume video GMSG: ";
                valueOf = String.valueOf(valueOf);
                C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if ("watermark".equals(str)) {
            a6.zzgH();
        } else {
            String str2 = "Unknown video action: ";
            valueOf = String.valueOf(str);
            C1129c.m6192d(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }
}
