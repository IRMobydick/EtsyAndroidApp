package com.etsy.android.lib.util;

import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.models.ConversationMessage;
import com.etsy.android.lib.models.Listing;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* compiled from: MachineTranslationUtil */
public class aj {
    public static final List<String> m3229a() {
        return Arrays.asList(EtsyConfig.m837a().m869d().m889g(EtsyConfigKeys.f1182A));
    }

    public static final List<String> m3234b() {
        return Arrays.asList(EtsyConfig.m837a().m869d().m889g(EtsyConfigKeys.f1228u));
    }

    public static String m3235c() {
        String language = Locale.getDefault().getLanguage();
        return language.equals("en") ? "en-US" : language;
    }

    private static String m3240g() {
        return Locale.getDefault().getLanguage();
    }

    public static String m3228a(String str) {
        if (str.length() > 2) {
            str = m3233b(str);
        }
        return new Locale(str).getDisplayName();
    }

    public static String m3233b(String str) {
        return (!bh.m3340a(str) || str.length() <= 2) ? str : str.substring(0, 2);
    }

    public static boolean m3237d() {
        return m3234b().contains(m3240g()) && EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1231x);
    }

    public static boolean m3238e() {
        return m3234b().contains(m3240g()) && EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1230w);
    }

    public static boolean m3239f() {
        return m3229a().contains(m3240g()) && EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1229v);
    }

    public static boolean m3231a(Listing listing) {
        return m3232a(listing.getTitle(), m3233b(listing.getLanguage()));
    }

    public static boolean m3230a(ConversationMessage conversationMessage) {
        return m3232a(conversationMessage.getMessage(), conversationMessage.getLanguage());
    }

    public static boolean m3232a(String str, String str2) {
        return bh.m3340a(str) && bh.m3340a(str2) && !Locale.getDefault().getLanguage().equals(str2);
    }

    public static boolean m3236c(String str) {
        return str.startsWith("MACHINE_");
    }
}
