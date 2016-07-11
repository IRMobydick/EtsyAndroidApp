package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

public class ey {
    public static final String f427a;
    public final bc f428b;
    public final SharedPreferences f429c;
    public final Object f430d;
    public AtomicBoolean f431e;
    public cx f432f;
    public String f433g;

    static {
        f427a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ey.class.getName()});
    }

    public ey(Context context, String str, bc bcVar) {
        String str2;
        this.f430d = new Object();
        this.f431e = new AtomicBoolean(false);
        if (str == null) {
            AppboyLogger.m664e(f427a, "ServerConfigStorageProvider received null api key.");
            str2 = StringUtils.EMPTY;
        } else {
            str2 = "." + str;
        }
        this.f429c = context.getSharedPreferences("com.appboy.storage.serverconfigstorageprovider" + str2, 0);
        this.f433g = this.f429c.getString("last_configured_appboy_sdk_version", null);
        this.f428b = bcVar;
        new ez().execute(new Void[0]);
    }

    public final boolean m295a() {
        boolean z;
        synchronized (this.f430d) {
            if (this.f432f != null) {
                z = this.f432f.f278i;
            } else {
                z = this.f429c.getBoolean("piq_enabled", false);
            }
        }
        return z;
    }

    public final boolean m296b() {
        boolean z;
        synchronized (this.f430d) {
            if (this.f432f != null) {
                z = this.f432f.f275f;
            } else {
                z = this.f429c.getBoolean("location_enabled_set", false);
            }
        }
        return z;
    }

    public final boolean m297c() {
        boolean z;
        synchronized (this.f430d) {
            if (this.f432f != null) {
                z = this.f432f.f275f;
            } else {
                z = this.f429c.getBoolean("location_enabled", false);
            }
        }
        return z;
    }

    public final long m298d() {
        long j;
        synchronized (this.f430d) {
            if (this.f432f != null) {
                j = this.f432f.f276g;
            } else {
                j = this.f429c.getLong("location_time", -1);
            }
        }
        return j;
    }

    public final float m299e() {
        float f;
        synchronized (this.f430d) {
            if (this.f432f != null) {
                f = this.f432f.f277h;
            } else {
                f = this.f429c.getFloat("location_distance", -1.0f);
            }
        }
        return f;
    }

    public final long m300f() {
        long j;
        synchronized (this.f430d) {
            if (this.f432f != null) {
                j = this.f432f.f270a;
            } else {
                j = this.f429c.getLong("config_time", 0);
            }
        }
        return j;
    }

    public final Set<String> m301g() {
        Set<String> set;
        synchronized (this.f430d) {
            if (this.f432f != null) {
                set = this.f432f.f271b;
            } else {
                set = m293a("blacklisted_events");
            }
            if (set != null) {
            } else {
                set = new HashSet();
            }
        }
        return set;
    }

    public final Set<String> m302h() {
        Set<String> set;
        synchronized (this.f430d) {
            if (this.f432f != null) {
                set = this.f432f.f272c;
            } else {
                set = m293a("blacklisted_attributes");
            }
            if (set != null) {
            } else {
                set = new HashSet();
            }
        }
        return set;
    }

    public final Set<String> m303i() {
        Set<String> set;
        synchronized (this.f430d) {
            if (this.f432f != null) {
                set = this.f432f.f273d;
            } else {
                set = m293a("blacklisted_purchases");
            }
            if (set != null) {
            } else {
                set = new HashSet();
            }
        }
        return set;
    }

    public final void m294a(boolean z) {
        this.f431e.set(z);
    }

    private Set<String> m293a(String str) {
        try {
            String string = this.f429c.getString(str, StringUtils.EMPTY);
            if (fj.m354c(string)) {
                return null;
            }
            JSONArray jSONArray = new JSONArray(string);
            Set<String> hashSet = new HashSet();
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
            return hashSet;
        } catch (Throwable e) {
            AppboyLogger.m671w(f427a, "Experienced exception retrieving blacklisted strings from local storage. Returning null.", e);
            return null;
        }
    }
}
