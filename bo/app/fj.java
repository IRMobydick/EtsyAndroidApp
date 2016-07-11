package bo.app;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.support.ValidationUtils;
import java.security.MessageDigest;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;

public final class fj {
    private static final String f447a;

    static {
        f447a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, fj.class.getName()});
    }

    public static String m349a(String str) {
        if (str == null) {
            throw new NullPointerException("Provided String must be non-null.");
        } else if (str.length() != 0) {
            return str;
        } else {
            throw new IllegalArgumentException("Provided String must be non-empty.");
        }
    }

    public static String m350a(Collection<String> collection, String str) {
        if (collection == null) {
            return StringUtils.EMPTY;
        }
        return m351a((String[]) collection.toArray(new String[collection.size()]), str);
    }

    public static String m351a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return StringUtils.EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : strArr) {
            if (str2 != null) {
                stringBuilder.append(str2).append(str);
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2 == null || !stringBuilder2.endsWith(str)) {
            return stringBuilder2;
        }
        return stringBuilder2.substring(0, stringBuilder2.length() - str.length());
    }

    public static boolean m353b(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean m354c(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String m355d(String str) {
        return str.trim().equals(StringUtils.EMPTY) ? null : str;
    }

    public static int m348a(String str, String str2) {
        return str.split(str2, -1).length - 1;
    }

    public static String m352b(String str, String str2) {
        String e = m356e(str == null ? "null" : str.toString());
        if (e == null) {
            e = Integer.toString(str.hashCode());
        }
        return "." + e + "." + str2;
    }

    public static String m356e(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(com.adjust.sdk.Constants.MD5).digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString((b & ValidationUtils.APPBOY_STRING_MAX_LENGTH) | AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY).substring(1, 3));
            }
            return stringBuffer.toString();
        } catch (Throwable e) {
            AppboyLogger.m667i(f447a, "Failed to calculate MD5 hash", e);
            return null;
        }
    }
}
