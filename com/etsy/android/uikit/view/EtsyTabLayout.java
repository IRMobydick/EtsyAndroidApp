package com.etsy.android.uikit.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.util.GraphikUtil;

public class EtsyTabLayout extends TabLayout {
    public EtsyTabLayout(Context context) {
        super(context);
    }

    public EtsyTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EtsyTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void addTab(@NonNull Tab tab, int i, boolean z) {
        super.addTab(tab, i, z);
        applyGraphikIfNeeded();
    }

    public void addTab(@NonNull Tab tab) {
        super.addTab(tab);
        applyGraphikIfNeeded();
    }

    public void addTab(@NonNull Tab tab, int i) {
        super.addTab(tab, i);
        applyGraphikIfNeeded();
    }

    public void addTab(@NonNull Tab tab, boolean z) {
        super.addTab(tab, z);
        applyGraphikIfNeeded();
    }

    private void applyGraphikIfNeeded() {
        if (GraphikUtil.m5548b()) {
            recursiveSetTypeface(this);
        }
    }

    private void recursiveSetTypeface(View view) {
        if (view instanceof TextView) {
            GraphikUtil.m5547a((TextView) view, R.typeface_normal);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                recursiveSetTypeface(viewGroup.getChildAt(i));
            }
        }
    }

    public void setTabText(int i, String str) {
        super.getTabAt(i).setText((CharSequence) str);
        applyGraphikIfNeeded();
    }
}
