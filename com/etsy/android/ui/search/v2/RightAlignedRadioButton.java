package com.etsy.android.ui.search.v2;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

/* renamed from: com.etsy.android.ui.search.v2.c */
class RightAlignedRadioButton extends LayerDrawable {
    private Drawable f3338a;
    private boolean f3339b;

    public RightAlignedRadioButton(Drawable drawable) {
        super(new Drawable[]{drawable});
        this.f3339b = false;
        this.f3338a = drawable;
    }

    public void draw(Canvas canvas) {
        if (this.f3339b) {
            this.f3338a.draw(canvas);
        }
    }

    public int getIntrinsicWidth() {
        return 0;
    }

    public int getIntrinsicHeight() {
        return 0;
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        this.f3338a.jumpToCurrentState();
        invalidateSelf();
    }

    public Rect getDirtyBounds() {
        return this.f3338a.getBounds();
    }

    public void m4903a(Canvas canvas, int i, int i2, int i3, int i4) {
        if (this.f3338a != null) {
            this.f3339b = true;
            this.f3338a.setBounds(i, i2, i3, i4);
            draw(canvas);
            this.f3339b = false;
        }
    }

    public int m4902a() {
        return this.f3338a.getIntrinsicWidth();
    }

    public int m4904b() {
        return this.f3338a.getIntrinsicHeight();
    }
}
