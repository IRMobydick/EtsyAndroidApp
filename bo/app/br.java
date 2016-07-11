package bo.app;

import android.content.Context;
import com.amazon.device.messaging.development.ADMManifest;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;

public class br {
    public static final String f163c;
    public final Context f164a;
    public final cj f165b;

    static {
        f163c = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, br.class.getName()});
    }

    public br(Context context, cj cjVar) {
        this.f164a = context;
        this.f165b = cjVar;
    }

    public static boolean m38a(Context context) {
        return m37a() && m39b(context);
    }

    private static boolean m37a() {
        try {
            Class.forName("com.amazon.device.messaging.ADM");
            return true;
        } catch (Exception e) {
            AppboyLogger.m666i(f163c, "com.amazon.device.messaging.ADM not found");
            return false;
        }
    }

    private static boolean m39b(Context context) {
        try {
            ADMManifest.checkManifestAuthoredProperly(context);
            return true;
        } catch (Throwable e) {
            AppboyLogger.m666i(f163c, "Manifest not authored properly to support ADM.");
            AppboyLogger.m667i(f163c, "ADM manifest exception: ", e);
            return false;
        }
    }
}
