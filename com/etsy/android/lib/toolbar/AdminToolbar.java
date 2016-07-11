package com.etsy.android.lib.toolbar;

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
import com.adjust.sdk.Constants;
import com.android.volley.NetworkResponse;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.logger.LiveActivityCounter;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.NotificationType;
import com.etsy.android.lib.util.ad;
import com.etsy.android.lib.util.bh;
import com.foresee.sdk.configuration.Configuration;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayDeque;
import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.toolbar.a */
public class AdminToolbar {
    private static final int f1935a;
    private static AdminToolbar f1936n;
    private final String f1937b;
    private final boolean f1938c;
    private Context f1939d;
    private SharedPreferences f1940e;
    private String f1941f;
    private String f1942g;
    private String f1943h;
    private String f1944i;
    private ArrayDeque<String> f1945j;
    private HashSet<String> f1946k;
    private ArrayDeque<AdminToolbarNetworkResponse> f1947l;
    private ArrayDeque<String> f1948m;

    static {
        f1935a = NotificationType.ADMIN_TOOLBAR.getId();
    }

    AdminToolbar(Context context, boolean z) {
        this.f1941f = StringUtils.EMPTY;
        this.f1942g = StringUtils.EMPTY;
        this.f1943h = StringUtils.EMPTY;
        this.f1944i = StringUtils.EMPTY;
        this.f1939d = context;
        this.f1940e = context.getSharedPreferences(context.getString(R.config_prefs_key), 0);
        this.f1945j = new ArrayDeque();
        this.f1946k = new HashSet();
        this.f1947l = new ArrayDeque();
        this.f1948m = new ArrayDeque();
        this.f1938c = z;
        this.f1937b = InstallInfo.m919a().m933j() + context.getString(R.admin_toolbar);
    }

    public static boolean m2993a() {
        return f1936n != null && f1936n.f1938c && f1936n.m3008j() && !LiveActivityCounter.m2052c();
    }

    private boolean m3008j() {
        return this.f1940e.getBoolean(this.f1939d.getString(R.config_prefs_admin_toolbar), false);
    }

    public static void m2987a(Context context, boolean z) {
        if (f1936n == null) {
            f1936n = new AdminToolbar(context, z);
        }
    }

    public static void m2990a(String str) {
        if (AdminToolbar.m2993a()) {
            f1936n.f1941f = str;
            f1936n.m3009k();
        }
    }

    public static void m2995b(String str) {
        if (AdminToolbar.m2993a()) {
            f1936n.f1942g = str;
            f1936n.m3009k();
        }
    }

    public static void m2989a(EtsyId etsyId) {
        if (AdminToolbar.m2993a()) {
            f1936n.f1943h = ad.m3188a(ActivityFeedEntity.LISTING, etsyId.getId(), null);
        }
    }

    public static void m2999c(String str) {
        if (AdminToolbar.m2993a()) {
            f1936n.f1943h = ad.m3188a(ActivityFeedEntity.SHOP, str, ResponseConstants.REVIEWS);
        }
    }

    public static void m3001d(String str) {
        if (AdminToolbar.m2993a()) {
            f1936n.m3003e(str);
            f1936n.m3009k();
        }
    }

    public static void m2988a(EtsyConfig etsyConfig) {
        if (AdminToolbar.m2993a()) {
            f1936n.f1944i = etsyConfig.f1179b;
            f1936n.f1946k.add(etsyConfig.f1179b + ":\n" + etsyConfig.f1180c + ", " + etsyConfig.f1181d);
        }
    }

    public static void m2992a(String str, String str2) {
        if (AdminToolbar.m2993a() && !str2.contains("analytics/uploadCompressedData")) {
            try {
                f1936n.m2997b(str, str2);
                f1936n.m3009k();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2991a(String str, NetworkResponse networkResponse) {
        if (AdminToolbar.m2993a() && networkResponse != null && !str.contains("analytics/uploadCompressedData")) {
            f1936n.m2996b(str, networkResponse);
        }
    }

    private void m3003e(String str) {
        this.f1945j.add(str);
        while (this.f1945j.size() > 5) {
            this.f1945j.remove();
        }
    }

    private void m2997b(String str, String str2) {
        this.f1948m.add(str + " " + URLDecoder.decode(str2, Constants.ENCODING));
        while (this.f1948m.size() > 5) {
            this.f1948m.remove();
        }
    }

    private void m2996b(String str, NetworkResponse networkResponse) {
        this.f1947l.add(new AdminToolbarNetworkResponse(str, networkResponse));
        while (this.f1947l.size() > 3) {
            this.f1947l.remove();
        }
    }

    private void m3009k() {
        Builder builder = new Builder(this.f1939d);
        builder.setSmallIcon(InstallInfo.m919a().m934k());
        builder.setContentTitle(AdminToolbar.m2994b());
        Style bigTextStyle = new BigTextStyle();
        bigTextStyle.bigText(m3010l());
        builder.setStyle(bigTextStyle);
        builder.addAction(R.ic_camera, "Screenshot", PendingIntent.getBroadcast(this.f1939d, 0, new Intent("com.etsy.toolbar.TAKE_SCREENSHOT"), 0));
        builder.setContentIntent(PendingIntent.getActivity(this.f1939d, 0, new Intent(this.f1939d, AdminToolbarActivity.class), 0));
        ((NotificationManager) this.f1939d.getSystemService(Configuration.NOTIFICATION_LAYOUT_NAME)).notify(f1935a, builder.build());
    }

    private Spanned m3010l() {
        StringBuilder stringBuilder = new StringBuilder(String.format("<b>Fragment:</b> %s<br/><b>Activity:</b> %s", new Object[]{this.f1941f, this.f1942g}));
        if (this.f1945j.size() > 0) {
            stringBuilder.append(String.format("<br/><b>%s</b>", new Object[]{this.f1945j.peekLast()}));
        }
        if (bh.m3340a(this.f1944i)) {
            stringBuilder.append(String.format("<br/><b>%s:</b> %s", new Object[]{"AB", this.f1944i}));
        }
        if (this.f1948m.size() > 0) {
            stringBuilder.append(String.format("<br/><b>%s:</b> %s", new Object[]{"Request", this.f1948m.peekLast()}));
        }
        return Html.fromHtml(stringBuilder.toString());
    }

    public static String m2994b() {
        if (f1936n != null) {
            return f1936n.f1937b;
        }
        return StringUtils.EMPTY;
    }

    public static String m2998c() {
        if (f1936n != null) {
            return f1936n.f1941f;
        }
        return "Error: Admin Toolbar instance not initialized";
    }

    public static String m3000d() {
        if (f1936n != null) {
            return f1936n.f1942g;
        }
        return "Error: Admin Toolbar instance not initialized";
    }

    public static ArrayDeque<String> m3002e() {
        if (f1936n != null) {
            return f1936n.f1945j;
        }
        return new ArrayDeque();
    }

    public static HashSet<String> m3004f() {
        if (f1936n != null) {
            return f1936n.f1946k;
        }
        return new HashSet();
    }

    public static ArrayDeque<AdminToolbarNetworkResponse> m3005g() {
        if (f1936n != null) {
            return f1936n.f1947l;
        }
        return new ArrayDeque();
    }

    public static ArrayDeque<String> m3006h() {
        if (f1936n != null) {
            return f1936n.f1948m;
        }
        return new ArrayDeque();
    }

    public static String m3007i() {
        if (f1936n != null) {
            return f1936n.f1943h;
        }
        return StringUtils.EMPTY;
    }
}
