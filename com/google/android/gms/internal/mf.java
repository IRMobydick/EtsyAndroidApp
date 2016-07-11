package com.google.android.gms.internal;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.appboy.Constants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Map;

@jw
public class mf {
    private final Context f5429a;
    private String f5430b;
    private final float f5431c;
    private float f5432d;
    private float f5433e;
    private float f5434f;
    private int f5435g;

    public mf(Context context) {
        this.f5435g = 0;
        this.f5429a = context;
        this.f5431c = context.getResources().getDisplayMetrics().density;
    }

    public mf(Context context, String str) {
        this(context);
        this.f5430b = str;
    }

    private void m7169a() {
        if (this.f5429a instanceof Activity) {
            Object b = m7170b(this.f5430b);
            Builder builder = new Builder(this.f5429a);
            builder.setMessage(b);
            builder.setTitle("Ad Information");
            builder.setPositiveButton("Share", new 1(this, b));
            builder.setNegativeButton("Close", new 2(this));
            builder.create().show();
            return;
        }
        C1129c.m6190c("Can not create dialog without Activity Context");
    }

    static String m7170b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "No debug information";
        }
        Uri build = new Uri.Builder().encodedQuery(str.replaceAll("\\+", "%20")).build();
        StringBuilder stringBuilder = new StringBuilder();
        Map a = C1101o.m6041e().m7096a(build);
        for (String str2 : a.keySet()) {
            stringBuilder.append(str2).append(" = ").append((String) a.get(str2)).append("\n\n");
        }
        Object trim = stringBuilder.toString().trim();
        return TextUtils.isEmpty(trim) ? "No debug information" : trim;
    }

    void m7171a(int i, float f, float f2) {
        if (i == 0) {
            this.f5435g = 0;
            this.f5432d = f;
            this.f5433e = f2;
            this.f5434f = f2;
        } else if (this.f5435g == -1) {
        } else {
            if (i == 2) {
                if (f2 > this.f5433e) {
                    this.f5433e = f2;
                } else if (f2 < this.f5434f) {
                    this.f5434f = f2;
                }
                if (this.f5433e - this.f5434f > 30.0f * this.f5431c) {
                    this.f5435g = -1;
                    return;
                }
                if (this.f5435g == 0 || this.f5435g == 2) {
                    if (f - this.f5432d >= Constants.LOCATION_UPDATE_DISTANCE_LOCAL_CONFIG_MINIMUM * this.f5431c) {
                        this.f5432d = f;
                        this.f5435g++;
                    }
                } else if ((this.f5435g == 1 || this.f5435g == 3) && f - this.f5432d <= -50.0f * this.f5431c) {
                    this.f5432d = f;
                    this.f5435g++;
                }
                if (this.f5435g == 1 || this.f5435g == 3) {
                    if (f > this.f5432d) {
                        this.f5432d = f;
                    }
                } else if (this.f5435g == 2 && f < this.f5432d) {
                    this.f5432d = f;
                }
            } else if (i == 1 && this.f5435g == 4) {
                m7169a();
            }
        }
    }

    public void m7172a(MotionEvent motionEvent) {
        int historySize = motionEvent.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            m7171a(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        m7171a(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }

    public void m7173a(String str) {
        this.f5430b = str;
    }
}
