package com.appboy.support;

import com.appboy.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.lib.models.apiv3.StructuredShopShippingEstimate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class ValidationUtils {
    public static final int APPBOY_STRING_MAX_LENGTH = 255;
    private static final String f1044a;
    private static final Set<String> f1045b;
    private static final Set<String> f1046c;
    private static String f1047d;
    private static String f1048e;

    static {
        f1044a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ValidationUtils.class.getName()});
        f1045b = new HashSet(Arrays.asList(new String[]{"appboy"}));
        f1046c = new HashSet(Arrays.asList(new String[]{ResponseConstants.FIRST_NAME, ResponseConstants.LAST_NAME, ResponseConstants.EMAIL, "gender", "dob", StructuredShopShippingEstimate.TYPE_COUNTRY, "home_city", "email_subscribe", "push_subscribe", ResponseConstants.PHONE, Link.FACEBOOK_TITLE, Link.TWITTER_TITLE, ResponseConstants.IMAGE_URL}));
        f1047d = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        f1048e = "^[0-9 .\\(\\)\\+\\-]+$";
    }

    public static boolean isValidEmailAddress(String str) {
        return str != null && str.toLowerCase().matches(f1047d);
    }

    public static boolean isValidPhoneNumber(String str) {
        return str != null && str.matches(f1048e);
    }

    public static boolean isValidCustomAttributeKey(String str) {
        if (str == null) {
            AppboyLogger.m670w(f1044a, "Custom attribute key cannot be null.");
            return false;
        } else if (customAttributeKeyHasReservedPrefix(str) || customAttributeKeyIsReservedKey(str)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isBlacklistedCustomAttributeKey(String str, Set<String> set) {
        if (!set.contains(str)) {
            return false;
        }
        AppboyLogger.m670w(f1044a, String.format("Custom attribute key cannot be blacklisted attribute: %s.", new Object[]{str}));
        return true;
    }

    public static boolean isValidCustomAttributeValue(String str) {
        if (str != null) {
            return true;
        }
        AppboyLogger.m670w(f1044a, "Custom attribute value cannot be null.");
        return false;
    }

    public static boolean customAttributeKeyHasReservedPrefix(String str) {
        String trim = str.trim();
        for (String startsWith : f1045b) {
            if (trim.toLowerCase().startsWith(startsWith)) {
                AppboyLogger.m670w(f1044a, String.format("'%s' contains a reserved prefix. Cannot use the given key.", new Object[]{trim}));
                return true;
            }
        }
        return false;
    }

    public static boolean customAttributeKeyIsReservedKey(String str) {
        if (!f1046c.contains(str.trim())) {
            return false;
        }
        AppboyLogger.m670w(f1044a, String.format("'%s' is a reserved attribute key. Cannot use the given key.", new Object[]{r2}));
        return true;
    }

    public static String[] ensureCustomAttributeArrayValues(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = ensureAppboyFieldLength(strArr[i]);
            }
        }
        return strArr;
    }

    public static String ensureAppboyFieldLength(String str) {
        String trim = str.trim();
        if (trim.length() <= APPBOY_STRING_MAX_LENGTH) {
            return trim;
        }
        AppboyLogger.m670w(f1044a, String.format("Provided string field is too long [%d]. The max length is %d, truncating provided field.", new Object[]{Integer.valueOf(trim.length()), Integer.valueOf(APPBOY_STRING_MAX_LENGTH)}));
        return trim.substring(0, APPBOY_STRING_MAX_LENGTH);
    }
}
