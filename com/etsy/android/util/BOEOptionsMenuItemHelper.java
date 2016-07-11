package com.etsy.android.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import com.etsy.android.iconsy.AbstractFontIcon;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.util.BaseOptionsMenuItemHelper;

public class BOEOptionsMenuItemHelper extends BaseOptionsMenuItemHelper {
    private static final String f4300a;

    static {
        f4300a = EtsyDebug.m1891a(BOEOptionsMenuItemHelper.class);
    }

    public static void m5676a(Resources resources, Menu menu) {
        BaseOptionsMenuItemHelper.m5530a(OptionsMenuIcon.values(), resources, menu);
    }

    public static void m5677a(Menu menu) {
        BaseOptionsMenuItemHelper.m5531a(OptionsMenuIcon.values(), menu);
    }

    public static View m5675a(Context context, int i, int i2, AbstractFontIcon abstractFontIcon) {
        return BaseOptionsMenuItemHelper.m5529a(context, i, context.getString(i2), context.getResources().getDimension(2131362098), abstractFontIcon);
    }
}
