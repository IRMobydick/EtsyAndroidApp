package com.etsy.android.lib.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.android.volley.BuildConfig;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.http.request.EtsyApiV2Request;
import com.etsy.android.lib.core.http.request.EtsyApiV2RequestJob;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.EtsyApiV3RequestJob;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.requests.HttpUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.scribe.d.d;
import org.scribe.e.b;
import org.scribe.model.Token;
import org.scribe.model.e;

/* renamed from: com.etsy.android.lib.core.b */
public class AuthHelper {
    private static final String f1460a;

    static {
        f1460a = EtsyDebug.m1891a(AuthHelper.class);
    }

    public static String m1155a() {
        return EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cI);
    }

    public static String m1163b() {
        return EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cJ);
    }

    public static Token m1165c() {
        return aj.m1101a().m1122h().m1184c();
    }

    public static long m1166d() {
        return aj.m1101a().m1123i().m1702b();
    }

    @NonNull
    public static Map<String, String> m1160a(@NonNull EtsyApiV2RequestJob<?> etsyApiV2RequestJob) {
        Map hashMap = new HashMap();
        if (((EtsyApiV2Request) etsyApiV2RequestJob.m1409a()).isSigned()) {
            hashMap.put("oauth_nonce", UUID.randomUUID().toString());
            hashMap.put("oauth_signature_method", "HMAC-SHA1");
            hashMap.put("oauth_consumer_key", AuthHelper.m1155a());
            hashMap.put("oauth_timestamp", String.valueOf(AuthHelper.m1166d()));
            Token c = AuthHelper.m1165c();
            if (!(c == null || TextUtils.isEmpty(c.getToken()))) {
                hashMap.put("oauth_token", c.getToken());
                String a = AuthHelper.m1156a(etsyApiV2RequestJob.getUrl(), ((EtsyApiV2Request) etsyApiV2RequestJob.m1409a()).getQueryString(), etsyApiV2RequestJob.getMethod(), c.getSecret(), hashMap);
                if (a != null) {
                    hashMap.put("oauth_signature", a);
                }
            }
            hashMap.put("Authorization", AuthHelper.m1159a(hashMap));
        }
        return hashMap;
    }

    @NonNull
    public static Map<String, String> m1161a(@NonNull EtsyApiV3RequestJob<?> etsyApiV3RequestJob) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("oauth_version", "2.0");
        hashMap.put("x-api-key", AuthHelper.m1155a());
        Token c = AuthHelper.m1165c();
        if (!(!((EtsyApiV3Request) etsyApiV3RequestJob.m1409a()).isSigned() || c == null || TextUtils.isEmpty(c.getToken()))) {
            hashMap.put("Authorization", String.format("Bearer %s", new Object[]{c.getToken()}));
        }
        return hashMap;
    }

    @Deprecated
    @NonNull
    public static Map<String, String> m1162a(@NonNull String str, @NonNull String str2, int i, boolean z, boolean z2) {
        Map hashMap = new HashMap();
        Token c = AuthHelper.m1165c();
        if (z) {
            hashMap.put("oauth_version", "2.0");
            hashMap.put("x-api-key", AuthHelper.m1155a());
            if (!(!z2 || c == null || TextUtils.isEmpty(c.getToken()))) {
                hashMap.put("Authorization", String.format("Bearer %s", new Object[]{c.getToken()}));
            }
        } else {
            hashMap.put("oauth_version", BuildConfig.VERSION_NAME);
            if (z2) {
                hashMap.put("oauth_nonce", UUID.randomUUID().toString());
                hashMap.put("oauth_signature_method", "HMAC-SHA1");
                hashMap.put("oauth_consumer_key", AuthHelper.m1155a());
                hashMap.put("oauth_timestamp", String.valueOf(AuthHelper.m1166d()));
                if (!(c == null || TextUtils.isEmpty(c.getToken()))) {
                    hashMap.put("oauth_token", c.getToken());
                    String a = AuthHelper.m1156a(str, str2, i, c.getSecret(), hashMap);
                    if (a != null) {
                        hashMap.put("oauth_signature", a);
                    }
                }
                hashMap.put("Authorization", AuthHelper.m1159a(hashMap));
            }
        }
        return hashMap;
    }

    @Nullable
    private static String m1156a(@NonNull String str, @NonNull String str2, int i, @NonNull String str3, @NonNull Map<String, String> map) {
        try {
            return new d().a(AuthHelper.m1157a(str, str2, i, map), AuthHelper.m1163b(), str3);
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1460a, "couldn't get the signature", e);
            return null;
        }
    }

    @NonNull
    private static String m1157a(@NonNull String str, @NonNull String str2, int i, @NonNull Map<String, String> map) {
        String a = b.a(HttpUtil.getRequestMethodString(i));
        String a2 = b.a(str.replaceAll("\\?.*", StringUtils.EMPTY).replace("\\:\\d{4}", StringUtils.EMPTY));
        String a3 = AuthHelper.m1158a(str2, map);
        return String.format("%s&%s&%s", new Object[]{a, a2, a3});
    }

    @NonNull
    private static String m1158a(@NonNull String str, @NonNull Map<String, String> map) {
        e eVar = new e(AuthHelper.m1164b(map));
        eVar.b(str.replace("?", StringUtils.EMPTY));
        return eVar.c().a();
    }

    @NonNull
    public static String m1159a(@NonNull Map<String, String> map) {
        Map b = AuthHelper.m1164b(map);
        StringBuilder stringBuilder = new StringBuilder(b.size() * 20);
        stringBuilder.append("OAuth ");
        for (Entry entry : b.entrySet()) {
            if (stringBuilder.length() > "OAuth ".length()) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(String.format("%s=\"%s\"", new Object[]{entry.getKey(), b.a((String) entry.getValue())}));
        }
        return stringBuilder.toString();
    }

    private static Map<String, String> m1164b(@NonNull Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (str.startsWith("oauth_") || str.startsWith("scope")) {
                hashMap.put(str, entry.getValue());
            }
        }
        return hashMap;
    }
}
