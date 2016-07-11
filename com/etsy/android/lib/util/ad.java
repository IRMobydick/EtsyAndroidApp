package com.etsy.android.lib.util;

import android.net.Uri.Builder;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* compiled from: EtsyUrlUtil */
public class ad {
    private static final String f1996a;
    private static final List<String> f1997b;

    static {
        f1996a = EtsyDebug.m1891a(ad.class);
        f1997b = Arrays.asList(new String[]{"blog", "codeascraft", "forums", "giftcards", "help", "investors", "lookbooks", "wholesale"});
    }

    public static boolean m3190a(String str) {
        return "etsy".equalsIgnoreCase(str) || "sellonetsy".equalsIgnoreCase(str);
    }

    public static boolean m3191b(String str) {
        return "http".equalsIgnoreCase(str) || Constants.SCHEME.equalsIgnoreCase(str);
    }

    public static boolean m3192c(String str) {
        String toLowerCase = str.toLowerCase(Locale.US);
        return "etsy.com".equalsIgnoreCase(toLowerCase) || toLowerCase.endsWith(".etsy.com") || "etsy.me".equals(toLowerCase) || toLowerCase.endsWith(".etsy.me");
    }

    public static boolean m3193d(String str) {
        if (str.length() == 2 && Character.isLetter(str.charAt(0)) && Character.isLetter(str.charAt(1))) {
            return true;
        }
        return false;
    }

    public static String m3189a(URL url) {
        String str;
        int i = 2;
        String[] split = url.getHost().split("\\.");
        if (split.length > 2) {
            if ("www".equalsIgnoreCase(split[0])) {
                str = split[1];
            } else {
                str = split[0];
                i = 1;
            }
            if (m3194e(str) && "etsy".equalsIgnoreCase(split[r0])) {
                try {
                    return m3187a(ActivityFeedEntity.SHOP, str);
                } catch (Throwable e) {
                    EtsyDebug.m1917d(f1996a, "Couldn't make shop url from " + str, e);
                    e.printStackTrace();
                    return url.toString();
                } catch (Throwable e2) {
                    EtsyDebug.m1917d(f1996a, "Couldn't make shop url from " + str, e2);
                    e2.printStackTrace();
                    return url.toString();
                }
            }
        }
        return url.toString();
    }

    private static boolean m3194e(String str) {
        String trim = str.trim();
        return trim.length() >= 4 && !f1997b.contains(trim);
    }

    public static String m3187a(String str, String str2) {
        Builder builder = new Builder();
        builder.scheme(Constants.SCHEME).authority("www.etsy.com").appendPath(str);
        builder.appendEncodedPath(str2);
        return builder.build().toString();
    }

    public static String m3188a(String str, String str2, String str3) {
        String str4 = EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cv) + "/" + str + "/" + str2;
        if (bh.m3340a(str3)) {
            return str4 + "/" + str3;
        }
        return str4;
    }
}
