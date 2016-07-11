package com.etsy.android.lib.util;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.UserProfile;
import com.etsy.android.uikit.util.GraphikUtil;
import java.text.Collator;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* compiled from: StringUtils */
public class bh {
    private static final String f2028a;
    private static final DateFormat f2029b;

    static {
        f2028a = EtsyDebug.m1891a(bh.class);
        f2029b = DateFormat.getDateInstance();
    }

    public static boolean m3340a(String str) {
        return (str == null || str.isEmpty() || StringUtils.EMPTY.equals(str.trim()) || "null".equals(str.trim())) ? false : true;
    }

    public static boolean m3343b(String str) {
        return (str == null || str.isEmpty() || StringUtils.EMPTY.equals(str.trim())) ? false : true;
    }

    public static String m3334a(User user) {
        if (user == null) {
            return StringUtils.EMPTY;
        }
        UserProfile profile = user.getProfile();
        String str = StringUtils.EMPTY;
        if (profile != null) {
            if (m3343b(profile.getFirstName())) {
                str = profile.getFirstName();
            }
            if (m3343b(profile.getLastName())) {
                str = str + (StringUtils.EMPTY.equals(str) ? StringUtils.EMPTY : " ") + profile.getLastName();
            }
            if (TextUtils.isEmpty(str) && m3343b(profile.getLoginName())) {
                str = profile.getLoginName();
            }
        }
        if (StringUtils.EMPTY.equals(str)) {
            return user.getLoginName();
        }
        return str;
    }

    public static String m3342b(User user) {
        if (user != null) {
            UserProfile profile = user.getProfile();
            if (profile != null && m3343b(profile.getFirstName())) {
                return profile.getFirstName();
            }
        }
        return StringUtils.EMPTY;
    }

    public static String m3335a(UserProfile userProfile) {
        String str = StringUtils.EMPTY;
        if (userProfile == null) {
            return str;
        }
        if (m3340a(userProfile.getCity())) {
            str = str + userProfile.getCity();
        }
        if (userProfile.getCountry() == null || !m3340a(userProfile.getCountry().getName())) {
            return str;
        }
        return str + (StringUtils.EMPTY.equals(str) ? StringUtils.EMPTY : ", ") + userProfile.getCountry().getName();
    }

    public static Spannable m3331a(String str, String str2, int i) {
        Spannable spannableString = new SpannableString(str);
        int indexOf = str.indexOf(str2);
        if (indexOf >= 0) {
            spannableString.setSpan(new ForegroundColorSpan(i), indexOf, str2.length() + indexOf, 33);
        }
        return spannableString;
    }

    public static Spannable m3332a(String str, String str2, Context context) {
        Spannable spannableString = new SpannableString(str);
        int indexOf = str.indexOf(str2);
        if (indexOf >= 0) {
            spannableString.setSpan(GraphikUtil.m5544a(context), indexOf, str2.length() + indexOf, 18);
        }
        return spannableString;
    }

    public static Spannable m3341b(String str, String str2, Context context) {
        Spannable spannableString = new SpannableString(str);
        int indexOf = str.indexOf(str2);
        if (indexOf >= 0) {
            if (indexOf != 0) {
                spannableString.setSpan(GraphikUtil.m5544a(context), 0, indexOf, 18);
            }
            spannableString.setSpan(GraphikUtil.m5544a(context), indexOf + str2.length(), str.length(), 18);
        }
        return spannableString;
    }

    public static String m3333a(double d) {
        return NumberFormat.getInstance().format(d);
    }

    public static String m3337a(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        return f2029b.format(date);
    }

    public static int m3330a(String str, String str2) {
        Collator instance = Collator.getInstance(Locale.getDefault());
        instance.setStrength(0);
        return instance.compare(str, str2);
    }

    public static StringBuilder m3338a(StringBuilder stringBuilder) {
        int length = stringBuilder.length();
        for (int i = 0; i < length; i++) {
            stringBuilder.setCharAt(i, Character.toLowerCase(stringBuilder.charAt(i)));
        }
        return stringBuilder;
    }

    public static boolean m3339a(CharSequence charSequence, CharSequence charSequence2) {
        return StringUtils.equals(charSequence, charSequence2);
    }

    public static String m3336a(String str, int i) {
        if (str.length() <= i) {
            return str;
        }
        String str2 = "\u2026";
        int lastIndexOf = str.lastIndexOf(32, i - 1);
        if (lastIndexOf == -1) {
            return str.substring(0, i - 1) + str2;
        }
        return str.substring(0, lastIndexOf) + str2;
    }
}
