package com.appboy.ui.support;

import android.net.Uri;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UriUtils {
    private static final String TAG;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, UriUtils.class.getName()});
    }

    public static Map<String, String> getQueryParameters(Uri uri) {
        if (uri.isOpaque()) {
            AppboyLogger.m662d(TAG, "URI is not hierarchical. There are no query parameters to parse.");
            return Collections.emptyMap();
        }
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null) {
            return Collections.emptyMap();
        }
        Map hashMap = new HashMap();
        int i = 0;
        do {
            int indexOf = encodedQuery.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = encodedQuery.length();
            }
            int indexOf2 = encodedQuery.indexOf(61, i);
            if (indexOf2 > indexOf || indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            if (indexOf > i) {
                hashMap.put(Uri.decode(encodedQuery.substring(i, indexOf2)), Uri.decode(encodedQuery.substring(indexOf2 + 1, indexOf)));
            }
            i = indexOf + 1;
        } while (i < encodedQuery.length());
        return Collections.unmodifiableMap(hashMap);
    }
}
