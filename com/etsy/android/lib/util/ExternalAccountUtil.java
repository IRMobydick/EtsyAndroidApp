package com.etsy.android.lib.util;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;
import org.apache.commons.lang3.StringUtils;

public class ExternalAccountUtil {

    public enum AccountType {
        GOOGLE,
        FACEBOOK
    }

    public enum SignInFlow {
        REGULAR,
        EXTERNAL_SIGN_IN,
        LINK
    }

    public static AccountType m3093a(String str) {
        if ("google".equals(str)) {
            return AccountType.GOOGLE;
        }
        if (Link.FACEBOOK_TITLE.equals(str)) {
            return AccountType.FACEBOOK;
        }
        throw new RuntimeException("Could not find AccountType matching " + str);
    }

    public static String m3095a(AccountType accountType) {
        if (accountType == AccountType.GOOGLE) {
            return "google";
        }
        if (accountType == AccountType.FACEBOOK) {
            return Link.FACEBOOK_TITLE;
        }
        throw new RuntimeException("Could not find name matching account type " + accountType);
    }

    @StringRes
    public static int m3099b(String str) {
        return m3098b(m3093a(str));
    }

    @StringRes
    public static int m3098b(AccountType accountType) {
        if (accountType == AccountType.GOOGLE) {
            return R.google;
        }
        if (accountType == AccountType.FACEBOOK) {
            return R.facebook;
        }
        throw new RuntimeException("Could not find label for account type " + accountType);
    }

    public static boolean m3103c(AccountType accountType) {
        if (accountType == AccountType.GOOGLE) {
            return EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bE);
        }
        if (accountType == AccountType.FACEBOOK) {
            return EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bF);
        }
        return false;
    }

    public static String m3094a() {
        return "social_sign_in.error";
    }

    public static void m3102c(String str) {
        EtsyGraphite.m1807a(m3094a() + "." + str);
    }

    public static void m3105d(String str) {
        EtsyGraphite.m1807a("social_sign_in." + str);
    }

    public static void m3097a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "etsy";
        }
        m3105d(str);
        m3105d(str + "_by." + str2);
    }

    public static void m3096a(String str, AccountType accountType) {
        try {
            m3097a(str, m3095a(accountType));
        } catch (RuntimeException e) {
            EtsyLogger.m1966a().m1986a("external_account.graphite." + str, e.getMessage());
            m3097a(str, StringUtils.EMPTY);
        }
    }

    public static void m3100b() {
        m3102c("google_account_integrity_error");
        new AdHocEventCompatBuilder("google_account_integrity_error").m5515a("login_nag").m5517a();
    }

    public static void m3101c() {
        m3105d("using_google_stored_token");
        new AdHocEventCompatBuilder("using_stored_google_token").m5515a("login_nag").m5517a();
    }

    public static void m3104d() {
        m3105d("google_token_not_connected");
        new AdHocEventCompatBuilder("google_token_not_connected").m5515a("login_nag").m5517a();
    }
}
