package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.configuration.XmlAppConfigurationProvider;
import org.apache.commons.lang3.StringUtils;

public final class ck implements cj {
    private final XmlAppConfigurationProvider f236a;
    private final SharedPreferences f237b;

    public ck(Context context, XmlAppConfigurationProvider xmlAppConfigurationProvider) {
        this.f236a = xmlAppConfigurationProvider;
        this.f237b = context.getSharedPreferences("com.appboy.push_registration", 0);
    }

    public final synchronized String m113a() {
        String str = null;
        synchronized (this) {
            if (!this.f237b.contains("version_code") || this.f236a.getVersionCode() == this.f237b.getInt("version_code", RtlSpacingHelper.UNDEFINED)) {
                str = this.f237b.getString("registration_id", null);
            }
        }
        return str;
    }

    public final synchronized void m114a(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        Editor edit = this.f237b.edit();
        edit.putString("registration_id", str);
        edit.putInt("version_code", this.f236a.getVersionCode());
        edit.apply();
    }

    public final synchronized void m115b() {
        Editor edit = this.f237b.edit();
        edit.putString("registration_id", StringUtils.EMPTY);
        edit.apply();
    }
}
