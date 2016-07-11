package com.appboy.ui.actions;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.appboy.ui.support.StringUtils;

public class ActionFactory {
    public static IAction createUriAction(Context context, String str) {
        return createUriAction(context, str, null);
    }

    public static IAction createUriAction(Context context, String str, Bundle bundle) {
        if (StringUtils.isNullOrBlank(str)) {
            return null;
        }
        Uri parse = Uri.parse(str);
        if (WebAction.getSupportedSchemes().contains(parse.getScheme())) {
            return new WebAction(str, bundle);
        }
        if ("intent".equals(parse.getScheme())) {
            return new ActivityAction(context.getPackageName(), parse, bundle);
        }
        return new ViewAction(parse, bundle);
    }

    public static IAction createViewUriAction(String str, Bundle bundle) {
        if (StringUtils.isNullOrBlank(str)) {
            return null;
        }
        return new ViewAction(Uri.parse(str), bundle);
    }
}
