package com.etsy.android.lib.requests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONStringer;
import org.scribe.e.b;

public class HttpUtil {
    public static final String CONTENT_ENCODING = "utf-8";
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String JSON_CONTENT_TYPE_ENCODED;
    private static final String TAG;
    public static final String URL_FORM_CONTENT_TYPE;

    static {
        TAG = EtsyDebug.m1891a(HttpUtil.class);
        JSON_CONTENT_TYPE_ENCODED = String.format(Locale.US, "%s; charset=%s", new Object[]{JSON_CONTENT_TYPE, CONTENT_ENCODING});
        URL_FORM_CONTENT_TYPE = String.format(Locale.US, "application/x-www-form-urlencoded", new Object[0]);
    }

    public static String getRequestMethodString(int i) {
        switch (i) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return BaseHttpRequest.GET;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return BaseHttpRequest.POST;
            case Task.NETWORK_STATE_ANY /*2*/:
                return BaseHttpRequest.PUT;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return BaseHttpRequest.DELETE;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return BaseHttpRequest.HEAD;
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                return BaseHttpRequest.TRACE;
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                return BaseHttpRequest.PATCH;
            default:
                return null;
        }
    }

    public static int getVolleyRequestMethod(RequestMethod requestMethod) {
        if (requestMethod.equals(RequestMethod.GET)) {
            return 0;
        }
        if (requestMethod.equals(RequestMethod.POST)) {
            return 1;
        }
        if (requestMethod.equals(RequestMethod.PUT)) {
            return 2;
        }
        if (requestMethod.equals(RequestMethod.DELETE)) {
            return 3;
        }
        return 0;
    }

    @NonNull
    public static String createUrlEncodingParams(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        if (map.size() > 0) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                stringBuilder.append(createUrlEncodingParam((String) entry.getKey(), (String) entry.getValue()));
                if (it.hasNext()) {
                    stringBuilder.append("&");
                }
            }
        }
        return stringBuilder.toString();
    }

    @NonNull
    public static String createUrlEncodingParamsFromList(Map<String, List<String>> map) {
        StringBuilder stringBuilder = new StringBuilder();
        if (map.size() > 0) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                List list = (List) entry.getValue();
                if (list != null) {
                    Iterator it2 = list.iterator();
                    while (it2.hasNext()) {
                        stringBuilder.append(createUrlEncodingParam((String) entry.getKey(), (String) it2.next()));
                        if (it2.hasNext()) {
                            stringBuilder.append("&");
                        }
                    }
                    if (it.hasNext()) {
                        stringBuilder.append("&");
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    @NonNull
    public static String getPathPart(@NonNull String str) {
        int indexOf = str.indexOf("://");
        if (indexOf != -1) {
            indexOf = str.indexOf(47, indexOf + 3);
            if (indexOf == -1) {
                str = "/";
            } else {
                str = str.substring(indexOf);
            }
        } else if (!str.startsWith("/")) {
            str = "/" + str;
        }
        indexOf = str.indexOf("?");
        return indexOf == -1 ? str : str.substring(0, indexOf);
    }

    @NonNull
    public static HashMap<String, List<String>> parseQueryParamsAsList(@NonNull String str) {
        Map linkedHashMap = new LinkedHashMap();
        int indexOf = str.indexOf(63);
        if (!(indexOf == -1 || indexOf == str.length() - 1)) {
            for (String str2 : str.substring(indexOf + 1).split("&")) {
                String str22;
                if (!(str22 == null || str22.length() == 0)) {
                    String str3;
                    indexOf = str22.indexOf("=");
                    if (indexOf == -1) {
                        str3 = StringUtils.EMPTY;
                    } else {
                        String substring = str22.substring(0, indexOf);
                        if (indexOf == str22.length() - 1) {
                            str3 = StringUtils.EMPTY;
                            str22 = substring;
                        } else {
                            str3 = str22.substring(indexOf + 1);
                            str22 = substring;
                        }
                    }
                    addQueryParamAsList(linkedHashMap, str22, b.b(str3));
                }
            }
        }
        return linkedHashMap;
    }

    public static void addQueryParamAsList(@NonNull Map<String, List<String>> map, @Nullable String str, @Nullable String str2) {
        addQueryParamAsList((Map) map, str, Arrays.asList(new String[]{str2}), false);
    }

    public static void addQueryParamAsList(@NonNull Map<String, List<String>> map, @Nullable String str, @Nullable String str2, boolean z) {
        addQueryParamAsList((Map) map, str, Arrays.asList(new String[]{str2}), z);
    }

    public static void addQueryParamAsList(@NonNull Map<String, List<String>> map, @Nullable String str, @NonNull List<String> list) {
        addQueryParamAsList((Map) map, str, (List) list, false);
    }

    public static void addQueryParamAsList(@NonNull Map<String, List<String>> map, @Nullable String str, @NonNull List<String> list, boolean z) {
        List linkedList;
        if (!map.containsKey(str)) {
            linkedList = new LinkedList();
            linkedList.addAll(list);
            map.put(str, linkedList);
        } else if (str == null || !(z || str.endsWith("[]"))) {
            linkedList = (List) map.get(str);
            linkedList.clear();
            linkedList.addAll(list);
        } else {
            ((List) map.get(str)).addAll(list);
        }
    }

    public static String createUrlEncodingParam(String str, String str2) {
        return str + "=" + b.a(str2);
    }

    @Deprecated
    public static String createBody(String str, String str2) {
        return createBody(str, null, str2);
    }

    @Deprecated
    public static String createBody(Map<String, String> map, String str) {
        return createBody(null, map, str);
    }

    @Deprecated
    public static String createBody(String str, Map<String, String> map, String str2) {
        if (str == null || map.size() <= 0) {
            return str != null ? str : createBodyParams(map, str2);
        } else {
            throw new IllegalStateException("Specify either request body or parameters, NOT both");
        }
    }

    @Deprecated
    private static String createBodyParams(Map<String, String> map, String str) {
        if (str.equals(URL_FORM_CONTENT_TYPE)) {
            return createUrlEncodingParams(map);
        }
        if (str.equals(JSON_CONTENT_TYPE_ENCODED)) {
            try {
                JSONStringer jSONStringer = new JSONStringer();
                jSONStringer.object();
                for (Entry entry : map.entrySet()) {
                    jSONStringer.key((String) entry.getKey());
                    jSONStringer.value(entry.getValue());
                }
                jSONStringer.endObject();
                return jSONStringer.toString();
            } catch (JSONException e) {
                EtsyDebug.m1919e(TAG, "createBodyParams " + str + Draft.IMAGE_DELIMITER + e);
            }
        }
        return null;
    }
}
