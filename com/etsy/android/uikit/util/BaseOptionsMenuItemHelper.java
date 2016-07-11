package com.etsy.android.uikit.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.iconsy.AbstractFontIcon;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.iconsy.views.IconSelectorDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.ab;

/* renamed from: com.etsy.android.uikit.util.d */
public abstract class BaseOptionsMenuItemHelper {
    protected static void m5530a(BaseOptionsMenuItemHelper[] baseOptionsMenuItemHelperArr, Resources resources, Menu menu) {
        for (BaseOptionsMenuItemHelper baseOptionsMenuItemHelper : baseOptionsMenuItemHelperArr) {
            MenuItem findItem = menu.findItem(baseOptionsMenuItemHelper.getMenuId());
            if (findItem != null) {
                findItem.setIcon(IconSelectorDrawable.create(IconDrawable.m775a(resources).m780a(baseOptionsMenuItemHelper.getIcon()).m781b(R.actionbar_icon).m782c(R.actionbar_icon)));
            }
        }
    }

    protected static void m5531a(BaseOptionsMenuItemHelper[] baseOptionsMenuItemHelperArr, Menu menu) {
        for (BaseOptionsMenuItemHelper menuId : baseOptionsMenuItemHelperArr) {
            MenuItem findItem = menu.findItem(menuId.getMenuId());
            if (!(findItem == null || findItem.getTitleCondensed() == null)) {
                findItem.setTitleCondensed(findItem.getTitleCondensed().toString());
            }
        }
    }

    protected static View m5529a(Context context, int i, String str, float f, AbstractFontIcon abstractFontIcon) {
        ColorStateList colorStateList = context.getResources().getColorStateList(i);
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.padding_medium_large);
        int b = ab.m3177b(context);
        View textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(-2, -1));
        textView.setGravity(16);
        textView.setTextColor(colorStateList);
        textView.setTextSize(0, f);
        textView.setAllCaps(true);
        if (!GraphikUtil.m5547a(textView, R.typeface_bold)) {
            textView.setTypeface(null, 1);
        }
        textView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        textView.setMinHeight(b);
        textView.setBackgroundResource(R.bg_unbounded_selector);
        textView.setText(str);
        if (abstractFontIcon != null) {
            IconDrawable a = IconDrawable.m775a(context.getResources());
            a.m780a(abstractFontIcon);
            a.m778a(f);
            Drawable iconSelectorDrawable = new IconSelectorDrawable();
            a.m779a(colorStateList.getColorForState(IconSelectorDrawable.DISABLED_STATE_SET, colorStateList.getDefaultColor()));
            iconSelectorDrawable.setDisabledState(a.m777a());
            a.m779a(colorStateList.getDefaultColor());
            iconSelectorDrawable.setBaseState(a.m777a());
            textView.setCompoundDrawablesWithIntrinsicBounds(iconSelectorDrawable, null, null, null);
            textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelSize(R.padding_tiny));
        }
        return textView;
    }
}
