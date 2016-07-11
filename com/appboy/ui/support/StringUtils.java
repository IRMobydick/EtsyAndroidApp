package com.appboy.ui.support;

import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;

public final class StringUtils {
    public static final String EMPTY_STRING = "";

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String getOptionalStringResource(Resources resources, int i, String str) {
        try {
            str = resources.getString(i);
        } catch (NotFoundException e) {
        }
        return str;
    }
}
