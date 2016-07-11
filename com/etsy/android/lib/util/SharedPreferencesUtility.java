package com.etsy.android.lib.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop.CustomShopsState;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.UserAddress;
import com.etsy.android.lib.models.UserProfile;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

public class SharedPreferencesUtility {

    public enum WriteMode {
        MERGE,
        OVERWRITE
    }

    public static void m3120a(Context context, User user) {
        m3121a(context, user, WriteMode.OVERWRITE);
    }

    public static void m3121a(Context context, User user, WriteMode writeMode) {
        Object valueOf;
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        UserProfile profile = user.getProfile();
        HashMap hashMap = new HashMap();
        hashMap.put("etsyUserAvatar", profile.getImageUrl75x75());
        hashMap.put("etsyUserName", bh.m3334a(user));
        hashMap.put("etsyUserFirstName", bh.m3342b(user));
        hashMap.put("etsyUserLogin", user.getLoginName());
        hashMap.put("etsyUserEmail", user.getEmail());
        hashMap.put("etsyUserIdString", user.getUserId().getId());
        hashMap.put("etsyUserLocation", bh.m3335a(profile));
        hashMap.put("etsyUserAwaitingFeedback", Integer.valueOf(user.getAwaitingFeedbackCount()));
        if (user.getProfile().getCountry() != null) {
            hashMap.put("etsyUserCountryId", Integer.valueOf(user.getProfile().getCountry().getCountryId()));
        }
        if (user.getPublicKey() != null) {
            hashMap.put("member_pub_key", user.getPublicKey().getKeyString());
            hashMap.put("member_pub_key_id", user.getPublicKey().getKeyId());
        }
        Boolean valueOf2 = Boolean.valueOf(false);
        ArrayList arrayList = (ArrayList) user.getUserAddresses();
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                UserAddress userAddress = (UserAddress) it.next();
                if (userAddress.isDefaultShipping() && "209".equalsIgnoreCase(userAddress.getCountryId().getId())) {
                    valueOf = Boolean.valueOf(true);
                    break;
                }
            }
        }
        Boolean bool = valueOf2;
        hashMap.put("etsyUserHasUsDefaultShipping", valueOf);
        if (writeMode == WriteMode.MERGE) {
            for (Entry entry : hashMap.entrySet()) {
                if (entry.getValue() instanceof String) {
                    if (!TextUtils.isEmpty((String) entry.getValue())) {
                        edit.putString((String) entry.getKey(), (String) entry.getValue());
                    }
                } else if (entry.getValue() instanceof Integer) {
                    edit.putInt((String) entry.getKey(), ((Integer) entry.getValue()).intValue());
                } else if (entry.getValue() instanceof Boolean) {
                    edit.putBoolean((String) entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
                }
            }
        } else {
            for (Entry entry2 : hashMap.entrySet()) {
                if (entry2.getValue() instanceof String) {
                    edit.putString((String) entry2.getKey(), (String) entry2.getValue());
                } else if (entry2.getValue() instanceof Integer) {
                    edit.putInt((String) entry2.getKey(), ((Integer) entry2.getValue()).intValue());
                } else if (entry2.getValue() instanceof Boolean) {
                    edit.putBoolean((String) entry2.getKey(), ((Boolean) entry2.getValue()).booleanValue());
                }
            }
        }
        if (user.getMainShop() != null) {
            edit.putString("etsyShopIdString", user.getMainShop().getShopId().getId());
            edit.putString("etsyShopName", user.getMainShop().getShopName());
            edit.putString("etsyShopIcon", user.getMainShop().getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue()));
            edit.putInt(ResponseConstants.CUSTOM_SHOPS_STATE, user.getMainShop().getCustomShopsState() != null ? user.getMainShop().getCustomShopsState().getStateInt() : CustomShopsState.DISABLED.getStateInt());
        }
        edit.apply();
        m3160r(context);
    }

    public static void m3124a(Context context, boolean z) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putBoolean("etsyHistoryEnabled", z);
        edit.apply();
    }

    public static void m3131b(Context context, boolean z) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putBoolean("bug_hunt_shake_enabled", z);
        edit.apply();
    }

    public static void m3118a(Context context) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putBoolean("update_services", false);
        edit.apply();
    }

    public static void m3123a(Context context, String str, boolean z) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static void m3128b(Context context) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.remove("etsyUserAvatar");
        edit.remove("etsyUserId");
        edit.remove("etsyUserIdString");
        edit.remove("etsyShopId");
        edit.remove("etsyShopIdString");
        edit.remove("etsyUserCountryId");
        edit.remove("etsyShopName");
        edit.remove("etsyUserLocation");
        edit.remove("etsyUserName");
        edit.remove("etsyUserLogin");
        edit.remove("etsyUserEmail");
        edit.remove("etsyUserFirstName");
        edit.remove("etsyUserAwaitingFeedback");
        edit.remove("etsyUserHasUsDefaultShipping");
        edit.remove("seen_foe_fue");
        edit.remove("user_pseudonym");
        edit.remove(ResponseConstants.CUSTOM_SHOPS_STATE);
        edit.remove("update_services");
        edit.remove("etsyCurrencyPref");
        edit.remove("etsyCurrencySymbol");
        CurrencyUtil.m3089g();
        m3125a(edit);
        edit.apply();
    }

    public static EtsyId m3134c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EtsyUserPrefs", 0);
        if (sharedPreferences.contains("etsyUserId")) {
            return new EtsyId(String.valueOf(sharedPreferences.getInt("etsyUserId", -1)));
        }
        return new EtsyId(sharedPreferences.getString("etsyUserIdString", StringUtils.EMPTY));
    }

    public static boolean m3126a(Context context, EtsyId etsyId) {
        if (etsyId == null || !etsyId.hasId()) {
            return false;
        }
        if (!etsyId.hasId() || m3134c(context).equals(etsyId)) {
            return true;
        }
        return false;
    }

    public static String m3138d(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getString("etsyUserName", StringUtils.EMPTY);
    }

    public static String m3140e(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getString("etsyUserLogin", StringUtils.EMPTY);
    }

    public static String m3142f(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getString("etsyUserEmail", StringUtils.EMPTY);
    }

    public static String m3144g(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getString("etsyUserAvatar", StringUtils.EMPTY);
    }

    public static String m3147h(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getString("etsyShopName", StringUtils.EMPTY);
    }

    public static String m3148i(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getString("shippingAddressCountryIso", StringUtils.EMPTY);
    }

    public static String m3150j(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getString("shippingAddressZIP", StringUtils.EMPTY);
    }

    public static EtsyId m3152k(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EtsyUserPrefs", 0);
        if (sharedPreferences.contains("etsyShopId")) {
            return new EtsyId(String.valueOf(sharedPreferences.getInt("etsyShopId", 0)));
        }
        return new EtsyId(sharedPreferences.getString("etsyShopIdString", StringUtils.EMPTY));
    }

    public static boolean m3154l(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getBoolean("etsyHistoryEnabled", true);
    }

    public static boolean m3155m(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getBoolean("bug_hunt_shake_enabled", true);
    }

    public static String m3156n(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getString("bugHuntUsername", null);
    }

    public static boolean m3157o(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getBoolean("update_services", true);
    }

    public static boolean m3127a(Context context, String str) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getBoolean(str, true);
    }

    public static boolean m3132b(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EtsyUserPrefs", 0);
        if (!sharedPreferences.contains(str) || sharedPreferences.getBoolean(str, false)) {
            return false;
        }
        return true;
    }

    public static void m3136c(Context context, String str) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putBoolean(str, true);
        edit.apply();
    }

    public static void m3139d(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("EtsyUserPrefs", 0);
        if (!sharedPreferences.contains(str)) {
            Editor edit = sharedPreferences.edit();
            edit.putBoolean(str, false);
            edit.apply();
        }
    }

    public static int m3158p(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getInt("etsyVersionCode", 0);
    }

    public static void m3119a(Context context, int i) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putInt("etsyVersionCode", i);
        edit.apply();
    }

    public static void m3129b(Context context, int i) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putInt("etsyUserAwaitingFeedback", i);
        edit.apply();
    }

    public static void m3135c(Context context, int i) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putInt("etsyUserUnreadConvoCount", i);
        edit.apply();
    }

    private static void m3160r(Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.etsy.android.lib.action.PREFS_UPDATED"));
    }

    public static void m3141e(Context context, String str) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putString("bugHuntUsername", str);
        edit.apply();
    }

    public static void m3143f(Context context, String str) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putString("shippingAddressCountryIso", str);
        edit.apply();
    }

    public static void m3145g(Context context, String str) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putString("shippingAddressZIP", str);
        edit.apply();
    }

    public static boolean m3133b(Context context, String str, boolean z) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getBoolean(str, z);
    }

    public static void m3137c(Context context, String str, boolean z) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static String m3117a(Context context, String str, String str2) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getString(str, str2);
    }

    public static void m3130b(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static long m3116a(Context context, String str, long j) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getLong(str, j);
    }

    public static long m3146h(Context context, String str) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getLong("social_share_dialog_shown_time_" + str, 0);
    }

    public static void m3149i(Context context, String str) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putLong("social_share_dialog_shown_time_" + str, System.currentTimeMillis());
        edit.apply();
    }

    public static void m3151j(Context context, String str) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.remove("social_share_dialog_shown_time_" + str);
        edit.apply();
    }

    private static void m3125a(Editor editor) {
        for (ShareType name : ShareType.values()) {
            editor.remove("social_share_dialog_shown_time_" + name.getName());
        }
        editor.apply();
    }

    public static String m3159q(Context context) {
        return context.getSharedPreferences("EtsyUserPrefs", 0).getString("user_pseudonym", StringUtils.EMPTY);
    }

    public static void m3153k(Context context, String str) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putString("user_pseudonym", str);
        edit.apply();
    }

    public static void m3122a(Context context, String str, ShareType shareType, String str2) {
        Editor edit = context.getSharedPreferences("EtsyUserPrefs", 0).edit();
        edit.putString("social_invites_uuid", UUID.randomUUID().toString());
        edit.putLong("social_invites_time", System.currentTimeMillis() / 1000);
        edit.putString("social_invites_page", str);
        edit.putString("social_invites_trigger", shareType.getName());
        edit.putString("social_invites_object_id", str2);
        edit.apply();
    }
}
