package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import org.apache.commons.lang3.StringUtils;

public class dp {
    public static final String f338a;
    public static boolean f339c;
    public final SharedPreferences f340b;
    private bv f341d;

    static {
        f338a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dp.class.getName()});
        f339c = false;
    }

    public dp(Context context, String str, bv bvVar) {
        String str2;
        this.f341d = bvVar;
        if (str == null) {
            AppboyLogger.m664e(f338a, "PlaceIQManager received null api key.");
            str2 = StringUtils.EMPTY;
        } else {
            str2 = "." + str;
        }
        this.f340b = context.getSharedPreferences("com.appboy.storage.piqqueue" + str2, 0);
        if (f339c) {
            AppboyLogger.m666i(f338a, "Not calling placeIQ because it has already been attempted this app run");
        } else {
            new dq().execute(new Void[0]);
        }
    }
}
