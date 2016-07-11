package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import java.util.UUID;

public final class bs implements ch {
    private final Context f166a;

    public bs(Context context) {
        this.f166a = context;
    }

    public final dd m42a() {
        return new dd(Build.SERIAL, m43b());
    }

    public final String m43b() {
        SharedPreferences sharedPreferences = this.f166a.getSharedPreferences("com.appboy.device", 0);
        String string = sharedPreferences.getString("device_id", null);
        if (string != null) {
            return string;
        }
        string = UUID.randomUUID().toString();
        Editor edit = sharedPreferences.edit();
        edit.putString("device_id", string);
        edit.apply();
        return string;
    }
}
