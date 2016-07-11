package com.foresee.sdk.tracker;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class SurveyUrlBuilder {
    private SurveyUrlBuilder() {
    }

    public static String buildSurveyUrl(String str, String str2, String str3, String str4, Map<String, String> map, Map<String, String> map2) {
        if (str == null || str.length() == 0) {
            throw new IllegalStateException("SurveyUrlBuilder not configured with a base url.");
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        URLConstructor.appendFormat(stringBuilder, "sid=%s", str4, true);
        URLConstructor.appendFormat(stringBuilder, "cid=%s", str3);
        for (Entry entry : map2.entrySet()) {
            URLConstructor.appendQueryStringParam(stringBuilder, (String) entry.getKey(), (String) entry.getValue());
        }
        if (str2 != null) {
            URLConstructor.appendCpp(stringBuilder, "browser_version", parseBrowserVersion(str2));
            URLConstructor.appendCpp(stringBuilder, "browser", "AppleWebKit");
        }
        for (Entry entry2 : map.entrySet()) {
            URLConstructor.appendCpp(stringBuilder, (String) entry2.getKey(), (String) entry2.getValue());
        }
        return stringBuilder.toString();
    }

    public static String buildSurveyUrl(String str, String str2, String str3, Map<String, String> map, Map<String, String> map2) {
        return buildSurveyUrl(str, null, str2, str3, map, map2);
    }

    private static String parseBrowserVersion(String str) {
        Matcher matcher = Pattern.compile("AppleWebKit/([\\d.]*)").matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return StringUtils.EMPTY;
    }
}
