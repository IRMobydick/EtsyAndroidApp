package com.etsy.android.uikit.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import com.etsy.android.lib.convos.Draft;

public class FragmentViewPager extends ViewPager {
    public FragmentViewPager(Context context) {
        super(context);
    }

    public FragmentViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static String createFragmentName(int i, long j) {
        return "android:switcher:" + i + Draft.IMAGE_DELIMITER + j;
    }
}
