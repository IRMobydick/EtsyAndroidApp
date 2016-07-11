package com.etsy.android.util;

import android.net.Uri;
import android.net.Uri.Builder;
import com.appboy.Constants;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;
import org.json.JSONObject;

/* renamed from: com.etsy.android.util.b */
public class DeepLinkUtil {
    public static Uri m5704a(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.get(Constants.APPBOY_PUSH_DEEP_LINK_KEY) == null) {
            return null;
        }
        Builder buildUpon = Uri.parse(hashMap.get(Constants.APPBOY_PUSH_DEEP_LINK_KEY).toString()).buildUpon();
        if (hashMap.get(ResponseConstants.OBJECT_ID) != null) {
            buildUpon.appendPath(hashMap.get(ResponseConstants.OBJECT_ID).toString()).build();
        }
        for (String str : hashMap.keySet()) {
            if (!(Constants.APPBOY_PUSH_DEEP_LINK_KEY.equals(str) || ResponseConstants.OBJECT_ID.equals(str) || hashMap.get(str) == null)) {
                buildUpon.appendQueryParameter(str, hashMap.get(str).toString());
            }
        }
        return buildUpon.build();
    }

    public static Uri m5705b(HashMap<String, Object> hashMap) {
        Object hashMap2 = new HashMap();
        Object obj = null;
        for (String str : hashMap.keySet()) {
            Object obj2;
            if (str == null || hashMap.get(str) == null) {
                obj2 = 1;
            } else {
                hashMap2.put(str, Uri.decode(hashMap.get(str).toString()));
                obj2 = obj;
            }
            obj = obj2;
        }
        if (obj != null) {
            EtsyLogger.m1966a().m1996b("DeepLinkUtil", "Yozio parameters were null: " + new JSONObject(hashMap2));
        }
        return DeepLinkUtil.m5704a(hashMap2);
    }
}
