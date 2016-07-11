package com.etsy.android.uikit.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class SocialShareUtil {

    public enum ShareType {
        POST_PURCHASE("pp"),
        FAVORITE_ITEM("fi"),
        ADD_TO_LIST("atl"),
        FAVORITE_SHOP("fs"),
        APPRECIATION_PHOTO("ap"),
        SHOP_SHARE("ss"),
        LOCAL_SHARE("lcl"),
        SHOP_HOME("sh");
        
        private final String name;

        private ShareType(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    public static void m5156a(String str, ShareType shareType, String str2) {
        SharedPreferencesUtility.m3122a(EtsyApplication.get(), str, shareType, str2);
    }

    public static HashMap<String, Object> m5155a() {
        Context context = EtsyApplication.get();
        String a = SharedPreferencesUtility.m3117a(context, "social_invites_uuid", StringUtils.EMPTY);
        long a2 = SharedPreferencesUtility.m3116a(context, "social_invites_time", 0);
        String a3 = SharedPreferencesUtility.m3117a(context, "social_invites_page", StringUtils.EMPTY);
        String a4 = SharedPreferencesUtility.m3117a(context, "social_invites_trigger", StringUtils.EMPTY);
        String a5 = SharedPreferencesUtility.m3117a(context, "social_invites_object_id", StringUtils.EMPTY);
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("si_uuid", a);
        hashMap.put("si_time", Long.valueOf(a2));
        hashMap.put("si_page", a3);
        hashMap.put("si_trigger", a4);
        hashMap.put("si_object_id", a5);
        return hashMap;
    }

    public static String m5154a(String... strArr) {
        return String.format("share_sheet.%s.%s", new Object[]{SharedPreferencesUtility.m3117a(EtsyApplication.get(), "social_invites_trigger", StringUtils.EMPTY), TextUtils.join(".", strArr)});
    }

    public static String m5153a(String str) {
        Context context = EtsyApplication.get();
        String a = SharedPreferencesUtility.m3117a(context, "social_invites_uuid", StringUtils.EMPTY);
        long a2 = SharedPreferencesUtility.m3116a(context, "social_invites_time", 0);
        String a3 = SharedPreferencesUtility.m3117a(context, "social_invites_page", StringUtils.EMPTY);
        String a4 = SharedPreferencesUtility.m3117a(context, "social_invites_trigger", StringUtils.EMPTY);
        return Uri.parse(str).buildUpon().appendQueryParameter("social_invites", "1").appendQueryParameter("si_uuid", a).appendQueryParameter(ResponseConstants.USER_ID, aj.m1101a().m1125k().getId()).appendQueryParameter("si_time", String.valueOf(a2)).appendQueryParameter("si_page", a3).appendQueryParameter("si_trigger", a4).appendQueryParameter("si_object_id", SharedPreferencesUtility.m3117a(context, "social_invites_object_id", StringUtils.EMPTY)).appendQueryParameter(ResponseConstants.PLATFORM, AppBuild.ANDROID_PLATFORM).build().toString();
    }
}
