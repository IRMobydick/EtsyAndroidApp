package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.apiv3.SearchCategoryRedirectPage;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.SearchAdRequestParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@jw
public final class jy {
    private static final SimpleDateFormat f5220a;

    static {
        f5220a = new SimpleDateFormat("yyyyMMdd", Locale.US);
    }

    private static Bundle m6889a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("runtime_free", Long.toString(bundle.getLong("runtime_free_memory", -1)));
        bundle2.putString("runtime_max", Long.toString(bundle.getLong("runtime_max_memory", -1)));
        bundle2.putString("runtime_total", Long.toString(bundle.getLong("runtime_total_memory", -1)));
        MemoryInfo memoryInfo = (MemoryInfo) bundle.getParcelable("debug_memory_info");
        if (memoryInfo != null) {
            bundle2.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
            bundle2.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
            bundle2.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
            bundle2.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
            bundle2.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
            bundle2.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
            bundle2.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
            bundle2.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
            bundle2.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
        }
        return bundle2;
    }

    public static AdResponseParcel m6890a(Context context, AdRequestInfoParcel adRequestInfoParcel, String str) {
        String optString;
        try {
            String str2;
            JSONObject jSONObject = new JSONObject(str);
            String optString2 = jSONObject.optString("ad_base_url", null);
            Object optString3 = jSONObject.optString("ad_url", null);
            String optString4 = jSONObject.optString("ad_size", null);
            boolean z = (adRequestInfoParcel == null || adRequestInfoParcel.zzLo == 0) ? false : true;
            CharSequence optString5 = jSONObject.optString("ad_json", null);
            if (optString5 == null) {
                optString5 = jSONObject.optString("ad_html", null);
            }
            if (optString5 == null) {
                optString5 = jSONObject.optString("body", null);
            }
            long j = -1;
            String optString6 = jSONObject.optString("debug_dialog", null);
            long j2 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1;
            optString = jSONObject.optString("orientation", null);
            int i = -1;
            if ("portrait".equals(optString)) {
                i = C1101o.m6043g().m7160b();
            } else if ("landscape".equals(optString)) {
                i = C1101o.m6043g().m7146a();
            }
            AdResponseParcel adResponseParcel = null;
            if (!TextUtils.isEmpty(optString5) || TextUtils.isEmpty(optString3)) {
                CharSequence charSequence = optString5;
            } else {
                adResponseParcel = zzii.zza(adRequestInfoParcel, context, adRequestInfoParcel.zzsx.afmaVersion, optString3, null, null, null, null, null);
                optString2 = adResponseParcel.zzHH;
                str2 = adResponseParcel.body;
                j = adResponseParcel.zzLT;
            }
            if (str2 == null) {
                return new AdResponseParcel(0);
            }
            long j3;
            String optString7;
            String str3;
            boolean optBoolean;
            JSONArray optJSONArray = jSONObject.optJSONArray("click_urls");
            List list = adResponseParcel == null ? null : adResponseParcel.zzEF;
            if (optJSONArray != null) {
                list = m6894a(optJSONArray, list);
            }
            optJSONArray = jSONObject.optJSONArray("impression_urls");
            List list2 = adResponseParcel == null ? null : adResponseParcel.zzEG;
            if (optJSONArray != null) {
                list2 = m6894a(optJSONArray, list2);
            }
            optJSONArray = jSONObject.optJSONArray("manual_impression_urls");
            List list3 = adResponseParcel == null ? null : adResponseParcel.zzLR;
            if (optJSONArray != null) {
                list3 = m6894a(optJSONArray, list3);
            }
            if (adResponseParcel != null) {
                if (adResponseParcel.orientation != -1) {
                    i = adResponseParcel.orientation;
                }
                if (adResponseParcel.zzLO > 0) {
                    j3 = adResponseParcel.zzLO;
                    optString7 = jSONObject.optString("active_view");
                    str3 = null;
                    optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
                    if (optBoolean) {
                        str3 = jSONObject.optString("ad_passback_url", null);
                    }
                    return new AdResponseParcel(adRequestInfoParcel, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list3, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString6, optBoolean, str3, optString7, jSONObject.optBoolean("custom_render_allowed", false), z, adRequestInfoParcel.zzLq, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optInt("oauth2_token_status", 0), jSONObject.optString("gws_query_id", StringUtils.EMPTY), ResponseConstants.HEIGHT.equals(jSONObject.optString("fluid", StringUtils.EMPTY)), jSONObject.optBoolean("native_express", false), RewardItemParcel.zza(jSONObject.optJSONArray("rewards")), m6894a(jSONObject.optJSONArray("video_start_urls"), null), m6894a(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), AutoClickProtectionConfigurationParcel.zzg(jSONObject.optJSONObject("auto_protection_configuration")), adRequestInfoParcel.zzLH, jSONObject.optString("set_cookie", StringUtils.EMPTY), m6894a(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optString("safe_browsing"), jSONObject.optBoolean("render_in_browser", adRequestInfoParcel.zzEJ));
                }
            }
            j3 = j2;
            optString7 = jSONObject.optString("active_view");
            str3 = null;
            optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            if (optBoolean) {
                str3 = jSONObject.optString("ad_passback_url", null);
            }
            return new AdResponseParcel(adRequestInfoParcel, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list3, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString6, optBoolean, str3, optString7, jSONObject.optBoolean("custom_render_allowed", false), z, adRequestInfoParcel.zzLq, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optInt("oauth2_token_status", 0), jSONObject.optString("gws_query_id", StringUtils.EMPTY), ResponseConstants.HEIGHT.equals(jSONObject.optString("fluid", StringUtils.EMPTY)), jSONObject.optBoolean("native_express", false), RewardItemParcel.zza(jSONObject.optJSONArray("rewards")), m6894a(jSONObject.optJSONArray("video_start_urls"), null), m6894a(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), AutoClickProtectionConfigurationParcel.zzg(jSONObject.optJSONObject("auto_protection_configuration")), adRequestInfoParcel.zzLH, jSONObject.optString("set_cookie", StringUtils.EMPTY), m6894a(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optString("safe_browsing"), jSONObject.optBoolean("render_in_browser", adRequestInfoParcel.zzEJ));
        } catch (JSONException e) {
            String str4 = "Could not parse the mediation config: ";
            optString = String.valueOf(e.getMessage());
            C1129c.m6192d(optString.length() != 0 ? str4.concat(optString) : new String(str4));
            return new AdResponseParcel(0);
        }
    }

    private static Integer m6891a(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    private static String m6892a(int i) {
        return String.format(Locale.US, "#%06x", new Object[]{Integer.valueOf(ViewCompat.MEASURED_SIZE_MASK & i)});
    }

    private static String m6893a(NativeAdOptionsParcel nativeAdOptionsParcel) {
        switch (nativeAdOptionsParcel != null ? nativeAdOptionsParcel.zzBm : 0) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return "portrait";
            case Task.NETWORK_STATE_ANY /*2*/:
                return "landscape";
            default:
                return "any";
        }
    }

    @Nullable
    private static List<String> m6894a(@Nullable JSONArray jSONArray, @Nullable List<String> list) {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new LinkedList();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    @Nullable
    static JSONArray m6895a(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    public static JSONObject m6896a(Context context, AdRequestInfoParcel adRequestInfoParcel, kd kdVar, kk kkVar, Location location, ds dsVar, String str, String str2, List<String> list, Bundle bundle, String str3) {
        String str4;
        String valueOf;
        try {
            HashMap hashMap = new HashMap();
            if (list.size() > 0) {
                hashMap.put("eid", TextUtils.join(",", list));
            }
            if (adRequestInfoParcel.zzLh != null) {
                hashMap.put("ad_pos", adRequestInfoParcel.zzLh);
            }
            m6899a(hashMap, adRequestInfoParcel.zzLi);
            hashMap.put(ResponseConstants.FORMAT, adRequestInfoParcel.zzsB.zzvs);
            if (adRequestInfoParcel.zzsB.width == -1) {
                hashMap.put("smart_w", "full");
            }
            if (adRequestInfoParcel.zzsB.height == -2) {
                hashMap.put("smart_h", "auto");
            }
            if (adRequestInfoParcel.zzsB.zzvw) {
                hashMap.put("fluid", ResponseConstants.HEIGHT);
            }
            if (adRequestInfoParcel.zzsB.zzvu != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (AdSizeParcel adSizeParcel : adRequestInfoParcel.zzsB.zzvu) {
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append("|");
                    }
                    stringBuilder.append(adSizeParcel.width == -1 ? (int) (((float) adSizeParcel.widthPixels) / kdVar.f5290r) : adSizeParcel.width);
                    stringBuilder.append(EtsyDialogFragment.OPT_X_BUTTON);
                    stringBuilder.append(adSizeParcel.height == -2 ? (int) (((float) adSizeParcel.heightPixels) / kdVar.f5290r) : adSizeParcel.height);
                }
                hashMap.put("sz", stringBuilder);
            }
            if (adRequestInfoParcel.zzLo != 0) {
                hashMap.put("native_version", Integer.valueOf(adRequestInfoParcel.zzLo));
                if (!adRequestInfoParcel.zzsB.zzvx) {
                    hashMap.put("native_templates", adRequestInfoParcel.zzsT);
                    hashMap.put("native_image_orientation", m6893a(adRequestInfoParcel.zzsP));
                    if (!adRequestInfoParcel.zzLz.isEmpty()) {
                        hashMap.put("native_custom_templates", adRequestInfoParcel.zzLz);
                    }
                }
            }
            hashMap.put("slotname", adRequestInfoParcel.zzsv);
            hashMap.put("pn", adRequestInfoParcel.applicationInfo.packageName);
            if (adRequestInfoParcel.zzLj != null) {
                hashMap.put("vc", Integer.valueOf(adRequestInfoParcel.zzLj.versionCode));
            }
            hashMap.put("ms", str2);
            hashMap.put("seq_num", adRequestInfoParcel.zzLl);
            hashMap.put("session_id", adRequestInfoParcel.zzLm);
            hashMap.put("js", adRequestInfoParcel.zzsx.afmaVersion);
            m6901a(hashMap, kdVar, kkVar, adRequestInfoParcel.zzLM);
            m6902a(hashMap, str3, str);
            hashMap.put(ResponseConstants.PLATFORM, Build.MANUFACTURER);
            hashMap.put("submodel", Build.MODEL);
            if (location != null) {
                m6898a(hashMap, location);
            } else if (adRequestInfoParcel.zzLi.versionCode >= 2 && adRequestInfoParcel.zzLi.zzuV != null) {
                m6898a(hashMap, adRequestInfoParcel.zzLi.zzuV);
            }
            if (adRequestInfoParcel.versionCode >= 2) {
                hashMap.put("quality_signals", adRequestInfoParcel.zzLn);
            }
            if (adRequestInfoParcel.versionCode >= 4 && adRequestInfoParcel.zzLq) {
                hashMap.put("forceHttps", Boolean.valueOf(adRequestInfoParcel.zzLq));
            }
            if (bundle != null) {
                hashMap.put("content_info", bundle);
            }
            if (adRequestInfoParcel.versionCode >= 5) {
                hashMap.put("u_sd", Float.valueOf(adRequestInfoParcel.zzLu));
                hashMap.put("sh", Integer.valueOf(adRequestInfoParcel.zzLt));
                hashMap.put("sw", Integer.valueOf(adRequestInfoParcel.zzLs));
            } else {
                hashMap.put("u_sd", Float.valueOf(kdVar.f5290r));
                hashMap.put("sh", Integer.valueOf(kdVar.f5292t));
                hashMap.put("sw", Integer.valueOf(kdVar.f5291s));
            }
            if (adRequestInfoParcel.versionCode >= 6) {
                if (!TextUtils.isEmpty(adRequestInfoParcel.zzLv)) {
                    try {
                        hashMap.put("view_hierarchy", new JSONObject(adRequestInfoParcel.zzLv));
                    } catch (Throwable e) {
                        C1129c.m6193d("Problem serializing view hierarchy to JSON", e);
                    }
                }
                hashMap.put("correlation_id", Long.valueOf(adRequestInfoParcel.zzLw));
            }
            if (adRequestInfoParcel.versionCode >= 7) {
                hashMap.put("request_id", adRequestInfoParcel.zzLx);
            }
            if (adRequestInfoParcel.versionCode >= 11 && adRequestInfoParcel.zzLB != null) {
                hashMap.put("capability", adRequestInfoParcel.zzLB.toBundle());
            }
            if (adRequestInfoParcel.versionCode >= 12 && !TextUtils.isEmpty(adRequestInfoParcel.zzLC)) {
                hashMap.put("anchor", adRequestInfoParcel.zzLC);
            }
            if (adRequestInfoParcel.versionCode >= 13) {
                hashMap.put("android_app_volume", Float.valueOf(adRequestInfoParcel.zzLD));
            }
            if (adRequestInfoParcel.versionCode >= 18) {
                hashMap.put("android_app_muted", Boolean.valueOf(adRequestInfoParcel.zzLJ));
            }
            if (adRequestInfoParcel.versionCode >= 14 && adRequestInfoParcel.zzLE > 0) {
                hashMap.put("target_api", Integer.valueOf(adRequestInfoParcel.zzLE));
            }
            if (adRequestInfoParcel.versionCode >= 15) {
                hashMap.put("scroll_index", Integer.valueOf(adRequestInfoParcel.zzLF == -1 ? -1 : adRequestInfoParcel.zzLF));
            }
            if (adRequestInfoParcel.versionCode >= 16) {
                hashMap.put("_activity_context", Boolean.valueOf(adRequestInfoParcel.zzLG));
            }
            if (adRequestInfoParcel.versionCode >= 18) {
                if (!TextUtils.isEmpty(adRequestInfoParcel.zzLK)) {
                    try {
                        hashMap.put("app_settings", new JSONObject(adRequestInfoParcel.zzLK));
                    } catch (Throwable e2) {
                        C1129c.m6193d("Problem creating json from app settings", e2);
                    }
                }
                hashMap.put("render_in_browser", Boolean.valueOf(adRequestInfoParcel.zzEJ));
            }
            if (adRequestInfoParcel.versionCode >= 18) {
                hashMap.put("android_num_video_cache_tasks", Integer.valueOf(adRequestInfoParcel.zzLL));
            }
            if (C1129c.m6187a(2)) {
                str4 = "Ad Request JSON: ";
                valueOf = String.valueOf(C1101o.m6041e().m7098a((Map) hashMap).toString(2));
                lo.m7056e(valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
            }
            return C1101o.m6041e().m7098a((Map) hashMap);
        } catch (JSONException e3) {
            str4 = "Problem serializing ad request to JSON: ";
            valueOf = String.valueOf(e3.getMessage());
            C1129c.m6192d(valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
            return null;
        }
    }

    public static JSONObject m6897a(AdResponseParcel adResponseParcel) {
        JSONObject jSONObject = new JSONObject();
        if (adResponseParcel.zzHH != null) {
            jSONObject.put("ad_base_url", adResponseParcel.zzHH);
        }
        if (adResponseParcel.zzLS != null) {
            jSONObject.put("ad_size", adResponseParcel.zzLS);
        }
        jSONObject.put("native", adResponseParcel.zzvv);
        if (adResponseParcel.zzvv) {
            jSONObject.put("ad_json", adResponseParcel.body);
        } else {
            jSONObject.put("ad_html", adResponseParcel.body);
        }
        if (adResponseParcel.zzLU != null) {
            jSONObject.put("debug_dialog", adResponseParcel.zzLU);
        }
        if (adResponseParcel.zzLO != -1) {
            jSONObject.put("interstitial_timeout", ((double) adResponseParcel.zzLO) / 1000.0d);
        }
        if (adResponseParcel.orientation == C1101o.m6043g().m7160b()) {
            jSONObject.put("orientation", "portrait");
        } else if (adResponseParcel.orientation == C1101o.m6043g().m7146a()) {
            jSONObject.put("orientation", "landscape");
        }
        if (adResponseParcel.zzEF != null) {
            jSONObject.put("click_urls", m6895a(adResponseParcel.zzEF));
        }
        if (adResponseParcel.zzEG != null) {
            jSONObject.put("impression_urls", m6895a(adResponseParcel.zzEG));
        }
        if (adResponseParcel.zzLR != null) {
            jSONObject.put("manual_impression_urls", m6895a(adResponseParcel.zzLR));
        }
        if (adResponseParcel.zzLX != null) {
            jSONObject.put("active_view", adResponseParcel.zzLX);
        }
        jSONObject.put("ad_is_javascript", adResponseParcel.zzLV);
        if (adResponseParcel.zzLW != null) {
            jSONObject.put("ad_passback_url", adResponseParcel.zzLW);
        }
        jSONObject.put("mediation", adResponseParcel.zzLP);
        jSONObject.put("custom_render_allowed", adResponseParcel.zzLY);
        jSONObject.put("content_url_opted_out", adResponseParcel.zzLZ);
        jSONObject.put("prefetch", adResponseParcel.zzMa);
        jSONObject.put("oauth2_token_status", adResponseParcel.zzMb);
        if (adResponseParcel.zzEL != -1) {
            jSONObject.put("refresh_interval_milliseconds", adResponseParcel.zzEL);
        }
        if (adResponseParcel.zzLQ != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", adResponseParcel.zzLQ);
        }
        if (!TextUtils.isEmpty(adResponseParcel.zzMe)) {
            jSONObject.put("gws_query_id", adResponseParcel.zzMe);
        }
        jSONObject.put("fluid", adResponseParcel.zzvw ? ResponseConstants.HEIGHT : StringUtils.EMPTY);
        jSONObject.put("native_express", adResponseParcel.zzvx);
        if (adResponseParcel.zzMg != null) {
            jSONObject.put("video_start_urls", m6895a(adResponseParcel.zzMg));
        }
        if (adResponseParcel.zzMh != null) {
            jSONObject.put("video_complete_urls", m6895a(adResponseParcel.zzMh));
        }
        if (adResponseParcel.zzMf != null) {
            jSONObject.put("rewards", adResponseParcel.zzMf.zzir());
        }
        jSONObject.put("use_displayed_impression", adResponseParcel.zzMi);
        jSONObject.put("auto_protection_configuration", adResponseParcel.zzMj);
        jSONObject.put("render_in_browser", adResponseParcel.zzEJ);
        return jSONObject;
    }

    private static void m6898a(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put(ResponseConstants.RADIUS, valueOf);
        hashMap2.put(ResponseConstants.LAT, valueOf3);
        hashMap2.put(Constants.LONG, valueOf4);
        hashMap2.put(com.appboy.Constants.APPBOY_LOCATION_TIME_INTERVAL_KEY, valueOf2);
        hashMap.put("uule", hashMap2);
    }

    private static void m6899a(HashMap<String, Object> hashMap, AdRequestParcel adRequestParcel) {
        String a = lm.m7051a();
        if (a != null) {
            hashMap.put("abf", a);
        }
        if (adRequestParcel.zzuN != -1) {
            hashMap.put("cust_age", f5220a.format(new Date(adRequestParcel.zzuN)));
        }
        if (adRequestParcel.extras != null) {
            hashMap.put("extras", adRequestParcel.extras);
        }
        if (adRequestParcel.zzuO != -1) {
            hashMap.put("cust_gender", Integer.valueOf(adRequestParcel.zzuO));
        }
        if (adRequestParcel.zzuP != null) {
            hashMap.put("kw", adRequestParcel.zzuP);
        }
        if (adRequestParcel.zzuR != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(adRequestParcel.zzuR));
        }
        if (adRequestParcel.zzuQ) {
            hashMap.put("adtest", "on");
        }
        if (adRequestParcel.versionCode >= 2) {
            if (adRequestParcel.zzuS) {
                hashMap.put("d_imp_hdr", Integer.valueOf(1));
            }
            if (!TextUtils.isEmpty(adRequestParcel.zzuT)) {
                hashMap.put("ppid", adRequestParcel.zzuT);
            }
            if (adRequestParcel.zzuU != null) {
                m6900a((HashMap) hashMap, adRequestParcel.zzuU);
            }
        }
        if (adRequestParcel.versionCode >= 3 && adRequestParcel.zzuW != null) {
            hashMap.put(ResponseConstants.URL, adRequestParcel.zzuW);
        }
        if (adRequestParcel.versionCode >= 5) {
            if (adRequestParcel.zzuY != null) {
                hashMap.put("custom_targeting", adRequestParcel.zzuY);
            }
            if (adRequestParcel.zzuZ != null) {
                hashMap.put("category_exclusions", adRequestParcel.zzuZ);
            }
            if (adRequestParcel.zzva != null) {
                hashMap.put("request_agent", adRequestParcel.zzva);
            }
        }
        if (adRequestParcel.versionCode >= 6 && adRequestParcel.zzvb != null) {
            hashMap.put("request_pkg", adRequestParcel.zzvb);
        }
        if (adRequestParcel.versionCode >= 7) {
            hashMap.put("is_designed_for_families", Boolean.valueOf(adRequestParcel.zzvc));
        }
    }

    private static void m6900a(HashMap<String, Object> hashMap, SearchAdRequestParcel searchAdRequestParcel) {
        Object obj;
        Object obj2 = null;
        if (Color.alpha(searchAdRequestParcel.zzwA) != 0) {
            hashMap.put("acolor", m6892a(searchAdRequestParcel.zzwA));
        }
        if (Color.alpha(searchAdRequestParcel.backgroundColor) != 0) {
            hashMap.put("bgcolor", m6892a(searchAdRequestParcel.backgroundColor));
        }
        if (!(Color.alpha(searchAdRequestParcel.zzwB) == 0 || Color.alpha(searchAdRequestParcel.zzwC) == 0)) {
            hashMap.put("gradientto", m6892a(searchAdRequestParcel.zzwB));
            hashMap.put("gradientfrom", m6892a(searchAdRequestParcel.zzwC));
        }
        if (Color.alpha(searchAdRequestParcel.zzwD) != 0) {
            hashMap.put("bcolor", m6892a(searchAdRequestParcel.zzwD));
        }
        hashMap.put("bthick", Integer.toString(searchAdRequestParcel.zzwE));
        switch (searchAdRequestParcel.zzwF) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                obj = ShopAboutVideo.PROVIDER_STATUS_NONE;
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                obj = "dashed";
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                obj = "dotted";
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                obj = "solid";
                break;
            default:
                obj = null;
                break;
        }
        if (obj != null) {
            hashMap.put("btype", obj);
        }
        switch (searchAdRequestParcel.zzwG) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                obj2 = "light";
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                obj2 = Constants.MEDIUM;
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                obj2 = "dark";
                break;
        }
        if (obj2 != null) {
            hashMap.put("callbuttoncolor", obj2);
        }
        if (searchAdRequestParcel.zzwH != null) {
            hashMap.put("channel", searchAdRequestParcel.zzwH);
        }
        if (Color.alpha(searchAdRequestParcel.zzwI) != 0) {
            hashMap.put("dcolor", m6892a(searchAdRequestParcel.zzwI));
        }
        if (searchAdRequestParcel.zzwJ != null) {
            hashMap.put("font", searchAdRequestParcel.zzwJ);
        }
        if (Color.alpha(searchAdRequestParcel.zzwK) != 0) {
            hashMap.put("hcolor", m6892a(searchAdRequestParcel.zzwK));
        }
        hashMap.put("headersize", Integer.toString(searchAdRequestParcel.zzwL));
        if (searchAdRequestParcel.zzwM != null) {
            hashMap.put(SearchCategoryRedirectPage.PARAM_QUERY, searchAdRequestParcel.zzwM);
        }
    }

    private static void m6901a(HashMap<String, Object> hashMap, kd kdVar, kk kkVar, Bundle bundle) {
        hashMap.put("am", Integer.valueOf(kdVar.f5273a));
        hashMap.put("cog", m6891a(kdVar.f5274b));
        hashMap.put("coh", m6891a(kdVar.f5275c));
        if (!TextUtils.isEmpty(kdVar.f5276d)) {
            hashMap.put("carrier", kdVar.f5276d);
        }
        hashMap.put("gl", kdVar.f5277e);
        if (kdVar.f5278f) {
            hashMap.put("simulator", Integer.valueOf(1));
        }
        if (kdVar.f5279g) {
            hashMap.put("is_sidewinder", Integer.valueOf(1));
        }
        hashMap.put("ma", m6891a(kdVar.f5280h));
        hashMap.put("sp", m6891a(kdVar.f5281i));
        hashMap.put("hl", kdVar.f5282j);
        if (!TextUtils.isEmpty(kdVar.f5283k)) {
            hashMap.put("mv", kdVar.f5283k);
        }
        hashMap.put("muv", Integer.valueOf(kdVar.f5284l));
        if (kdVar.f5285m != -2) {
            hashMap.put("cnt", Integer.valueOf(kdVar.f5285m));
        }
        hashMap.put("gnt", Integer.valueOf(kdVar.f5286n));
        hashMap.put("pt", Integer.valueOf(kdVar.f5287o));
        hashMap.put("rm", Integer.valueOf(kdVar.f5288p));
        hashMap.put("riv", Integer.valueOf(kdVar.f5289q));
        Bundle bundle2 = new Bundle();
        bundle2.putString("build", kdVar.f5297y);
        Bundle bundle3 = new Bundle();
        bundle3.putBoolean("is_charging", kdVar.f5294v);
        bundle3.putDouble("battery_level", kdVar.f5293u);
        bundle2.putBundle("battery", bundle3);
        bundle3 = new Bundle();
        bundle3.putInt("active_network_state", kdVar.f5296x);
        bundle3.putBoolean("active_network_metered", kdVar.f5295w);
        if (kkVar != null) {
            Bundle bundle4 = new Bundle();
            bundle4.putInt("predicted_latency_micros", 0);
            bundle4.putLong("predicted_down_throughput_bps", 0);
            bundle4.putLong("predicted_up_throughput_bps", 0);
            bundle3.putBundle("predictions", bundle4);
        }
        bundle2.putBundle("network", bundle3);
        bundle3 = new Bundle();
        bundle3.putBoolean("is_browser_custom_tabs_capable", kdVar.f5298z);
        bundle2.putBundle("browser", bundle3);
        if (bundle != null) {
            bundle2.putBundle("android_mem_info", m6889a(bundle));
        }
        hashMap.put("device", bundle2);
    }

    private static void m6902a(HashMap<String, Object> hashMap, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("doritos", str);
        bundle.putString("token", str2);
        hashMap.put("pii", bundle);
    }
}
