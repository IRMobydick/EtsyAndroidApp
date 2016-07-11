package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.f;
import com.google.android.gms.internal.if.1;
import com.google.android.gms.internal.if.2;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

@jw
/* renamed from: com.google.android.gms.internal.if */
public class C1159if extends im {
    private final Map<String, String> f5094a;
    private final Context f5095b;
    private String f5096c;
    private long f5097d;
    private long f5098e;
    private String f5099f;
    private String f5100g;

    public C1159if(no noVar, Map<String, String> map) {
        super(noVar, "createCalendarEvent");
        this.f5094a = map;
        this.f5095b = noVar.m7256f();
        m6763c();
    }

    private String m6762a(String str) {
        return TextUtils.isEmpty((CharSequence) this.f5094a.get(str)) ? StringUtils.EMPTY : (String) this.f5094a.get(str);
    }

    private void m6763c() {
        this.f5096c = m6762a(ResponseConstants.DESCRIPTION);
        this.f5099f = m6762a("summary");
        this.f5097d = m6764e("start_ticks");
        this.f5098e = m6764e("end_ticks");
        this.f5100g = m6762a(ResponseConstants.LOCATION);
    }

    private long m6764e(String str) {
        String str2 = (String) this.f5094a.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void m6765a() {
        if (this.f5095b == null) {
            m6758b("Activity context is not available.");
        } else if (C1101o.m6041e().m7132e(this.f5095b).m6421f()) {
            Builder d = C1101o.m6041e().m7129d(this.f5095b);
            Resources o = C1101o.m6044h().m7040o();
            d.setTitle(o != null ? o.getString(f.create_calendar_title) : "Create calendar event");
            d.setMessage(o != null ? o.getString(f.create_calendar_message) : "Allow Ad to create a calendar event?");
            d.setPositiveButton(o != null ? o.getString(f.accept) : "Accept", new 1(this));
            d.setNegativeButton(o != null ? o.getString(f.decline) : "Decline", new 2(this));
            d.create().show();
        } else {
            m6758b("This feature is not available on the device.");
        }
    }

    @TargetApi(14)
    Intent m6766b() {
        Intent data = new Intent("android.intent.action.EDIT").setData(Events.CONTENT_URI);
        data.putExtra(FindsModule.FIELD_TITLE, this.f5096c);
        data.putExtra("eventLocation", this.f5100g);
        data.putExtra(ResponseConstants.DESCRIPTION, this.f5099f);
        if (this.f5097d > -1) {
            data.putExtra("beginTime", this.f5097d);
        }
        if (this.f5098e > -1) {
            data.putExtra("endTime", this.f5098e);
        }
        data.setFlags(268435456);
        return data;
    }
}
