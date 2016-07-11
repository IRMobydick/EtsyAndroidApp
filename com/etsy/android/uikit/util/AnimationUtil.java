package com.etsy.android.uikit.util;

import android.animation.IntEvaluator;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/* renamed from: com.etsy.android.uikit.util.c */
class AnimationUtil extends IntEvaluator {
    private final LayoutParams f4154a;
    private final View f4155b;

    public AnimationUtil(View view) {
        this.f4155b = view;
        this.f4154a = view.getLayoutParams();
    }

    public Integer evaluate(float f, Integer num, Integer num2) {
        int intValue = super.evaluate(f, num, num2).intValue();
        this.f4154a.height = intValue;
        this.f4155b.setLayoutParams(this.f4154a);
        return Integer.valueOf(intValue);
    }
}
