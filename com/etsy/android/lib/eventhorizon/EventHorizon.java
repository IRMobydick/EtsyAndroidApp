package com.etsy.android.lib.eventhorizon;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.text.Html;
import android.text.Spanned;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.NotificationType;
import com.foresee.sdk.configuration.Configuration;
import java.util.ArrayDeque;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.JSONObject;

/* renamed from: com.etsy.android.lib.eventhorizon.a */
public class EventHorizon {
    private static EventHorizon f1706f;
    private static final int f1707g;
    private final boolean f1708a;
    private final String f1709b;
    private Context f1710c;
    private ArrayDeque<JSONObject> f1711d;
    private SharedPreferences f1712e;

    static {
        f1707g = NotificationType.EVENT_HORIZON.getId();
    }

    EventHorizon(Context context, boolean z) {
        this.f1710c = context;
        this.f1708a = z;
        this.f1709b = InstallInfo.m919a().m933j() + " " + context.getString(R.event_horizon);
        this.f1711d = new ArrayDeque();
        this.f1712e = context.getSharedPreferences(context.getString(R.config_prefs_key), 0);
    }

    public static boolean m1753a() {
        return f1706f != null && f1706f.f1708a && f1706f.m1757d();
    }

    private boolean m1757d() {
        return this.f1712e.getBoolean(this.f1710c.getString(R.config_prefs_event_horizon), false);
    }

    public static void m1751a(Context context, boolean z) {
        if (f1706f == null) {
            f1706f = new EventHorizon(context, z);
        }
    }

    public static void m1752a(JSONObject jSONObject) {
        if (EventHorizon.m1753a()) {
            EtsyDebug.m1912c(EventHorizon.class.getName(), "addBeacon");
            f1706f.m1755b(jSONObject);
            f1706f.m1758e();
        }
    }

    private void m1755b(JSONObject jSONObject) {
        this.f1711d.add(jSONObject);
        while (this.f1711d.size() > 100) {
            this.f1711d.remove();
        }
    }

    private void m1758e() {
        Builder builder = new Builder(this.f1710c);
        builder.setSmallIcon(InstallInfo.m919a().m934k());
        builder.setContentTitle(EventHorizon.m1754b());
        Style bigTextStyle = new BigTextStyle();
        bigTextStyle.bigText(m1759f());
        builder.setStyle(bigTextStyle);
        builder.setContentIntent(PendingIntent.getActivity(this.f1710c, 0, new Intent(this.f1710c, EventHorizonActivity.class), 0));
        ((NotificationManager) this.f1710c.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME)).notify(f1707g, builder.build());
    }

    private Spanned m1759f() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f1711d.size() > 0) {
            try {
                JSONObject jSONObject = ((JSONObject) this.f1711d.peekLast()).getJSONObject("Value");
                String string = jSONObject.getString(ResponseConstants.EVENT_NAME);
                String format = DateFormatUtils.format(jSONObject.getLong("timestamp"), "HH:mm:ss SSS");
                stringBuilder.append(String.format("<br/><b>%s:</b> %s", new Object[]{"Event Name", string}));
                stringBuilder.append(String.format("<br/><b>%s:</b> %s", new Object[]{"Timestamp", format}));
            } catch (Throwable e) {
                EtsyDebug.m1917d(EventHorizon.class.getName(), "getBigText fail", e);
            }
        }
        return Html.fromHtml(stringBuilder.toString());
    }

    public static String m1754b() {
        if (f1706f != null) {
            return f1706f.f1709b;
        }
        return StringUtils.EMPTY;
    }

    public static ArrayDeque<JSONObject> m1756c() {
        if (f1706f != null) {
            return f1706f.f1711d;
        }
        return new ArrayDeque();
    }
}
