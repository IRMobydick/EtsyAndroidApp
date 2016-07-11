package com.etsy.android.iconsy.views;

import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import com.etsy.android.iconsy.AbstractFontIcon;

public class IconSelectorDrawable extends StateListDrawable {
    public static final int[] CHECKED_STATE;
    public static final int COLOR_TEN_PERCENT_BLACK = 436207616;
    public static final int DEFAULT_DISABLED_ALPHA = 153;
    public static final int[] DISABLED_STATE_SET;
    public static final int[] PRESSED_STATE_SET;
    private IconDrawable[] mDrawables;

    static {
        DISABLED_STATE_SET = new int[]{-16842910};
        PRESSED_STATE_SET = new int[]{16842919};
        CHECKED_STATE = new int[]{16842912};
    }

    public static IconSelectorDrawable create(IconDrawable iconDrawable) {
        IconSelectorDrawable iconSelectorDrawable = new IconSelectorDrawable();
        IconDrawable[] generateSetOfThreeDrawables = iconSelectorDrawable.generateSetOfThreeDrawables(iconDrawable);
        iconSelectorDrawable.setupNonBaseStateDrawables(generateSetOfThreeDrawables);
        iconSelectorDrawable.setBaseState(generateSetOfThreeDrawables[generateSetOfThreeDrawables.length - 1]);
        return iconSelectorDrawable;
    }

    public static IconSelectorDrawable createChecked(IconDrawable iconDrawable, AbstractFontIcon abstractFontIcon) {
        IconSelectorDrawable iconSelectorDrawable = new IconSelectorDrawable();
        IconDrawable[] generateSetOfThreeDrawables = iconSelectorDrawable.generateSetOfThreeDrawables(iconDrawable);
        iconSelectorDrawable.setupNonBaseStateDrawables(generateSetOfThreeDrawables);
        iconDrawable.m780a(abstractFontIcon);
        iconSelectorDrawable.setCheckedDrawables(iconDrawable);
        iconSelectorDrawable.setBaseState(generateSetOfThreeDrawables[2]);
        return iconSelectorDrawable;
    }

    private void setupNonBaseStateDrawables(IconDrawable[] iconDrawableArr) {
        setDisabledState(createDisabledDrawable(iconDrawableArr[0]));
        setPressedState(createPressedDrawable(iconDrawableArr[1]));
        this.mDrawables = iconDrawableArr;
    }

    public void setBaseState(Drawable drawable) {
        addState(StateSet.WILD_CARD, drawable);
    }

    public void setPressedState(Drawable drawable) {
        addState(PRESSED_STATE_SET, drawable);
    }

    public void setDisabledState(Drawable drawable) {
        addState(DISABLED_STATE_SET, drawable);
    }

    public void setCheckedDrawables(IconDrawable iconDrawable) {
        setCheckedDrawables(generateSetOfThreeDrawables(iconDrawable));
    }

    public void setCheckedDrawables(IconDrawable[] iconDrawableArr) {
        addState(combine(DISABLED_STATE_SET, CHECKED_STATE), createDisabledDrawable(iconDrawableArr[0]));
        addState(combine(PRESSED_STATE_SET, CHECKED_STATE), createPressedDrawable(iconDrawableArr[1]));
        addState(CHECKED_STATE, iconDrawableArr[2]);
    }

    public IconDrawable[] getDrawables() {
        return this.mDrawables;
    }

    private int[] combine(int[] iArr, int[] iArr2) {
        Object obj = new int[(iArr.length + iArr2.length)];
        System.arraycopy(iArr, 0, obj, 0, iArr.length);
        System.arraycopy(iArr2, 0, obj, iArr.length, iArr2.length);
        return obj;
    }

    private IconDrawable[] generateSetOfThreeDrawables(IconDrawable iconDrawable) {
        return new IconDrawable[]{iconDrawable.m777a(), iconDrawable.m777a(), iconDrawable.m777a()};
    }

    private static IconDrawable createDisabledDrawable(IconDrawable iconDrawable) {
        iconDrawable.setDefaultAlpha(DEFAULT_DISABLED_ALPHA);
        return iconDrawable;
    }

    private static IconDrawable createPressedDrawable(IconDrawable iconDrawable) {
        iconDrawable.setDefaultColorFilter(new PorterDuffColorFilter(COLOR_TEN_PERCENT_BLACK, Mode.SRC_ATOP));
        return iconDrawable;
    }
}
