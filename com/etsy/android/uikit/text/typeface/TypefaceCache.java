package com.etsy.android.uikit.text.typeface;

import android.graphics.Typeface;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* renamed from: com.etsy.android.uikit.text.typeface.b */
class TypefaceCache extends MetricAffectingSpan {
    private Typeface f4044a;

    public TypefaceCache(Typeface typeface) {
        this.f4044a = typeface;
    }

    public void updateMeasureState(TextPaint textPaint) {
        textPaint.setTypeface(this.f4044a);
        textPaint.setFlags(textPaint.getFlags() | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setTypeface(this.f4044a);
        textPaint.setFlags(textPaint.getFlags() | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
    }
}
