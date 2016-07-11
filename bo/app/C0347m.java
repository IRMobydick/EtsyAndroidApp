package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.apache.commons.lang3.StringUtils;

/* renamed from: bo.app.m */
public final class C0347m {
    public final SharedPreferences f796a;

    public C0347m(Context context) {
        this.f796a = context.getSharedPreferences("com.appboy.offline.storagemap", 0);
    }

    public final String m613a() {
        return this.f796a.getString("last_user", StringUtils.EMPTY);
    }

    public static Editor m612a(Context context) {
        return context.getSharedPreferences("com_appboy_override_configuration_cache", 0).edit();
    }
}
